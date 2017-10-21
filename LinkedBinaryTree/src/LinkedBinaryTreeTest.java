import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.asList;

import org.junit.Test;

public class LinkedBinaryTreeTest {

	@Test
	public void testCountNoLeaves() {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		assertEquals(0, tree.countLeaves());
	}

	@Test
	public void testCountOneLeaves() {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		tree.addRoot("Fred");
		assertEquals(1, tree.countLeaves());
	}

	@Test
	public void testCountFourLeaves() {
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
		assertEquals(4, tree.countLeaves());
	}

	@Test
	public void testIsProperEmpty() {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		assertTrue(tree.isProper());
	}

	@Test
	public void testIsProperOneNode() {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		tree.addRoot("Fred");
		assertTrue(tree.isProper());
	}

	@Test
	public void testIsNotProper() {
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
		assertFalse(tree.isProper());
	}

	@Test
	public void testIsProper() {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		Position<String> a = tree.addRoot("A");
        Position<String> b = tree.addLeft(a, "B");
        Position<String> c = tree.addRight(a, "C");
        Position<String> d = tree.addLeft(c, "D");
        Position<String> e = tree.addRight(c, "E");
        Position<String> f = tree.addLeft(e, "F");
        Position<String> g = tree.addRight(e, "G");
		assertTrue(tree.isProper());
	}

	@Test
	public void testPreOrder() {
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
		ArrayList<String> actual = tree.preOrder();
		List<String> expected = asList("Fred", "Jane", "Pete", "Kate", "Dave", "Emma", "Alex", "Maya", "Nick");
		assertTrue(actual.equals(expected));
	}

}
