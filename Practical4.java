package ADACodesFiles;

import java.util.Arrays;
import java.util.Random;

public class Practical4 {

    // --- MERGE SORT ---
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) { arr[k] = L[i]; i++; k++; }
        while (j < n2) { arr[k] = R[j]; j++; k++; }
    }

    // --- QUICK SORT ---
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // --- ARRAY GENERATORS ---
    public static int[] generateBestAvgCase(int n) {
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000);
        return arr;
    }

    // For QuickSort (with last element pivot), sorted array is worst case
    public static int[] generateWorstCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        return arr;
    }

    // --- EXECUTION TIME CALCULATOR ---
    public static void analyzeAdvancedSorts() {
        int[] sizes = {10, 20, 30, 40}; // Based on requirement [cite: 8]
        System.out.println("--- Merge Sort Time Analysis (in ns) ---");
        System.out.println("Size\tBest/Avg\tWorst");
        for (int n : sizes) {
            int[] avg = generateBestAvgCase(n);
            int[] worst = generateWorstCase(n);

            long start1 = System.nanoTime();
            mergeSort(Arrays.copyOf(avg, avg.length), 0, n - 1);
            long tAvg = System.nanoTime() - start1;

            long start2 = System.nanoTime();
            mergeSort(Arrays.copyOf(worst, worst.length), 0, n - 1);
            long tWorst = System.nanoTime() - start2;

            System.out.println(n + "\t" + tAvg + "\t\t" + tWorst);
        }

        System.out.println("\n--- Quick Sort Time Analysis (in ns) ---");
        System.out.println("Size\tBest/Avg\tWorst");
        for (int n : sizes) {
            int[] avg = generateBestAvgCase(n);
            int[] worst = generateWorstCase(n); // Sorted array

            long start1 = System.nanoTime();
            quickSort(Arrays.copyOf(avg, avg.length), 0, n - 1);
            long tAvg = System.nanoTime() - start1;

            long start2 = System.nanoTime();
            quickSort(Arrays.copyOf(worst, worst.length), 0, n - 1);
            long tWorst = System.nanoTime() - start2;

            System.out.println(n + "\t" + tAvg + "\t\t" + tWorst);
        }
    }

    public static void main(String[] args) {
        analyzeAdvancedSorts();
    }
}