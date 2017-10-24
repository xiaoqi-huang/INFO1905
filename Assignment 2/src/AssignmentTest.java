import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import textbook.LinkedBinaryTree;
import textbook.Position;

public class AssignmentTest {

	private LinkedBinaryTree<String> buildTree(String expression) {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		String[] tokens = expression.split(" ");
		if (tokens.length == 1) {
			tree.addRoot(tokens[0]);
		} else if (tokens.length == 2) {
			Position<String> root = tree.addRoot(tokens[0]);
			tree.addLeft(root, tokens[1]);
		} else if (tokens.length == 3) {
			Position<String> root = tree.addRoot(tokens[0]);
			tree.addLeft(root, tokens[1]);
			tree.addRight(root, tokens[2]);
		} else if (tokens.length == 7 ) {
			Position<String> p1 = tree.addRoot(tokens[0]);
			Position<String> p2 = tree.addLeft(p1, tokens[1]);
			Position<String> p3 = tree.addRight(p1, tokens[2]);
			tree.addLeft(p2, tokens[3]);
			tree.addRight(p2, tokens[4]);
			tree.addLeft(p3, tokens[5]);
			tree.addRight(p3, tokens[6]);
		}
		return tree;
	}

	// Set up JUnit to be able to check for expected exceptions
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// Some simple testing of prefix2tree
	@Test(timeout = 100)
	public void testPrefix2tree() {

		LinkedBinaryTree<String> tree;

		tree = Assignment.prefix2tree("hi");
		assertEquals(1, tree.size());
		assertEquals("hi", tree.root().getElement());

		tree = Assignment.prefix2tree("+ 5 10");
		assertEquals(3, tree.size());
		assertEquals("+", tree.root().getElement());
		assertEquals("5", tree.left(tree.root()).getElement());
		assertEquals("10", tree.right(tree.root()).getElement());

		tree = Assignment.prefix2tree("- 5 10");
		assertEquals(3, tree.size());
		assertEquals("-", tree.root().getElement());
		assertEquals("5", tree.left(tree.root()).getElement());
		assertEquals("10", tree.right(tree.root()).getElement());

		tree = Assignment.prefix2tree("* 5 10");
		assertEquals(3, tree.size());
		assertEquals("*", tree.root().getElement());
		assertEquals("5", tree.left(tree.root()).getElement());
		assertEquals("10", tree.right(tree.root()).getElement());

		tree = Assignment.prefix2tree("+ 5 - 4 3");
		assertEquals(5, tree.size());
		assertEquals("+", tree.root().getElement());
		assertEquals("5", tree.left(tree.root()).getElement());
		assertEquals("-", tree.right(tree.root()).getElement());
		assertEquals("4", tree.left(tree.right(tree.root())).getElement());
		assertEquals("3", tree.right(tree.right(tree.root())).getElement());

		thrown.expect(IllegalArgumentException.class);
		tree = Assignment.prefix2tree("+ 5 - 4");
	}

	// example of using the Assignment.equals method to check that "- x + 1 2" simplifies to "- x 3"
	@Test(timeout = 100)
	public void testSimplify1() {
		LinkedBinaryTree<String> tree = Assignment.prefix2tree("- x + 1 2");
		tree = Assignment.simplify(tree);
		LinkedBinaryTree<String> expected = Assignment.prefix2tree("- x 3");
		assertTrue(Assignment.equals(tree, expected));
	}

	/* *************************************************************************
	 * testTree2prefix
	 * ************************************************************************* */
	 @Test(timeout = 100)
	 public void testTree2prefix_Null() {
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.tree2prefix(null);
	 }

	 @Test(timeout = 100)
	 public void testTree2prefix_InvalidTree() {
		 LinkedBinaryTree<String> tree = buildTree("+ 1 - + val 1 val");

		 thrown.expect(IllegalArgumentException.class);
		 Assignment.tree2prefix(tree);
	 }

	 @Test(timeout = 100)
	 public void testTree2prefix_Simple() {
		 LinkedBinaryTree<String> tree = buildTree("+ 1 val");

		assertEquals("+ 1 val", Assignment.tree2prefix(tree));
	 }

