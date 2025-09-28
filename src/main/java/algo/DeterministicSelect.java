package algo;

import java.util.Arrays;

public final class DeterministicSelect {
    private DeterministicSelect() {}

    public static int select(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid index: " + k);
        }

        Metrics.reset();
        long start = System.nanoTime();
        int result = select(arr, 0, arr.length - 1, k);
        long end = System.nanoTime();
        Metrics.writeCSV("select.csv", arr.length, end - start);
        return result;
    }

    private static int select(int[] arr, int left, int right, int k) {
        Metrics.enterRecursion();
        if (left == right) {
            Metrics.exitRecursion();
            return arr[left];
        }

        int pivotIndex = medianOfMedians(arr, left, right);
        pivotIndex = partition(arr, left, right, pivotIndex);

        int res;
        if (k == pivotIndex) {
            res = arr[k];
        } else if (k < pivotIndex) {
            res = select(arr, left, pivotIndex - 1, k);
        } else {
            res = select(arr, pivotIndex + 1, right, k);
        }

        Metrics.exitRecursion();
        return res;
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        Util.swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            Metrics.compare();
            if (arr[i] <= pivotValue) { // <= allows duplicates
                Util.swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        Util.swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n / 2;
        }

        int numMedians = 0;
        for (int i = left; i <= right; i += 5) {
            int subRight = Math.min(i + 4, right);
            Arrays.sort(arr, i, subRight + 1);
            int medianIndex = i + (subRight - i) / 2;
            Util.swap(arr, left + numMedians, medianIndex);
            numMedians++;
        }

        Arrays.sort(arr, left, left + numMedians);
        return left + numMedians / 2;
    }
}
