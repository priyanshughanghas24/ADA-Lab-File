package ADACodesFiles;

import java.util.Arrays;

class Item {
    int profit, weight, index;
    Double ratio;

    public Item(int profit, int weight, int index) {
        this.profit = profit;
        this.weight = weight;
        this.index = index;
        // Calculate profit to weight ratio
        this.ratio = (double) profit / weight;
    }
}

public class Practical5 {

    public static void getFractionalKnapsack(int[] profit, int[] weight, int capacity) {
        int n = profit.length;
        Item[] items = new Item[n];

        // Creating item objects
        for (int i = 0; i < n; i++) {
            items[i] = new Item(profit[i], weight[i], i);
        }

        // Sorting items by profit/weight ratio in descending order
        Arrays.sort(items, (a, b) -> b.ratio.compareTo(a.ratio));

        double totalProfit = 0d;
        // This array will store the fraction of each item taken (Vector of values)
        double[] fractions = new double[n];

        for (Item item : items) {
            if (capacity >= item.weight) {
                // If capacity is enough, take the whole item
                capacity -= item.weight;
                totalProfit += item.profit;
                fractions[item.index] = 1.0;
            } else {
                // If capacity is not enough, take a fraction of the item
                double fraction = (double) capacity / item.weight;
                totalProfit += (item.profit * fraction);
                fractions[item.index] = fraction;
                capacity = 0;
                break; // Knapsack is full
            }
        }

        // Output results
        System.out.println("Maximum Profit: " + totalProfit);
        System.out.print("Vector of Values (fractions taken for each item): [");
        for (int i = 0; i < n; i++) {
            System.out.print(fractions[i] + (i < n - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        // Sample Input
        int[] profit = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int capacity = 50;

        System.out.println("--- Fractional Knapsack ---");
        System.out.println("Profits: " + Arrays.toString(profit));
        System.out.println("Weights: " + Arrays.toString(weight));
        System.out.println("Total Capacity: " + capacity + "\n");

        getFractionalKnapsack(profit, weight, capacity);
    }
}