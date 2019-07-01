package bitwise;

/**
 * The unary bitwise complement operator "~" inverts a bit pattern; it can be applied to any of the integral types,
 * making every "0" a "1" and every "1" a "0". For example, a byte contains 8 bits; applying this operator to a value
 * whose bit pattern is "00000000" would change its pattern to "11111111".
 * <p>
 * The signed left shift operator "<<" shifts a bit pattern to the left, and
 * The signed right shift operator ">>" shifts a bit pattern to the right.
 * <p>
 * The unsigned right shift operator ">>>" shifts a zero into the leftmost position, while the leftmost position after ">>" depends on sign extension.
 * <p>
 * The bitwise & operator performs a bitwise AND operation.
 * <p>
 * The bitwise ^ operator performs a bitwise exclusive OR operation.
 * <p>
 * The bitwise | operator performs a bitwise inclusive OR operation.
 */
class BitDemo {

    public static void main(String[] args) {
        int bitmask = 0x000F;
        int val = 0x2222;
        // prints "2"
        System.out.println(val & bitmask);
    }
}