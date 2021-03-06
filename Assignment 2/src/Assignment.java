import java.util.HashMap;
import java.util.Map.Entry;

import textbook.LinkedBinaryTree;
import textbook.LinkedQueue;
import textbook.Position;

public class Assignment {

	/* *************************************************************************
	 * Helper methods
	 * ************************************************************************* */

	 private static boolean isOperator(String element) {
		 return (element != null) && (element.equals("+") || element.equals("-") || element.equals("*"));
	 }

	 private static boolean isOperator(Position<String> node) {
		 return (node != null) && isOperator(node.getElement());
	 }

	 private static boolean isInteger(String element) {
		 if (element == null) {
			 return false;
		 }
		 try {
			 Integer.parseInt(element);
			 return true;
		 } catch (Exception e) {
			 return false;
		 }
	 }

	 private static boolean isInteger(Position<String> node) {
		 return (node != null) && isInteger(node.getElement());
	}

	private static boolean isVariable(String element) {
		return (element != null) && (!isOperator(element) && !isInteger(element));
	}

	private static boolean isVariable(Position<String> node) {
		return (node != null) && isVariable(node.getElement());
	}

	private static String calculate(Position<String> node, Position<String> left, Position<String> right) {
 		int leftOperand = Integer.parseInt(left.getElement());
 		int rightOperand = Integer.parseInt(right.getElement());

 		if (node.getElement().equals("+")) {
 			return String.valueOf(leftOperand + rightOperand);
 		}
		if (node.getElement().equals("-")) {
			return String.valueOf(leftOperand - rightOperand);
 		}
		if (node.getElement().equals("*")) {
			return String.valueOf(leftOperand * rightOperand);
 		}
		return null;
	}

	/* *************************************************************************
	 * Main methods
	 * ************************************************************************* */

	/**
	 * Convert an arithmetic expression (in prefix notation), to a binary tree
	 *
	 * Binary operators are +, -, * (i.e. addition, subtraction, multiplication)
	 * Anything else is assumed to be a variable or numeric value
	 *
	 * Example: "+ 2 15" will be a tree with root "+", left child "2" and right
	 * child "15" i.e. + 2 15
	 *
	 * Example: "+ 2 - 4 5" will be a tree with root "+", left child "2", right
	 * child a subtree representing "- 4 5" i.e. + 2 - 4 5
	 *
	 * This method runs in O(n) time
	 *
	 * @param expression
	 *            - an arithmetic expression in prefix notation
	 * @return BinaryTree representing an expression expressed in prefix
	 *         notation
	 * @throws IllegalArgumentException
	 *             if expression was not a valid expression
	 */
	public static LinkedBinaryTree<String> prefix2tree(String expression) throws IllegalArgumentException {
		if (expression == null) {
			throw new IllegalArgumentException("Expression string was null");
		}
		// break up the expression string using spaces, into a queue
		LinkedQueue<String> tokens = new LinkedQueue<String>();
		for (String token : expression.split(" ")) {
			tokens.enqueue(token);
		}
		// recursively build the tree
		return prefix2tree(tokens);
	}

	/**
	 * Recursive helper method to build an tree representing an arithmetic
	 * expression in prefix notation, where the expression has already been
	 * broken up into a queue of tokens
	 *
	 * @param tokens
	 * @return
	 * @throws IllegalArgumentException
	 *             if expression was not a valid expression
	 */
	private static LinkedBinaryTree<String> prefix2tree(LinkedQueue<String> tokens) throws IllegalArgumentException {
		LinkedBinaryTree<String> tree = new LinkedBinaryTree<String>();

		// use the next element of the queue to build the root
		if (tokens.isEmpty()) {
			throw new IllegalArgumentException("String was not a valid arithmetic expression in prefix notation");
		}
		String element = tokens.dequeue();
		tree.addRoot(element);

		// if the element is a binary operation, we need to build the left and
		// right subtrees
		if (element.equals("+") || element.equals("-") || element.equals("*")) {
			LinkedBinaryTree<String> left = prefix2tree(tokens);
			LinkedBinaryTree<String> right = prefix2tree(tokens);
			tree.attach(tree.root(), left, right);
		}
		// otherwise, assume it's a variable or a value, so it's a leaf (i.e.
		// nothing more to do)

		return tree;
	}

