package demo;

import com.google.common.primitives.UnsignedInts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Intel HEX raw record, e.g. ':00000001FF'.
 */
final public class Row {

    private final static Pattern LINE_PATTERN = Pattern.compile("^[0-9A-Fa-f]+$");

    private String line;

    public Row(String line) {
        checkArgument(line.indexOf(':') != -1, "Line must start with a colon: " + line);
        line = line.substring(1);

        Matcher matcher = LINE_PATTERN.matcher(line);
        checkArgument(matcher.matches(), "Line must contain only hexadecimal chars: " + line);
        checkArgument(line.length() % 2 == 0, "Line must contain even number of hexadecimal chars: " + line);

        this.line = line;
    }

    public short getUnsigned8Bit() {
        checkArgument(line.length() >= 2);

        short value = (short) UnsignedInts.parseUnsignedInt(line.substring(0, 2).toUpperCase(), 16);
        line = line.substring(2);
        return value;
    }

    public int getUnsigned16Bit() {
        checkArgument(line.length() >= 4);

        return (getUnsigned8Bit() << 8) + getUnsigned8Bit();
    }

    public boolean isEmpty() {
        return line.isEmpty();
    }

}
