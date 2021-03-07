import com.mine.hello._

object Hello {
    def main(args: Array[String]) {
        val x = "cw_0x689RpI-jtRR7oE8h_eQsKImvJapLeSbXpwF4e4="
        
        var enc_acc = Utils.encrypt(x, "62174378490347")
        println(enc_acc)

        var acc = Utils.decrypt(x, enc_acc)
        println(acc)
    }
}