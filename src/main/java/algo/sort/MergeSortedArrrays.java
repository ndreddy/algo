package algo.sort;

import algo.AlgoUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * We have our lists of orders sorted numerically already, in arrays.
 * Write a method to merge our arrays of orders into one sorted array.
 * int[] myArray = new int[]{3, 4, 6, 10, 11, 15};
 * int[] alicesArray = new int[]{1, 5, 8, 12, 14, 19};
 *
 * System.out.println(Arrays.toString(mergeArrays(myArray, alicesArray)));
 * // prints [1, 3, 4, 5, 6, 8, 10, 11, 12, 14, 15, 19]
 *
 * Complexity
 * O(n) time and O(n) additional space, where n is the number of items in the merged array.
 *
 * The added space comes from allocating the mergedArray. There's no way to do this " in place"
 * because neither of our input arrays are necessarily big enough to hold the merged array.
 */
public class MergeSortedArrrays {

    /**
     *  We have two sorted already arrays, Write a method to merge our arrays of orders into one sorted array.
     * @param arr1 [3, 4, 6, 10, 11, 15]
     * @param arr2 [1, 5, 8, 12, 14, 19]
     * @return arr3 [1, 3, 4, 5, 6, 8, 10, 11, 12, 14, 15, 19]
     */
    public static int [] mergeArrays(int[] arr1, int[] arr2) {
        int[] arr3 = new int[arr1.length + arr2.length];
        int idx1 =0, idx2 =0, idx3=0;

        while (idx3 < arr3.length) {
            boolean isExhArr1 = idx1 >= arr1.length;
            boolean isExhArr2 = idx2 >= arr2.length;

            if(!isExhArr1 && (isExhArr2 || arr1[idx1] < arr2[idx2])){
                arr3[idx3] = arr1[idx1];
                idx1++;
            } else {
                arr3[idx3] = arr2[idx2];
                idx2++;
            }
            idx3++;
        }
        return arr3;
    }

    @Test
    public void testMergeArrays() {
        int[] a1 = new int[]{3, 4, 6, 10, 11, 15};
        int[] a2 = new int[]{1, 5, 8, 12, 14, 19};

        int[] a3 = mergeArrays(a1,a2);
//        Stream.of(a3).forEach(System.out::println);
        Arrays.stream(a3).forEach(System.out::println);
    }

    /**
     *
     * You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write
     * a method to merge B into A in sorted order.
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
