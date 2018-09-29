package algo.recursion;

public class FibonacciB {
    public static int fib(int n) {
        int [] memo = new int[n+1];
        return fib(n, memo);
    }

    public static int fib(int n, int[] memo) {
        if (n == 0 || n == 1) return n;

        if (memo[n] == 0) {
            memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        }
        return memo[n];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int max = 100; // Make this as big as you want! (Though you'll exceed the bounds of a long around 46)
        int trials = 10; // Run code multiple times to compute average time.
        double[] times = new double[max]; // Store times

        for (int j = 0; j < trials; j++) { // Run this 10 times to compute
            for (int i = 0; i < max; i++) {
                long start = System.currentTimeMillis();
                System.out.println(fib(i));
                long end = System.currentTimeMillis();
                long time = end - start;
                times[i] += time;
            }
        }

        for (int j = 0; j < max; j++) {
            //System.out.println(j + ": " + times[j] / trials + "ms");
        }
    }

}