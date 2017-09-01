package algo.string;

/**
 * Created by ndreddy on 28/08/17.
 */
public class UniqueChar {


    /**
     * Time complexity is O(n), where n is the length of the string, and space complexity is O(n). For simplicity,
     * assume char set is ASCII (if not, we need to increase the storage size. The rest of the logic would be the same).
     * NOTE: This is a great thing to point out to your interviewer!
     *
     * @param str
     * @return
     */
    public static boolean isUniqueChars(String str) {
        if (str.length() > 128) {
            return false;
        }
        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) return false;
            char_set[val] = true;
        }
        return true;
    }

    /**
     * We can reduce our space usage a little bit by using a bit vector. We will assume, in the below code, that the
     * string is only lower case ‘a’ through ‘z’. This will allow us to use just a single int
     *
     * @param str
     * @return
     */
    /* Assumes only letters a through z. */
    public static boolean isUniqueCharsBitVector(String str) {
        if (str.length() > 26) { // Only 26 characters
            return false;
        }
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) return false;
            checker |= (1 << val);
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"abcde", "hello", "apple", "kite", "padle"};
        for (String word : words) {
            System.out.println(word + ": " + isUniqueChars(word));
        }
    }

}