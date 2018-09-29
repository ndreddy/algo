package algo.arraysandstrings;

import org.junit.Test;

import java.util.Arrays;

/**
 * Write a method reverseWords() that takes a message as an array of characters and
 * reverses the order of the words in-place
 * <p>
 * For example:
 * char[] message = { 'c', 'a', 'k', 'e', ' ','p', 'o', 'u', 'n', 'd', ' ','s', 't', 'e', 'a', 'l' };
 * <p>
 * reverseWords(message);
 * <p>
 * System.out.println(message);
 * // prints: "steal pound cake"
 */
public class ReverseWords {


    public static void reverseWords(char[] arr) {

        /*
        first we reverse all the characters in the entire message array
        this gives us the right word order
        but with each word backwards
        */
        reverseChars(arr, 0, arr.length - 1);

        // now we'll make the words forward again
        // by reversing each word's characters

        // we hold the index of the *start* of the current word
        // as we look for the *end* of the current word
        int j = 0; //Current word start index
        for (int i = 0; i <= arr.length; i++) {

            // found the end of the current word!
            if (i == arr.length || arr[i] == ' ') {

                // if we haven't exhausted the array, our
                // next word's start is one character ahead
                reverseChars(arr, j, i - 1);
                j = i + 1;
            }
        }

        return;
    }

    private static void reverseChars(char[] arr, int leftIndex, int rightIndex) {

        // walk towards the middle, from both sides
        while (leftIndex < rightIndex) {

            // swap the left char and right char
            char temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;
            leftIndex++;
            rightIndex--;
        }
    }

    @Test
    public void testReverseWords() {
        char[] message = { 'c', 'a', 'k', 'e', ' ','p', 'o', 'u', 'n', 'd', ' ','s', 't', 'e', 'a', 'l' };
        System.out.println("Input Words - " + Arrays.toString(message));
        reverseWords(message);
        System.out.println("Reversed Words - " + Arrays.toString(message));
    }
}
