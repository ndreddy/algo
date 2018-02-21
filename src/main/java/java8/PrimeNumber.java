package java8;

import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class PrimeNumber {

    public static boolean isPrime(final int n) {
        IntPredicate isdiv =  i -> n % i == 0;

        // Declarative,  what
        // parallel
        // immutability
        return (n > 1) &&
                IntStream.range(2, n)
                        .noneMatch(isdiv);

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
    }
}
