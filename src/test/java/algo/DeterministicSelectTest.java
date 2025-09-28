package algo;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] arr = {7, 2, 5, 3, 1};
        assertEquals(3, DeterministicSelect.select(arr, 2));
    }

    @Test
    void testRandomArray() {
        Random rand = new Random();
        int[] arr = rand.ints(1000, -1000, 1000).toArray();
        int k = 450;
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        assertEquals(copy[k], DeterministicSelect.select(arr, k));
    }
}
