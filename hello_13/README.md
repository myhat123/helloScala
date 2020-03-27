启动spark
=========

./sbin/start-master.sh -h localhost
./sbin/start-slave.sh spark://localhost:7077

spark sql
=========

转成spark sql语句中的表
df1.createOrReplaceTempView("contractinfo")

var sqlDF = spark.sql("""
    select * from contractinfo t1, redeem t2
    where date_sub(t2.txdate, 1)=cast(t1.txdate as date)
    and t2.agmt_name=t1.agmt_name
    and t2.prod=t1.prod
""")

date_sub 是 spark sql 的内置函数

sql的api接口文档（spark 2.2.1还没有，从spark 2.3.0开始有）
https://spark.apache.org/docs/latest/api/sql/index.html

spark dataframe
===============

import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

初始化spark后，import spark.implicits._

df1.printSchema()
var dft1 = df1.select($"agmt_name", $"txdate".cast(DateType).alias("txdate"), $"prod", $"accrue_organ", $"bal")
var dft3 = df3.select($"agmt_name", date_sub($"txdate", 1).alias("txdate"), $"prod", $"amt")
dft1.join(dft3, Seq("txdate", "agmt_name", "prod"), "inner").show()

效果与spark sql相同

提交计算
=======

spark-submit --master spark://localhost:7077 --class hello_13.FinTest ./build/libs/hello_13.jar

数据说明
=======

产品合同信息
----------
txdate,agmt_name,prod,accrue_organ,bal
2020-03-15,A,0011,36000226,10
2020-03-15,B,0012,36000226,5

2020-03-16,B,0012,36000226,5

2020-03-18,A,0011,36000254,15
2020-03-18,B,0012,36000226,5

2020-03-20,A,0011,36000254,15
2020-03-20,B,0012,36000226,5
2020-03-20,C,0013,36000226,5

2020-03-21,A,0011,36000380,15
2020-03-21,B,0012,36000226,5
2020-03-21,C,0013,36000226,5

赎回
----
txdate,agmt_name,prod,amt
2020-03-16,A,0011,10          => contractinfo 关联15日 交易前一日
2020-03-21,A,0011,15          => contractinfo 关联20日 交易前一日

购买
----
txdate,agmt_name,prod,amt
2020-03-18,A,0011,15          => contractinfo 关联18日 交易当日
2020-03-20,C,0013,5           => contractinfo 关联20日 交易当日
2020-03-21,A,0011,15          => contractinfo 关联21日 交易当日