package algo.arraysandstrings;

/**
 * One Away: There are three types of edits that can be performed on strings: insert a character,
 * remove a character, or replace a character. Given two strings, write a function to check if they are
 * one edit (or zero edits) away.
 * EXAMPLE
 * pale, ple -) true
 * pales, pale -) true
 * pale, bale -) true
 * pale, bae -) false
 */
public class OneEditAway {

    public static boolean isOneEditAway(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return isOneUpdateAway(s1, s2);
        } else if (s1.length() + 1 == s2.length()) {
            return isOneInsertAway(s1, s2);
        } else if (s1.length() - 1 == s2.length()) {
            return isOneInsertAway(s2, s1);
        }
        return false;
    }

    public static boolean isOneUpdateAway(String s1, String s2) {
        boolean diff = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff) return false;
                diff = true;
            }
        }
        return true;
    }

    public static boolean isOneInsertAway(String s1, String s2) {
        int i = 0; // index1 for s1
        boolean diff = false;

        // Iterate larger string s2
        for (int j = 0; j < s2.length(); j++) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (diff) return false; // only
                diff = true;
            } else {
                i++; // skip s1 for one diff
            }
        }
        return true;
    }

    /* Check if you can insert a character into s1 to make s2. */
    public static boolean oneEditInsert(String s1, String s2) {
        int index1 = 0;
        int index2 = 0;
        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2) {
                    return false;
                }
                index2++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String a = "aple";
        String b = "applle";
        boolean isOneEdit = isOneEditAway(a, b);
        System.out.println(a + ", " + b + ": " + isOneEdit);
    }

}
