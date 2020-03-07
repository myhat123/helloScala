import com.mine.hello._

object Hello {
    def main(args: Array[String]) {
        val x = Utils.sum(2, 3)
        println(s"Hello sum: ${x}")
    }
}