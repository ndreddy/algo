package bitwise;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class BitShifts {


    @Test
    public void testGetBit() {
        System.out.println(getBit(5, 3));

    }

    /**
     * Checks if its ith bit is set using a left shift with an and operation.
     * <p>
     * First, we'll  the set bit is at the index we want to test.
     * 1 << 0 → 0000 0001 // for the 0th bit
     * 1 << 1 → 0000 0010 // for the 1st bit
     * 1 << 2 → 0000 0100 // for the 2nd bit
     *
     * Then, we'll & the shifted 1 with the value we're testing.
     * If the result is zero, then the bit isn't set; otherwise, it is.
     *
     * @param n number
     * @param i index
     * @return true if ith bit set to true.
     * <p>
     * ...
     * 1 << 7 → 1000 0000 // for the 7th bit
     */
    boolean getBit(int n, int i) {

        //Step 1: Create a mask by taking 1 and shifting it left until index
        int mask = 1 << i;

        // Step 2: Perform AND with the number.
        // By performing an AND with n, we clear all bits other than the bit at bit i
        return (n & mask) != 0; // in one-liner ((n & (1 << i)) != 0);
    }


    /**
     *
     * @param n
     * @param i
     * @return
     */
    int setBit(int n, int i){
        int mask= 1<<1;
        return n | i;
    }


    /**
     * What do you think these functions would do on parameters x = -93242 and count 40?
     * <p>
     * With the arithmetic shift, we would get -1 because we are shifting
     * a one into the most significant bit
     * repeatedly. A sequence of allis in a (signed) integer represents -1.
     *
     * @param x
     * @param count
     * @return
     */
    int repeatedArithmeticRighShift(int x, int count) {
        for (int i = 0; i < count; i++) {
            x >>= 1;  // Arithmetic right shift by 1
        }
        return x;
    }

    /**
     * With the logical shift, we would get 0 because we are shifting a zero
     * into the most significant bit repeatedly.
     *
     * @param x
     * @param count
     * @return
     */
    int repeatedLogicalRightShift(int x, int count) {
        for (int i = 0; i < count; i++) {
            x >>>= 1; // Logical right shift by 1
        }
        return x;
    }

    @Test
    public void sendInt() {
        int value = 1013125236; //the value we want to send

        byte firstByte = (byte) (value & 0x000000FF); //grab the first byte
        byte secondByte = (byte) ((value & 0x0000FF00) >> 8); //grab the second byte
        byte thirdByte = (byte) ((value & 0x00FF0000) >> 16);
        byte fourthByte = (byte) ((value & 0xFF000000) >> 24);

        byte[] valueArray = new byte[]{firstByte, secondByte, thirdByte, fourthByte};
        for (byte b : valueArray) {
            System.out.println("Value Array []= " + b);
        }
    }

    public static final int toUInt(byte[] data) {
        if (data == null || data.length < 4) return -1;
        int ch1 = data[0];
        int ch2 = data[1];
        int ch3 = data[2];
        int ch4 = data[3];
        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
    }


    public static int toUnsignedInt(byte[] data) {
        long result = -1;
        for (int i = 0; i < data.length; i++) {
            result += data[i] << 8 * (data.length-1-i);
        }
        return (int)result;
    }


    @Test
    public void leftShift() {
        //0000...0010 << 1  →  0100
        // A single left shift multiplies a binary number by 2:
        System.out.println(2 << 1); // prints 4

        //0000...0010 << 2  →  1000
        System.out.println(2 << 2);// prints 8


    }


    @Test
    public void unsignedRightShift() {
        // x is stored using 32 bit 2's complement form.
        // Binary representation of -1 is all 1s (111..1)
        int x = -1; // 11111111 11111111 11111111 11111111 in 2's complement

        System.out.println(x >>> 1);  // is 01111111...11111111 = 2147483647
        System.out.println(x >>> 29);  // The value of 'x>>>29' is 00...0111
        System.out.println(x >>> 30);  // The value of 'x>>>30' is 00...0011
        System.out.println(x >>> 31);  // The value of 'x>>>31' is 00...0001
        System.out.println(x >>> 32);  // shifts by 32 % 32 = 0 positions, so no effect.
        System.out.println(x >>> 33);  // The value of 'x>>>31' is 00...0001
    }



}
