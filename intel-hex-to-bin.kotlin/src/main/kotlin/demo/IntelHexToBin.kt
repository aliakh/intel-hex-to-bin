package demo

import com.google.common.base.Preconditions.checkArgument
import java.io.IOException

/**
 * Main application object.
 */
object IntelHexToBin {

    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
        checkArgument(args.size != 0)

        val hexFileName = args[0]
        val binFileName = getBinFileName(args[0])

        val converter = Converter(0xFF.toShort())
        converter.convert(hexFileName, binFileName)
    }

}
