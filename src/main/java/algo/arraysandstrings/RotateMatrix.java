package algo.arraysandstrings;

import algo.AlgoUtil;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the
 * image by 90 degrees. Can you do this in place?
 * https://www.youtube.com/watch?v=aClxtDcdpsQ
 *
 */
public class RotateMatrix {
    public static boolean rotate(int[][] m) {
        if (m.length == 0 || m.length != m[0].length) return false; // Not a square
        int n = m.length;

        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int j = last - offset;
                int top = m[first][i]; // save top

                // left -> top

                m[first][i] = m[j][first];

                // bottom -> left
                m[j][first] = m[last][j];

                // right -> bottom
                m[last][j] = m[i][last];

                // top -> right
                m[i][last] = top; // right <- saved top
            }
        }
        return true;
    }


    private static void transpose(int[][] m) {

        for (int i = 0; i < m.length; i++) {
            for (int j = i; j < m[0].length; j++) {
                int x = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = x;
            }
        }
    }

    public static void swapRows(int[][] m) {
        for (int  i = 0, k = m.length - 1; i < k; ++i, --k) {
            int[] x = m[i];
            m[i] = m[k];
            m[k] = x;
        }
    }

    public static void rotateByNinetyToLeft(int[][] m) {
        transpose(m);
        swapRows(m);
    }

    public static void rotateByNinetyToRight(int[][] m) {
        swapRows(m);
        transpose(m);
    }

    public static void main(String[] args) {
        int[][] matrix = AlgoUtil.randomMatrix(3, 3, 0, 9);
        AlgoUtil.printMatrix(matrix);
        rotate(matrix);
        System.out.println();
        AlgoUtil.printMatrix(matrix);
    }

}
