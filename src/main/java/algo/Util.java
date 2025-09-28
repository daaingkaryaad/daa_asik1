package algo;

import java.util.Random;

public class Util {
    private static final Random rand = new Random();

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    public static void guardArray(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Array is null");
    }
}
