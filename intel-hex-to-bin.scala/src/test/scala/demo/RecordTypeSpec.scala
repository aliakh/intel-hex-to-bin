package demo

import org.scalatest._

/**
  * Unit tests for {@link RecordType}
  */
class RecordTypeSpec extends FlatSpec with Matchers {

  it should "pass Code to RecordType test" in {
    RecordTypeConverter.toRecordType(0.toShort) should be(DATA)
    RecordTypeConverter.toRecordType(1.toShort) should be(END_OF_FILE)
    RecordTypeConverter.toRecordType(2.toShort) should be(EXTENDED_SEGMENT_ADDRESS)
    RecordTypeConverter.toRecordType(3.toShort) should be(START_SEGMENT_ADDRESS)
    RecordTypeConverter.toRecordType(4.toShort) should be(EXTENDED_LINEAR_ADDRESS)
    RecordTypeConverter.toRecordType(5.toShort) should be(START_LINEAR_ADDRESS)
  }

  it should "fail Code to RecordType test" in {
    a[IllegalArgumentException] should be thrownBy {
      RecordTypeConverter.toRecordType(6.toShort)
    }
  }

  it should "pass RecordType to Code test" in {
    RecordTypeConverter.toCode(DATA) should be(0.toShort)
    RecordTypeConverter.toCode(END_OF_FILE) should be(1.toShort)
    RecordTypeConverter.toCode(EXTENDED_SEGMENT_ADDRESS) should be(2.toShort)
    RecordTypeConverter.toCode(START_SEGMENT_ADDRESS) should be(3.toShort)
    RecordTypeConverter.toCode(EXTENDED_LINEAR_ADDRESS) should be(4.toShort)
    RecordTypeConverter.toCode(START_LINEAR_ADDRESS) should be(5.toShort)
  }

}