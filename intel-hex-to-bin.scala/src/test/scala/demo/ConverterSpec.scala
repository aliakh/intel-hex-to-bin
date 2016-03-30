package demo

import java.io.File

import com.google.common.io.Files
import org.scalatest._

/**
  * Unit tests for {@link FileName}
  */
class ConverterSpec extends FlatSpec with Matchers {

  it should "pass convertI8HexTest" in {
    convert(0x00, "i8hex.1.hex", "i8hex_1.pad_00.actual.bin", "i8hex.1.expected.bin")
    convert(0xFF, "i8hex.1.hex", "i8hex_1.pad_ff.actual.bin", "i8hex.1.expected.bin")

    convert(0x00, "i8hex.2.hex", "i8hex_2.pad_00.actual.bin", "i8hex.2.pad_00.expected.bin")
    convert(0xFF, "i8hex.2.hex", "i8hex_2.pad_ff.actual.bin", "i8hex.2.pad_ff.expected.bin")
  }

  it should "pass convertI16HexTest" in {
    convert(0x00, "i16hex.1.hex", "i16hex_1.pad_00.actual.bin", "i16hex.1.expected.bin")
    convert(0xFF, "i16hex.1.hex", "i16hex_1.pad_ff.actual.bin", "i16hex.1.expected.bin")

    convert(0x00, "i16hex.2.hex", "i16hex_2.pad_00.actual.bin", "i16hex.2.pad_00.expected.bin")
    convert(0xFF, "i16hex.2.hex", "i16hex_2.pad_ff.actual.bin", "i16hex.2.pad_ff.expected.bin")
  }

  it should "pass convertI32HexTest" in {
    convert(0x00, "i32hex.hex", "i32hex.pad_00.actual.bin", "i32hex.pad_00.expected.bin")
    convert(0xFF, "i32hex.hex", "i32hex.pad_ff.actual.bin", "i32hex.pad_ff.expected.bin")
  }

  private def convert(pad: Short, sourceHexFileName: String, actualBinFileName: String, expectedBinFileName: String) = {
    val separator = File.separator
    val currentFolderName = new File(".").getCanonicalPath
    val path = currentFolderName + separator + "target" + separator + "scala-2.11" + separator + "test-classes" + separator

    val hexFileName = path + sourceHexFileName
    val binFileName = path + actualBinFileName

    val converter = new Converter(pad)
    converter.convert(hexFileName, binFileName)

    val actual = Files.toByteArray(new File(path + actualBinFileName))
    val expected = Files.toByteArray(new File(path + expectedBinFileName))
    actual should be(expected)
  }

}