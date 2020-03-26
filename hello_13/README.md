启动spark
=========

./sbin/start-master.sh -h localhost
./sbin/start-slave.sh spark://localhost:7077

提交计算
=======

spark-submit --master spark://localhost:7077 --class hello_13.FinTest ./build/libs/hello_13.jar