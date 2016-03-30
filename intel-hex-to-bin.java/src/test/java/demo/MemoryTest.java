package demo;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit tests for {@link Memory}
 */
public class MemoryTest {

    @Test
    public void memoryTest() {
        Memory memory = new Memory();

        assertEquals(memory.size(), 0);
        assertEquals(memory.toArray(), new byte[0]);

        memory.expand(2, (short) 0xFF);

        assertEquals(memory.size(), 2);
        assertEquals(memory.toArray(), new byte[]{(byte) 0xFF, (byte) 0xFF});

        memory.set(0, (short) 0x00);
        memory.set(1, (short) 0x01);

        assertEquals(memory.size(), 2);
        assertEquals(memory.toArray(), new byte[]{0x00, 0x01});

        memory.expand(4, (short) 0x00);

        assertEquals(memory.size(), 4);
        assertEquals(memory.toArray(), new byte[]{0x00, 0x01, 0x00, 0x00});

        memory.set(2, (short) 0x02);
        memory.set(3, (short) 0x03);

        assertEquals(memory.size(), 4);
        assertEquals(memory.toArray(), new byte[]{0x00, 0x01, 0x02, 0x03});
    }

}
