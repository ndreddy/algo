package algo.recursion;

import java.util.Scanner;


// Write a function to reverse a string using recursion.
public class StringReverseRecursion {


    /**
     * Explanation:
     * Recursive function (reverse) takes string pointer (str) as input and
     * calls itself with next location to passed pointer (str+1).
     * Recursion continues this way, when pointer reaches ‘\0’, all functions accumulated in stack print
     * char at passed location (str) and return one by one.
     *
     * <p>
     * Time Complexity: O(n)
     *
     * @param str
     * @return
     */
    static String reverse(String str) {

        /*
            Constraints
            1. Not null
            2. Size should be 2 or move
         */
        if ((str == null) || (str.length() <= 1)) {
            return str;
        }

        return reverse(str.substring(1)) + str.charAt(0);
//        System.out.print(str.charAt(str.length() - 1));
//        return reverse(str.substring(0, str.length() - 1));

    }

    public static String reverseString(String str) {
        if (str.isEmpty())
            return str;
        //Calling Function Recursively
        return reverseString(str.substring(1)) + str.charAt(0);
    }


    public static void main(String[] args) {
        String str;
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        scanner.close();
        String reversed = reverse(str);
        System.out.println("The reversed string is: " + reversed);
    }
}