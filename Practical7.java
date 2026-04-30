package ADACodesFiles;

public class Practical7 {

    public static void findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // DP table to store lengths of LCS of substrings
        int[][] dp = new int[m + 1][n + 1];

        // Build the DP table in a bottom-up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // The length of the LCS is in the bottom-right corner of the table
        int lcsLength = dp[m][n];
        System.out.println("Length of LCS: " + lcsLength);

        // Backtrack to find the actual LCS sequence
        int index = lcsLength;
        char[] lcsSequence = new char[index];

        int i = m, j = n;
        while (i > 0 && j > 0) {
            // If characters match, they are part of the LCS
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcsSequence[index - 1] = s1.charAt(i - 1);
                i--;
                j--;
                index--;
            }
            // If not, go in the direction of the larger value
            else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // Print the sequence
        System.out.print("LCS Sequence: ");
        for (int k = 0; k < lcsLength; k++) {
            System.out.print(lcsSequence[k]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample Input
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        System.out.println("--- Longest Common Subsequence ---");
        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + s2 + "\n");

        findLCS(s1, s2);
    }
}