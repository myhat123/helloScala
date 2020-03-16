/*
 * https://medium.com/@mrpowers/the-different-type-of-spark-functions-custom-transformations-column-functions-udfs-bf556c9d0ce7
 *
 
def toLowerFun(str: String): Option[String] = {
  val s = Option(str).getOrElse(return None)
  Some(s.toLowerCase())
}

val toLower = udf[Option[String], String](toLowerFun)
*/

object HelloWorld {

    def transIdCard15to18(IdCardNo: String): Option[String] = {
        var s = Option(IdCardNo).getOrElse(return None)
        var cardNo: String = null
        if (s.trim().length == 15) {
            var id = s.trim()
            var c = id.substring(0, 6) + "19" + id.substring(6, id.length)
            cardNo = c + transCardLastNo(c)
        } else if (s.trim().length == 18) {
            cardNo = s
        }
        
        return Option(cardNo)
    }

    def transCardLastNo(newCardId: String): Char = {
        val ch = newCardId.toList
        val co = List(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8 ,4, 2)
        val verCode = List('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2')

        var m = 0
        for ( i <- 0 to ch.length-1 ) {
            m += (ch(i) - '0')*co(i)
        }
        var residue = m % 11
        return verCode(residue)

    }

    def main(args: Array[String]): Unit = {
        println(transCardLastNo("1234"))
        println(transIdCard15to18("370986890623212"))
        println(transIdCard15to18("370725881105149"))
        println(transIdCard15to18("362228521021081"))
        println(transIdCard15to18("3707149"))
        println(transIdCard15to18("36222819521021081X"))
        println(transIdCard15to18(null))
    }
}
