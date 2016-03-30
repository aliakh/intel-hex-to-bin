package demo;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link RecordType}
 */
public class RecordTypeTest {

    @Test
    public void valuesTest() {
        assertEquals(6, RecordType.values().length);

        assertValue(RecordType.DATA, (byte) 0);
        assertValue(RecordType.END_OF_FILE, (byte) 1);
        assertValue(RecordType.EXTENDED_SEGMENT_ADDRESS, (byte) 2);
        assertValue(RecordType.START_SEGMENT_ADDRESS, (byte) 3);
        assertValue(RecordType.EXTENDED_LINEAR_ADDRESS, (byte) 4);
        assertValue(RecordType.START_LINEAR_ADDRESS, (byte) 5);
    }

    private void assertValue(RecordType recordType, byte code) {
        assertEquals(code, recordType.getCode());
        assertEquals(RecordType.of(code), recordType);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void codeFailureTest() {
        RecordType.of((byte) RecordType.values().length);
    }

}
