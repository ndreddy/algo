package algo.binarysearch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by ndreddy on 16/03/17.
 * <p>
 * Find a duplicate, Space Edition. We have a list of integers, where:
 * <p>
 * The integers are in the range 1..n The list has a length of n+1. It follows that our list has at least one
 * integer which appears at least twice. But it may have several duplicates, and each duplicate may appear more than
 * twice.
 * <p>
 * Write a function which finds an integer that appears more than once in our list. (If there are multiple duplicates,
 * you only need to find one of them.)
 * <p>
 * We're going to run this function on our new, super-hip Macbook Pro With Retina Display™. Thing is, the damn thing
 * came with the RAM soldered right to the motherboard, so we can't upgrade our RAM. So we need to optimize for space!
 *
 * Tips
 * <p>
 * We can do this in O(1) space.
 * <p>
 * We can do this in less than O(n^2) time, while keeping O(1) space.
 */
public class FindDuplicateInt {


    public int findRepeatnln(int [] arr) {
        int l = 0;
        int h = arr.length -1;

        while (l < h) {


            // divide our range 1..n into an upper range and lower range
            // (such that they don't overlap)
            // lower range is floor..midpoint
            // upper range is midpoint+1..ceiling
            int mid = l + ((h-l) / 2);

            int expectedItems = h - mid  ; // Distinct Possible Ints In Right

            // Find actual elements in right
            int actualItems = 0;
            for (int num : arr) {
                if( num > mid && num <= h) {
                    actualItems ++;
                }
            }

            if(actualItems > expectedItems){
                // go right
                l = mid + 1;
            } else {
                // go left
                h = mid;
            }
        }

        // l and h have converged
        // we found a number that repeats!
        return l;
    }

    @Test
    public void testFindRepeatln() {
        int[] nums = {3, 2, 1, 3, 4, 5};
        int d = findRepeatnln(nums);
        assertEquals(3, d);
    }


    /**
     * Time - O(n), Space - O(n)
     * @param nums
     * @return
     */
    public int findRepeat(int [] nums) {
        Set<Integer> numSet = new HashSet<Integer>();

        for (int num : nums) {
            if(numSet.contains(num)) {
                return num;
            } else {
                numSet.add(num);
            }
        }

        throw new IllegalArgumentException("No dupes");
    }


    @Test
    public void testFindRepeat() {
        int[] nums = {1, 2, 3, 3, 4, 5, 6};
        int d = findRepeat(nums);
        assertEquals(3, d);
    }


    /**
     *  O(1)space and O(n^2)time
     * @param nums
     * @return
     */
    public int findRepeatBruteForce(int [] nums) {

        for (int i = 1; i < nums.length; i++) {
            boolean hasSeen = false;
            for (int num : nums) {
                if(num == i){
                    if(hasSeen){
                        return i;
                    } else {
                        hasSeen = true;
                    }

                }

            }
        }

        throw new IllegalArgumentException("No Dupes!");

    }

    @Test
    public void testFindRepeatBruteForce() {
        int[] nums = {1, 2, 3, 3, 4, 5, 6};
        int d = findRepeatBruteForce(nums);
        assertEquals(3, d);
    }



    /**
     * Complexity time - O(3n), Space O(1)
     * list.contains - O(n), list.dequeue - O(n)
     * @param list
     * @return
     */
    public int getDuplicate(List<Integer> list) {

        int size = list.size();
        for (int i = 0; i < size; i++) {

            if (list.contains(i)) {
                list.remove(Integer.valueOf(i));
                if (list.contains(i))
                    return i;
            }
        }

        throw new IllegalArgumentException("Dupes not found");
    }


    /**
     * Time - O(n), Space - O(n)
     * @param arr
     * @return
     */
    public int getArrDupes(int[] arr) {

        boolean[] a = new boolean[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            if (a[arr[i]] == false) {
                a[arr[i]] = true;
            } else {
                return arr[i];
            }

        }

        throw new IllegalArgumentException("Dupes not found");
    }

    @Test
    public void testArrDupes() {
        int[] arr = {1, 2, 3, 3, 4, 5, 6};
        int d = getArrDupes(arr);
        assertEquals(3, d);
    }

    @Test
    public void testDupes() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(5);
        int d = getDuplicate(list);
        assertEquals(3, d);
    }

}
