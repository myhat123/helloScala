object Hello {
    def main(args: Array[String]) {
        val x = sum(2, 3)
        println(s"Hello sum: ${x}")
    }

    def sum(a: Int, b: Int): Int = {
        a + b
    }
}