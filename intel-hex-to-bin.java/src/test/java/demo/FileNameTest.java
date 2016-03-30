package demo;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link FileName}
 */
public class FileNameTest {

    @Test
    public void getBinFileNameTest() {
        assertEquals(FileName.getBinFileName("test.hex"), "test.bin");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void noNameFailureTest() {
        FileName.getBinFileName(".hex");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void noExtensionFailureTest() {
        FileName.getBinFileName("test");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void emptyExtensionFailureTest() {
        FileName.getBinFileName("test.");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void wrongExtensionFailureTest() {
        FileName.getBinFileName("test.txt");
    }

}
