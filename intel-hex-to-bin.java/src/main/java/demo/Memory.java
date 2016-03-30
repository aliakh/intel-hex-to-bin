package demo;

/**
 * Memory image.
 */
final public class Memory {

    private short[] source = new short[0];

    public void expand(int address, short pad) {
        short[] source2 = new short[address];

        System.arraycopy(source, 0, source2, 0, source.length);

        for (int i = source.length; i < address; i++) {
            source2[i] = pad;
        }

        source = source2;
    }

    public void set(int index, short value) {
        source[index] = value;
    }

    public byte[] toArray() {
        byte[] target = new byte[source.length];
        for (int i = 0; i < source.length; i++) {
            target[i] = (byte) source[i];
        }
        return target;
    }

    public int size() {
        return source.length;
    }

}
