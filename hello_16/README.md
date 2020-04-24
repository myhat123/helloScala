参考资料

https://guides.gradle.org/creating-multi-project-builds/

多项目构建
========

> gradle init --type scala-library

去除src目录，自建 sparkpi, fintest 子目录，把src目录结构拷贝到子目录中
settings.gradle 增加子项目名称

> gradle :projects

官网文档
=======
https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block

参考这个示例，处理shadowJar插件

Applying plugins to subprojects

```groovy
plugins {
    id 'org.gradle.sample.hello' version '1.0.0' apply false
    id 'org.gradle.sample.goodbye' version '1.0.0' apply false
}

subprojects {
    if (name.startsWith('hello')) {
        apply plugin: 'org.gradle.sample.hello'
    }
}
```

spark本地运行
============

> ./sbin/start-master.sh -h localhost

> ./sbin/start-slave.sh spark://localhost:7077


写入数据
=======
> gradle :fintest:shadowJar

> spark-submit --master spark://localhost:7077 --class fintest.FinTest ./fintest/build/libs/fintest-1.0-all.jar

删除数据
=======
> gradle :cleanup:shadowJar

> spark-submit --master spark://localhost:7077 --class cleanup.CleanUp ./cleanup/build/libs/cleanup-1.0-all.jar brch_qry_dtl 2019-11-27 2019-11-28

cassandra
=========

cassandra 3.11.4  
运行 cassandra -f

data-files目录下有schema.cql中，创建 keyspace, table