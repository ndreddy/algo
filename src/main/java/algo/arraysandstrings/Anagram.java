package algo.arraysandstrings;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ndreddy on 31/03/17.
 */
public class Anagram {

    public boolean isAnagram(String s1, String s2) {

        if(s1.length() != s2.length()){
            return false;
        }

        int value = 0;
        for (int i = 0; i < s1.length(); i++) {
            value += s1.charAt(i);
            value -= s2.charAt(i);
        }

        return value == 0;
    }

    @Test
    public void test() {
        assertTrue(isAnagram("abc", "bac"));
        assertFalse(isAnagram("abcDEF", "bacdef"));
    }

}
