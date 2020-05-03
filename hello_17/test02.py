from pyspark.sql import SparkSession

def main():
    spark = SparkSession \
        .builder \
        .appName("计算cassandra数据") \
        .getOrCreate()

    df = spark.read \
            .format("org.apache.spark.sql.cassandra") \
            .options(table="brch_qry_dtl", keyspace="finance") \
            .load()

    df.createOrReplaceTempView("qry_dtl")
    
    sqlDF = spark.sql("""
      select count(*) as cnt from qry_dtl
      where tran_date='2019-11-27'
    """)

    data = sqlDF.collect()
    
    for x in data:
        print('记录数: {0}'.format(x['cnt']))

    spark.stop()

if __name__ == "__main__":
    main()