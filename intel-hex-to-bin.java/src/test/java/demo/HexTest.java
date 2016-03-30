package demo;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link Hex}
 */
public class HexTest {

    @Test
    public void numberTo8BitHexStringTest() {
        assertEquals(Hex.to8BitHexString((short) 0x00), "0x00");
        assertEquals(Hex.to8BitHexString((short) 0x01), "0x01");

        assertEquals(Hex.to8BitHexString((short) 0x12), "0x12");
        assertEquals(Hex.to8BitHexString((short) 0xAB), "0xAB");
        assertEquals(Hex.to8BitHexString((short) 0xFF), "0xFF");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void numberTo8BitHexStringUnderflowTest() {
        Hex.to8BitHexString((short) -1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void numberTo8BitHexStringOverflowTest() {
        Hex.to8BitHexString((short) 0x100);
    }

    @Test
    public void numberTo16BitHexStringTest() {
        assertEquals(Hex.to16BitHexString(0x0000), "0x0000");
        assertEquals(Hex.to16BitHexString(0x0001), "0x0001");

        assertEquals(Hex.to16BitHexString(0x0012), "0x0012");
        assertEquals(Hex.to16BitHexString(0x00AB), "0x00AB");

        assertEquals(Hex.to16BitHexString(0x0123), "0x0123");
        assertEquals(Hex.to16BitHexString(0x0ABC), "0x0ABC");

        assertEquals(Hex.to16BitHexString(0x1234), "0x1234");
        assertEquals(Hex.to16BitHexString(0xABCD), "0xABCD");
        assertEquals(Hex.to16BitHexString(0xFFFF), "0xFFFF");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void numberTo16BitHexStringUnderflowTest() {
        Hex.to16BitHexString(-1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void numberTo16BitHexStringOverflowTest() {
        Hex.to16BitHexString(0x10000);
    }

    @Test
    public void arrayTo8BitHexStringTest() {
        assertEquals(Hex.to8BitHexString(new short[]{}), "[]");
        assertEquals(Hex.to8BitHexString(new short[]{0x12}), "[0x12]");
        assertEquals(Hex.to8BitHexString(new short[]{0x12, 0xAB}), "[0x12,0xAB]");
    }

}
