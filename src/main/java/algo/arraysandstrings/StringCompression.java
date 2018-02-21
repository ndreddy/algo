package algo.arraysandstrings;

/**
 * String Compression: Implement a method to perform basic string compression using the counts
 * of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the
 * "compressed" string would not become smaller than the original string, your method should return
 * the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 */
public class StringCompression {
    public static String compress(String str) {
        int compressedLen = getCompLen(str);
        if (compressedLen >= str.length()) return str;

        StringBuilder compressedStr = new StringBuilder(compressedLen); // initialize capacity
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

			/* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedStr.append(str.charAt(i)).append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressedStr.toString();
    }


    public static int getCompLen(String str) {
        int len = 0;
        int count = 1;
        for (int i = 0; i < str.length(); i++) {

			/* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                len += 1 + String.valueOf(count).length();
                count = 0;
            }
            count++;
        }
        return len;
    }

    public static void main(String[] args) {
        String str = "aabbccccaaa";
        System.out.println(str);
        // prints a2b2c4a3
        System.out.println(compress(str));
    }

}

