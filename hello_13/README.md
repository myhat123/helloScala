启动spark
=========

./sbin/start-master.sh -h localhost
./sbin/start-slave.sh spark://localhost:7077

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