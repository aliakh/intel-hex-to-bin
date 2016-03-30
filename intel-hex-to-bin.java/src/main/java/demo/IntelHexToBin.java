package demo;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Main application class.
 */
final public class IntelHexToBin {

    public static void main(String[] args) throws IOException {
        checkArgument(args.length != 0);

        String hexFileName = args[0];
        String binFileName = FileName.getBinFileName(args[0]);

        Converter converter = new Converter((byte) 0xFF);
        converter.convert(hexFileName, binFileName);
    }

}
