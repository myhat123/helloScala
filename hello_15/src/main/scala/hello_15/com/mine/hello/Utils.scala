package com.mine.hello

import scalikejdbc._
import com.github.nscala_time.time.Imports._

object Utils {
    Class.forName("org.postgresql.Driver")
    ConnectionPool.singleton("jdbc:postgresql://localhost:5432/money", "hjh", "1234")

    implicit val session = AutoSession

    def getQryDtl(tran_date: String, flag: Int) :List[QryDtl] = {
        // 第一种解析
        // val s = DateTimeFormat.forPattern("yyyy-MM-dd")
        // var t = LocalDate.parse(tran_date, s)

        // 第二种解析 字符串转日期，这个做法更简单，直接看nscala time源码
        /*
            https://github.com/nscala-time/nscala-time/blob/master/src/main/scala/com/github/nscala_time/time/Imports.scala
            https://github.com/nscala-time/nscala-time/blob/master/src/main/scala/com/github/nscala_time/time/RichLocalDate.scala
            https://github.com/nscala-time/nscala-time/blob/master/src/main/scala/com/github/nscala_time/time/StaticLocalDate.scala
        */
        var t = LocalDate.parse(tran_date)
        val qry: List[QryDtl] = 
            sql"""
            SELECT acc, tran_date, amt, dr_cr_flag, rpt_sum, timestampl
            FROM brch_qry_dtl
            where tran_date=${t} and dr_cr_flag=${flag}
            """.map(rs => QryDtl(
                rs.string("acc"), rs.string("rpt_sum"), rs.date("tran_date"),
                rs.bigDecimal("amt"), rs.int("dr_cr_flag")
            )).list.apply()
        
        return qry
    }
}