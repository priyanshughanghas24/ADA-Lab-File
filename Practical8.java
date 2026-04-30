package ADACodesFiles;

import java.util.Arrays;

public class Practical8 {

    // Function to print the optimal parenthesis order using the 's' table
    public static void printOptimalParens(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void matrixChainOrder(int[] p) {
        int n = p.length - 1; // Number of matrices

        // m[i][j] stores minimum number of scalar multiplications needed
        int[][] m = new int[n + 1][n + 1];

        // s[i][j] stores the index of the matrix after which the product is split
        int[][] s = new int[n + 1][n + 1];

        // Cost is zero when multiplying one matrix
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        // L is the chain length
        for (int L = 2; L <= n; L++) {
            for (int i = 1; i <= n - L + 1; i++) {
                int j = i + L - 1;
                m[i][j] = Integer.MAX_VALUE;

                // Try making a cut at every possible position k between i and j-1
                for (int k = i; k <= j - 1; k++) {
                    // q = cost/scalar multiplications
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];

                    if (q < m[i][j]) {
                        m[i][j] = q;
                        // Store the split point
                        s[i][j] = k;
                    }
                }
            }
        }

        // Output results
        System.out.println("Minimum number of scalar multiplications: " + m[1][n]);
        System.out.print("Optimal Parenthesization: ");
        printOptimalParens(s, 1, n);
        System.out.println();
    }

    public static void main(String[] args) {
        // Array representing matrix dimensions
        // A1 is 10x20, A2 is 20x30, A3 is 30x40, A4 is 40x30
        int[] p = {10, 20, 30, 40, 30};

        System.out.println("--- Matrix Chain Multiplication ---");
        System.out.println("Matrix dimensions array: " + Arrays.toString(p) + "\n");

        matrixChainOrder(p);
    }
}