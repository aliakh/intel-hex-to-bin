package demo

import java.io.File
import java.nio.file.Paths

import com.google.common.base.Preconditions.checkArgument

/**
  * Intel HEX to binary memory image converter.
  */
class Converter(private val pad: Short) {

  private val memory: Memory = new Memory()

  private var highAddress = 0
  private var endOfFile = false

  def convert(hexFileName: String, binFileName: String) = {
    val path = Paths.get(hexFileName)

    val lines = java.nio.file.Files.readAllLines(path)
    for (i <- 0 until lines.size()) {
      val line = lines.get(i)
      convertLine(line)
    }

    writeFile(binFileName)
  }

  private def convertLine(line: String) = {
    val row = new Row(line)
    val record = new Record(row)
    println("Record: " + record)

    record.recordType match {
      case DATA => {
        checkArgument(!endOfFile)
        val lowAddress = record.address + record.byteCount
        println("lowAddress=" + Hex.to16BitHexString(lowAddress))
        updateMemory(record, lowAddress)
      }
      case EXTENDED_SEGMENT_ADDRESS => {
        checkArgument(!endOfFile)
        highAddress = record.getUnsigned16BitFromData(0) << 4
        println("highAddress=" + Hex.to16BitHexString(highAddress))
      }
      case EXTENDED_LINEAR_ADDRESS => {
        checkArgument(!endOfFile)
        highAddress = record.getUnsigned16BitFromData(0) << 16
        println("highAddress=" + Hex.to32BitHexString(highAddress))
      }
      case END_OF_FILE => {
        endOfFile = true
      }
      case _ => throw new IllegalArgumentException("Unknown record type: " + record)
    }
  }

  private def updateMemory(record: Record, lowAddress: Int) = {
    memory.expand(highAddress + lowAddress, pad)

    val data = record.data
    for (i <- data.indices) {
      memory.set(highAddress + record.address + i, data(i))
    }
  }

  private def writeFile(fileName: String) = {
    val fromArray = memory.toArray
    val toFile = new File(fileName)
    com.google.common.io.Files.write(fromArray, toFile)
  }

}
