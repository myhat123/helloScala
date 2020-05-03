from pyspark.sql import SparkSession

def main():
    spark = SparkSession \
        .builder \
        .appName("写入cassandra数据") \
        .getOrCreate()

    # df = spark.read \
    #         .option("header", "true") \
    #         .option("delimiter", ",") \
    #         .option("inferSchema", "true") \
    #         .csv("file:///home/hzg/work/helloScala/data-files/data.csv")

    df = spark.read \
            .options(header="true", delimiter=",", inferSchema="true") \
            .csv("file:///home/hzg/work/helloScala/data-files/data.csv")

    df.createOrReplaceTempView("qry_dtl")
    # df.show()

    df.write \
        .format("org.apache.spark.sql.cassandra") \
        .options(table="brch_qry_dtl", keyspace="finance") \
        .save()

    spark.stop()

if __name__ == "__main__":
    main()