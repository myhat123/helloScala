import com.mine.hello._

object Hello {
    def main(args: Array[String]) {
        val x = Utils.mkDateGroup("20201111", 15)
        
        for (k <- x) {
            println(k(0), k(1))
        }
    }
}