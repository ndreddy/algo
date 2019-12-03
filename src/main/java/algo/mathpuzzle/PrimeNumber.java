package algo.mathpuzzle;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimeNumber {

    public static boolean isPrime(final int n) {

        return (n > 1) &&
                IntStream.range(2, n)
                        .noneMatch(i -> n % i == 0);

    }

    public static boolean isPrime1(int candidate) {
        return IntStream.rangeClosed(2, candidate-1)
                .limit((long) Math.floor(Math.sqrt((double) candidate)) - 1)
                .noneMatch(i -> candidate % i == 0);
    }

    // Finds the sum of sqrt of first k prime numbers starting from n
    public static double compute(int n, int k) {
        return Stream.iterate(n, e -> e + 1)
                .filter(PrimeNumber::isPrime)
                .limit(k)
                .map(Math::sqrt)
                .reduce(0.0, Double::sum);

    }

    // imperative style, saying how
    // Mostly sequential
    // Mutability, hard to make code concurrent
    public static boolean isPrimeNum(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return n > 1;
    }


    public static void main(String[] args) {

        System.out.println("is Prime? 5 " + isPrime(5));
        System.out.println("compute: " + compute(5, 3) );
    }
}
