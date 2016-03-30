package demo

/**
 * Intel HEX record types.
 */
enum class RecordType private constructor(code: Short) {

    DATA(0),
    END_OF_FILE(1),
    EXTENDED_SEGMENT_ADDRESS(2),
    START_SEGMENT_ADDRESS(3),
    EXTENDED_LINEAR_ADDRESS(4),
    START_LINEAR_ADDRESS(5);

    val code: Short

    init {
        this.code = code
    }

    override fun toString(): String {
        return this.name + "[" + code + ']'
    }
}

fun Short.toRecordType(): RecordType {
    for (recordType in RecordType.values()) {
        if (recordType.code == this) {
            return recordType
        }
    }
    throw IllegalArgumentException("Record type not found for the code: " + this)
}
