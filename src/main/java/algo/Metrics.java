package algo;

import java.io.FileWriter;
import java.io.IOException;

public class Metrics {
    public static long comparisons = 0;        // count number of comparisons
    public static long recursionDepth = 0;     // track max recursion depth
    private static long currentDepth = 0;      // track current recursion depth

    // reset counters before a new experiment
    public static void reset() {
        comparisons = 0;
        recursionDepth = 0;
        currentDepth = 0;
    }

    // called whenever a comparison is made
    public static void compare() {
        comparisons++;
    }

    // called on recursion entry
    public static void enterRecursion() {
        currentDepth++;
        recursionDepth = Math.max(recursionDepth, currentDepth);
    }

    // called on recursion exit
    public static void exitRecursion() {
        currentDepth--;
    }

    // write metrics to a CSV file
    public static void writeCSV(String filename, int n, long timeNs) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(n + "," + timeNs + "," + comparisons + "," + recursionDepth + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
