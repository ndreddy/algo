package algo.mathpuzzle;

public class ReverseDigits {

    static int reverseDigits(int n) {

        int m, r = 0; // mod, reversed number
        while (n > 0) {
            m = n % 10;
            r = r * 10 + m;
            n = n / 10;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println("Reverse = " + reverseDigits(123));
    }

}
