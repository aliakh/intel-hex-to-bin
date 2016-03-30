package demo

import org.scalatest._

/**
  * Unit tests for {@link Memory}
  */
class MemorySpec extends FlatSpec with Matchers {

  it should "pass memoryTest" in {
    val memory = new Memory()

    memory.size should be(0)
    memory.toArray should be(Array[Byte]())

    memory.expand(2, 0xFF.toShort)

    memory.size should be(2)
    memory.toArray should be(Array(0xFF.toByte, 0xFF.toByte))

    memory.set(0, 0x00.toShort)
    memory.set(1, 0x01.toShort)

    memory.size should be(2)
    memory.toArray should be(Array(0x00.toByte, 0x01.toByte))

    memory.expand(4, 0x00.toShort)

    memory.size should be(4)
    memory.toArray should be(Array(0x00.toByte, 0x01.toByte, 0x00.toByte, 0x00.toByte))

    memory.set(2, 0x02.toShort)
    memory.set(3, 0x03.toShort)

    memory.size should be(4)
    memory.toArray should be(Array(0x00.toByte, 0x01.toByte, 0x02.toByte, 0x03.toByte))
  }

}
