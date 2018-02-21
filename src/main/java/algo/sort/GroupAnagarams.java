package algo.sort;

import algo.AlgoUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Group Anagrams: Write a method to sort an arraysandstrings of strings so that all the anagrams are next to
 * each other.
 */
public class GroupAnagarams {
    static class AnagramComparator implements Comparator<String> {
        private String sortChars(String s) {
            char[] content = s.toCharArray();
            Arrays.sort(content);
            return new String(content);
        }

        public int compare(String s1, String s2) {
            return sortChars(s1).compareTo(sortChars(s2));
        }
    }

    public static void main(String[] args) {
        String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        System.out.println(AlgoUtil.stringArrayToString(array));
        Arrays.sort(array, new AnagramComparator());
        System.out.println(AlgoUtil.stringArrayToString(array));
    }
}
