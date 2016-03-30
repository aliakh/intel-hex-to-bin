package demo

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * Unit tests for [Record]
 */
class RecordTest {

    @Test
    fun constructorTest() {
        val row = Row(":021234005678EA")
        val record = Record(row)

        assertEquals(record.byteCount, 0x02.toShort())
        assertEquals(record.address, 0x1234)
        assertEquals(record.recordType, 0x00.toShort().toRecordType())
        assertEquals(record.data, shortArrayOf(0x56, 0x78))
        assertEquals(record.checksum, 0xEA.toShort())
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun constructorChecksumFailureTest() {
        val row = Row(":02123400567800")
        Record(row)
    }

}
