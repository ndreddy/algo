package algo.arraysandstrings;

/**
 * Write a method to replace all spaces in a string with ‘%20’.
 * <p>
 * Steps
 * Count the number of spaces during the  rst scan of the string.
 * Parse the string again from the end and for each character:
 * If a space is encountered, store “%20”
 * Else, store the character as it is in the newly shifted location.
 */
public class URLify {


    public static void replaceSpacesMyVersion(char[] str) {

        // First you must find the trim size of the str arr
        int oldLength = findLastCharacter(str) + 1;
        int numSpaces = getSpaceCount(str, oldLength);
        int newLength = oldLength + numSpaces * 2;

        int j = newLength - 1;

        // You must start from end of none blank char
        for (int i = oldLength -1 ; i >= 0; i--) {
            if (str[i] != ' ') {
                str[j] = str[i];
                j--;

            } else {
                str[j] = '0';
                str[j - 1] = '2';
                str[j - 2] = '%';
                j = j-3;
            }
        }


    }

    private static int getSpaceCount(char[] str, int len) {

        int count = 0;
        for (int i = 0; i < len; i++) {
            if (str[i] == ' ')
                count++;
        }
        return count;
    }


    // Assume string has sufficient free space at the end
    public static void replaceSpaces(char[] str, int trueLength) {
        int spaceCount = 0, index, i = 0;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }

        index = trueLength + spaceCount * 2;
        if (trueLength < str.length) str[trueLength] = '\0';

        for (i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }
    }

    public static int findLastCharacter(char[] str) {
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] != ' ') {
                return i;
            }
        }
        return -1;
    }

    /**
     * Using String methods
     *
     * @param str
     * @return
     */
    public String replace(String str) {
        String[] words = str.split(" ");
        StringBuilder sentence = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; ++i) {
            sentence.append("%20");
            sentence.append(words[i]);
        }

        return sentence.toString();
    }


    public static void main(String[] args) {
        String str = "Mr John Smith    ";
        char[] arr = str.toCharArray();
        int trueLength = findLastCharacter(arr) + 1;
        replaceSpacesMyVersion(arr);
        //replaceSpaces(arr, trueLength);
        System.out.println("\"" + String.valueOf(arr) + "\"");

    }
}


