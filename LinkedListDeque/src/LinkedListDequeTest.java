import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListDequeTest {

	@Test
	public void testSizeZero() {
		LinkedListDeque<String> lld = new LinkedListDeque<String>();
		assertEquals(0, lld.size());
	}

	@Test
	public void testSizeFive() {
		LinkedListDeque<String> lld = new LinkedListDeque<String>();
		lld.addFirst("A");
		lld.addFirst("B");
		lld.addFirst("C");
		lld.addFirst("D");
		lld.addFirst("E");
		assertEquals(5, lld.size());
	}

	@Test
	public void testIsEmpty() {
		LinkedListDeque<String> lld = new LinkedListDeque<String>();
		assertTrue(lld.isEmpty());
	}

	@Test
	public void testIsNotEmpty() {
		LinkedListDeque<String> lld = new LinkedListDeque<String>();
		lld.addFirst("A");
		lld.addFirst("B");
		lld.addFirst("C");
		lld.addFirst("D");
		lld.addFirst("E");
		assertFalse(lld.isEmpty());
	}

	@Test
	public void testPeek() {
		LinkedListDeque<String> lld = new LinkedListDeque<String>();
		lld.addLast("A");
		lld.addLast("B");
		assertEquals("A", lld.peekFirst());
		assertEquals("B", lld.peekLast());
	}

	@Test
	public void testAddFirst() {
		LinkedListDeque<String> lld = new LinkedListDeque<String>();
		lld.addFirst("A");
		lld.addFirst("B");
		lld.addFirst("C");
		lld.addFirst("D");
		lld.addFirst("E");
		assertEquals("E", lld.pollFirst());
		assertEquals("D", lld.pollFirst());
		assertEquals("C", lld.pollFirst());
		assertEquals("B", lld.pollFirst());
		assertEquals("A", lld.pollFirst());
	}

	@Test
	public void testAddLast() {
		LinkedListDeque<String> lld = new LinkedListDeque<String>();
		lld.addLast("A");
		lld.addLast("B");
		lld.addLast("C");
		lld.addLast("D");
		lld.addLast("E");
		assertEquals("A", lld.pollFirst());
		assertEquals("B", lld.pollFirst());
		assertEquals("C", lld.pollFirst());
		assertEquals("D", lld.pollFirst());
		assertEquals("E", lld.pollFirst());
	}

	@Test
	public void testPollFirst() {
		LinkedListDeque<String> lld = new LinkedListDeque<String>();
		lld.addLast("A");
		lld.addLast("B");
		lld.addLast("C");
		lld.addLast("D");
		lld.addLast("E");
		assertEquals("A", lld.pollFirst());
		assertEquals("B", lld.pollFirst());
		assertEquals("C", lld.pollFirst());
		assertEquals("D", lld.pollFirst());
		assertEquals("E", lld.pollFirst());
	}

	@Test
	public void testPollLast() {
		LinkedListDeque<String> lld = new LinkedListDeque<String>();
		lld.addLast("A");
		lld.addLast("B");
		lld.addLast("C");
		lld.addLast("D");
		lld.addLast("E");
		assertEquals("E", lld.pollLast());
		assertEquals("D", lld.pollLast());
		assertEquals("C", lld.pollLast());
		assertEquals("B", lld.pollLast());
		assertEquals("A", lld.pollLast());
	}

}
