package demo

sealed trait RecordType

case object DATA extends RecordType

case object END_OF_FILE extends RecordType

case object EXTENDED_SEGMENT_ADDRESS extends RecordType

case object START_SEGMENT_ADDRESS extends RecordType

case object EXTENDED_LINEAR_ADDRESS extends RecordType

case object START_LINEAR_ADDRESS extends RecordType

object RecordTypeConverter {

  val values = List(
    (0, DATA),
    (1, END_OF_FILE),
    (2, EXTENDED_SEGMENT_ADDRESS),
    (3, START_SEGMENT_ADDRESS),
    (4, EXTENDED_LINEAR_ADDRESS),
    (5, START_LINEAR_ADDRESS)
  )

  def toRecordType(code: Short): RecordType = {
    for (value <- values) {
      if (value._1 == code) {
        return value._2
      }
    }
    throw new IllegalArgumentException("Record type not found for the code: " + code)
  }

  def toCode(recordType: RecordType): Short = {
    for (value <- values) {
      if (value._2 == recordType) {
        return value._1.toShort
      }
    }
    throw new IllegalArgumentException("Code not found for the record type: " + recordType)
  }

}