	 @Test(timeout = 100)
	 public void testTree2prefix_Complex() {
		 LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		 Position<String> p1 = tree.addRoot("*");
		 Position<String> p2 = tree.addLeft(p1, "*");
		 Position<String> p3 = tree.addRight(p1, "*");
		 tree.addLeft(p2, "1");
		 tree.addRight(p2, "1");
		 Position<String> p6 = tree.addLeft(p3, "*");
		 Position<String> p7 = tree.addRight(p3, "*");
		 Position<String> p8 = tree.addLeft(p6, "*");
		 Position<String> p9 = tree.addRight(p6, "*");
		 tree.addLeft(p7, "1");
		 tree.addRight(p7, "1");
		 tree.addLeft(p8, "1");
		 Position<String> p13 = tree.addRight(p8, "*");
		 tree.addLeft(p9, "1");
		 tree.addRight(p9, "1");
		 tree.addLeft(p13, "1");
		 tree.addRight(p13, "1");

		 assertEquals("* * 1 1 * * * 1 * 1 1 * 1 1 * 1 1", Assignment.tree2prefix(tree));
	 }

	/* *************************************************************************
	 * testTree2infix
	 * ************************************************************************* */
	 @Test(timeout = 100)
	 public void testTree2infix_Null() {
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.tree2infix(null);
	 }

	 @Test(timeout = 100)
	 public void testTree2infix_InvalidTree() {
		 LinkedBinaryTree<String> tree = buildTree("+ 1 - + val 1 val");

		 thrown.expect(IllegalArgumentException.class);
		 Assignment.tree2infix(tree);
	 }

	 @Test(timeout = 100)
	 public void testTree2infix_Simple() {
		 LinkedBinaryTree<String> tree = buildTree("+ 1 val");

		assertEquals("(1+val)", Assignment.tree2infix(tree));
	 }

	 @Test(timeout = 100)
	 public void testTree2infix_Complex() {
		 LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();
		 Position<String> p1 = tree.addRoot("*");
		 Position<String> p2 = tree.addLeft(p1, "*");
		 Position<String> p3 = tree.addRight(p1, "*");
		 tree.addLeft(p2, "1");
		 tree.addRight(p2, "1");
		 Position<String> p6 = tree.addLeft(p3, "*");
		 Position<String> p7 = tree.addRight(p3, "*");
		 Position<String> p8 = tree.addLeft(p6, "*");
		 Position<String> p9 = tree.addRight(p6, "*");
		 tree.addLeft(p7, "1");
		 tree.addRight(p7, "1");
		 tree.addLeft(p8, "1");
		 Position<String> p13 = tree.addRight(p8, "*");
		 tree.addLeft(p9, "1");
		 tree.addRight(p9, "1");
		 tree.addLeft(p13, "1");
		 tree.addRight(p13, "1");

		 assertEquals("((1*1)*(((1*(1*1))*(1*1))*(1*1)))", Assignment.tree2infix(tree));
	 }

	/* *************************************************************************
	 * testSimplify
	 * ************************************************************************* */
	 @Test(timeout = 100)
	 public void testSimplify_Null() {
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.simplify(null);
	 }

	 @Test(timeout = 100)
	 public void testSimplify_InvalidTree() {
		 LinkedBinaryTree<String> tree = buildTree("+ 1 - + val 1 val");

		 thrown.expect(IllegalArgumentException.class);
		 Assignment.simplify(tree);
	 }

	 @Test(timeout = 100)
	 public void testSimplify_Simple() {
		 LinkedBinaryTree<String> tree = Assignment.prefix2tree("* 1 2");
		 LinkedBinaryTree<String> actual = Assignment.simplifyFancy(tree);
		 LinkedBinaryTree<String> expected = Assignment.prefix2tree("2");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("+ 1 2");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("3");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("- 1 2");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("-1");
		 assertTrue(Assignment.equals(actual, expected));
	 }

	 @Test(timeout = 100)
	 public void testSimplify_Complex() {
		 LinkedBinaryTree<String> tree = Assignment.prefix2tree("* + * 2 3 5 + - 5 2 x");
		 LinkedBinaryTree<String> actual = Assignment.simplifyFancy(tree);
		 LinkedBinaryTree<String> expected = Assignment.prefix2tree("* 11 + 3 x");
		 assertTrue(Assignment.equals(actual, expected));
	 }

	 /* *************************************************************************
 	 * testSimplifyFancy
 	 * ************************************************************************* */
	 @Test(timeout = 100)
	 public void testSimplifyFancy_Null() {
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.simplifyFancy(null);
	 }