	/**
	 * Test to see if two trees are identical (every position in the tree stores the same value)
	 *
	 * e.g. two trees representing "+ 1 2" are identical to each other, but not to a tree representing "+ 2 1"
	 * @param a
	 * @param b
	 * @return true if the trees have the same structure and values, false otherwise
	 */
	 /**
 	 * Test to see if two trees are identical (every position in the tree stores the same value)
 	 *
 	 * e.g. two trees representing "+ 1 2" are identical to each other, but not to a tree representing "+ 2 1"
 	 * @param a
 	 * @param b
 	 * @return true if the trees have the same structure and values, false otherwise
 	 */
 	public static boolean equals(LinkedBinaryTree<String> a, LinkedBinaryTree<String> b) {
 		return equals(a, b, a.root(), b.root());
 	}

 	/**
 	 * Recursive helper method to compare two trees
 	 * @param aTree one of the trees to compare
 	 * @param bTree the other tree to compare
 	 * @param aRoot a position in the first tree
 	 * @param bRoot a position in the second tree (corresponding to a position in the first)
 	 * @return true if the subtrees rooted at the given positions are identical
 	 */
 	private static boolean equals(LinkedBinaryTree<String> aTree, LinkedBinaryTree<String> bTree, Position<String> aRoot, Position<String> bRoot) {
 		//if either of the positions is null, then they are the same only if they are both null
 		if(aRoot == null || bRoot == null) {
 			return (aRoot == null) && (bRoot == null);
 		}
 		//first check that the elements stored in the current positions are the same
 		String a = aRoot.getElement();
 		String b = bRoot.getElement();
 		if((a==null && b==null) || a.equals(b)) {
 			//then recursively check if the left subtrees are the same...
 			boolean left = equals(aTree, bTree, aTree.left(aRoot), bTree.left(bRoot));
 			//...and if the right subtrees are the same
 			boolean right = equals(aTree, bTree, aTree.right(aRoot), bTree.right(bRoot));
 			//return true if they both matched
 			return left && right;
 		}
 		else {
 			return false;
 		}
 	}

	/**
	 * Given a tree, this method should output a string for the corresponding
	 * arithmetic expression in prefix notation, without (parenthesis) (also
	 * known as Polish notation)
	 *
	 * Example: A tree with root "+", left child "2" and right child "15" would
	 * be "+ 2 15" Example: A tree with root "-", left child a subtree
	 * representing "(2+15)" and right child "4" would be "- + 2 15 4"
	 *
	 * Ideally, this method should run in O(n) time
	 *
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @return prefix notation expression of the tree
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression
	 */
	public static String tree2prefix(LinkedBinaryTree<String> tree) throws IllegalArgumentException {
		if (tree == null) {
			throw new IllegalArgumentException();
		}
		if (!isArithmeticExpression(tree)) {
			throw new IllegalArgumentException();
		}
		Position<String> root = tree.root();
		return tree2prefix(tree, root);
	}

	private static String tree2prefix(LinkedBinaryTree<String> tree, Position<String> node) throws IllegalArgumentException {
		String element = node.getElement();
		// If the element is a value or variable, simply return it
		if (!isOperator(element)) {
			return element;
		}
		// If the element is an operator
		return element + " " + tree2prefix(tree, tree.left(node)) + " " + tree2prefix(tree, tree.right(node));
	}

