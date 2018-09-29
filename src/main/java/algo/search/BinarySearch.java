package algo.search;

public class BinarySearch {

    public static int binarySearch(int[] a, int x) {
        int l = 0;
        int r = a.length - 1;
        int m;

        while (l <= r) {
            m = (l + r) / 2;
            if(a[m] == x) return m;
            if (a[m] < x) l = m + 1; // go right
            else r = m - 1; // go left
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] a, int x, int l, int r) {
        if (l > r) return -1; // Error

        int m = (l + r) / 2;
        if (a[m] < x) {
            return binarySearchRecursive(a, x, m + 1, r);
        } else if (a[m] > x) {
            return binarySearchRecursive(a, x, l, m - 1);
        } else {
            return m;
        }
    }

    // Recursive algorithm to return the closest element
    public static int binarySearchRecursiveClosest(int[] a, int x, int l, int r) {
        if (l > r) { // high is on the left side now
            if (r < 0) return l;
            if (l >= a.length) return r;
            if (x - a[r] < a[l] - x) {
                return r;
            }
            return l;
        }

        int mid = (l + r) / 2;
        if (a[mid] < x) {
            return binarySearchRecursiveClosest(a, x, mid + 1, r);
        } else if (a[mid] > x) {
            return binarySearchRecursiveClosest(a, x, l, mid - 1);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] array = {3, 6, 9, 12, 15, 18};
        for (int i = 0; i < 20; i++) {
            int loc = binarySearch(array, i);
            int loc2 = binarySearchRecursive(array, i, 0, array.length - 1);
            int loc3 = binarySearchRecursiveClosest(array, i, 0, array.length - 1);
            System.out.println(i + ": " + loc + " " + loc2 + " " + loc3);
        }
    }

}