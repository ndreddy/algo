package algo.search;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ndreddy on 06/04/17.
 * A sorted array has been rotated so that the elements might appear in the order 3 4 5 6 7 1 2.
 * How would you find the minimum element?
 * <p>
 * A simple solution is to traverse the complete array and find minimum. This solution requires Θ(n) time.
 * We can do it in O(Logn) using Binary Search
 *
 * The minimum element is the only element whose previous is greater than it.
 * If there is no previous element element, then there is no rotation (first element is minimum)
 *
 */
public class FindMinInRotatedSortedArray {


    /**
     *
     * @param arr
     * @return
     */
    int findMin(int [] arr) {

        int l = 0;              // index of left/first
        int r = arr.length -1;   // index of right/last
        int m; // mid index

        // always restrict the search to the unsorted sub-array.
        // The min is always there.
        while ( arr[l] > arr[r]) {
            m = (l+r)/2; // find mid
            if(arr[m] > arr[r]) {
                // go right
                l = m +1;
            } else {
                // go left
                r = m;
            }
        }

        // answer
        return arr[l];
    }


    @Test
    public void testFindMin() {
        int [] arr1 = {1,2,3,4,5};
        assertEquals(1, findMin(arr1));

        int[] arr2 = {3, 4, 5, 6, 7, 8, 2};
        assertEquals(2, findMin(arr2));

        int [] arr3 = {2,3,4,5,1};
        assertEquals(1, findMin(arr3));

    }


    /**
     *
     * @param arr
     * @return
     */
    int findMax(int [] arr) {

        int l = 0;              // index of first
        int h = arr.length -1;   // index of last
        int mid = h;

        // always restrict the search to the unsorted sub-array.
        // The min is always there.
        while ( arr[l] > arr[h]) {
            mid = (l+h)/2; // find mid
            if(arr[mid] > arr[h]) {
                l = mid +1;
            } else {
                h = mid;
            }
        }

        // answer
        return arr[mid];
    }


    @Test
    public void testFindMax() {
        int [] arr1 = {1,2,3,4,5};
        assertEquals(5, findMax(arr1));

        int[] arr2 = {3, 4, 5, 6, 7, 8, 2};
        assertEquals(8, findMax(arr2));

        int [] arr3 = {2,3,4,5,1};
        assertEquals(5, findMax(arr3));

    }


    /**
     * *****************************************************
     * @param arr
     * @return
     */
    int findMin1(int [] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i] > arr[i+1]){
                return arr[i+1];
            }
        }
        return arr[0];
    }

    @Test
    public void testFindMin1() {
        int[] arr = {3, 4, 5, 6, 7, 8, 2};
        assertEquals(2, findMin1(arr));
        int [] arr1 = {1,2,3,4,5};
        assertEquals(1, findMin1(arr1));

        int [] arr2 = {2,3,4,5,1};
        assertEquals(1, findMin1(arr1));

    }


    /**
     * ********************************************
     * @param arr
     * @return
     */
    int findMinNumber(int[] arr) {
        int minNum = arr[0];

        for (int i : arr) {
            minNum = Math.min(minNum, i);
        }

        return minNum;
    }


    @Test
    public void testMin() {
        int[] arr = {3, 4, 5, 6, 7, 8, 2};
        assertEquals(2, findMinNumber(arr));
    }

}
