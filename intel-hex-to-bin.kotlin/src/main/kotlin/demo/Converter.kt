package demo

import com.google.common.base.Preconditions.checkArgument
import java.io.File
import java.io.IOException
import java.nio.file.Paths

/**
 * Intel HEX to binary memory image converter.
 */
class Converter(private val pad: Short) {

    private val memory: Memory

    private var highAddress = 0
    private var endOfFile = false

    init {
        this.memory = Memory()
    }

    @Throws(IOException::class)
    fun convert(hexFileName: String, binFileName: String) {
        val path = Paths.get(hexFileName)

        val lines = java.nio.file.Files.readAllLines(path)
        for (line in lines) {
            convertLine(line)
        }

        writeFile(binFileName)
    }

    private fun convertLine(line: String) {
        val row = Row(line)
        val record = Record(row)
        println("Record: " + record)

        when (record.recordType) {
            RecordType.DATA -> {
                checkArgument(!endOfFile)
                val lowAddress = record.address + record.byteCount
                println("lowAddress=" + lowAddress.to16BitHexString())
                updateMemory(record, lowAddress)
            }
            RecordType.EXTENDED_SEGMENT_ADDRESS -> {
                checkArgument(!endOfFile)
                highAddress = record.getUnsigned16BitFromData(0) shl 4
                println("highAddress=" + highAddress.to16BitHexString())
            }
            RecordType.EXTENDED_LINEAR_ADDRESS -> {
                checkArgument(!endOfFile)
                highAddress = record.getUnsigned16BitFromData(0) shl 16
                println("highAddress=" + highAddress.to32BitHexString())
            }
            RecordType.END_OF_FILE -> {
                endOfFile = true
            }
            else -> throw IllegalArgumentException("Unknown record type: " + record)
        }
    }

    private fun updateMemory(record: Record, lowAddress: Int) {
        memory.expand(highAddress + lowAddress, pad)

        val data = record.data
        for (i in data.indices) {
            memory.set(highAddress + record.address.toInt() + i, data[i])
        }
    }

    @Throws(IOException::class)
    private fun writeFile(fileName: String) {
        val fromArray = memory.toArray()
        val toFile = File(fileName)
        com.google.common.io.Files.write(fromArray, toFile)
    }

}
