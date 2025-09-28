package algo;

import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    public static long comparisons = 0;
    public static long recursionDepth = 0;
    private static long currentDepth = 0;

    public static void reset() {
        comparisons = 0;
        recursionDepth = 0;
        currentDepth = 0;
    }

    public static void compare() {
        comparisons++;
    }

    public static void enterRecursion() {
        currentDepth++;
        recursionDepth = Math.max(recursionDepth, currentDepth);
    }

    public static void exitRecursion() {
        currentDepth--;
    }

    public static void writeCSV(String filename, int n, long timeNs) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(n + "," + timeNs + "," + comparisons + "," + recursionDepth + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
