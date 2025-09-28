package algo;

import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] arr) {
        Metrics.reset();
        long start = System.nanoTime();

        quickSort(arr, 0, arr.length - 1);

        long end = System.nanoTime();
        Metrics.writeCSV("quicksort.csv", arr.length, end - start);
    }

    private static void quickSort(int[] arr, int left, int right) {
        while (left < right) {
            Metrics.enterRecursion();

            int pivotIndex = partition(arr, left, right);
            // Smaller-first recursion to keep stack depth O(log n)
            if (pivotIndex - left < right - pivotIndex) {
                quickSort(arr, left, pivotIndex - 1);
                left = pivotIndex + 1; // tail recursion elimination
            } else {
                quickSort(arr, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }

            Metrics.exitRecursion();
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right);

        int i = left;
        for (int j = left; j < right; j++) {
            Metrics.compare();
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
