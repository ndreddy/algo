package algo.mathpuzzle;

/**
 * Input : 153
 * Output : Yes
 * 153 is an Armstrong number.
 * 1*1*1 + 5*5*5 + 3*3*3 = 153
 *
 * The idea is to first count number digits (or find order). Let the number of digits be n.
 * For every digit r in input number x, compute r^n.
 * If sum of all such values is equal to n, then return true, else false.
 */
public class ArmstrongNumber {

    int kthArmstrongNo(int k) {

        int count=0;
        for (int i = 0; i < Integer.MIN_VALUE; i++) {
            int n =k;
            int m, d, sum = 0; // mod, digits, sum

            // Find total digits in n
            d = (int) Math.log10(n);

            // Calculate sum of power of digits
            while (n>0){
                m = n%10;

            }

        }
        return 0;
    }
}

