import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PositionalListIteratorTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test(timeout = 1000)
	public void testIteratorEmpty() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		Iterator<String> it = ll.iterator();
		assertFalse(it.hasNext());
	}

	@Test(timeout = 1000)
	public void testIteratorFirst() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addFirst("E");
		ll.addFirst("D");
		ll.addFirst("C");
		ll.addFirst("B");
		ll.addFirst("A");
		
		Iterator<String> it = ll.iterator();

		assertTrue(it.hasNext());
		assertEquals("A", it.next());
	}
	
	@Test(timeout = 1000)
	public void testIteratorEverything() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addFirst("A");
		ll.addFirst("B");
		ll.addFirst("C");
		ll.addFirst("D");
		ll.addFirst("E");
		
		Iterator<String> it = ll.iterator();

		assertTrue(it.hasNext());
		assertEquals("E", it.next());
		assertTrue(it.hasNext());
		assertEquals("D", it.next());
		assertTrue(it.hasNext());
		assertEquals("C", it.next());
		assertTrue(it.hasNext());
		assertEquals("B", it.next());
		assertTrue(it.hasNext());
		assertEquals("A", it.next());
		assertFalse(it.hasNext());
	}

	@Test(timeout = 1000)
	public void testIteratorException() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		Iterator<String> it = ll.iterator();
		assertFalse(it.hasNext());

		exception.expect(NoSuchElementException.class);
		it.next();
	}

}
