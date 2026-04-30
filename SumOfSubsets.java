package ADACodesFiles;

import java.util.*;

public class SumOfSubsets {
    static void findSubsets(int[] set, int target, int index, List<Integer> current, int currentSum) {
        // If current sum matches target, print the subset
        if (currentSum == target) {
            System.out.println(current);
            return;
        }

        // If sum exceeds target or no more elements left
        if (currentSum > target || index == set.length) {
            return;
        }

        // Include the current element
        current.add(set[index]);
        findSubsets(set, target, index + 1, current, currentSum + set[index]);

        // Exclude the current element (Backtrack)
        current.remove(current.size() - 1);
        findSubsets(set, target, index + 1, current, currentSum);
    }

    public static void main(String[] args) {
        int[] set = {10, 7, 5, 18, 12, 20, 15};
        int target = 35;
        System.out.println("Subsets with sum " + target + ":");
        findSubsets(set, target, 0, new ArrayList<>(), 0);
    }
}