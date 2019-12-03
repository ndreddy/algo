package algo.recursion;

/**
 * Write a method to generate the nth Fibonacci number. 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, …….. the sequence
 * Fn of Fn = Fn-1 + Fn-2 with seed values F0 = 0 and F1 = 1.
 * <p>
 * Write a function int fib(int n) that returns Fn. For example, if n = 0, then fib() should return 0. If n = 1, then it
 * should return 1. For n > 1, it should return Fn-1 + Fn-2.
 * <p>
 * For n = 9
 * Output:34
 */
public class Fibonacchi {

    //If we call fib(5), how many calls do we make in total?
    //Try drawing it out as a tree where each call has two child calls, unless it's a base case.
    //We can notice this is a binary tree  whose height is n, which means the total number of nodes is O(2^n)

    /**
     * total runtime is O(2^n) That's an "exponential time cost,"
     *
     * @param n
     * @return
     */
    public static int fibA(int n) {

//        if (n <= 1) return n;

        // edge cases:
        if (n < 0) {
            throw new IllegalArgumentException("Index was negative. No such thing as a negative index in a series.");
        } else if (n == 0 || n == 1) {
            return n;
        }

        return fibA(n - 1) + fibA(n - 2);
    }

    /********* FibB *******************
     *
     * Method 2 ( Use Dynamic Programming )
     We can avoid the repeated work done is the method 1 by storing the Fibonacci numbers calculated so far.
     */

//Memoization ensures that a function doesn't run for the same inputs more than once
// by keeping a record of the results for the given inputs (usually in a hash map).

    /********* Fibc *******************
     * Time Complexity: O(n)
     * Extra Space: O(n)
     */

    public static int fibB(int n) {
        if (n <= 1) return n;

        int[] memo = new int[n];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i < n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n - 1] + memo[n - 2];
    }


    /********* FibC *******************

     */

    /**
     * Time : O(n)
     * Space: O(1)
     *
     * @param n
     * @return
     */
    public static int fibC(int n) {
        if (n == 0) return 0;
        int a = 0;
        int b = 1;
        int c;
        for (int i = 2; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return a + b;
    }


    public static void main(String args[]) {
        int n = 6;
        System.out.println("fibA - " + fibA(n));
        System.out.println("fibB - " + fibB(n));
        System.out.println("fibC - " + fibC(n));
    }

}
