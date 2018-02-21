package algo.sort;

import algo.AlgoUtil;

/**
 * You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write
 * a method to merge B into A in sorted order.
 */
public class SortedMerge {

    /**
     * Merges arraysandstrings
     *
     * @param a     first arraysandstrings
     * @param b     second arraysandstrings
     * @param lastA number of "real" elements in a
     * @param lastB number of "real" elements in b
     */
    public static void merge(int[] a, int[] b, int lastA, int lastB) {
        int indexMerged = lastB + lastA - 1; /* Index of last location of merged arraysandstrings */
        int indexA = lastA - 1; /* Index of last element in arraysandstrings b */
        int indexB = lastB - 1; /* Index of last element in arraysandstrings a */

		/* Merge a and b, starting from the last element in each */
        while (indexB >= 0) {
            if (indexA >= 0 && a[indexA] > b[indexB]) { /* end of a is bigger than end of b */
                a[indexMerged] = a[indexA]; // copy element
                indexA--;
            } else {
                a[indexMerged] = b[indexB]; // copy element
                indexB--;
            }
            indexMerged--; // move indices
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0};
        int[] b = {1, 4, 7, 6, 7, 7};
        merge(a, b, 8, 6);
        System.out.println(AlgoUtil.arrayToString(a));
    }
}
