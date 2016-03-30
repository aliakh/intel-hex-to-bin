package demo

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * Unit tests for [Hex]
 */
class HexTest {

    @Test
    fun numberTo8BitHexStringTest() {
        assertEquals(0x00.toShort().to8BitHexString(), "0x00")
        assertEquals(0x01.toShort().to8BitHexString(), "0x01")

        assertEquals(0x12.toShort().to8BitHexString(), "0x12")
        assertEquals(0xAB.toShort().to8BitHexString(), "0xAB")
        assertEquals(0xFF.toShort().to8BitHexString(), "0xFF")
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun numberTo8BitHexStringUnderflowTest() {
        (0 - 1).toShort().to8BitHexString()
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun numberTo8BitHexStringOverflowTest() {
        0x100.toShort().to8BitHexString()
    }

    @Test
    fun numberTo16BitHexStringTest() {
        assertEquals(0x0000.to16BitHexString(), "0x0000")
        assertEquals(0x0001.to16BitHexString(), "0x0001")

        assertEquals(0x0012.to16BitHexString(), "0x0012")
        assertEquals(0x00AB.to16BitHexString(), "0x00AB")

        assertEquals(0x0123.to16BitHexString(), "0x0123")
        assertEquals(0x0ABC.to16BitHexString(), "0x0ABC")

        assertEquals(0x1234.to16BitHexString(), "0x1234")
        assertEquals(0xABCD.to16BitHexString(), "0xABCD")
        assertEquals(0xFFFF.to16BitHexString(), "0xFFFF")
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun numberTo16BitHexStringUnderflowTest() {
        (0 - 1).to16BitHexString()
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun numberTo16BitHexStringOverflowTest() {
        0x10000.to16BitHexString()
    }

    @Test
    fun arrayTo8BitHexStringTest() {
        assertEquals(shortArrayOf().to8BitHexString(), "[]")
        assertEquals(shortArrayOf(0x12).to8BitHexString(), "[0x12]")
        assertEquals(shortArrayOf(0x12, 0xAB).to8BitHexString(), "[0x12,0xAB]")
    }

}
