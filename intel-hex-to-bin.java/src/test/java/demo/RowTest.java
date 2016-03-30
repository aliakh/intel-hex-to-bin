package demo;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Unit tests for {@link Row}
 */
public class RowTest {

    @Test
    public void constructorTest() {
        Row row = new Row(":021234005678FF");

        assertFalse(row.isEmpty());
        assertEquals(row.getUnsigned8Bit(), (short) 0x02);
        assertFalse(row.isEmpty());
        assertEquals(row.getUnsigned16Bit(), (short) 0x1234);
        assertFalse(row.isEmpty());
        assertEquals(row.getUnsigned8Bit(), (short) 0x00);
        assertFalse(row.isEmpty());
        assertEquals(row.getUnsigned8Bit(), (short) 0x56);
        assertFalse(row.isEmpty());
        assertEquals(row.getUnsigned8Bit(), (short) 0x78);
        assertFalse(row.isEmpty());
        assertEquals(row.getUnsigned8Bit(), (short) 0xFF);
        assertTrue(row.isEmpty());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void noColonConstructorTest() {
        new Row("00000001FF");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void wrongCharsConstructorTest() {
        new Row(":XX");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void oddCharsNumberConstructorTest() {
        new Row(":0");
    }

    @Test
    public void getUnsigned8BitTest() {
        Row row = new Row(":01ab");

        assertFalse(row.isEmpty());
        assertEquals(row.getUnsigned8Bit(), (short) 0x01);
        assertEquals(row.getUnsigned8Bit(), (short) 0xAB);
        assertTrue(row.isEmpty());
    }

    @Test
    public void getUnsigned16BitTest() {
        Row row = new Row(":1234abcd");

        assertFalse(row.isEmpty());
        assertEquals(row.getUnsigned16Bit(), 0x1234);
        assertEquals(row.getUnsigned16Bit(), 0xABCD);
        assertTrue(row.isEmpty());
    }

}
