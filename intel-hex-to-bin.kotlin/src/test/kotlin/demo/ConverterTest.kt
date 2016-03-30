package demo

import com.google.common.io.Files
import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import java.io.File
import java.io.IOException

/**
 * Unit tests for [Converter]
 */
class ConverterTest {

    @Test
    @Throws(IOException::class)
    fun convertI8HexTest() {
        convert(0x00, "i8hex.1.hex", "i8hex_1.pad_00.actual.bin", "i8hex.1.expected.bin")
        convert(0xFF, "i8hex.1.hex", "i8hex_1.pad_ff.actual.bin", "i8hex.1.expected.bin")

        convert(0x00, "i8hex.2.hex", "i8hex_2.pad_00.actual.bin", "i8hex.2.pad_00.expected.bin")
        convert(0xFF, "i8hex.2.hex", "i8hex_2.pad_ff.actual.bin", "i8hex.2.pad_ff.expected.bin")
    }

    @Test
    @Throws(IOException::class)
    fun convertI16HexTest() {
        convert(0x00, "i16hex.1.hex", "i16hex_1.pad_00.actual.bin", "i16hex.1.expected.bin")
        convert(0xFF, "i16hex.1.hex", "i16hex_1.pad_ff.actual.bin", "i16hex.1.expected.bin")

        convert(0x00, "i16hex.2.hex", "i16hex_2.pad_00.actual.bin", "i16hex.2.pad_00.expected.bin")
        convert(0xFF, "i16hex.2.hex", "i16hex_2.pad_ff.actual.bin", "i16hex.2.pad_ff.expected.bin")
    }

    @Test
    @Throws(IOException::class)
    fun convertI32HexTest() {
        convert(0x00, "i32hex.hex", "i32hex.pad_00.actual.bin", "i32hex.pad_00.expected.bin")
        convert(0xFF, "i32hex.hex", "i32hex.pad_ff.actual.bin", "i32hex.pad_ff.expected.bin")
    }

    @Throws(IOException::class)
    private fun convert(pad: Short, sourceHexFileName: String, actualBinFileName: String, expectedBinFileName: String) {
        val separator = File.separator
        val currentFolderName = File(".").canonicalPath
        val path = currentFolderName + separator + "build" + separator + "resources" + separator + "test" + separator

        val hexFileName = path + sourceHexFileName
        val binFileName = path + actualBinFileName

        val converter = Converter(pad.toShort())
        converter.convert(hexFileName, binFileName)

        val actual = Files.toByteArray(File(path + actualBinFileName))
        val expected = Files.toByteArray(File(path + expectedBinFileName))
        assertEquals(actual, expected)
    }

}
