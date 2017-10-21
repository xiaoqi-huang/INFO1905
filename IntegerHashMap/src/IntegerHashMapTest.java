import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IntegerHashMapTest<V> {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testConstruction() {
		IntegerHashMap<V> ihm = new IntegerHashMap<V>(5);

		assertEquals(0, ihm.size());
		assertTrue(ihm.isEmpty());

		ArrayList<Integer> keySet = (ArrayList<Integer>)ihm.keySet();
		assertTrue(keySet.isEmpty());
		assertFalse(keySet.iterator().hasNext());

		ArrayList<V> valueSet = (ArrayList<V>)ihm.values();
		assertTrue(valueSet.isEmpty());
		assertFalse(valueSet.iterator().hasNext());

		ArrayList<Entry<Integer, V>> entrySet = (ArrayList<Entry<Integer, V>>)ihm.entrySet();
		assertTrue(entrySet.isEmpty());
		assertFalse(entrySet.iterator().hasNext());

		assertNull(ihm.get(1));
		assertNull(ihm.remove(1));
	}

	@Test
	public void testSize() {
		IntegerHashMap<String> ihm = new IntegerHashMap<String>(5);

		assertEquals(0, ihm.size());
		ihm.put(1, "X");
		assertEquals(1, ihm.size());
		ihm.put(2, "Y");
		assertEquals(2, ihm.size());
		ihm.put(3, "Z");
		assertEquals(3, ihm.size());
		ihm.remove(1);
		assertEquals(2, ihm.size());
		ihm.remove(2);
		assertEquals(1, ihm.size());
		ihm.remove(3);
		assertEquals(0, ihm.size());
	}

	@Test
	public void testIsEmpty() {
		IntegerHashMap<String> ihm = new IntegerHashMap<String>(5);

		assertTrue(ihm.isEmpty());
		ihm.put(1, "X");
		assertFalse(ihm.isEmpty());
		ihm.put(2, "Y");
		assertFalse(ihm.isEmpty());
		ihm.put(3, "Z");
		assertFalse(ihm.isEmpty());
		ihm.remove(1);
		assertFalse(ihm.isEmpty());
		ihm.remove(2);
		assertFalse(ihm.isEmpty());
		ihm.remove(3);
		assertTrue(ihm.isEmpty());
	}

	@Test
	public void testSmallHashMap() {
		IntegerHashMap<String> ihm = new IntegerHashMap<String>(5);

		ihm.put(1, "A");
		ihm.put(2, "B");
		ihm.put(3, "C");
		assertEquals("A", ihm.get(1));
		assertEquals("B", ihm.get(2));
		assertEquals("C", ihm.get(3));

		ihm.remove(2);
		assertNull(ihm.get(2));

		ihm.put(1, "D");
		assertEquals("D", ihm.get(1));

		ihm.put(100, "X");
		assertEquals("X", ihm.get(100));

		exception.expect(RuntimeException.class);
		ihm.put(101, "Y");
	}

	@Test
	public void testKeySet() {
		IntegerHashMap<String> ihm = new IntegerHashMap<String>(5);

		ihm.put(1, "A");
		ihm.put(2, "B");
		ihm.put(3, "C");
		List<Integer> expected = Arrays.asList(1, 2, 3);
		List<Integer> actual = (List<Integer>) ihm.keySet();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		ihm.remove(2);
		expected = Arrays.asList(1, 3);
		actual = (List<Integer>) ihm.keySet();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		ihm.put(1, "D");
		expected = Arrays.asList(1, 3);
		actual = (List<Integer>) ihm.keySet();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		ihm.put(100, "X");
		expected = Arrays.asList(100, 1, 3);
		actual = (List<Integer>) ihm.keySet();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		exception.expect(RuntimeException.class);
		ihm.put(101, "Y");
		expected = Arrays.asList(100, 1, 3);
		actual = (List<Integer>) ihm.keySet();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testValueSet() {
		IntegerHashMap<String> ihm = new IntegerHashMap<String>(5);

		ihm.put(1, "A");
		ihm.put(2, "B");
		ihm.put(3, "C");
		List<String> expected = Arrays.asList("A", "B", "C");
		List<String> actual = (List<String>) ihm.values();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		ihm.remove(2);
		expected = Arrays.asList("A", "C");
		actual = (List<String>) ihm.values();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		ihm.put(1, "D");
		expected = Arrays.asList("D", "C");
		actual = (List<String>) ihm.values();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		ihm.put(100, "X");
		expected = Arrays.asList("X", "D", "C");
		actual = (List<String>) ihm.values();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		exception.expect(RuntimeException.class);
		ihm.put(101, "Y");
		expected = Arrays.asList("X", "D", "C");
		actual = (List<String>) ihm.values();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}
}
