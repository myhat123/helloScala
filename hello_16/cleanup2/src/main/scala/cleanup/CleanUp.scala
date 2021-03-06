/*
 * This Scala source file was generated by the Gradle 'init' task.
 */
package cleanup2

import org.apache.spark.sql.SparkSession

import com.datastax.spark.connector.writer._

import org.joda.time._
import org.joda.time.format._

import conf.Utils

object CleanUp {
  val tables = Map(
    "brch_qry_dtl" -> "tran_date"
  )

  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("使用writetime删除cassandra数据")
      .getOrCreate()
    
    val tableName = args(0)
    val startDate = args(1)

    import spark.implicits._
    import com.datastax.spark.connector._

    val sc = spark.sparkContext
    // sc.cassandraTable("finance", "brch_qry_dtl").where("tran_date = ?", "2019-11-27").collect().foreach(println)

    if (tables.contains(tableName)) {
      val columnName = tables(tableName)
      // sc.cassandraTable("finance", tableName).where(s"${columnName} >= ?", startDate).where(s"${columnName} <= ?", endDate).deleteFromCassandra("finance", tableName)
      
      val fmt = DateTimeFormat.forPattern("yyyy-MM-dd")
      var localDate = DateTime.parse(startDate, fmt)
      sc.cassandraTable("finance", tableName).deleteFromCassandra(
        "finance",
        tableName,
        writeConf = WriteConf(timestamp = TimestampOption.constant(localDate)))
    }

    spark.stop()
  }
}