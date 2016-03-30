package demo;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link Record}
 */
public class RecordTest {

    @Test
    public void constructorTest() {
        Row row = new Row(":021234005678EA");
        Record record = new Record(row);

        assertEquals(record.getByteCount(), (short) 0x02);
        assertEquals(record.getAddress(), 0x1234);
        assertEquals(record.getRecordType(), RecordType.of((short) 0x00));
        assertEquals(record.getData(), new short[]{0x56, 0x78});
        assertEquals(record.getChecksum(), (short) 0xEA);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void checksumFailureConstructorTest() {
        Row row = new Row(":02123400567800");
        new Record(row);
    }

}
