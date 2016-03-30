package demo

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * Unit tests for [FileName]
 */
class FileNameTest {

    @Test
    fun getBinFileNameTest() {
        assertEquals(getBinFileName("test.hex"), "test.bin")
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun noNameFailureTest() {
        getBinFileName(".hex")
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun noExtensionFailureTest() {
        getBinFileName("test")
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun emptyExtensionFailureTest() {
        getBinFileName("test.")
    }

    @Test(expectedExceptions = arrayOf(IllegalArgumentException::class))
    fun wrongExtensionFailureTest() {
        getBinFileName("test.txt")
    }

}
