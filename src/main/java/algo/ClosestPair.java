package algo;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point {
        public final double x, y;
        public Point(double x, double y) {
            this.x = x; this.y = y;
        }
    }

    public static double findClosest(Point[] points) {
        Metrics.reset();
        long start = System.nanoTime();

        Point[] px = Arrays.copyOf(points, points.length);
        Point[] py = Arrays.copyOf(points, points.length);
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));

        double dist = closestPair(px, py, 0, px.length - 1);

        long end = System.nanoTime();
        Metrics.writeCSV("closest.csv", points.length, end - start);
        return dist;
    }

    private static double closestPair(Point[] px, Point[] py, int left, int right) {
        if (right - left <= 3) {
            return bruteForce(px, left, right);
        }

        int mid = (left + right) / 2;
        Point midPoint = px[mid];

        Point[] pyl = Arrays.stream(py).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] pyr = Arrays.stream(py).filter(p -> p.x > midPoint.x).toArray(Point[]::new);

        double dl = closestPair(px, pyl, left, mid);
        double dr = closestPair(px, pyr, mid + 1, right);
        double d = Math.min(dl, dr);

        // Build strip of points within d of mid line
        Point[] strip = Arrays.stream(py).filter(p -> Math.abs(p.x - midPoint.x) < d).toArray(Point[]::new);

        return Math.min(d, stripClosest(strip, d));
    }

    private static double bruteForce(Point[] pts, int left, int right) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                Metrics.compare();
                min = Math.min(min, dist(pts[i], pts[j]));
            }
        }
        return min;
    }

    private static double stripClosest(Point[] strip, double d) {
        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                Metrics.compare();
                min = Math.min(min, dist(strip[i], strip[j]));
            }
        }
        return min;
    }

    private static double dist(Point a, Point b) {
        return Math.hypot(a.x - b.x, a.y - b.y);
    }
}
