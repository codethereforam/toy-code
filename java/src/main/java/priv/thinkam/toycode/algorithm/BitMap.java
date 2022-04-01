package priv.thinkam.toycode.algorithm;

public class BitMap { // Java 中 char 类型占 16bit，也即是 2 个字节
    private final char[] bytes;
    private final int nbits;


    public BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits / 16 + 1];
    }


    public void set(int k) {
        if (k > nbits) return;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        bytes[byteIndex] |= (1 << bitIndex);

    }


    public boolean get(int k) {
        if (k > nbits) return false;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(31);
        bitMap.set(2);
        System.out.println(bitMap.get(2));
    }
}