	/**
	 * Given a tree, this method should output a string for the corresponding
	 * arithmetic expression in infix notation with parenthesis (i.e. the most
	 * commonly used notation).
	 *
	 * Example: A tree with root "+", left child "2" and right child "15" would
	 * be "(2+15)"
	 *
	 * Example: A tree with root "-", left child a subtree representing "(2+15)"
	 * and right child "4" would be "((2+15)-4)"
	 *
	 * Optionally, you may leave out the outermost parenthesis, but it's fine to
	 * leave them on. (i.e. "2+15" and "(2+15)-4" would also be acceptable
	 * output for the examples above)
	 *
	 * Ideally, this method should run in O(n) time
	 *
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @return infix notation expression of the tree
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression
	 */
	public static String tree2infix(LinkedBinaryTree<String> tree) throws IllegalArgumentException {
		if (tree == null) {
			throw new IllegalArgumentException("Tree was null");
		}
		if (!isArithmeticExpression(tree)) {
			throw new IllegalArgumentException();
		}
		Position<String> root = tree.root();
		return tree2infix(tree, root);
	}

	private static String tree2infix(LinkedBinaryTree<String> tree, Position<String> node) throws IllegalArgumentException {
		String element = node.getElement();
		// If the element is a value or variable, simply return it
		if (!isOperator(element)) {
			return element;
		}
		// If the element is an operator
		return "(" + tree2infix(tree, tree.left(node)) + element + tree2infix(tree, tree.right(node)) + ")";
	}

	/**
	 * Given a tree, this method should simplify any subtrees which can be
	 * evaluated to a single integer value.
	 *
	 * Ideally, this method should run in O(n) time
	 *
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @return resulting binary tree after evaluating as many of the subtrees as
	 *         possible
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression
	 */
	public static LinkedBinaryTree<String> simplify(LinkedBinaryTree<String> tree) throws IllegalArgumentException {
		if (tree == null) {
			throw new IllegalArgumentException("Tree was null");
		}
		if (!isArithmeticExpression(tree)) {
			throw new IllegalArgumentException();
		}
		Position<String> root = tree.root();
		return simplify(tree, root);
	}

	private static LinkedBinaryTree<String> simplify(LinkedBinaryTree<String> tree, Position<String> node) throws IllegalArgumentException {
		LinkedBinaryTree<String> subtree = new LinkedBinaryTree<String>();
		// If the node is a variable or a value
		if (!isOperator(node)) {
			subtree.addRoot(node.getElement());
			return subtree;
		}
		// If the node is an operator
		LinkedBinaryTree<String> leftTree = simplify(tree, tree.left(node));
		LinkedBinaryTree<String> rightTree = simplify(tree, tree.right(node));
		// If the simplified leftTree and rightTree have values in their roots,
		// do calculation using the operator in the node
		if (isInteger(leftTree.root()) && isInteger(rightTree.root())) {
			String newElement = calculate(node, leftTree.root(), rightTree.root());
			subtree.addRoot(newElement);
			return subtree;
		}
		// Otherwise, attach simplified leftTree and rightTree to the node
		subtree.addRoot(node.getElement());
		subtree.attach(subtree.root(), leftTree, rightTree);
		return subtree;
	}

	/**
	 * This should do everything the simplify method does AND also apply the following rules:
	 *  * 1 x == x  i.e.  (1*x)==x
	 *  * x 1 == x        (x*1)==x
	 *  * 0 x == 0        (0*x)==0
	 *  * x 0 == 0        (x*0)==0
	 *  + 0 x == x        (0+x)==x
	 *  + x 0 == x        (x+0)==x
	 *  - x 0 == x        (x-0)==x
	 *  - x x == 0        (x-x)==0
	 *
	 *  Example: - * 1 x x == 0, in infix notation: ((1*x)-x) = (x-x) = 0
	 *
	 * Ideally, this method should run in O(n) time (harder to achieve than for other methods!)
	 *
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @return resulting binary tree after applying the simplifications
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression
	 */
	public static LinkedBinaryTree<String> simplifyFancy(LinkedBinaryTree<String> tree) throws IllegalArgumentException {
		if (tree == null) {
			throw new IllegalArgumentException("Tree was null");
		}
		if (!isArithmeticExpression(tree)) {
			throw new IllegalArgumentException();
		}
		Position<String> root = tree.root();
		return simplifyFancy(tree, root);
	}

