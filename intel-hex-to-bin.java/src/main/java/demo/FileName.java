package demo;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Converter from Intel HEX file name to binary file name.
 */
public interface FileName {

    static String getBinFileName(String hexFileName) {
        int i = hexFileName.lastIndexOf(".");
        checkArgument(i != 0, "There is no name in the file name: " + hexFileName);
        checkArgument(i > 0, "There is no extension in the file name: " + hexFileName);

        String extension = hexFileName.substring(i + 1, hexFileName.length());
        checkArgument("hex".equalsIgnoreCase(extension), "Wrong extension for the Intel HEX file: " + hexFileName);

        return hexFileName.substring(0, i) + ".bin";
    }

}
