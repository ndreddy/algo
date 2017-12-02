package algo.search;

/**
 * 10.3 Search in Rotated Array: Given a sorted array of n integers that has been rotated an unknown
 number of times, write code to find an element in the array. You may assume that the array was
 originally sorted in increasing order.
 EXAMPLE
 InputfindSin {15, 16, 19, 20, 25, 1, 3,4,5,7,10, 14}
 Output 8 (the index of 5 in the array)
 */
public class SearchInRotatedArray {

    public static int search(int a[], int x) {
        return search(a, 0, a.length - 1, x);
    }


    public static int search(int a[], int l, int r, int x) {
        int m = (l + r) / 2;
        if (x == a[m]) { // Found element
            return m;
        }
        if (r < l) {
            return -1;
        }

		/* While there may be an inflection point due to the rotation, either the left or
		 * right half must be normally ordered.  We can look at the normally ordered half
		 * to make a determination as to which half we should search.
		 */
        if (a[l] < a[m]) { // Left is normally ordered.
            if (x >= a[l] && x < a[m]) {
                return search(a, l, m - 1, x);
            } else {
                return search(a, m + 1, r, x);
            }
        } else if (a[m] < a[l]) { // Right is normally ordered.
            if (x <= a[r] && x > a[m] ) {
                return search(a, m + 1, r, x);
            } else {
                return search(a, l, m - 1, x);
            }
        } else if (a[l] == a[m]) { // Left is either all repeats OR loops around (with the right half being all dups)
            if (a[m] != a[r]) { // If right half is different, search there
                return search(a, m + 1, r, x);
            } else { // Else, we have to search both halves
                int result = search(a, l, m - 1, x);
                if (result == -1) {
                    return search(a, m + 1, r, x);
                } else {
                    return result;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 1, 2, 2, 2, 2, 2, 2, 2};

        System.out.println(search(a, 2));
        System.out.println(search(a, 3));
        System.out.println(search(a, 4));
        System.out.println(search(a, 1));
        System.out.println(search(a, 8));
    }
}
