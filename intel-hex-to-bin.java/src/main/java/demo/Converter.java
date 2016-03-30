package demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Intel HEX to binary memory image converter.
 */
final public class Converter {

    final private Memory memory;
    final private short pad;

    private int highAddress = 0;
    private boolean endOfFile = false;

    public Converter(short pad) {
        this.memory = new Memory();
        this.pad = pad;
    }

    public void convert(String hexFileName, String binFileName) throws IOException {
        Path path = Paths.get(hexFileName);

        List<String> lines = java.nio.file.Files.readAllLines(path);
        for (String line : lines) {
            convertLine(line);
        }

        writeFile(binFileName);
    }

    private void convertLine(String line) {
        Row row = new Row(line);
        Record record = new Record(row);
        System.out.println("Record: " + record);

        switch (record.getRecordType()) {
            case DATA: {
                checkArgument(!endOfFile);
                int lowAddress = record.getAddress() + record.getByteCount();
                System.out.println("lowAddress=" + Hex.to16BitHexString(lowAddress));
                updateMemory(record, lowAddress);
                break;
            }
            case EXTENDED_SEGMENT_ADDRESS: {
                checkArgument(!endOfFile);
                highAddress = record.getUnsigned16BitFromData(0) << 4;
                System.out.println("highAddress=" + Hex.to16BitHexString(highAddress));
                break;
            }
            case EXTENDED_LINEAR_ADDRESS: {
                checkArgument(!endOfFile);
                highAddress = record.getUnsigned16BitFromData(0) << 16;
                System.out.println("highAddress=" + Hex.to32BitHexString(highAddress));
                break;
            }
            case END_OF_FILE: {
                endOfFile = true;
                break;
            }
            default: throw new IllegalArgumentException("Unknown record type: " + record);
        }
    }

    private void updateMemory(Record record, int lowAddress) {
        memory.expand(highAddress + lowAddress, pad);

        short[] data = record.getData();
        for (int i = 0; i < data.length; i++) {
            memory.set(highAddress + record.getAddress() + i, data[i]);
        }
    }

    private void writeFile(String fileName) throws IOException {
        byte[] fromArray = memory.toArray();
        File toFile = new File(fileName);
        com.google.common.io.Files.write(fromArray, toFile);
    }

}
