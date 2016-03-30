package demo

/**
 * Memory image.
 */
class Memory {

    private var source = ShortArray(0)

    fun expand(address: Int, pad: Short) {
        val source2 = ShortArray(address)

        System.arraycopy(source, 0, source2, 0, source.size)

        for (i in source.size..address - 1) {
            source2[i] = pad
        }

        source = source2
    }

    fun set(index: Int, value: Short) {
        source[index] = value
    }

    fun toArray(): ByteArray {
        val target = ByteArray(source.size)
        for (i in source.indices) {
            target[i] = source[i].toByte()
        }
        return target
    }

    fun size(): Int {
        return source.size
    }

}
