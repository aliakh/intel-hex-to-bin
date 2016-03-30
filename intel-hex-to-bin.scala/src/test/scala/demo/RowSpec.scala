package demo

import org.scalatest._

/**
  * Unit tests for {@link Row}
  */
class RowSpec extends FlatSpec with Matchers {

  it should "pass constructorTest" in {
    val row = new Row(":021234005678FF")

    row.isEmpty should be(false)
    row.getUnsigned8Bit should be(0x02.toShort)
    row.isEmpty should be(false)
    row.getUnsigned16Bit should be(0x1234)
    row.isEmpty should be(false)
    row.getUnsigned8Bit should be(0x00.toShort)
    row.isEmpty should be(false)
    row.getUnsigned8Bit should be(0x56.toShort)
    row.isEmpty should be(false)
    row.getUnsigned8Bit should be(0x78.toShort)
    row.isEmpty should be(false)
    row.getUnsigned8Bit should be(0xFF.toShort)
    row.isEmpty should be(true)
  }

  it should "fail noColonConstructorTest" in {
    a[IllegalArgumentException] should be thrownBy {
      new Row("00000001FF")
    }
  }

  it should "fail wrongCharsConstructorTest" in {
    a[IllegalArgumentException] should be thrownBy {
      new Row(":XX")
    }
  }

  it should "fail oddCharsNumberConstructorTest" in {
    a[IllegalArgumentException] should be thrownBy {
      new Row(":0")
    }
  }

  it should "pass getUnsigned8BitTest" in {
    val row = new Row(":01ab")

    row.isEmpty should be(false)
    row.getUnsigned8Bit should be(0x01.toShort)
    row.getUnsigned8Bit should be(0xAB.toShort)
    row.isEmpty should be(true)
  }

  it should "pass getUnsigned16BitTest" in {
    val row = new Row(":1234abcd")

    row.isEmpty should be(false)
    row.getUnsigned16Bit should be(0x1234)
    row.getUnsigned16Bit should be(0xABCD)
    row.isEmpty should be(true)
  }

}