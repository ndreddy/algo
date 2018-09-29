package algo.sort;

import algo.AlgoUtil;

/**
 * Runtime: 0 (n log (n)) average, 0 (n2) worst case.
 * Memory: 0 (log (n) ) .
 */
public class QuickSort {


    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[(l + r) / 2]; // Pick a pivot point. Can be an element

        //1. Until we've gone through the whole array
        while (l <= r) {
            //2. Find element on left that should be on right
            while (arr[l] < pivot) l++;

            //3. Find element on right that should be on left
            while (arr[r] > pivot) r--;

            //4. Swap elements, and move left and right indices
            if (l <= r) {
                swap(arr, l, r);
                l++;
                r--;
            }
        }
        return l;
    }

    public static void quickSort(int[] arr, int l, int r) {

       /*
        This also works.
       if(l < r) {
            int p = partition(arr, l, r);
            quickSort(arr,l,p-1);
            quickSort(arr,p,r);
        }*/

        // Partition Index
        int p = partition(arr, l, r);

        // Sort left half
        if (l < p - 1) quickSort(arr, l, p - 1);

        // Sort right half
        if (p < r) quickSort(arr, p, r);

    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = AlgoUtil.randomArray(20, 0, 6);
        AlgoUtil.printIntArray(arr);
        quickSort(arr, 0, arr.length - 1);
        AlgoUtil.printIntArray(arr);
    }

}
