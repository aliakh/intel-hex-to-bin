package demo

import org.scalatest._

/**
  * Unit tests for {@link Record}
  */
class RecordSpec extends FlatSpec with Matchers {

  it should "pass constructorTest" in {
    val row = new Row(":021234005678EA")
    val record = new Record(row)

    record.byteCount should be(0x02.toShort)
    record.address should be(0x1234)
    record.recordType should be(RecordTypeConverter.toRecordType(0x00.toShort))
    record.data should be(Array(0x56.toShort, 0x78.toShort))
    record.checksum should be(0xEA.toShort)
  }

  it should "fail constructorChecksumFailureTest" in {
    a[IllegalArgumentException] should be thrownBy {
      val row = new Row(":02123400567800")
      new Record(row)
    }
  }

}
