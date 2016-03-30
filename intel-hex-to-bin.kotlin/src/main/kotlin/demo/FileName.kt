package demo

import com.google.common.base.Preconditions.checkArgument

/**
 * Converter from Intel HEX file name to binary file name.
 */
fun getBinFileName(hexFileName: String): String {
    val i = hexFileName.lastIndexOf(".")
    checkArgument(i != 0, "There is no name in the file name: " + hexFileName)
    checkArgument(i > 0, "There is no extension in the file name: " + hexFileName)

    val extension = hexFileName.substring(i + 1, hexFileName.length)
    checkArgument("hex".equals(extension, ignoreCase = true), "Wrong extension for the Intel HEX file: " + hexFileName)

    return hexFileName.substring(0, i) + ".bin"
}
