package bitwise;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class BitShifts {


    @Test
    public void sendInt(){
        int value = 1013125236; //the value we want to send

        byte firstByte = (byte) (value & 0x000000FF); //grab the first byte
        byte secondByte = (byte) ((value & 0x0000FF00) >> 8); //grab the second byte
        byte thirdByte = (byte) ((value & 0x00FF0000) >> 16);
        byte fourthByte = (byte) ((value & 0xFF000000) >> 24);

        byte [] valueArray = new byte[]{firstByte, secondByte, thirdByte, fourthByte};
        for (byte b : valueArray) {
            System.out.println("Value Array []= " + b);
        }
    }


    @Test
    public void leftShift() {
        //0010 << 1  →  0100
        System.out.println(2 << 1);

        //0010 << 2  →  1000
        // A single left shift multiplies a binary number by 2:
        // 0010 << 1  →  0100
        // 0010 is 2
        // 0100 is 4

    }


    @Test
    public void unsignedRightShift(){
        // x is stored using 32 bit 2's complement form.
        // Binary representation of -1 is all 1s (111..1)
        int x = -1;

        System.out.println(x>>>29);  // The value of 'x>>>29' is 00...0111
        System.out.println(x>>>30);  // The value of 'x>>>30' is 00...0011
        System.out.println(x>>>31);  // The value of 'x>>>31' is 00...0001
    }


    /**
     * Writes int value to the output stream.
     * All numeric data longer than 1 byte are sent in order of most significant byte to
     * least significant byte. This is the canonical network byte order defined by TCP/IP standards.
     *
     * @param os Output stream
     * @param n  value to be written to outputstream.
     * @throws IOException
     */
    static void writeInt(OutputStream os, int n) throws IOException {
        os.write((n >>> 24) & 0xff);
        os.write((n >>> 16) & 0xff);
        os.write((n >>> 8) & 0xff);
        os.write((n >>> 0) & 0xff);
    }

    static void writeShort(OutputStream os, int n) throws IOException {
        os.write((byte) n >>> 8 & 0xff);
        os.write((byte) n >>> 0 & 0xff);
    }

    static void writeFloatingInt(OutputStream os, int tag, int value) throws IOException {
        if(value <=0) return;
        writeShort(os,tag);
        writeShort(os,4);
        writeInt(os,value);
    }

    /**
     * Writes key, length and value to output stream.
     *
     * @param os    output stream.
     * @param tag   the key
     * @param value the value
     */
    static void writeFloatingString(OutputStream os, int tag, String value) throws IOException {
        if (value == null) return;
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        writeShort(os, tag);
        writeShort(os, bytes.length + 1); // Length of th floating field.
        if (bytes.length > 0) os.write(bytes);
        os.write((byte)0); // Null terminator.
    }

    static void putInt(byte[] b, int off, int val) {
        b[off + 3] = (byte) (val);
        b[off + 2] = (byte) (val >>> 8);
        b[off + 1] = (byte) (val >>> 16);
        b[off] = (byte) (val >>> 24);
    }


    static byte[] updateMsgLength(byte[] bytes) {
        int size = bytes.length;
        int msgLen = size - 8; // totalSize - headerLen
        putInt(bytes, 0, msgLen);
        return bytes;
    }
      /*You can not shift a byte in java, you can only shift an int or a long. So the byte will undergo promotion first, e.g.

00101011 -> 00000000000000000000000000101011

or

11010100 -> 11111111111111111111111111010100

Now, x >> N means (if you view it as a string of binary digits):

The rightmost N bits are discarded
The leftmost bit is replicated as many times as necessary to pad the result to the original size (32 or 64 bits), e.g.
00000000000000000000000000101011 >> 2 -> 00000000000000000000000000001010

11111111111111111111111111010100 >> 2 -> 11111111111111111111111111110101*/

}
