老版本的文档，可以看出原始设计的用法

https://github.com/scalikejdbc/scalikejdbc/wiki/GettingStarted

老文档已经无法使用了，切换到新用法

> java -jar build/libs/hello_15-all.jar

日期型处理方法一：
==============
在sql语句中转日期型

```scala
val qry: List[QryDtl] = 
    sql"""
    SELECT acc, tran_date, amt, dr_cr_flag, rpt_sum, timestampl
    FROM brch_qry_dtl
    where tran_date=cast(${tran_date} as date) and dr_cr_flag=${flag}
    """.map(rs => QryDtl(
        rs.string("acc"), rs.string("rpt_sum"), rs.date("tran_date"),
        rs.bigDecimal("amt"), rs.int("dr_cr_flag")
    )).list.apply()
```

日期型处理方法二：
==============
使用nscala-time和joda-time包，进行转换，把字符串型转成日期型，这里的日期型是joda LocalDate

看源代码作为练习
```scala
/*
    https://github.com/nscala-time/nscala-time/blob/master/src/main/scala/com/github/nscala_time/time/Imports.scala
    https://github.com/nscala-time/nscala-time/blob/master/src/main/scala/com/github/nscala_time/time/RichLocalDate.scala
    https://github.com/nscala-time/nscala-time/blob/master/src/main/scala/com/github/nscala_time/time/StaticLocalDate.scala
*/
```

在scalikejdbc中是可以识别 joda LocalDate 的，见下方链接文档
```scala
val s = DateTimeFormat.forPattern("yyyy-MM-dd")
var t = LocalDate.parse(tran_date, s)
```

这里的rs.string, rs.date, rs.bigDecimal, rs.int 类型均是小写字母
对应 case class 中的类型说明

```scala
case class QryDtl (
    acc: String, 
    rptSum: String, 
    tranDate: Date,
    amt: BigDecimal,
    drCrFlag: Int
)
```

类型说明文档
http://scalikejdbc.org/documentation/sql-interpolation.html

代码格式
=======

增加scalariform

https://github.com/hierynomus/scalariform-gradle-plugin

```groovy
plugins {
  id "com.github.hierynomus.scalariform" version "0.4.0"
}

scalariform {
  alignParameters = true
  alignSingleLineCaseStatements = true
}
```

> gradle formatAllScala