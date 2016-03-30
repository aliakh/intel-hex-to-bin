package demo

import com.google.common.base.Preconditions.checkArgument
import com.google.common.primitives.UnsignedInts
import java.util.regex.Pattern

/**
 * Intel HEX raw record, e.g. ':00000001FF'.
 */
class Row(private var line: String) {

    private final val LINE_PATTERN = Pattern.compile("^[0-9A-Fa-f]+$")

    init {
        checkArgument(line.indexOf(':') != -1, "Line must start with a colon: " + line)
        line = line.substring(1)

        val matcher = LINE_PATTERN.matcher(line)
        checkArgument(matcher.matches(), "Line must contain only hexadecimal chars: " + line)
        checkArgument(line.length % 2 == 0, "Line must contain even number of hexadecimal chars: " + line)
    }

    fun getUnsigned8Bit(): Short {
        checkArgument(line.length >= 2)

        val value = UnsignedInts.parseUnsignedInt(line.substring(0, 2).toUpperCase(), 16).toShort()
        line = line.substring(2)
        return value
    }

    fun getUnsigned16Bit(): Int {
        checkArgument(line.length >= 4)

        return (getUnsigned8Bit().toInt() shl 8) + getUnsigned8Bit()
    }

    fun isEmpty(): Boolean {
        return line.isEmpty()
    }

}
