import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}

object StreamWordCount {
  def main(args: Array[String]): Unit = {

    //创建流处理环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    //接收socket文本流
    val dataStream: DataStream[String] = env.socketTextStream("localhost", 7777)

    //处理 分组并且sum聚合
    val sumStream: DataStream[(String, Int)] = dataStream
      .flatMap(_.split(" "))
      .filter(_.nonEmpty)
      .map((_, 1))
      .keyBy(0)
      .sum(1)

    //打印
    sumStream.print()

    env.execute()
  }

}
