import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSortTest {

	@Test
	public void testShortList() {
		List<Integer> values = new ArrayList<Integer>(Arrays.asList(2, 3, 1, 5, 4));
		QuickSort.sort(values);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), values);
	}

	@Test
	public void testReversedList() {
		List<Integer> values = new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1));
		QuickSort.sort(values);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), values);
	}

	@Test
	public void testEmptyList() {
		List<Integer> values = new ArrayList<Integer>(Arrays.asList());
		QuickSort.sort(values);
        assertEquals(Arrays.asList(), values);
	}

	@Test
	public void testSingleElement() {
		List<Integer> values = new ArrayList<Integer>(Arrays.asList(1));
		QuickSort.sort(values);
        assertEquals(Arrays.asList(1), values);
	}

	@Test
	public void testTies() {
		List<Integer> values = new ArrayList<Integer>(Arrays.asList(4, 2, 3, 2, 1));
		QuickSort.sort(values);
		assertEquals(Arrays.asList(1, 2, 2, 3, 4), values);
	}
}
