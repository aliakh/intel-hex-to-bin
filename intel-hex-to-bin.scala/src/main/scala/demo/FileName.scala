package demo

import com.google.common.base.Preconditions.checkArgument

/**
  * Converter from Intel HEX file name to binary file name.
  */
object FileName {

  def getBinFileName(hexFileName: String): String = {
    val i = hexFileName.lastIndexOf(".")
    checkArgument(i != 0, ("There is no name in the file name: " + hexFileName).asInstanceOf[Any])
    checkArgument(i > 0, ("There is no extension in the file name: " + hexFileName).asInstanceOf[Any])

    val extension = hexFileName.substring(i + 1, hexFileName.length)
    checkArgument("hex".equalsIgnoreCase(extension), ("Wrong extension for the Intel HEX file: " + hexFileName).asInstanceOf[Any])

    hexFileName.substring(0, i) + ".bin"
  }

}