	 @Test(timeout = 100)
	 public void testSimplifyFancy_InvalidTree() {
		 LinkedBinaryTree<String> tree = buildTree("+ 1 - + val 1 val");

		 thrown.expect(IllegalArgumentException.class);
		 Assignment.simplifyFancy(tree);
	 }

	 @Test(timeout = 100)
	 public void testSimplifyFancy_SimpleVariable() {
		 LinkedBinaryTree<String> tree = Assignment.prefix2tree("* 1 x");
		 LinkedBinaryTree<String> actual = Assignment.simplifyFancy(tree);
		 LinkedBinaryTree<String> expected = Assignment.prefix2tree("x");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("* x 1");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("x");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("* 0 x");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("0");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("* x 0");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("0");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("+ 0 x");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("x");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("+ x 0");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("x");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("- x 0");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("x");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("- x x");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("0");
		 assertTrue(Assignment.equals(actual, expected));
	 }

	 @Test(timeout = 100)
	 public void testSimplifyFancy_SimpleOperator() {
		 LinkedBinaryTree<String> tree = Assignment.prefix2tree("* 1 + a b");
		 LinkedBinaryTree<String> actual = Assignment.simplifyFancy(tree);
		 LinkedBinaryTree<String> expected = Assignment.prefix2tree("+ a b");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("* + a b 1");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("+ a b");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("* 0 + a b");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("0");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("* + a b 0");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("0");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("+ 0 + a b");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("+ a b");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("+ + a b 0");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("+ a b");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("- + a b 0");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("+ a b");
		 assertTrue(Assignment.equals(actual, expected));

		 tree = Assignment.prefix2tree("- * a b * a b");
		 actual = Assignment.simplifyFancy(tree);
		 expected = Assignment.prefix2tree("0");
		 assertTrue(Assignment.equals(actual, expected));
	 }

	 @Test(timeout = 100)
	 public void testSimplifyFancy_Complex() {
		 LinkedBinaryTree<String> tree = Assignment.prefix2tree("* + 1 2 - + * 1 - a 0 * b 1 + + a b * + a 0 * 0 + 0 b");
		 LinkedBinaryTree<String> actual = Assignment.simplifyFancy(tree);
		 LinkedBinaryTree<String> expected = Assignment.prefix2tree("0");
		 assertTrue(Assignment.equals(actual, expected));
	 }

	/* *************************************************************************
	 * testSubstitute
	 * ************************************************************************* */
	 @Test(timeout = 100)
	 public void testSubstitute_Null() {
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.substitute(null, "a", 1);
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.substitute(new LinkedBinaryTree<String>(), null, 1);
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.substitute(null, null, 1);
	 }

	 @Test(timeout = 100)
	 public void testSubstitute_InvalidTree() {
		 LinkedBinaryTree<String> tree = buildTree("+ 1 - + val 1 val");

		 thrown.expect(IllegalArgumentException.class);
		 Assignment.substitute(tree, "a", 1);
	 }

	 @Test(timeout = 100)
	 public void testSubstitute_Simple() {
		 LinkedBinaryTree<String> tree = Assignment.prefix2tree("* * a b * a b");

		 LinkedBinaryTree<String> actual = Assignment.substitute(tree, "a", 1);
		 LinkedBinaryTree<String> expected = Assignment.prefix2tree("* * 1 b * 1 b");
		 assertTrue(Assignment.equals(actual, expected));
	 }

	 @Test(timeout = 100)
	 public void testSubstitute_Complex() {
		 LinkedBinaryTree<String> tree = Assignment.prefix2tree("* * a * b c * * * a 0 * d * d e * f b");

		 LinkedBinaryTree<String> actual = Assignment.substitute(tree, "a", 1);
		 LinkedBinaryTree<String> expected = Assignment.prefix2tree("* * 1 * b c * * * 1 0 * d * d e * f b");
		 assertTrue(Assignment.equals(actual, expected));
	 }

	/* *************************************************************************
	 * testSubstituteMap
	 * ************************************************************************* */
	 @Test(timeout = 100)
	 public void testSubstituteMap_Null() {
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.substitute(null, new HashMap<String, Integer>());
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.substitute(new LinkedBinaryTree<String>(), null);
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.substitute(null, null);
	 }

