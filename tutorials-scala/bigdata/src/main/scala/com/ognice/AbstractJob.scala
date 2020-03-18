package com.ognice

import org.slf4j.{Logger, LoggerFactory}

trait AbstractJob {
  val log: Logger = LoggerFactory.getLogger(classOf[AbstractJob])

  protected var assetCast: AssetBroadcast = _

  def preCheck(): Unit

  def execute(spark: SparkSession, assetBroadCast: Broadcast[AssetBroadcast]): Unit

  var taskCode: String

  def main(args: Array[String]): Unit = {
    AssetConfiguration.init(args)
    assetCast = SparkService.initAssetBroadcast

    preCheck()

    val timePoint = System.currentTimeMillis()
    val task = TaskManager.start(assetCast.clearDate, taskCode, canRepeat = "1".equals(AssetConfiguration.getProperty("canRepeat", "0")))

    try {
      SparkService.setPreAssetConf
      val sparkSession = SparkService.getSparkSession
      val assetBroadCast = sparkSession.sparkContext.broadcast(assetCast)

      execute(sparkSession, assetBroadCast)
    } catch {
      case exception: Exception => {
        log.error(s"$taskCode 执行失败", exception)
        TaskManager.finish(task, TaskStatus.FATAL, JsonUtils.toJsonStr(Map("costTime" -> (System.currentTimeMillis() - timePoint))))
        System.exit(1)
      }
    } finally {
      TaskManager.finish(task, TaskStatus.SUCCESS, JsonUtils.toJsonStr(Map("costTime" -> (System.currentTimeMillis() - timePoint))))
      SparkService.getSparkSession.stop
    }
  }
}