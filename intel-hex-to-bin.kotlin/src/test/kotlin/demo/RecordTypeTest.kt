package demo

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * Unit tests for [RecordType]
 */
class RecordTypeTest {

    @Test
    fun valuesTest() {
        assertEquals(6, RecordType.values().size)

        assertValue(RecordType.DATA, 0)
        assertValue(RecordType.END_OF_FILE, 1)
        assertValue(RecordType.EXTENDED_SEGMENT_ADDRESS, 2)
        assertValue(RecordType.START_SEGMENT_ADDRESS, 3)
        assertValue(RecordType.EXTENDED_LINEAR_ADDRESS, 4)
        assertValue(RecordType.START_LINEAR_ADDRESS, 5)
    }

    private fun assertValue(recordType: RecordType, code: Short) {
        assertEquals(code, recordType.code)
        assertEquals(code.toRecordType(), recordType)
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun codeFailureTest() {
        RecordType.values().size.toShort().toRecordType()
    }

}
