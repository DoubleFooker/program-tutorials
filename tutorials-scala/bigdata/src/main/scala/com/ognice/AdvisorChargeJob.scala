package com.ognice

import java.text.SimpleDateFormat
import java.time.LocalDate
import scala.collection.mutable.ListBuffer
import scala.language.postfixOps

/**
 * 投顾计费-费前模式
 */
object AdvisorChargeJob extends AbstractJob {
  override var taskCode: String = TaskTitle.AdvisorCharge.code + "-" + LocalDate.now()
  val format = new SimpleDateFormat("yyyy-MM-dd")

  override def preCheck(): Unit = {
    var calDate = assetCast.clearDate
    val ossTask = TaskManager.findAsst4Task(calDate, TaskTitle.Oss.code);
    if (ossTask == null || ossTask.getStatus != "S") {
      throw new RuntimeException(s"${assetCast.clearDate} 未将数据导入oss")
    }
  }

  override def execute(spark: SparkSession, assetBroadcast: Broadcast[AssetBroadcast]): Unit = {
    val lastChargeDateStr = LocalDate.now().minusDays(1).toString
    // 1.启明：加载签约用户记录 当天签约的不计费
    val signContractDs = AdvisorChargeService.loadActiveSignUserFromCore(spark)
    // * 为按产品签约
    val productSignContractDs = signContractDs.filter(" txnAccountId='*' ")
    val txnSignContractDs = signContractDs.filter(" txnAccountId!='*' ")
    // 1.1 资产数据
    val caGbcDs = AssetFundOssHelperService.loadFromOss(spark, SqlHelper.SPARK_TABLE_CA_GBC)
      .filter("total_asset > 0")
      .select("txn_account_id", "broker", "account3_id", "po_code", "max_dc_holding_cost", "dc_holding_cost", "processing_amount", "acc_profit", "total_asset")
      .mapPartitions(rowIterator => {
        val list = ListBuffer[(String, String, AssetCaDTO)]()
        for (row <- rowIterator) {
          val assetCaDTO = SparkEncoderHelper.convertToAssetCaDTO(row)
          list += ((assetCaDTO.account3Id + "#" + assetCaDTO.broker + "#" + assetCaDTO.poCode, assetCaDTO.txnAccountId, assetCaDTO))
        }
        list.toIterator
      })(Encoders.tuple(Encoders.STRING, Encoders.STRING, Encoders.kryo(classOf[AssetCaDTO])))
      .toDF("productKey", "txnAccountId", "assetCaGbcLatest")
    // 2.加载策略费率信息
    val prodChargeInfo = AdvisorChargeService.loadAdvisorProdChargeInfoFromCore()
    val prodChargeInfoBc = spark.sparkContext.broadcast(prodChargeInfo)

    // 2.1加载止盈投顾 关联底层模型数据
    val targetPoCodeMap = AdvisorChargeService.loadTargetRefPoCodeMapFromCore()
    val targetPoCodeMapBc = spark.sparkContext.broadcast(targetPoCodeMap)

    // 3.加载投顾计提折扣
    val chargeAccruedDiscount = AdvisorChargeService.loadAdvisorChargeAccruedDiscountFromCore()
    val chargeAccruedDiscountBc = spark.sparkContext.broadcast(chargeAccruedDiscount)
    // 1.2 取上个交易日
    val lastDateFeeDs = LoadService.loadFromDB(spark, "advisor_accrued_charge", "txn_account_id", "asset", s" charge_day='$lastChargeDateStr' ")
      .mapPartitions(rowIterator => {
        val list = ListBuffer[(String, AdvisorAccruedCharge)]()
        for (row <- rowIterator) {
          val accruedCharge = SparkEncoderHelper.convertToAdvisorAccruedChargeRecord(row)
          list += ((accruedCharge.txnAccountId, accruedCharge))
        }
        list.toIterator
      })(Encoders.tuple(Encoders.STRING, Encoders.kryo(classOf[AdvisorAccruedCharge])))
      .toDF("txnAccountId", "lastDayCharge")
    val notConfiguredPoCodeCounter = new MonitorAccumulator
    val brokerCalUserCounter = new MonitorAccumulator
    val brokerCalAmountCounter = new MonitorAccumulator
    val brokerCalFeeCounter = new MonitorAccumulator
    val warnDataMonitor = new MonitorAccumulator
    spark.sparkContext.register(notConfiguredPoCodeCounter, "notConfiguredPoCodeCounter")
    spark.sparkContext.register(brokerCalUserCounter, "brokerCalUserCounter")
    spark.sparkContext.register(brokerCalAmountCounter, "brokerCalAmountCounter")
    spark.sparkContext.register(brokerCalFeeCounter, "brokerCalFeeCounter")
    spark.sparkContext.register(warnDataMonitor, "warnDataMonitor")
    // 按交易账号签约用户 signContract join gbc记录 key: txnAccountId
    txnSignContractDs.join(caGbcDs, txnSignContractDs("txnAccountId") === caGbcDs("txnAccountId"))
      .union(
        // 按产品签约用户 key account3Id#broker#poCode
        productSignContractDs.join(caGbcDs, productSignContractDs("productKey") === caGbcDs("productKey"))

      ) // join 昨日费用计提记录 key: txnAccountId
      .join(lastDateFeeDs, lastDateFeeDs("txnAccountId") === caGbcDs("txnAccountId"), "left")
      // select AssetCaDTO，AdvisorAccruedChargeRecord
      .select(caGbcDs("assetCaGbcLatest"), lastDateFeeDs("lastDayCharge"))
      .as(Encoders.tuple(Encoders.kryo(classOf[AssetCaDTO]), Encoders.kryo(classOf[AdvisorAccruedCharge])))
      .mapPartitions(rowIterator => {
        val result = ListBuffer[AdvisorAccruedCharge]()
        for (row <- rowIterator) {
          val assetCaDTO = row._1
          val lastDateFee = row._2
          val chargeInfo = AdvisorChargeService.getChargeRate(assetCaDTO.poCode, assetCaDTO.broker, targetPoCodeMapBc, prodChargeInfoBc)
          if (chargeInfo != null) {
            val discountInfoList = chargeAccruedDiscountBc.value.getOrElse(assetCaDTO.poCode, List[AdvisorChargeAccruedDiscountRecord]())
            val advisorAccruedCharge = AdvisorChargeService.calAdvisorFee(assetBroadcast, warnDataMonitor, assetCaDTO, chargeInfo, discountInfoList, lastDateFee)
            if (advisorAccruedCharge != null) {
              result += advisorAccruedCharge
              brokerCalUserCounter.add(s"${advisorAccruedCharge.broker}", 1)
              brokerCalAmountCounter.add(s"${advisorAccruedCharge.broker}", advisorAccruedCharge.calAsset)
              brokerCalFeeCounter.add(s"${advisorAccruedCharge.broker}", advisorAccruedCharge.advisorFee)
            }
          } else {
            notConfiguredPoCodeCounter.add(s"${assetCaDTO.poCode}#${assetCaDTO.broker}", 1)
            log.error(s"poCode投顾费信息为空，poCode:${assetCaDTO.poCode},broker:${assetCaDTO.broker},txnAccountId:${assetCaDTO.txnAccountId}")
          }
        }
        result.toIterator
      }).foreachPartition(rowIterator => {
      AdvisorChargeService.store(rowIterator, assetBroadcast)
    })
    if (!warnDataMonitor.isZero) {
      throw new RuntimeException("投顾费计提数据异常，请确认")
    }
  }
}
