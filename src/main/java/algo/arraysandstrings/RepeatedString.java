package algo.arraysandstrings;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RepeatedString {

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        if (!s.contains("a")) return 0;
        long size = s.length();
        if (size == 1) return n;

        StringBuilder sb = new StringBuilder(s);
        long repeats = n / size;
        while (sb.length() < n) {
            sb.append(s);
        }

        long count = 0;
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) == 'a') count++;
        }
        System.out.println("Result = " + count);
        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\github\\algo\\target\\output.txt"));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);


        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
