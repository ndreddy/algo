package bitwise;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 *  All numeric data longer than 1 byte are sent in order of most significant byte to
 *  least significant byte. This is the canonical network byte order defined by TCP/IP standards.
 *
 *  /*You can not shift a byte in java, you can only shift an int or a long.
 *  So the byte will undergo promotion first, e.g.
 *
 * 00101011 -> 00000000000000000000000000101011
 * or
 * 11010100 -> 11111111111111111111111111010100
 *
 * Now, x >> N means (if you view it as a string of binary digits):
 *
 * The rightmost N bits are discarded
 * The leftmost bit is replicated as many times as necessary to pad the result to the original size (32 or 64 bits), e.g.
 * 00000000000000000000000000101011 >> 2 -> 00000000000000000000000000001010
 *
 * 11111111111111111111111111010100 >> 2 -> 11111111111111111111111111110101
 *
 * **************************************
 * Binary    = 11000000 10101000 00000001 00000010
 * IpAddress = 192      168      1        2
 *
 * (ipAddress>>24)
 *             -------------------------->24
 * Binary    = 00000000 00000000 00000000 11000000
 *
 * (ipAddress>>24) & 0xFF
 * Binary    = 00000000 00000000 00000000 11000000
 * & 0xFF    = 00000000 00000000 00000000 11111111
 * Result    = 00000000 00000000 00000000 11000000 = 192 (2^7 + 2^6)
 * In this case, the 0xFF is optional.
 *
 * (ipAddress>>16) & 0xFF
 * Binary  = 00000000 00000000 11000000 10101000 = 49320 (2^14 + 2^15 + 2^7 + 2^5 + 2^3)
 * & 0xFF  = 00000000 00000000 00000000 11111111
 * Result  = 00000000 00000000 00000000 10101000 = 168
 *
 * (ipAddress>>8) & 0xFF
 * Binary  = 00000000 11000000 10101000 00000001 = 12625921
 * & 0xFF  = 00000000 00000000 00000000 11111111
 * Result  = 00000000 00000000 00000000 00000001 = 1
 *
 * (ipAddress) & 0xFF
 * Binary  = 11000000 10101000 00000001 00000010 = 3232235778
 * & 0xFF  = 00000000 00000000 00000000 11111111
 * Result  = 00000000 00000000 00000000 00000010 = 2
 *
 */
public class ByteShift {


    /**
     * Writes int value to the output stream.
     *
     *
     * @param os Output stream
     * @param n  value to be written to outputstream.
     * @throws IOException
     */
    static void writeInt(OutputStream os, int n) throws IOException {
        os.write((n >>> 24) & 0xff); // & 0xFF is optional.
        os.write((n >>> 16) & 0xff); // & 0xFF, gets the last 8 bits.
        os.write((n >>> 8) & 0xff);
        os.write((n >>> 0) & 0xff);
    }

    static void writeShort(OutputStream os, int n) throws IOException {
        os.write((byte) n >>> 8 & 0xff);
        os.write((byte) n >>> 0 & 0xff);
    }

    static void writeFloatingInt(OutputStream os, int tag, int value) throws IOException {
        if (value <= 0) return;
        writeShort(os, tag);
        writeShort(os, 4);
        writeInt(os, value);
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
        os.write((byte) 0); // Null terminator.
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

    public long ipAddressToLong(String ipAddress) {

        String[] addrArray = ipAddress.split("\\.");

        long num = 0;
        for (int i = 0; i < addrArray.length; i++) {

            int power = 3 - i;

            // 1. (192 % 256) * 256 pow 3
            // 2. (168 % 256) * 256 pow 2
            // 3. (2 % 256) * 256 pow 1
            // 4. (1 % 256) * 256 pow 0
            num += ((Integer.parseInt(addrArray[i]) % 256 * Math.pow(256, power)));

        }

        return num;
    }

    public String longToIpAddress(long i) {

        return ((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + (i & 0xFF);

    }

}
