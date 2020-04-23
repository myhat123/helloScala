/*
 * This Scala source file was generated by the Gradle 'init' task.
 */
package cleanup

import org.apache.spark.sql.SparkSession

import conf.Utils

object CleanUp {
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("Clean Up")
      .getOrCreate()
    
    val tableName = args(0)
    val cleanDate = args(1)

    import spark.implicits._
    import com.datastax.spark.connector._

    val sc = spark.sparkContext
    // sc.cassandraTable("finance", "brch_qry_dtl").where("tran_date = ?", "2019-11-27").collect().foreach(println)

    sc.cassandraTable("finance", tableName).where("tran_date = ?", cleanDate).deleteFromCassandra("finance", "brch_qry_dtl")
    spark.stop()
  }
}