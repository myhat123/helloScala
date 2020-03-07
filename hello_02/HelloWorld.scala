object Utils {
    def sum(a: Int, b: Int): Int = {
        a + b
    }
}

object Hello {
    def main(args: Array[String]) {
        val x = Utils.sum(2, 3)
        println(s"Hello sum: ${x}")
    }
}