package algo.string;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ndreddy on 02/03/17. How can we tell if any permutation of a string is a palindrome?
 * <p>
 * Well, how would we check that a string is a palindrome? We could use the somewhat-common "keep two pointers" pattern.
 * We'd start a pointer at the beginning of the string and a pointer at the end of the string, and check that the
 * characters at those pointers are equal as we walk both pointers towards the middle of the string.
 * <p>
 * Notice: we're essentially checking that each character left of the middle has a corresponding copy right of the
 * middle.
 * <p>
 * We can simply check that each character appears an even number of times (unless there is a middle character, which
 * can appear once or some other odd number of times). This ensures that the characters can be ordered so that each char
 * on the left side of the string has a matching char on the right side of the string.
 * <p>
 * Solution Our approach is to check that each character appears an even number of times, allowing for only one
 * characters to appear an odd number of times (a middle character). This is enough to determine if a permutation of the
 * input string is a palindrome.
 * <p>
 * We iterate through each character in the input string, keeping track of the characters we’ve seen an odd number of
 * times using a hash set unpairedCharacters.
 */
public class PalindromePermutation {

    /**
     * Simple palidrome check using "keep two pointers" pattern
     *
     * @param str
     * @return
     */
    public static boolean isPalindrome(String str) {

        int size = str.length();
        for (int i = 0; i < size; i++) {
            if (str.charAt(i) != str.charAt(size - 1 - i)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks whether any permutation of an input string is a palindrome.
     * @param str
     * @return
     */
    public static boolean isPermutaionsPalindrome(String str) {

        Set<Character> unpairedChars = new HashSet<Character>();

        for (Character c : str.toCharArray()) {
            if (unpairedChars.contains(c)) {
                unpairedChars.remove(c);
            } else {
                unpairedChars.add(c);
            }
        }
        if(unpairedChars.size() <= 1) {
            return  true;
        }

        return false;
    }


    @Test
    public void testPermPalindrome() {

        assertTrue(isPermutaionsPalindrome("civic"));
        assertTrue(isPermutaionsPalindrome("ivicc"));

        assertTrue(isPermutaionsPalindrome("Rats live on no evil star"));

        // Negatives
        assertFalse(isPermutaionsPalindrome("civil"));
        assertFalse(isPermutaionsPalindrome("livci"));

    }

    @Test
    public void testSimplePalindrome() {
        String str = "civic";
        assertTrue(isPalindrome(str));
    }

    @Test
    public void testAnyPermutationPalindrome() {
        String str = "ivicc";
        assertTrue(isPalindrome(str));
    }

    public static void main(String[] args) {

        System.out.printf("PalindromePermutation : %s", isPalindrome("civic"));
    }
}
