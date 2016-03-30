package demo

import org.scalatest._

/**
  * Unit tests for {@link Hex}
  */
class HexSpec extends FlatSpec with Matchers {

  it should "pass numberTo8BitHexStringTest" in {
    Hex.to8BitHexString(0x00.toShort) should be("0x00")
    Hex.to8BitHexString(0x01.toShort) should be("0x01")
    Hex.to8BitHexString(0x12.toShort) should be("0x12")
    Hex.to8BitHexString(0xAB.toShort) should be("0xAB")
    Hex.to8BitHexString(0xFF.toShort) should be("0xFF")
  }

  it should "fail numberTo8BitHexStringUnderflowTest" in {
    a[IllegalArgumentException] should be thrownBy {
      Hex.to8BitHexString((0-1).toShort)
    }
  }

  it should "fail numberTo8BitHexStringOverflowTest" in {
    a[IllegalArgumentException] should be thrownBy {
      Hex.to8BitHexString(0x100.toShort)
    }
  }

  it should "pass numberTo16BitHexStringTest" in {
    Hex.to16BitHexString(0x0000) should be("0x0000")
    Hex.to16BitHexString(0x0001) should be("0x0001")
    Hex.to16BitHexString(0x0012) should be("0x0012")
    Hex.to16BitHexString(0x00AB) should be("0x00AB")
    Hex.to16BitHexString(0x0123) should be("0x0123")
    Hex.to16BitHexString(0x0ABC) should be("0x0ABC")
    Hex.to16BitHexString(0x1234) should be("0x1234")
    Hex.to16BitHexString(0xABCD) should be("0xABCD")
    Hex.to16BitHexString(0xFFFF) should be("0xFFFF")
  }

  it should "fail numberTo16BitHexStringUnderflowTest" in {
    a[IllegalArgumentException] should be thrownBy {
      Hex.to16BitHexString(-1)
    }
  }

  it should "fail numberTo16BitHexStringOverflowTest" in {
    a[IllegalArgumentException] should be thrownBy {
      Hex.to16BitHexString(0x10000)
    }
  }

  it should "pass arrayTo8BitHexStringTest" in {
    Hex.to8BitHexString(Array[Short]()) should be("[]")
    Hex.to8BitHexString(Array[Short](0x12)) should be("[0x12]")
    Hex.to8BitHexString(Array[Short](0x12, 0xAB)) should be("[0x12,0xAB]")
  }

}