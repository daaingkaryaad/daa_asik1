package algo;

import java.util.Arrays;
import java.util.Random;

public class Bench {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 10000;
        int trials = 20;

        System.out.println("n,time_select(ns),time_sort(ns)");

        for (int t = 0; t < trials; t++) {
            int[] arr = rand.ints(n, -100000, 100000).toArray();
            int k = n / 2;

            // Benchmark DeterministicSelect
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            long start1 = System.nanoTime();
            int res1 = DeterministicSelect.select(arr1, k);
            long end1 = System.nanoTime();

            // Benchmark Arrays.sort
            int[] arr2 = Arrays.copyOf(arr, arr.length);
            long start2 = System.nanoTime();
            Arrays.sort(arr2);
            int res2 = arr2[k];
            long end2 = System.nanoTime();

            if (res1 != res2) {
                throw new AssertionError("Mismatch: " + res1 + " vs " + res2);
            }

            System.out.printf("%d,%d,%d%n", n, (end1 - start1), (end2 - start2));
        }
    }
}