	private static LinkedBinaryTree<String> simplifyFancy(LinkedBinaryTree<String> tree, Position<String> node) throws IllegalArgumentException {
		LinkedBinaryTree<String> subtree = new LinkedBinaryTree<String>();
		// If the node is a variable or a value, add the element of the node to the subtree
		if (!isOperator(node)) {
			subtree.addRoot(node.getElement());
			return subtree;
		}
		// Otherwise, call this method itself on both subtrees of the node
		LinkedBinaryTree<String> leftTree = simplifyFancy(tree, tree.left(node));
		LinkedBinaryTree<String> rightTree = simplifyFancy(tree, tree.right(node));

		if (isInteger(leftTree.root()) && isInteger(rightTree.root())) {
			String newElement = calculate(node, leftTree.root(), rightTree.root());
			subtree.addRoot(newElement);
			return subtree;
		}
		// 1 * x = x; x * 1 = x; 0 * x = 0; x * 0 = 0
		if (node.getElement().equals("*")) {
			if (leftTree.root().getElement().equals("1")) {
				return rightTree;
			}
			if (rightTree.root().getElement().equals("1")) {
				return leftTree;
			}
			if (leftTree.root().getElement().equals("0") || rightTree.root().getElement().equals("0")) {
				subtree.addRoot("0");
				return subtree;
			}
		}
		// 0 + x = x; x + 0 = x
		if (node.getElement().equals("+")) {
			if (leftTree.root().getElement().equals("0")) {
				return rightTree;
			}
			if (rightTree.root().getElement().equals("0")) {
				return leftTree;
			}
		}
		// x - 0 = x; x - x = 0
		if (node.getElement().equals("-")) {
			if (rightTree.root().getElement().equals("0")) {
				return leftTree;
			}
			if (equals(leftTree, rightTree)) {
				subtree.addRoot("0");
				return subtree;
			}
		}
		subtree.addRoot(node.getElement());
		subtree.attach(subtree.root(), leftTree, rightTree);
		return subtree;
	}

	/**
	 * Given a tree, a variable label and a value, this should replace all
	 * instances of that variable in the tree with the given value
	 *
	 * Ideally, this method should run in O(n) time (quite hard! O(n^2) is easier.)
	 *
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @param variable
	 *            - a variable label that might exist in the tree
	 * @param value
	 *            - an integer value that the variable represents
	 * @return Tree after replacing all instances of the specified variable with
	 *         it's numeric value
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression, or either of the other
	 *             arguments are null
	 */
	public static LinkedBinaryTree<String> substitute(LinkedBinaryTree<String> tree, String variable, int value)
			throws IllegalArgumentException {
		if (tree == null || variable == null) {
			throw new IllegalArgumentException();
		}
		if (!isVariable(variable)) {
			throw new IllegalArgumentException();
		}
		if (!isArithmeticExpression(tree)) {
			throw new IllegalArgumentException();
		}
		Position<String> root = tree.root();
		return substitute(tree, root, variable, value);
	}

	private static LinkedBinaryTree<String> substitute(LinkedBinaryTree<String> tree, Position<String> node, String variable, int value) throws IllegalArgumentException {
		LinkedBinaryTree<String> subtree = new LinkedBinaryTree<String>();
		// If the node is an integer, just return it
		if (isInteger(node)) {
			subtree.addRoot(node.getElement());
			return subtree;
		}
		// If the node is a variable
		if (isVariable(node)) {
			// If the node equals to the variable, substitue it with the value
			if (node.getElement().equals(variable)) {
				subtree.addRoot(String.valueOf(value));
			} else {
				subtree.addRoot(node.getElement());
			}
			return subtree;
		}
		// If the node is an operator, combine its substituted subtrees (call this method recursively)
		LinkedBinaryTree<String> leftTree = substitute(tree, tree.left(node), variable, value);
		LinkedBinaryTree<String> rightTree = substitute(tree, tree.right(node), variable, value);
		subtree.addRoot(node.getElement());
		subtree.attach(subtree.root(), leftTree, rightTree);
		return subtree;
	}

