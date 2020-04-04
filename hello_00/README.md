脚本执行
=======

scala HelloWorld.sc

交互环境
=======

$ scala
Welcome to Scala 2.12.10 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_211).
Type in expressions for evaluation. Or try :help.

scala> 

下载第三方包
==========

测试使用 nscala-time 包 https://github.com/nscala-time/nscala-time
官方代码中build.sbt中声明依赖包是joda-time, joda-convert两个

下载地址：
https://mvnrepository.com/artifact/org.joda/joda-convert/2.2.1
https://mvnrepository.com/artifact/joda-time/joda-time/2.10.5

下载版本对应scala 2.12版本

使用第三方包
==========

$ scala -cp libs/joda-convert-2.2.1.jar:./libs/joda-time-2.10.5.jar:./libs/nscala-time_2.12-2.22.0.jar
Welcome to Scala 2.12.10 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_211).
Type in expressions for evaluation. Or try :help.

scala> import com.github.nscala_time.time.Imports._
import com.github.nscala_time.time.Imports._

scala> DateTime.now() + 2.months
res0: org.joda.time.DateTime = 2020-05-27T18:47:30.004+08:00

scala> DateTime.now() to DateTime.tomorrow
res1: org.joda.time.Interval = 2020-03-27T18:50:04.551+08:00/2020-03-28T18:50:04.552+08:00

scala> val s = DateTimeFormat.forPattern("yyyy-MM-dd")
s: org.joda.time.format.DateTimeFormatter = org.joda.time.format.DateTimeFormatter@1835d3ed

scala> var x = LocalDate.parse("2019-11-27", s)
x: org.joda.time.LocalDate = 2019-11-27

joda的用法
=========
https://www.joda.org/joda-time/userguide.html

var dt = new DateTime()
dt.getDayOfWeek()
dt.getYear()
dt.getMonthOfYear()
dt.getHourOfDay()
dt.getMinuteOfHour()

dt.plusDays(60)

nscala-time用法
==============

直接看代码，猜想用法
https://github.com/nscala-time/nscala-time/blob/master/src/main/scala/com/github/nscala_time/time/RichDateTime.scala

dt + 60.day
