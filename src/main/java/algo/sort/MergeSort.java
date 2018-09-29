package algo.sort;

import algo.AlgoUtil;

import java.util.Arrays;

/**
 * Runtime: O( n log (n) ) average and worst case.
 * Memory: Depends
 * what's our total time cost? O(nlogn)
 * ​​ logn comes from the number of times we have to cut n in half to get down to subarrays of just 1 element (our base case). The additional n comes from the time cost of merging all n items together each time we merge two sorted subarrays.
 *
 */
public class MergeSort {

    static void merge(int[] arrL, int[] arrR, int[] arr) {
        int i = 0, j = 0, k = 0;
        while (i < arrL.length && j < arrR.length) {
            if (arrL[i] <= arrR[j]) {
                arr[k] = arrL[i];k++;i++;
            } else {
                arr[k] = arrR[j];k++;j++;
            }
        }

        // one exhausted, only one of the below while loops gets executed.
        while (i < arrL.length) {
            arr[k] = arrL[i];k++;i++;
        }

        while (j < arrR.length) {
            arr[k] = arrR[j];k++;j++;
        }
    }

    static void mergeSort(int[] arr) {
        int n = arr.length;

        // 1. Exit/Base condition
        if (n < 1) return;

        // 2. Mid point
        int m = n / 2;

        // L & R arrays
        int[] arrL = Arrays.copyOfRange(arr, 0, m);
        int[] arrR = Arrays.copyOfRange(arr, m, n);

        // Recursive call to L & R
        mergeSort(arrL);
        mergeSort(arrR);

        // Merege L & R
        merge(arrL, arrR, arr);

    }

    //    CtCT book
    public static void mergesort(int[] array) {
        int[] helper = new int[array.length];
        mergesort(array, helper, 0, array.length - 1);
    }

    public static void mergesort(int[] array, int[] helper, int low, int high) {

        if (low < high) {
            int middle = (low + high) / 2;
            mergesort(array, helper, low, middle); // Sort left half
            mergesort(array, helper, middle + 1, high); // Sort right half
            merge(array, helper, low, middle, high); // Merge them
        }
    }

    public static void merge(int[] array, int[] helper, int low, int middle, int high) {
        /* Copy both halves into a helper array */
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        /* Iterate through helper array. Compare the left and right
         * half, copying back the smaller element from the two halves
         * into the original array. */
        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else { // If right element is smaller than left element
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
            System.out.println("Merged arr=" + Arrays.toString(array));

        }

        /* Copy the rest of the left side of the array into the
         * target array */
        int remaining = middle - helperLeft;
        for (int i = 0; i <= remaining; i++) {
            array[current + i] = helper[helperLeft + i];
        }
    }

    public static void main(String[] args) {
        int size = 19;
        int[] array = AlgoUtil.randomArray(size, 0, size - 1);
        int[] validate = new int[size];
        AlgoUtil.printIntArray(array);
        for (int i = 0; i < size; i++) {
            validate[array[i]]++;
        }
        mergeSort(array);
        for (int i = 0; i < size; i++) {
            validate[array[i]]--;
        }
        AlgoUtil.printIntArray(array);
        for (int i = 0; i < size; i++) {
            if (validate[i] != 0 || (i < (size - 1) && array[i] > array[i + 1])) {
                System.out.println("ERROR");
            }
        }
    }

}