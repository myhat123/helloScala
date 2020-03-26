参考资料

https://github.com/datastax/SparkBuildExamples/tree/master/scala/gradle/oss

spark cassandra connector的文档中提到了这个示例参考
我是从这里才了解到gradle作为scala, spark的构建工具

spark 2.2.1 要求 scala 2.11.x 版本，参考spark文档:
https://spark.apache.org/docs/2.2.1/

官方文档说明了 scala 的版本要求

具体要求的版本号，可以到 https://mvnrepository.com/ 查找

启动spark
=========

./sbin/start-master.sh -h localhost
./sbin/start-slave.sh spark://localhost:7077

spark自带管理器
http://localhost:8080/

交互式环境
========

./bin/spark-shell --master spark://localhost:7077

提交计算
=======

spark-submit --master spark://localhost:7077 --class hello_12.SparkPi ./build/libs/hello_12-all.jar
