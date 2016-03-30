package demo;

/**
 * Intel HEX record types.
 */
public enum RecordType {

    DATA((short) 0),
    END_OF_FILE((short) 1),
    EXTENDED_SEGMENT_ADDRESS((short) 2),
    START_SEGMENT_ADDRESS((short) 3),
    EXTENDED_LINEAR_ADDRESS((short) 4),
    START_LINEAR_ADDRESS((short) 5);

    final private short code;

    private RecordType(short code) {
        this.code = code;
    }

    static public RecordType of(short code) {
        for (RecordType recordType : RecordType.values()) {
            if (recordType.getCode() == code) {
                return recordType;
            }
        }
        throw new IllegalArgumentException("Record type not found for the code: " + code);
    }

    public short getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.name() + "[" + code + ']';
    }

}
