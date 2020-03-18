import org.apache.flink.api.scala.{AggregateDataSet, DataSet, ExecutionEnvironment}
import org.apache.flink.streaming.api.scala.createTypeInformation

object DateSetWcApp {
  def main(args: Array[String]): Unit = {

    //构造执行环境
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment

    //读取文件
    val txtDataSet: DataSet[String] = env.readTextFile("/Users/kaifuhuang/Documents/job/my/git/program-tutorials/tutorials-scala/bigdata/src/main/resources/hello.txt")

    //经过groupby进行分组，sum进行聚合
    val aggSet: AggregateDataSet[(String, Int)] = txtDataSet.flatMap(_.split(" ")).map((_, 1)).groupBy(0).sum(1)

    aggSet.print()
  }
}
