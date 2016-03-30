package demo

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * Unit tests for [Memory]
 */
class MemoryTest {

    @Test
    fun memoryTest() {
        val memory = Memory()

        assertEquals(memory.size(), 0)
        assertEquals(memory.toArray(), ByteArray(0))

        memory.expand(2, 0xFF.toShort())

        assertEquals(memory.size(), 2)
        assertEquals(memory.toArray(), byteArrayOf(0xFF.toByte(), 0xFF.toByte()))

        memory.set(0, 0x00.toShort())
        memory.set(1, 0x01.toShort())

        assertEquals(memory.size(), 2)
        assertEquals(memory.toArray(), byteArrayOf(0x00, 0x01))

        memory.expand(4, 0x00.toShort())

        assertEquals(memory.size(), 4)
        assertEquals(memory.toArray(), byteArrayOf(0x00, 0x01, 0x00, 0x00))

        memory.set(2, 0x02.toShort())
        memory.set(3, 0x03.toShort())

        assertEquals(memory.size(), 4)
        assertEquals(memory.toArray(), byteArrayOf(0x00, 0x01, 0x02, 0x03))
    }

}
