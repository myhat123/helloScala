package hello_15

import com.mine.hello._

object Hello {
  def main(args: Array[String]) {
    var results = Utils.getQryDtl("2019-11-27", 1)
    for (x <- results) {
      println(s"tran_date: ${x.tranDate}, acc: ${x.acc}, rpt_sum: ${x.rptSum}, amt: ${x.amt}, dr_cr_flag: ${x.drCrFlag}")
    }
  }
}
