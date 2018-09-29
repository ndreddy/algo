package algo.sort;

/**
 * Counting sort is a very time-efficient (and somewhat space-inefficient) algorithm for sorting that avoids comparisons and exploits the O(1) time insertions and lookups in an array.
 * <p>
 * The idea is simple: if you're sorting  integers and you know they all fall in the range 1..100, you can generate a sorted array this way:
 * <p>
 * Allocate an array Counts Array where the indices represent numbers from our input array and the values represent how many times the index number appears. Start each value at 0.
 * In one pass of the input array, update numCounts as you go, so that at the end the values in numCounts are correct.
 * Allocate an array sortedArray where we'll store our sorted numbers.
 * In one in-order pass of numCounts put each number, the correct number of times, into sortedArray.
 */
public class CountingSort {

    public int[] sort(int[] arr, int max) {

        // Counts Array array of 0's at indices 0...maxValue
        int c[] = new int[max + 1];

        // The final sorted array
        int[] s = new int[arr.length];

        //1. populate Counts Array
        for (int i : arr) c[i]++;

        int k = 0;
        // 2. i - indices represent numbers from our input array
        for (int i = 0; i < c.length; i++) {

            //3. j - values represent how many times the index number appears
            for (int j = 0; j < c[i]; j++) {

                // add it to the sorted array
                s[k] = i;
                k++;
            }
        }

        return s;
    }
}
