package demo

/**
 * Converters to hexadecimal strings.
 */

fun Short.to8BitHexString(): String {
    require(this in 0..0xFF)
    return "0x%02X".format(this)
}

fun Int.to16BitHexString(): String {
    require(this in 0..0xFFFF)
    return "0x%04X".format(this)
}

fun Int.to32BitHexString(): String {
    require(this >= 0)
    return "0x%04X".format(this)
}

fun ShortArray.to8BitHexString(): String {
    val s = StringBuilder()
    s.append('[')
    for ((i, value) in this.withIndex()) {
        s.append(value.to8BitHexString())
        if (i < this.size - 1) {
            s.append(",")
        }
    }
    return s.append(']').toString()
}
