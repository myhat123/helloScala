spark本地运行
============

> ./sbin/start-master.sh -h localhost

> ./sbin/start-slave.sh spark://localhost:7077

执行spark job
============

> pyenv shell 3.7.4

> spark-submit --master spark://localhost:7077 pi.py

> spark-submit --master spark://localhost:7077 --packages com.datastax.spark:spark-cassandra-connector_2.11:2.0.10 test.py

```
hzg@gofast:~/work/helloScala/hello_17$ spark-submit --master spark://localhost:7077 --packages com.datastax.spark:spark-cassandra-connector_2.11:2.0.10 test.py
Ivy Default Cache set to: /home/hzg/.ivy2/cache
The jars for the packages stored in: /home/hzg/.ivy2/jars
:: loading settings :: url = jar:file:/home/hzg/spark-2.2.1/jars/ivy-2.4.0.jar!/org/apache/ivy/core/settings/ivysettings.xml
com.datastax.spark#spark-cassandra-connector_2.11 added as a dependency
:: resolving dependencies :: org.apache.spark#spark-submit-parent;1.0
	confs: [default]
	found com.datastax.spark#spark-cassandra-connector_2.11;2.0.10 in central
	found joda-time#joda-time;2.3 in central
	found org.joda#joda-convert;1.2 in central
	found commons-beanutils#commons-beanutils;1.9.3 in central
	found commons-collections#commons-collections;3.2.2 in central
	found io.netty#netty-all;4.0.33.Final in central
	found com.twitter#jsr166e;1.1.0 in central
	found org.scala-lang#scala-reflect;2.11.8 in central
downloading https://repo1.maven.org/maven2/com/datastax/spark/spark-cassandra-connector_2.11/2.0.10/spark-cassandra-connector_2.11-2.0.10.jar ...
	[SUCCESSFUL ] com.datastax.spark#spark-cassandra-connector_2.11;2.0.10!spark-cassandra-connector_2.11.jar (164116ms)
downloading https://repo1.maven.org/maven2/joda-time/joda-time/2.3/joda-time-2.3.jar ...
	[SUCCESSFUL ] joda-time#joda-time;2.3!joda-time.jar (11686ms)
downloading https://repo1.maven.org/maven2/org/joda/joda-convert/1.2/joda-convert-1.2.jar ...
	[SUCCESSFUL ] org.joda#joda-convert;1.2!joda-convert.jar (885ms)
downloading https://repo1.maven.org/maven2/commons-beanutils/commons-beanutils/1.9.3/commons-beanutils-1.9.3.jar ...
	[SUCCESSFUL ] commons-beanutils#commons-beanutils;1.9.3!commons-beanutils.jar (4478ms)
downloading https://repo1.maven.org/maven2/io/netty/netty-all/4.0.33.Final/netty-all-4.0.33.Final.jar ...
	[SUCCESSFUL ] io.netty#netty-all;4.0.33.Final!netty-all.jar (42351ms)
downloading https://repo1.maven.org/maven2/com/twitter/jsr166e/1.1.0/jsr166e-1.1.0.jar ...
	[SUCCESSFUL ] com.twitter#jsr166e;1.1.0!jsr166e.jar (964ms)
downloading https://repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.11.8/scala-reflect-2.11.8.jar ...
	[SUCCESSFUL ] org.scala-lang#scala-reflect;2.11.8!scala-reflect.jar (91763ms)
downloading https://repo1.maven.org/maven2/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar ...
	[SUCCESSFUL ] commons-collections#commons-collections;3.2.2!commons-collections.jar (10993ms)
:: resolution report :: resolve 29885ms :: artifacts dl 327253ms
	:: modules in use:
	com.datastax.spark#spark-cassandra-connector_2.11;2.0.10 from central in [default]
	com.twitter#jsr166e;1.1.0 from central in [default]
	commons-beanutils#commons-beanutils;1.9.3 from central in [default]
	commons-collections#commons-collections;3.2.2 from central in [default]
	io.netty#netty-all;4.0.33.Final from central in [default]
	joda-time#joda-time;2.3 from central in [default]
	org.joda#joda-convert;1.2 from central in [default]
	org.scala-lang#scala-reflect;2.11.8 from central in [default]
	---------------------------------------------------------------------
	|                  |            modules            ||   artifacts   |
	|       conf       | number| search|dwnlded|evicted|| number|dwnlded|
	---------------------------------------------------------------------
	|      default     |   8   |   8   |   8   |   0   ||   8   |   8   |
	---------------------------------------------------------------------
:: retrieving :: org.apache.spark#spark-submit-parent
	confs: [default]
	8 artifacts copied, 0 already retrieved (16354kB/102ms)
```

其中：jar包保存在 /home/hzg/.ivy2/jars

> spark-submit --master spark://localhost:7077 --packages com.datastax.spark:spark-cassandra-connector_2.11:2.0.10 test02.py

spark python api
================

http://spark.apache.org/docs/latest/api/python/pyspark.sql.html#pyspark.sql.DataFrameReader

options(**options)  
option(key, value)

获取行结果

http://spark.apache.org/docs/latest/api/python/pyspark.sql.html#pyspark.sql.Row

