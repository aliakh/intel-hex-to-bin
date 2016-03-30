package demo

import com.google.common.base.Preconditions.checkArgument

/**
 * Intel HEX parsed record.
 */
class Record(row: Row) {

    val byteCount: Short
    val address: Int
    val recordType: RecordType
    val data: ShortArray
    val checksum: Short

    init {
        this.byteCount = row.getUnsigned8Bit()
        this.address = row.getUnsigned16Bit()
        this.recordType = row.getUnsigned8Bit().toRecordType()

        this.data = ShortArray(this.byteCount.toInt())
        for (i in 0..this.byteCount - 1) {
            this.data[i] = row.getUnsigned8Bit()
        }

        this.checksum = row.getUnsigned8Bit()

        checkArgument(row.isEmpty(), "Wrong bytes count in the record: " + this)
        validateChecksum()
    }

    private fun validateChecksum() {
        var sum: Short = byteCount

        sum = (sum + (address shr 8)).toShort()
        sum = ( sum + (address and 0xFF)).toShort()

        sum = (sum + recordType.code).toShort()

        for (i in 0..this.byteCount - 1) {
            sum = (sum + this.data[i]).toShort()
        }

        sum = (sum.toInt().inv() + 1 and 0xFF).toShort()

        checkArgument(sum == this.checksum, "Wrong checksum ${sum.to8BitHexString()} for the record: ${this.toString()}")
    }

    private fun getUnsigned8BitFromData(index: Int): Short {
        return this.data[index]
    }

    fun getUnsigned16BitFromData(index: Int): Int {
        checkArgument(this.data.size == 2)
        return (getUnsigned8BitFromData(index).toInt() shl 8) + getUnsigned8BitFromData(index + 1)
    }

    override fun toString(): String {
        return "Record{" +
                "byteCount=" + byteCount.to8BitHexString() +
                ", address=" + address.to16BitHexString() +
                ", recordType=" + recordType +
                ", data=" + data.to8BitHexString() +
                ", checksum=" + checksum.to8BitHexString() +
                '}'
    }

}
