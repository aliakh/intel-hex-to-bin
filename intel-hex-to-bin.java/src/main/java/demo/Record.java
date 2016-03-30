package demo;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Intel HEX parsed record.
 */
final public class Record {

    final private short byteCount;
    final private int address;
    final private RecordType recordType;
    final private short[] data;
    final private short checksum;

    public Record(Row row) {
        this.byteCount = row.getUnsigned8Bit();
        this.address = row.getUnsigned16Bit();
        this.recordType = RecordType.of(row.getUnsigned8Bit());

        this.data = new short[this.byteCount];
        for (int i = 0; i < this.byteCount; i++) {
            this.data[i] = row.getUnsigned8Bit();
        }

        this.checksum = row.getUnsigned8Bit();

        checkArgument(row.isEmpty(), "Wrong bytes count in the record: " + this);
        validateChecksum();
    }

    private void validateChecksum() {
        short sum = byteCount;

        sum += address >> 8;
        sum += address & 0xFF;

        sum += recordType.getCode();

        for (int i = 0; i < this.byteCount; i++) {
            sum += this.data[i];
        }

        sum = (short) (((~sum) + 1) & 0xFF);

        checkArgument(sum == this.checksum, String.format("Wrong checksum %s in the record: %s", Hex.to8BitHexString(sum), this.toString()));
    }

    private short getUnsigned8BitFromData(int index) {
        return this.getData()[index];
    }

    public int getUnsigned16BitFromData(int index) {
        checkArgument(this.getData().length == 2);
        return (getUnsigned8BitFromData(index) << 8) + getUnsigned8BitFromData(index + 1);
    }

    public short getByteCount() {
        return byteCount;
    }

    public int getAddress() {
        return address;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public short[] getData() {
        return data;
    }

    public short getChecksum() {
        return checksum;
    }

    @Override
    public String toString() {
        return "Record{" +
                "byteCount=" + Hex.to8BitHexString(byteCount) +
                ", address=" + Hex.to16BitHexString(address) +
                ", recordType=" + recordType +
                ", data=" + Hex.to8BitHexString(data) +
                ", checksum=" + Hex.to8BitHexString(checksum) +
                '}';
    }

}
