package demo;

import com.google.common.io.Files;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link Converter}
 */
public class ConverterTest {

    @Test
    public void convertI8HexTest() throws IOException {
        convert((short) 0x00, "i8hex.1.hex", "i8hex_1.pad_00.actual.bin", "i8hex.1.expected.bin");
        convert((short) 0xFF, "i8hex.1.hex", "i8hex_1.pad_ff.actual.bin", "i8hex.1.expected.bin");

        convert((short) 0x00, "i8hex.2.hex", "i8hex_2.pad_00.actual.bin", "i8hex.2.pad_00.expected.bin");
        convert((short) 0xFF, "i8hex.2.hex", "i8hex_2.pad_ff.actual.bin", "i8hex.2.pad_ff.expected.bin");
    }

    @Test
    public void convertI16HexTest() throws IOException {
        convert((short) 0x00, "i16hex.1.hex", "i16hex_1.pad_00.actual.bin", "i16hex.1.expected.bin");
        convert((short) 0xFF, "i16hex.1.hex", "i16hex_1.pad_ff.actual.bin", "i16hex.1.expected.bin");

        convert((short) 0x00, "i16hex.2.hex", "i16hex_2.pad_00.actual.bin", "i16hex.2.pad_00.expected.bin");
        convert((short) 0xFF, "i16hex.2.hex", "i16hex_2.pad_ff.actual.bin", "i16hex.2.pad_ff.expected.bin");
    }

    @Test
    public void convertI32HexTest() throws IOException {
        convert((short) 0x00, "i32hex.hex", "i32hex.pad_00.actual.bin", "i32hex.pad_00.expected.bin");
        convert((short) 0xFF, "i32hex.hex", "i32hex.pad_ff.actual.bin", "i32hex.pad_ff.expected.bin");
    }

    private void convert(short pad, String sourceHexFileName, String actualBinFileName, String expectedBinFileName) throws IOException {
        final String separator = File.separator;
        final String currentFolderName = new File(".").getCanonicalPath();
        final String path = currentFolderName + separator + "target" + separator + "test-classes" + separator;

        String hexFileName = path + sourceHexFileName;
        String binFileName = path + actualBinFileName;

        Converter converter = new Converter(pad);
        converter.convert(hexFileName, binFileName);

        byte[] actual = Files.toByteArray(new File(path + actualBinFileName));
        byte[] expected = Files.toByteArray(new File(path + expectedBinFileName));
        assertEquals(actual, expected);
    }

}
