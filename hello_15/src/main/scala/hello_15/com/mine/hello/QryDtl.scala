package com.mine.hello

import java.sql._   // Date
//import java.math._  // BigDecimal
import scala.math._

case class QryDtl (
    acc: String, 
    rptSum: String, 
    tranDate: Date,
    amt: BigDecimal,
    drCrFlag: Int
)