import org.scalatest._

class TestUtils extends FlatSpec with Matchers {
    it should "两数之和" in {
        Utils.sum(2, 3) should be (5)
    }
}