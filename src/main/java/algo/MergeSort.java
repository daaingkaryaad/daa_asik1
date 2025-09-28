package algo;

public class MergeSort {

    private static final int INSERTION_SORT_THRESHOLD = 16;

    public static void sort(int[] arr) {
        int[] buffer = new int[arr.length];
        Metrics.reset();
        long start = System.nanoTime();

        sort(arr, buffer, 0, arr.length - 1);

        long end = System.nanoTime();
        Metrics.writeCSV("mergesort.csv", arr.length, end - start);
    }

    private static void sort(int[] arr, int[] buffer, int left, int right) {
        Metrics.enterRecursion();

        if (right - left + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right);
            Metrics.exitRecursion();
            return;
        }

        int mid = left + (right - left) / 2;
        sort(arr, buffer, left, mid);
        sort(arr, buffer, mid + 1, right);
        merge(arr, buffer, left, mid, right);

        Metrics.exitRecursion();
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);

        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            Metrics.compare();
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = buffer[i++];
        }
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                Metrics.compare();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
}
