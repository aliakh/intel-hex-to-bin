package demo

/**
  * Memory image.
  */
class Memory {

  private var source = Array[Short]()

  def expand(address: Int, pad: Short) = {
    val source2 = new Array[Short](address)

    System.arraycopy(source, 0, source2, 0, source.length)

    for (i <- source.length until address ) {
      source2(i) = pad
    }

    source = source2
  }

  def set(index: Int, value: Short) = {
    source(index) = value
  }

  def toArray: Array[Byte] = {
    val target = new Array[Byte](source.length)
    for (i <- source.indices) {
      target(i) = source(i).toByte
    }
    target
  }

  def size: Int = {
    source.length
  }

}
