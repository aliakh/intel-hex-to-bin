package demo;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Converters from numbers to hexadecimal strings.
 */
public interface Hex {

    static String to8BitHexString(short value) {
        checkArgument((value >= 0) && (value <= 0xFF));
        return String.format("0x%02X", value);
    }

    static String to16BitHexString(int value) {
        checkArgument((value >= 0) && (value <= 0xFFFF));
        return String.format("0x%04X", value);
    }

    static String to32BitHexString(int value) {
        checkArgument(value >= 0);
        return String.format("0x%04X", value);
    }

    static String to8BitHexString(short[] values) {
        StringBuilder s = new StringBuilder();
        s.append('[');
        for (int i = 0; i < values.length; i++) {
            s.append(to8BitHexString(values[i]));
            if (i < values.length - 1) {
                s.append(",");
            }
        }
        return s.append(']').toString();
    }

}
