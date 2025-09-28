package algo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClosestPairTest {

    @Test
    void testSimplePoints() {
        ClosestPair.Point[] pts = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(1, 1)
        };
        double d = ClosestPair.findClosest(pts);
        assertEquals(Math.sqrt(2), d, 1e-9);
    }

    @Test
    void testLinePoints() {
        ClosestPair.Point[] pts = new ClosestPair.Point[5];
        for (int i = 0; i < 5; i++) {
            pts[i] = new ClosestPair.Point(i, 0);
        }
        double d = ClosestPair.findClosest(pts);
        assertEquals(1.0, d, 1e-9);
    }
}
