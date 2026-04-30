package ADACodesFiles;
import java.util.Arrays;
import java.util.Random;

public class Practical2 {

    // --- SORTING ALGORITHMS ---
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // --- ARRAY GENERATORS ---
    public static int[] generateBestCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        return arr;
    }

    public static int[] generateAverageCase(int n) {
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000);
        return arr;
    }

    public static int[] generateWorstCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }

    // --- EXECUTION TIME CALCULATOR ---
    public static long getExecutionTime(Runnable sortMethod, int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        long startTime = System.nanoTime();
        sortMethod.run();
        long endTime = System.nanoTime();
        return (endTime - startTime);
    }

    public static void analyzeSort(String name) {
        int[] sizes = {10, 20, 30, 40};
        System.out.println("--- " + name + " Time Analysis (in nanoseconds) ---");
        System.out.println("Size\tBest\tAverage\tWorst");

        for (int n : sizes) {
            int[] best = generateBestCase(n);
            int[] avg = generateAverageCase(n);
            int[] worst = generateWorstCase(n);

            long tBest = 0, tAvg = 0, tWorst = 0;

            if (name.equals("Bubble Sort")) {
                tBest = getExecutionTime(() -> bubbleSort(best), best);
                tAvg = getExecutionTime(() -> bubbleSort(avg), avg);
                tWorst = getExecutionTime(() -> bubbleSort(worst), worst);
            } else if (name.equals("Selection Sort")) {
                tBest = getExecutionTime(() -> selectionSort(best), best);
                tAvg = getExecutionTime(() -> selectionSort(avg), avg);
                tWorst = getExecutionTime(() -> selectionSort(worst), worst);
            } else if (name.equals("Insertion Sort")) {
                tBest = getExecutionTime(() -> insertionSort(best), best);
                tAvg = getExecutionTime(() -> insertionSort(avg), avg);
                tWorst = getExecutionTime(() -> insertionSort(worst), worst);
            }
            System.out.println(n + "\t" + tBest + "\t" + tAvg + "\t" + tWorst);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        analyzeSort("Bubble Sort");
        analyzeSort("Selection Sort");
        analyzeSort("Insertion Sort");
    }
}
