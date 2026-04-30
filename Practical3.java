package ADACodesFiles;

import java.util.Random;

public class Practical3 {

    // --- BINARY SEARCH ALGORITHM ---
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if target is present at mid
            if (arr[mid] == target) {
                return mid;
            }
            // If target greater, ignore left half
            if (arr[mid] < target) {
                left = mid + 1;
            }
            // If target is smaller, ignore right half
            else {
                right = mid - 1;
            }
        }
        // Target not found
        return -1;
    }

    // --- ARRAY GENERATOR ---
    // Binary search only works on sorted arrays
    public static int[] generateSortedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i * 2; // e.g., 0, 2, 4, 6...
        }
        return arr;
    }

    // --- EXECUTION TIME CALCULATOR ---
    public static void analyzeBinarySearch() {
        int[] sizes = {10, 20, 30, 40, 50}; // Kept sizes small as per previous practicals
        System.out.println("--- Binary Search Time Analysis (in nanoseconds) ---");
        System.out.println("Size\tBest\tAverage\tWorst");

        Random rand = new Random();

        for (int n : sizes) {
            int[] arr = generateSortedArray(n);

            // Best Case: Element is exactly at the middle
            int bestCaseTarget = arr[n / 2];

            // Average Case: A random element present in the array
            int avgCaseTarget = arr[rand.nextInt(n)];

            // Worst Case: Element is not present in the array
            int worstCaseTarget = -1;

            // Measure Best Case
            long start1 = System.nanoTime();
            binarySearch(arr, bestCaseTarget);
            long end1 = System.nanoTime();
            long tBest = end1 - start1;

            // Measure Average Case
            long start2 = System.nanoTime();
            binarySearch(arr, avgCaseTarget);
            long end2 = System.nanoTime();
            long tAvg = end2 - start2;

            // Measure Worst Case
            long start3 = System.nanoTime();
            binarySearch(arr, worstCaseTarget);
            long end3 = System.nanoTime();
            long tWorst = end3 - start3;

            System.out.println(n + "\t" + tBest + "\t" + tAvg + "\t" + tWorst);
        }
    }

    public static void main(String[] args) {
        analyzeBinarySearch();
    }
}