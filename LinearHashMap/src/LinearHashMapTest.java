import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class LinearHashMapTest<K, V> {

	@Test
	public void testConstruction() {
		LinearHashMap<Integer, V> lhm = new LinearHashMap<Integer, V>(5);

		assertEquals(0, lhm.size());
		assertTrue(lhm.isEmpty());

		ArrayList<Integer> keySet = (ArrayList<Integer>)lhm.keySet();
		assertTrue(keySet.isEmpty());
		assertFalse(keySet.iterator().hasNext());

		ArrayList<V> valueSet = (ArrayList<V>)lhm.values();
		assertTrue(valueSet.isEmpty());
		assertFalse(valueSet.iterator().hasNext());

		ArrayList<Entry<Integer, V>> entrySet = (ArrayList<Entry<Integer, V>>)lhm.entrySet();
		assertTrue(entrySet.isEmpty());
		assertFalse(entrySet.iterator().hasNext());

		assertNull(lhm.get(1));
		assertNull(lhm.remove(1));
	}

	@Test
	public void testSize() {
		LinearHashMap<Integer, String> lhm = new LinearHashMap<Integer, String>(5);

		assertEquals(0, lhm.size());
		lhm.put(1, "X");
		assertEquals(1, lhm.size());
		lhm.put(2, "Y");
		assertEquals(2, lhm.size());
		lhm.put(3, "Z");
		assertEquals(3, lhm.size());
		lhm.remove(1);
		assertEquals(2, lhm.size());
		lhm.remove(2);
		assertEquals(1, lhm.size());
		lhm.remove(3);
		assertEquals(0, lhm.size());
	}

	@Test
	public void testIsEmpty() {
		LinearHashMap<Integer, String> lhm = new LinearHashMap<Integer, String>(5);

		assertTrue(lhm.isEmpty());
		lhm.put(1, "X");
		assertFalse(lhm.isEmpty());
		lhm.put(2, "Y");
		assertFalse(lhm.isEmpty());
		lhm.put(3, "Z");
		assertFalse(lhm.isEmpty());
		lhm.remove(1);
		assertFalse(lhm.isEmpty());
		lhm.remove(2);
		assertFalse(lhm.isEmpty());
		lhm.remove(3);
		assertTrue(lhm.isEmpty());
	}

	@Test
	public void testSmallHashMap() {
		LinearHashMap<Integer, String> lhm = new LinearHashMap<Integer, String>(5);

		lhm.put(1, "A");
		lhm.put(2, "B");
		lhm.put(3, "C");
		assertEquals("A", lhm.get(1));
		assertEquals("B", lhm.get(2));
		assertEquals("C", lhm.get(3));

		lhm.remove(2);
		assertNull(lhm.get(2));

		lhm.put(1, "D");
		assertEquals("D", lhm.get(1));

		lhm.put(100, "X");
		assertEquals("X", lhm.get(100));

		lhm.put(101, "Y");
		assertEquals("Y", lhm.get(101));
	}

	@Test
	public void testKeySet() {
		LinearHashMap<Integer, String> lhm = new LinearHashMap<Integer, String>(5);

		lhm.put(1, "A");
		lhm.put(2, "B");
		lhm.put(3, "C");
		List<Integer> expected = Arrays.asList(1, 2, 3);
		List<Integer> actual = (List<Integer>) lhm.keySet();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		lhm.remove(2);
		expected = Arrays.asList(1, 3);
		actual = (List<Integer>) lhm.keySet();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		lhm.put(1, "D");
		expected = Arrays.asList(1, 3);
		actual = (List<Integer>) lhm.keySet();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		lhm.put(100, "X");
		expected = Arrays.asList(100, 1, 3);
		actual = (List<Integer>) lhm.keySet();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		lhm.put(101, "Y");
		expected = Arrays.asList(100, 1, 101, 3);
		actual = (List<Integer>) lhm.keySet();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testValueSet() {
		LinearHashMap<Integer, String> lhm = new LinearHashMap<Integer, String>(5);

		lhm.put(1, "A");
		lhm.put(2, "B");
		lhm.put(3, "C");
		List<String> expected = Arrays.asList("A", "B", "C");
		List<String> actual = (List<String>) lhm.values();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		lhm.remove(2);
		expected = Arrays.asList("A", "C");
		actual = (List<String>) lhm.values();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		lhm.put(1, "D");
		expected = Arrays.asList("D", "C");
		actual = (List<String>) lhm.values();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		lhm.put(100, "X");
		expected = Arrays.asList("X", "D", "C");
		actual = (List<String>) lhm.values();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);

		lhm.put(101, "Y");
		expected = Arrays.asList("X", "D", "Y", "C");
		actual = (List<String>) lhm.values();
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testHashFunction_StringKey() {
		LinearHashMap<String, String> lhm = new LinearHashMap(5);

		lhm.put("K", "V");
		assertEquals("V", lhm.get("K"));
	}

	@Test
	public void testHashFunction_IntegerKey() {
		LinearHashMap<Integer, String> lhm = new LinearHashMap(5);

		lhm.put(-3, "X");
		assertEquals("X", lhm.get(-3));
	}

	@Test
	public void testCollisions() {
		LinearHashMap<Integer, String> lhm = new LinearHashMap(4);

		lhm.put(0, "A");
		lhm.put(1, "B");
		assertEquals("A", lhm.get(0));
		assertEquals("B", lhm.get(1));

		lhm.put(4, "C");
		lhm.put(5, "D");
		assertEquals(4, lhm.size());
		assertEquals("A", lhm.get(0));
		assertEquals("B", lhm.get(1));
		assertEquals("C", lhm.get(4));
		assertEquals("D", lhm.get(5));

		lhm.remove(4);
		assertEquals(3, lhm.size());
		assertEquals("A", lhm.get(0));
		assertEquals("B", lhm.get(1));
		assertNull(lhm.get(4));
		assertEquals("D", lhm.get(5));
		lhm.remove(5);
		assertEquals(2, lhm.size());
		assertEquals("A", lhm.get(0));
		assertEquals("B", lhm.get(1));
		assertNull(lhm.get(4));
		assertNull(lhm.get(5));
		lhm.remove(0);
		assertEquals(1, lhm.size());
		assertNull(lhm.get(0));
		assertEquals("B", lhm.get(1));
		assertNull(lhm.get(4));
		assertNull(lhm.get(5));
		lhm.remove(1);
		assertEquals(0, lhm.size());
		assertNull(lhm.get(0));
		assertNull(lhm.get(1));
		assertNull(lhm.get(4));
		assertNull(lhm.get(5));
	}

	@Test
	public void testExpansion() {
		LinearHashMap<Integer, String> lhm = new LinearHashMap<Integer, String>(2);

		lhm.put(0, "A");
		lhm.put(1, "B");
		lhm.put(2, "C");
		lhm.put(3, "D");

		assertEquals("A", lhm.get(0));
		assertEquals("B", lhm.get(1));
		assertEquals("C", lhm.get(2));
		assertEquals("D", lhm.get(3));

		assertEquals("A", lhm.remove(0));
		assertEquals("B", lhm.remove(1));
		assertEquals("C", lhm.remove(2));
		assertEquals("D", lhm.remove(3));
		
		assertEquals(0, lhm.size());
		assertTrue(lhm.isEmpty());
	}
}
