import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class BubbleSortTest {

	@Test
	public void testShortList() {
		List<Integer> values = new ArrayList<Integer>(Arrays.asList(2, 3, 1, 5, 4));
        BubbleSort.sort(values);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), values);
	}

	@Test
	public void testReversedList() {
		List<Integer> values = new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1));
        BubbleSort.sort(values);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), values);
	}

	@Test
	public void testEmptyList() {
		List<Integer> values = new ArrayList<Integer>(Arrays.asList());
        BubbleSort.sort(values);
        assertEquals(Arrays.asList(), values);
	}

	@Test
	public void testSingleElement() {
		List<Integer> values = new ArrayList<Integer>(Arrays.asList(1));
        BubbleSort.sort(values);
        assertEquals(Arrays.asList(1), values);
	}

	@Test
	public void testTies() {
		List<Integer> values = new ArrayList<Integer>(Arrays.asList(4, 2, 3, 2, 1));
		BubbleSort.sort(values);
		assertEquals(Arrays.asList(1, 2, 2, 3, 4), values);
	}

	@Test
	public void testLargeList() {
		List<Integer> values = new ArrayList<Integer>();
		for (int i = 0; i < 10000; i++) {
			values.add(10000 - i - 1);
		}
		BubbleSort.sort(values);
		for (int i = 0; i < 10000; i++) {
			assertEquals(new Integer(i), values.get(i));
		}
	}

	@Test
	public void testExtraLargeList() {
		List<Integer> values = new ArrayList<Integer>();
		for (int i = 0; i < 30000; i++) {
			values.add(30000 - i - 1);
		}
		BubbleSort.sort(values);
		for (int i = 0; i < 30000; i++) {
			assertEquals(new Integer(i), values.get(i));
		}
	}

	@Test
	public void testLargeSortedList() {
		List<Integer> values = new ArrayList<Integer>();
		for (int i = 0; i < 1000000; i++) {
			values.add(30000 - i - 1);
		}
		BubbleSort.sort(values);
		for (int i = 0; i < 1000000; i++) {
			assertEquals(new Integer(i), values.get(i));
		}
	}

}
