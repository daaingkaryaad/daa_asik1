package algo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

class MergeSortTest {

    @Test
    void testSmallArray() {
        int[] arr = {5, 2, 4, 6, 1, 3};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, arr);
    }

    @Test
    void testRandomArray() {
        Random rand = new Random();
        int[] arr = rand.ints(1000, -1000, 1000).toArray();
        int[] copy = Arrays.copyOf(arr, arr.length);

        MergeSort.sort(arr);
        Arrays.sort(copy);

        assertArrayEquals(copy, arr);
    }
}
