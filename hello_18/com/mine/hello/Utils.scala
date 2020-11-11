package com.mine.hello

import com.github.nscala_time.time.Imports._

object Utils {
    def mkDateGroup(startDate: String, days: Int): List[List[String]] = {

        var groups = List[List[String]]()
        

        val s = DateTimeFormat.forPattern("yyyyMMdd")

        var k2 = LocalDate.parse(startDate, s)
        var i = 0
        for (i <- 1 to days) {
            var g = List[String]()
            var k1 = k2 - 1.day
            g = g :+ k1.toString("yyyyMMdd")
            g = g :+ k2.toString("yyyyMMdd")

            groups = groups :+ g

            k2 = k2 - 1.day
            
        }

        return groups
    }
}