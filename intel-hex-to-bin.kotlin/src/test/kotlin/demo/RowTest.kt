package demo

import org.testng.Assert.*
import org.testng.annotations.Test

/**
 * Unit tests for [Row]
 */
class RowTest {

    @Test
    fun constructorTest() {
        val row = Row(":021234005678FF")

        assertFalse(row.isEmpty())
        assertEquals(row.getUnsigned8Bit(), 0x02.toShort())
        assertFalse(row.isEmpty())
        assertEquals(row.getUnsigned16Bit(), 0x1234.toInt())
        assertFalse(row.isEmpty())
        assertEquals(row.getUnsigned8Bit(), 0x00.toShort())
        assertFalse(row.isEmpty())
        assertEquals(row.getUnsigned8Bit(), 0x56.toShort())
        assertFalse(row.isEmpty())
        assertEquals(row.getUnsigned8Bit(), 0x78.toShort())
        assertFalse(row.isEmpty())
        assertEquals(row.getUnsigned8Bit(), 0xFF.toShort())
        assertTrue(row.isEmpty())
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun noColonConstructorTest() {
        Row("00000001FF")
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun wrongCharsConstructorTest() {
        Row(":XX")
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun oddCharsNumberConstructorTest() {
        Row(":0")
    }

    @Test
    fun getUnsigned8BitTest() {
        val row = Row(":01ab")

        assertFalse(row.isEmpty())
        assertEquals(row.getUnsigned8Bit(), 0x01.toShort())
        assertEquals(row.getUnsigned8Bit(), 0xAB.toShort())
        assertTrue(row.isEmpty())
    }

    @Test
    fun getUnsigned16BitTest() {
        val row = Row(":1234abcd")

        assertFalse(row.isEmpty())
        assertEquals(row.getUnsigned16Bit(), 0x1234)
        assertEquals(row.getUnsigned16Bit(), 0xABCD)
        assertTrue(row.isEmpty())
    }

}