	/**
	 * Given a tree and a a map of variable labels to values, this should
	 * replace all instances of those variables in the tree with the
	 * corresponding given values
	 *
	 * Ideally, this method should run in O(n) expected time
	 *
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @param map
	 *            - a map of variable labels to integer values
	 * @return Tree after replacing all instances of variables which are keys in
	 *         the map, with their numeric values
	 * @throws IllegalArgumentException
	 *             if tree was not a valid expression, or map is null, or tries
	 *             to substitute a null into the tree
	 */
	public static LinkedBinaryTree<String> substitute(LinkedBinaryTree<String> tree, HashMap<String, Integer> map)
	throws IllegalArgumentException {
		if (tree == null || map == null) {
			throw new IllegalArgumentException();
		}
		if (!isArithmeticExpression(tree)) {
			throw new IllegalArgumentException();
		}
		Position<String> root = tree.root();
		return substitute(tree, root, map);
	}

	private static LinkedBinaryTree<String> substitute(LinkedBinaryTree<String> tree, Position<String> node, HashMap<String, Integer> map) throws IllegalArgumentException {
		LinkedBinaryTree<String> subtree = new LinkedBinaryTree<String>();
		// If the node is an integer, just return it
		if (isInteger(node)) {
			subtree.addRoot(node.getElement());
			return subtree;
		}
		// If the node is a variable, use the node as the key to find the corresponding value in the map
		if (isVariable(node)) {
			// If there is not an entry's key matching the node
			if (!map.containsKey(node.getElement())) {
				subtree.addRoot(node.getElement());
				return subtree;
			}
			Integer value = map.get(node.getElement());
			if (value == null) {
				throw new IllegalArgumentException();
			} else {
				subtree.addRoot(String.valueOf(value));
				return subtree;
			}
		}
		// If the node is an operator, combine its substituted subtrees (call this method recursively)
		LinkedBinaryTree<String> leftTree = substitute(tree, tree.left(node), map);
		LinkedBinaryTree<String> rightTree = substitute(tree, tree.right(node), map);
		subtree.addRoot(node.getElement());
		subtree.attach(subtree.root(), leftTree, rightTree);
		return subtree;
	}

	/**
	 * Given a tree, identify if that tree represents a valid arithmetic
	 * expression (possibly with variables)
	 *
	 * Ideally, this method should run in O(n) expected time
	 *
	 * @param tree
	 *            - a tree representing an arithmetic expression
	 * @return true if the tree is not null and it obeys the structure of an
	 *              arithmetic expression. Otherwise, it returns false
	 */
	public static boolean isArithmeticExpression(LinkedBinaryTree<String> tree) {
		if ((tree == null) || (tree.isEmpty())) {
			return false;
		}
		Position<String> root = tree.root();
		return isArithmeticExpression(tree, root);
	}

	private static boolean isArithmeticExpression(LinkedBinaryTree<String> tree, Position<String> node) {
		if (tree == null || node == null) {
			return false;
		}
		// The tree must be proper
		if (tree.numChildren(node) == 1) {
			return false;
		}
		// If a node is external, it must be a value or variable --> cannot be an operator
		if (tree.isExternal(node)) {
			return !isOperator(node);
		}
		// If a node is internal, it must be an operator
		if (!isOperator(node)) {
			return false;
		}
		// Both its subtrees should be valid
		return isArithmeticExpression(tree, tree.left(node)) && isArithmeticExpression(tree, tree.right(node));
	}
}
