/*
 * This Scala source file was generated by the Gradle 'init' task.
 */
package hello_13

import org.apache.spark.sql.SparkSession

object FinTest {
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("Fin Test")
      .getOrCreate()
    
    val df1 = spark.read.options(Map("inferSchema"->"true","delimiter"->",","header"->"true"))
                        .csv("file:///home/hzg/work/helloScala/data-files/contractinfo.csv")
    df1.createOrReplaceTempView("contractinfo")
    val sqlDF1 = spark.sql("SELECT distinct txdate FROM contractinfo")
    sqlDF1.show()

    val df2 = spark.read.options(Map("inferSchema"->"true","delimiter"->",","header"->"true"))
                        .csv("file:///home/hzg/work/helloScala/data-files/purchase.csv")
    df2.createOrReplaceTempView("purchase")

    val df3 = spark.read.options(Map("inferSchema"->"true","delimiter"->",","header"->"true"))
                        .csv("file:///home/hzg/work/helloScala/data-files/redeem.csv")
    df3.createOrReplaceTempView("redeem")

    spark.stop()
  }
}