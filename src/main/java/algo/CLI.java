package algo;

import java.util.Random;

public class CLI {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java algo.CLI <algorithm> <n>");
            return;
        }

        String algoName = args[0];
        int n = Integer.parseInt(args[1]);
        Random rand = new Random();
        int[] arr = rand.ints(n, -1000, 1000).toArray();

        switch (algoName.toLowerCase()) {
            case "mergesort":
                MergeSort.sort(arr);
                System.out.println("MergeSort done, results saved to mergesort.csv");
                break;
            case "quicksort":
                QuickSort.sort(arr);
                System.out.println("QuickSort done, results saved to quicksort.csv");
                break;
            case "select":
                int k = n / 2;
                DeterministicSelect.select(arr, k);
                System.out.println("DeterministicSelect done, results saved to select.csv");
                break;
            case "closest":
                ClosestPair.Point[] pts = new ClosestPair.Point[n];
                for (int i = 0; i < n; i++) {
                    pts[i] = new ClosestPair.Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
                }
                ClosestPair.findClosest(pts);
                System.out.println("ClosestPair done, results saved to closest.csv");
                break;
            default:
                System.out.println("Unknown algorithm: " + algoName);
        }
    }
}
