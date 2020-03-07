object HelloWorld {
    def sum(a: Int, b: Int): Int = {
        a + b
    }
}

val x = HelloWorld.sum(2, 3)
println(s"Hello sum: ${x}")