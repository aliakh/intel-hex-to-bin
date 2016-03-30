package demo

import com.google.common.base.Preconditions.checkArgument

/**
  * Intel HEX parsed record.
  */
class Record(private val row: Row) {

  val byteCount: Short = row.getUnsigned8Bit
  val address: Int = row.getUnsigned16Bit
  val recordType: RecordType = RecordTypeConverter.toRecordType(row.getUnsigned8Bit)

  val data: Array[Short] = new Array[Short](this.byteCount)
  for (i <- 0 until this.byteCount) {
    this.data(i) = row.getUnsigned8Bit
  }

  val checksum: Short = row.getUnsigned8Bit

  checkArgument(row.isEmpty, ("Wrong bytes count in the record: " + this).asInstanceOf[Any])
  validateChecksum()

  private def validateChecksum() = {
    var sum: Short = byteCount

    sum = (sum + (address >> 8)).toShort
    sum = (sum + (address & 0xFF)).toShort

    sum = (sum + RecordTypeConverter.toCode(recordType)).toShort

    for (i <- 0 until this.byteCount) {
      sum = (sum + this.data(i)).toShort
    }

    sum = (((sum ^ 0xFF) + 1) & 0xFF).toShort

    checkArgument(sum == this.checksum, java.lang.String.format("Wrong checksum %s for the record: %s", Hex.to16BitHexString(sum), this.toString()).asInstanceOf[Any])
  }

  private def getUnsigned8BitFromData(index: Int): Short = {
    this.data(index)
  }

  def getUnsigned16BitFromData(index: Int): Int = {
    checkArgument(this.data.length == 2)
    (getUnsigned8BitFromData(index) << 8) + getUnsigned8BitFromData(index + 1)
  }

  override def toString: String = {
    "Record{" +
      "byteCount=" + Hex.to8BitHexString(byteCount) +
      ", address=" + Hex.to16BitHexString(address) +
      ", recordType=" + recordType +
      ", data=" + Hex.to8BitHexString(data) +
      ", checksum=" + Hex.to8BitHexString(checksum) +
      '}'
  }

}


