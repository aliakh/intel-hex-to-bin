package demo

import com.google.common.base.Preconditions.checkArgument

/**
  * Main application object.
  */
object IntelHexToBin {

  def main(args: Array[String]): Unit = {
    checkArgument(args.length != 0)

    val hexFileName = args(0)
    val binFileName = FileName.getBinFileName(args(0))

    val converter = new Converter(0xFF.toShort)
    converter.convert(hexFileName, binFileName)
  }

}
