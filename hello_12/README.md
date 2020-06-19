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

> ./sbin/start-master.sh -h localhost

>./sbin/start-slave.sh spark://localhost:7077

spark自带管理器
http://localhost:8080/

交互式环境
========

> ./bin/spark-shell --master spark://localhost:7077

提交计算
=======

> spark-submit --master spark://localhost:7077 --class hello_12.SparkPi ./build/libs/hello_12-all.jar

构建脚本更新
==========

build.gradle.orig 是原有的版本

参照 https://www.jianshu.com/p/341744b98aaf

修改 provided，这是与 gradle 版本有关

```
$ gradle --version

------------------------------------------------------------
Gradle 6.2.2
------------------------------------------------------------

Build time:   2020-03-04 08:49:31 UTC
Revision:     7d0bf6dcb46c143bcc3b7a0fa40a8e5ca28e5856

Kotlin:       1.3.61
Groovy:       2.5.8
Ant:          Apache Ant(TM) version 1.10.7 compiled on September 1 2019
JVM:          1.8.0_211 (Oracle Corporation 25.211-b12)
OS:           Linux 5.3.0-40-generic amd64
```

rest api
========

curl -X POST http://localhost:6066/v1/submissions/create --header "Content-Type:application/json;charset=UTF-8" --data '{
  "appResource": "/home/hzg/work/helloScala/hello_12/build/libs/hello_12-all.jar",
  "sparkProperties": {
    "spark.executor.memory": "1g",
    "spark.master": "spark://localhost:7077",
    "spark.driver.memory": "1g",
    "spark.driver.cores": "2",
    "spark.eventLog.enabled": "false",
    "spark.app.name": "hello 12 sparkPi",
    "spark.submit.deployMode": "client",
    "spark.jars": "/home/hzg/work/helloScala/hello_12/build/libs/hello_12-all.jar",
    "spark.driver.supervise": "true"
  },
  "clientSparkVersion": "2.2.1",
  "mainClass": "hello_12.SparkPi",
  "environmentVariables": {
    "SPARK_ENV_LOADED": "1"
  },
  "action": "CreateSubmissionRequest",
  "appArgs": [
  ]
}'

{
  "action" : "CreateSubmissionResponse",
  "message" : "Driver successfully submitted as driver-20200619201032-0000",
  "serverSparkVersion" : "2.2.1",
  "submissionId" : "driver-20200619201032-0000",
  "success" : true
}

curl http://localhost:6066/v1/submissions/kill/driver-20200619201739-0000

curl http://localhost:6066/v1/submissions/status/driver-20200619201739-0000

curl http://localhost:8080/json/