	 @Test(timeout = 100)
	 public void testSubstituteMap_InvalidTree() {
		 LinkedBinaryTree<String> tree = buildTree("+ 1 - + val 1 val");

		 thrown.expect(IllegalArgumentException.class);
		 Assignment.substitute(tree, new HashMap<String, Integer>());
	 }

	 @Test(timeout = 100)
	 public void testSubstituteMap_SubstitueNull() {
		 LinkedBinaryTree<String> tree = Assignment.prefix2tree("* * a b * a b");
		 HashMap<String, Integer> map = new HashMap<String, Integer>();
		 map.put("a", 1);
		 map.put("b", null);
		 thrown.expect(IllegalArgumentException.class);
		 Assignment.substitute(tree, map);
	 }

	 @Test(timeout = 100)
	 public void testSubstituteMap_Simple() {
		 LinkedBinaryTree<String> tree = Assignment.prefix2tree("* * a b * a b");
		 HashMap<String, Integer> map = new HashMap<String, Integer>();
		 map.put("a", 1);
		 map.put("b", 2);

		 LinkedBinaryTree<String> actual = Assignment.substitute(tree, map);
		 LinkedBinaryTree<String> expected = Assignment.prefix2tree("* * 1 2 * 1 2");
		 assertTrue(Assignment.equals(actual, expected));
	 }

	 @Test(timeout = 100)
	 public void testSubstituteMap_Complex() {
		 LinkedBinaryTree<String> tree = Assignment.prefix2tree("* * a * b c * * * a 0 * d * d e * f x");
		 HashMap<String, Integer> map = new HashMap<String, Integer>();
		 map.put("a", 1);
		 map.put("b", 2);
		 map.put("c", 3);
		 map.put("d", 4);
		 map.put("e", 5);
		 map.put("f", 6);

		 LinkedBinaryTree<String> actual = Assignment.substitute(tree, map);
		 LinkedBinaryTree<String> expected = Assignment.prefix2tree("* * 1 * 2 3 * * * 1 0 * 4 * 4 5 * 6 x");
		 assertTrue(Assignment.equals(actual, expected));
	 }

	/* *************************************************************************
	 * testIsArithmeticExpression
	 * ************************************************************************* */
	 @Test(timeout = 100)
	 public void testIsArithmeticExpression_Null() {
		 assertFalse(Assignment.isArithmeticExpression(null));
	 }

	 @Test(timeout = 100)
	 public void testIsArithmeticExpression_OneOperator() {
		 LinkedBinaryTree<String> tree = buildTree("-");
		 assertFalse(Assignment.isArithmeticExpression(tree));
	 }

	 @Test(timeout = 100)
	 public void testIsArithmeticExpression_OneValue() {
		 LinkedBinaryTree<String> tree = buildTree("1");
		 assertTrue(Assignment.isArithmeticExpression(tree));
	 }

	 @Test(timeout = 100)
	 public void testIsArithmeticExpression_OneVariable() {
		 LinkedBinaryTree<String> tree = buildTree("val");
		 assertTrue(Assignment.isArithmeticExpression(tree));
	 }

	 @Test(timeout = 100)
	 public void testIsArithmeticExpression_Simple() {
		 LinkedBinaryTree<String> tree = buildTree("* 1");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("* val");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("1 *");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("1 val");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("val 1");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("val *");
		 assertFalse(Assignment.isArithmeticExpression(tree));

		 tree = buildTree("* * 1");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("* * val");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("* 1 val");
		 assertTrue(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("1 * 1");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("1 * val");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("1 1 val");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("val * 1");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("val * val");
		 assertFalse(Assignment.isArithmeticExpression(tree));
		 tree = buildTree("val 1 val");
		 assertFalse(Assignment.isArithmeticExpression(tree));
	 }

	 @Test(timeout = 100)
	 public void testIsArithmeticExpression_ComplexValid() {
		 LinkedBinaryTree<String> tree = buildTree("* + - 1 val val 1");
		 assertTrue(Assignment.isArithmeticExpression(tree));
	 }

	 @Test(timeout = 100)
	 public void testIsArithmeticExpression_ComplexInvalid() {
		 LinkedBinaryTree<String> tree = buildTree("+ 1 - + val 1 val");
		 assertFalse(Assignment.isArithmeticExpression(tree));
	 }
}
