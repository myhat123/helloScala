package com.mine.hello

import scalikejdbc._

object Utils {
    Class.forName("org.postgresql.Driver")
    ConnectionPool.singleton("jdbc:postgresql://localhost:5432/money", "hjh", "1234")

    implicit val session = AutoSession

    def getQryDtl(tran_date: String, flag: Int) :List[QryDtl] = {
        val qry: List[QryDtl] = 
            sql"""
            SELECT acc, tran_date, amt, dr_cr_flag, rpt_sum, timestampl
            FROM brch_qry_dtl
            where tran_date=cast(${tran_date} as date) and dr_cr_flag=${flag}
            """.map(rs => QryDtl(
                rs.string("acc"), rs.string("rpt_sum"), rs.date("tran_date"),
                rs.bigDecimal("amt"), rs.int("dr_cr_flag")
            )).list.apply()
        
        return qry
    }
}