import java.util.Iterator;

public class LinkedBinaryTreeIterator<E> implements Iterator<E> {

	LinkedBinaryTree<E> tree;
	Position<E> p;
	Position<E> last;

	public LinkedBinaryTreeIterator(LinkedBinaryTree<E> tree) {
		this.tree = tree;
		this.p = tree.root();
		this.last = last(tree.root());
	}

	@Override
	public boolean hasNext() {
		return p != last;
	}

	@Override
	public E next() {
		if (tree.isInternal(p)) {
			if (tree.left(p) != null) {
				p = tree.left(p);
			} else {
				p = tree.right(p);
			}
		} else {
			p = next(p);
		}
		return p.getElement();
	}

	public Position<E> next(Position<E> p) {
		if (p == tree.left(tree.parent(p)) && tree.right(tree.parent(p)) != null) {
			return tree.right(tree.parent(p));
		}
		return next(tree.parent(p));
	}

	private Position<E> last(Position<E> p) {
		while (tree.right(p) != null) {
			p = tree.right(p);
		}
		if (tree.left(p) != null) {
			p = tree.left(p);
			return last(p);
		}
		return p;
	}

}
