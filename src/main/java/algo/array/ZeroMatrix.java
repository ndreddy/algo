package algo.array;

import algo.AlgoUtil;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column is set to 0
 * <p></p>
 * At first glance, this problem seems easy: just iterate through the matrix and every time we see a 0, set that row
 * and column
 * to 0. There’s one problem with that solution though: we will “recognize” those 0s later on in our iteration and then
 * set their row and column to zero. Pretty soon, our entire matrix is 0s! One way around this is to keep a second
 * matrix which  ags the 0 locations. We would then do a second pass through the matrix to set the zeros. This would
 * take O(MN) space. Do we really need O(MN) space? No. Since we’re going to set the entire row and column to zero, do
 * we really need to track which cell in a row is zero? No. We only need to know that row 2, for example, has a zero.
 */
public class ZeroMatrix {

    public static void setZerosMyVersion(int[][] m) {
        boolean[] row = new boolean[m.length];
        boolean[] col = new boolean[m[0].length];

        // 1. Store which column and row has zeros
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        // 2. Set row to zero
        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                for (int j = 0; j < m[0].length; j++) {
                    m[i][j] = 0;
                }
            }
        }

        //3 . Set column to zero
        // always assign i for rows and j for columns to avoid confusion.
        for (int j = 0; j < col.length; j++) {
            if (col[j]) {
                for (int i = 0; i < m.length; i++) {
                    m[i][j] = 0;
                }
            }
        }


    }




    public static void setZeros(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];

        // Store the row and column index with data 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }

        // Nullify rows
        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                nullifyRow(matrix, i);
            }
        }

        // Nullify columns
        for (int j = 0; j < column.length; j++) {
            if (column[j]) {
                nullifyColumn(matrix, j);
            }
        }
    }

    public static void nullifyRow(int[][] matrix, int row) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[row][j] = 0;
        }
    }

    public static void nullifyColumn(int[][] matrix, int col) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }

    public static void setZerosB(int[][] matrix) {
        boolean rowHasZero = false;
        boolean colHasZero = false;

        // Check if first row has a zero
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowHasZero = true;
                break;
            }
        }

        // Check if first column has a zero
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colHasZero = true;
                break;
            }
        }

        // Check for zeros in the rest of the array
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Nullify rows based on values in first column
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                nullifyRow(matrix, i);
            }
        }

        // Nullify columns based on values in first row
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                nullifyColumn(matrix, j);
            }
        }

        // Nullify first row
        if (rowHasZero) {
            nullifyRow(matrix, 0);
        }

        // Nullify first column
        if (colHasZero) {
            nullifyColumn(matrix, 0);
        }
    }


    public static boolean matricesAreEqual(int[][] m1, int[][] m2) {
        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            return false;
        }

        for (int k = 0; k < m1.length; k++) {
            for (int j = 0; j < m1[0].length; j++) {
                if (m1[k][j] != m2[k][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] cloneMatrix(int[][] matrix) {
        int[][] c = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                c[i][j] = matrix[i][j];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int nrows = 3;
        int ncols = 10;
        int[][] matrix = AlgoUtil.randomMatrix(nrows, ncols, -10, 10);

        AlgoUtil.printMatrix(matrix);

        setZerosMyVersion(matrix);

        System.out.println();

        AlgoUtil.printMatrix(matrix);
    }
}
