import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class InPlaceHeapSortTest {

	@Test
	public void testHeapSort() {
		int[] actual = new int[]{1, 4, 2, 9, 5, 3, 6};
		InPlaceHeapSort.heapSort(actual);
		for (int i = 0; i < actual.length; i++) {
			System.out.print(actual[i]);
		}
		int[] expected = new int[]{9, 6, 5, 4, 3, 2, 1};
		assertTrue(Arrays.equals(expected, actual));
	}

}
