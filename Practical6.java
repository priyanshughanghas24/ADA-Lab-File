package ADACodesFiles;

import java.util.Arrays;

public class Practical6 {

    public static void knapsack01(int[] profit, int[] weight, int capacity) {
        int n = profit.length;
        // DP table to store maximum profit for each subproblem
        int[][] dp = new int[n + 1][capacity + 1];

        // Build DP table in a bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weight[i - 1] <= w) {
                    // Max of taking the item vs not taking the item
                    dp[i][w] = Math.max(profit[i - 1] + dp[i - 1][w - weight[i - 1]], dp[i - 1][w]);
                } else {
                    // Item weight is more than current capacity, skip it
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        int maxProfit = dp[n][capacity];
        System.out.println("Maximum Profit: " + maxProfit);

        // Backtrack to find the vector of values (which items were included)
        int[] vector = new int[n];
        int res = maxProfit;
        int w = capacity;

        for (int i = n; i > 0 && res > 0; i--) {
            // If profit comes from the top (dp[i-1][w]), item was NOT included
            if (res == dp[i - 1][w]) {
                vector[i - 1] = 0;
            } else {
                // Item WAS included
                vector[i - 1] = 1;
                // Reduce the profit and weight to find remaining items
                res = res - profit[i - 1];
                w = w - weight[i - 1];
            }
        }

        // Output the vector
        System.out.print("Vector of Values (1 for taken, 0 for not taken): [");
        for (int i = 0; i < n; i++) {
            System.out.print(vector[i] + (i < n - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        // Sample Input (Using the same as Fractional for comparison)
        int[] profit = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int capacity = 50;

        System.out.println("--- 0/1 Knapsack ---");
        System.out.println("Profits: " + Arrays.toString(profit));
        System.out.println("Weights: " + Arrays.toString(weight));
        System.out.println("Total Capacity: " + capacity + "\n");

        knapsack01(profit, weight, capacity);
    }
}