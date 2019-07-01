package bitwise;

import org.junit.Test;

public class BitOperations {

    @Test
    /**
     * The AND operation takes two bits and returns 1 if both bits are 1. Otherwise, it returns 0.
     *
     * 1 & 1  →  1
     * 1 & 0  →  0
     * 0 & 1  →  0
     * 0 & 0  →  0
     *
     * x & 05 = 0
     * x & 15 = X
     * x & x = x
     */
    public void bitwiseAND() {
        System.out.println(5 & 6);   // gives 4

        // at the bit level:
        //     0101  (5)
        //   & 0110  (6)
        //   = 0100  (4)

    }

    @Test
    /**
     * Returns 1 if either of the bits are 1. Otherwise, it returns 0.
     */
    public void bitwiseOR(){

    }

    @Test
    /**
     * Returns 1 only if one but not both of the bits is 1.
     * Otherwise, it returns 0. Only one hand fits the jar.
     *    1 ^ 1 → 0
     *    1 ^ 0 → 1
     *    0 ^ 1 → 1
     *    0 ^ 0 → 0
     *
     *    X ^ 0s = X
     *    X ^ 1s = ~x
     *    X ^ Xs = 0
     */
    public void bitwiseXOR(){
        System.out.println(5 ^ 6); // gives 3


        //# At the bit level:
        //   0101 (5)
        // ^ 0110 (6)
        // = 0011 (3)


    }

    public void encryt(){
        int[] bytes = {1001};
        byte key = (byte) 9;
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = bytes[i] ^ key;
        }
    }
}

