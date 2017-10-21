import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class LinkedBinaryTreeIteratorTest {

	@Test(timeout = 1000)
	public void testIteratorEverything() {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		Position<String> fred = tree.addRoot("Fred");
        Position<String> jane = tree.addLeft(fred, "Jane");
        Position<String> pete = tree.addLeft(jane, "Pete");
        Position<String> kate = tree.addRight(jane, "Kate");
        Position<String> dave = tree.addLeft(kate, "Dave");
        Position<String> emma = tree.addRight(kate, "Emma");
        Position<String> alex = tree.addRight(fred, "Alex");
        Position<String> maya = tree.addRight(alex, "Maya");
        Position<String> nick = tree.addLeft(maya, "Nick");
		Iterator<String> it = tree.iterator();

		assertTrue(it.hasNext());
		assertEquals("Jane", it.next());
		assertTrue(it.hasNext());
		assertEquals("Pete", it.next());
		assertTrue(it.hasNext());
		assertEquals("Kate", it.next());
		assertTrue(it.hasNext());
		assertEquals("Dave", it.next());
		assertTrue(it.hasNext());
		assertEquals("Emma", it.next());
		assertTrue(it.hasNext());
		assertEquals("Alex", it.next());
		assertTrue(it.hasNext());
		assertEquals("Maya", it.next());
		assertTrue(it.hasNext());
		assertEquals("Nick", it.next());
		assertFalse(it.hasNext());
	}

}
