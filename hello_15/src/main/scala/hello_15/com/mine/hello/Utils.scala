package com.mine.hello

import scalikejdbc._
import com.github.nscala_time.time.Imports._

object Utils {
    Class.forName("org.postgresql.Driver")
    ConnectionPool.singleton("jdbc:postgresql://localhost:5432/money", "hjh", "1234")

    implicit val session = AutoSession

    def getQryDtl(tran_date: String, flag: Int) :List[QryDtl] = {
        val s = DateTimeFormat.forPattern("yyyy-MM-dd")
        var t = LocalDate.parse(tran_date, s)

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