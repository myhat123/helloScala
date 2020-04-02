老版本的文档，可以看出原始设计的用法

https://github.com/scalikejdbc/scalikejdbc/wiki/GettingStarted

老文档已经无法使用了，切换到新用法

java -jar build/libs/hello_15-all.jar

val qry: List[QryDtl] = 
    sql"""
    SELECT acc, tran_date, amt, dr_cr_flag, rpt_sum, timestampl
    FROM brch_qry_dtl
    where tran_date=cast(${tran_date} as date) and dr_cr_flag=${flag}
    """.map(rs => QryDtl(
        rs.string("acc"), rs.string("rpt_sum"), rs.date("tran_date"),
        rs.bigDecimal("amt"), rs.int("dr_cr_flag")
    )).list.apply()

这里的rs.string, rs.date, rs.bigDecimal, rs.int 类型均是小写字母
对应 case class 中的类型说明

case class QryDtl (
    acc: String, 
    rptSum: String, 
    tranDate: Date,
    amt: BigDecimal,
    drCrFlag: Int
)

类型说明文档
http://scalikejdbc.org/documentation/sql-interpolation.html