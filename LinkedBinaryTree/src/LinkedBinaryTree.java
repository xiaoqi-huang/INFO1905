import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is a simple implementation of a Linked Binary Tree, designed
 * for use in INFO1105/1905/9105 at the University of Sydney.
 *
 * It contains some code derived from work:
 *    Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *    Developed for use with the book:
 *        Data Structures and Algorithms in Java, Sixth Edition
 *        Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *       John Wiley & Sons, 2014
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>, Iterable<E> {

	/*
	 * This nested class contains a completed implementation of Position
	 * which you should use within LinkedPositionalList.
	 *
	 * You do not need to edit the nested class.
	 */
	protected class Node<E> implements Position<E> {

		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		private E element;

		public Node(E element) {
			this.element = element;
			this.parent = null;
			this.left = null;
			this.right = null;
		}

		public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
			this.element = element;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

		public Node<E> getParent() {return parent;}
		public Node<E> getLeft() {return left;}
		public Node<E> getRight() {return right;}
		public E getElement() {return element;}
		public void setParent(Node<E> parent) {this.parent = parent;}
		public void setLeft(Node<E> left) {this.left = left;}
		public void setRight(Node<E> right) {this.right = right;}
		public void setElement(E element) {this.element = element;}

	} //end of nested class

	private Node<E> root;
	private int size;

	public LinkedBinaryTree() {
		root = null;
		size = 0;
	}

	@Override
	public Position<E> left(Position<E> p) {
		Node<E> node = (Node<E>)p;
		return node.getLeft();
	}

	@Override
	public Position<E> right(Position<E> p) {
		Node<E> node = (Node<E>)p;
		return node.getRight();
	}

	@Override
	public Position<E> sibling(Position<E> p) {
		Node<E> node = (Node<E>)p;
		Node<E> parent = node.getParent();
		if(parent == null) {
			return null;
		} else if(parent.getLeft() == node) {
			return parent.getRight();
		} else {
			return parent.getLeft();
		}
	}

	@Override
	public Position<E> root() {
		return root;
	}

	@Override
	public Position<E> parent(Position<E> p) {
		Node<E> node = (Node<E>)p;
		return node.getParent();
	}

	@Override
	public int numChildren(Position<E> p) {
		Node<E> node = (Node<E>)p;
		int numChildren = 0;
		if(node.getLeft() != null) {
			numChildren += 1;
		}
		if(node.getRight() != null) {
			numChildren += 1;
		}
		return numChildren;
	}

	@Override
	public boolean isInternal(Position<E> p) {
		return numChildren(p) > 0;
	}

	@Override
	public boolean isExternal(Position<E> p) {
		return numChildren(p) == 0;
	}

	@Override
	public boolean isRoot(Position<E> p) {
		return root == (Node<E>)p;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	//add a root to an empty tree
	public Position<E> addRoot(E e) {
		//if the tree is not empty, throw an exception
		if(!isEmpty()) {
			throw new IllegalStateException("Tree is not empty");
		}
		//create the new root
		root = new Node<E>(e);
		size = 1;
		return root;
	}

	/**
	 * Creates a new left child of Position p storing element e and returns its
	 * Position.
	 *
	 * @param p
	 *            the Position to the left of which the new element is inserted
	 * @param e
	 *            the new element
	 * @return the Position of the new element
	 * @throws IllegalArgumentException
	 *             if p is not a valid Position for this tree
	 * @throws IllegalArgumentException
	 *             if p already has a left child
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> n = (Node<E>)p;
		if(n.getLeft() != null) {
			throw new IllegalArgumentException("Node already had a left child!");
		}
		Node<E> child = new Node<E>(e);
		n.setLeft(child);
		child.setParent(n);
		size += 1;
		return child;
	}

	/**
	 * Creates a new right child of Position p storing element e and returns its
	 * Position.
	 *
	 * @param p
	 *            the Position to the right of which the new element is inserted
	 * @param e
	 *            the new element
	 * @return the Position of the new element
	 * @throws IllegalArgumentException
	 *             if p already has a right child
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> n = (Node<E>)p;
		if(n.getRight() != null) {
			throw new IllegalArgumentException("Node already had a right child!");
		}
		Node<E> child = new Node<E>(e);
		n.setRight(child);
		child.setParent(n);
		size += 1;
		return child;
	}

	/**
	 * Replaces the element at Position p with element e and returns the
	 * replaced element.
	 *
	 * @param p
	 *            the relevant Position
	 * @param e
	 *            the new element
	 * @return the replaced element
	 */
	public E set(Position<E> p, E e) {
		Node<E> node = (Node<E>)p;
		E oldElement = node.getElement();
		node.setElement(e);
		return oldElement;
	}

	/**
	 * Removes the node at Position p and replaces it with its child, if any.
	 *
	 * @param p
	 *            the relevant Position
	 * @return element that was removed
	 * @throws IllegalArgumentException
	 *             if p has two children.
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		Node<E> node = (Node<E>)p;

		//get the child
		if(numChildren(p) == 2) {
			throw new IllegalArgumentException("p has two children");
		}

		//get the child
		Node<E> child = node.getLeft();
		if(child == null) {
			child = node.getRight();
		}

		if(child != null) {
			//child's grandparent becomes it's parent
			child.setParent(node.getParent());
		}

		if(node == root) {
			//child becomes root
			root = child;
		} else {
			Node<E> parent = node.getParent();
			if(node == parent.getLeft()) {
				parent.setLeft(child);
			} else {
				parent.setRight(child);
			}
		}

		size -= 1;

		return node.getElement();
	}

	/**
	 * Attaches trees t1 and t2, respectively, as the left and right subtree of
	 * the leaf Position p. As a side effect, t1 and t2 are set to empty trees.
	 *
	 * @param p
	 *            a leaf of the tree
	 * @param t1
	 *            an independent tree whose structure becomes the left child of
	 *            p
	 * @param t2
	 *            an independent tree whose structure becomes the right child of
	 *            p
	 * @throws IllegalArgumentException
	 *             if p is not a leaf
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
		Node<E> node = (Node<E>)p;
		if(isInternal(p)) {
			throw new IllegalArgumentException("p must be a leaf");
		}
		size += t1.size() + t2.size();

		// attach t1 as left subtree of node
		if (!t1.isEmpty()) {
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t1.root = null;
			t1.size = 0;
		}

		// attach t2 as right subtree of node
		if (!t2.isEmpty()) {
			t2.root.setParent(node);
			node.setRight(t2.root);
			t2.root = null;
			t2.size = 0;
		}
	}



	// ----------- TUTORIAL exercise 1 -----------
	/** Returns the number of leaf nodes */
	public int countLeaves() {
		if (this.root == null) {
			return 0;
		}
		return countLeaves(this.root);
	}

	/** Returns the number of leaf nodes that are in the subtree rooted at p */
	private int countLeaves(Position<E> p) {
		Node<E> node = (Node<E>)p;
		int numLeaves = 0;

		if (isExternal(p)) {
			numLeaves++;
		} else {
			if (node.getLeft() != null) {
				numLeaves += countLeaves(node.getLeft());
			}
			if (node.getRight() != null) {
				numLeaves += countLeaves(node.getRight());
			}
		}
		return numLeaves;
	}

	// ----------- TUTORIAL exercise 2 -----------
	/** Returns true if the tree is proper, false otherwise */
	public boolean isProper() {
		if (isEmpty()) return true;
		return isProper(this.root);
	}

	public boolean isProper(Position<E> p) {
		Node<E> node = (Node<E>)p;
		if (numChildren(node) == 0) return true;
		if (numChildren(node) == 1) return false;
		return isProper(node.getLeft()) && isProper(node.getRight());
	}

	// ----------- TUTORIAL exercise 3 -----------
	/** Returns a mirrored copy of the tree */
	public LinkedBinaryTree<E> mirror() {
		return mirror(this.root);
	}

	public LinkedBinaryTree<E> mirror(Position<E> p) {
		Node<E> node = (Node<E>)p;
		LinkedBinaryTree<E> reversed = new LinkedBinaryTree<E>();
		reversed.addRoot(node.getElement());
		if (numChildren(node) == 1) {
			if (left(node) != null) {
				reversed.attach(reversed.root, new LinkedBinaryTree<E>(), mirror(left(node)));
			} else {
				reversed.attach(reversed.root, mirror(right(node)), new LinkedBinaryTree<E>());
			}
		}
		if (numChildren(node) == 2) {
			reversed.attach(reversed.root, mirror(right(node)), mirror(left(node)));
		}
		return reversed;
	}

	// ----------- TUTORIAL exercise 4 -----------
	/** Reverses the tree */
	public void reverseMe() {
		reverseMe(this.root);
	}

	public void reverseMe(Position<E> p) {
		Node<E> node = (Node<E>)p;
		if (isExternal(node)) {
			return;
		} else if (numChildren(node) == 1) {
			if (left(node) != null) {
				reverseMe(left(node));
				node.setRight((Node<E>)left(node));
				node.setLeft(null);
			} else {
				reverseMe(right(node));
				node.setLeft((Node<E>)right(node));
				node.setRight(null);
			}
		} else {
			reverseMe(left(node));
			Node<E> tmp = (Node<E>)left(node);
			reverseMe(right(node));
			node.setLeft((Node<E>)right(node));
			node.setRight(tmp);
		}
	}

	public ArrayList<E> preOrder() {
		return preOrder(this.root);
	}

	public ArrayList<E> preOrder(Position<E> p) {
		Node<E> node = (Node<E>)p;
		ArrayList<E> arr = new ArrayList<E>();
		arr.add(node.getElement());
		if (isInternal(node)) {
			if (left(node) != null) {
				arr.addAll(preOrder(left(node)));
			}
			if (right(node) != null) {
				arr.addAll(preOrder(right(node)));
			}
		}
		return arr;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedBinaryTreeIterator(this);
	}

}
