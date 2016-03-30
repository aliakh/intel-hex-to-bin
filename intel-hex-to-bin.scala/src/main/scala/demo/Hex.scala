package demo

/**
  * Converters to hexadecimal strings.
  */
object Hex {

  def to8BitHexString(value: Short): String = {
    require((0 to 0xFF).contains(value))
    "0x%02X".format(value)
  }

  def to16BitHexString(value: Int): String = {
    require((0 to 0xFFFF).contains(value))
    "0x%04X".format(value)
  }

  def to32BitHexString(value: Int): String = {
    require(value >= 0)
    "0x%04X".format(value)
  }

  def to8BitHexString(values: Array[Short]): String = {
    val s = new StringBuilder()
    s.append('[')
    for (i <- values.indices) {
      val value = values(i)
      s.append(Hex.to8BitHexString(value))
      if (i < values.length - 1) {
        s.append(",")
      }
    }
    s.append(']').toString()
  }

}
