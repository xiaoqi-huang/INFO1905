import java.util.Iterator;

/*
 * This class is the one you need to complete. Only the member variables
 * and constructor have been implemented for you.
 */
public class LinkedPositionalList<E> implements EnhancedPositionalList<E>, Iterable<E> {

	/*
	 * This nested class contains a completed implementation of Position
	 * which you should use within LinkedPositionalList.
	 *
	 * You do not need to edit this class.
	 */
	private class Node<E> implements Position<E> {

		private Node<E> prev;
		private Node<E> next;
		private E element;

		public Node(E element) {
			this.element = element;
			this.prev = null;
			this.next = null;
		}

		public Node<E> getPrev() {return prev;}
		public Node<E> getNext() {return next;}
		public E getElement() {return element;}
		public void setPrev(Node<E> prev) {this.prev = prev;}
		public void setNext(Node<E> next) {this.next= next;}
		public void setElement(E element) {this.element = element;}
		
	}

	//The header sentinel
	private Node<E> header;

	//The trailer sentinel
	private Node<E> trailer;

	//The number of positions in the list, not counting sentinels
	int size;

	//This constructor creates an empty list
	public LinkedPositionalList() {
		header = new Node<E>(null);
		trailer = new Node<E>(null);
		header.setNext(trailer);
		trailer.setPrev(header);
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0) return true;
		return false;
	}

	@Override
	public Position<E> first() {
		if(isEmpty()) return null;
		return header.getNext();
	}

	@Override
	public Position<E> last() {
		if(isEmpty()) return null;
		return trailer.getPrev();
	}

	@Override
	public Position<E> before(Position<E> p) {
		Node<E> n = (Node<E>)p;
		if(header.getNext() == n) return null;
		return n.getPrev();
	}

	@Override
	public Position<E> after(Position<E> p) {
		Node<E> n = (Node<E>)p;
		if(trailer.getPrev() == n) return null;
		return n.getNext();
	}

	@Override
	public Position<E> search(E value) {
		if (this.isEmpty()) return null;
		Position<E> p = this.first();
		while (p.getElement() != value) {
			p = this.after(p);
			if (p == null) return null;
		}
		return p;
	}

	@Override
	public Position<E> addFirst(E e) {
		size++;
		return addBetween(header, header.getNext(), e);
	}

	@Override
	public Position<E> addLast(E e) {
		size++;
		return addBetween(trailer.getPrev(), trailer, e);
	}

	@Override
	public Position<E> addBefore(Position<E> p, E e) {
		Node<E> n = (Node<E>)p;
		size++;
		return addBetween(n.getPrev(), n, e);
	}

	@Override
	public Position<E> addAfter(Position<E> p, E e) {
		Node<E> n = (Node<E>)p;
		size++;
		return addBetween(n, n.getNext(), e);
	}

	@Override
	public Position<E> addFirst(PositionalList<E> sublist) {
		if (!sublist.isEmpty()) {
			Position<E> p = sublist.last();
			while (p != null) {
				this.addFirst(p.getElement());
				p = sublist.before(p);
			}
		}
		return this.first();
	}

	@Override
	public Position<E> addLast(PositionalList<E> sublist) {
		if (!sublist.isEmpty()) {
			Position<E> p = sublist.first();
			while (p != null) {
				this.addLast(p.getElement());
				p = sublist.after(p);
			}
		}
		return this.first();
	}

	@Override
	public Position<E> addBefore(Position<E> p, PositionalList<E> sublist) {
		if (!sublist.isEmpty()) {
			Position<E> ps = sublist.first();
			while (ps != null) {
				this.addBefore(p, ps.getElement());
				ps = sublist.after(ps);
			}
		}
		return this.first();
	}

	@Override
	public Position<E> addAfter(Position<E> p, PositionalList<E> sublist) {
		if (!sublist.isEmpty()) {
			Position<E> ps = sublist.last();
			while (ps != null) {
				this.addAfter(p, ps.getElement());
				ps = sublist.before(ps);
			}
		}
		return this.first();
	}

	@Override
	public E set(Position<E> p, E e) {
		Node<E> n = (Node<E>)p;
		E tmp = n.getElement();
		n.setElement(e);
		return tmp;
	}

	@Override
	public E remove(Position<E> p) {
		Node<E> n = (Node<E>)p;
		n.getPrev().setNext(n.getNext());
		n.getNext().setPrev(n.getPrev());
		size--;
		return n.getElement();
	}

	@Override
	public int distance(Position<E> a, Position<E> b) {
		if (!contains(a) || !contains(b)) throw new IllegalArgumentException();
		if (this.isEmpty() || this.searchPosition(a) == -1 || this.searchPosition(b) == -1) return 0;
		return this.searchPosition(b) - this.searchPosition(a);
	}

	public boolean contains(Position<E> target) {
		if (isEmpty()) return false;
		Position<E> p = first();
		while (p != null) {
			if (p == target) return true;
			p = after(p);
		}
		return false;
	}

	private Node<E> addBetween(Node<E> prev, Node<E> next, E e) {
		Node<E> n = new Node<E>(e);
		prev.setNext(n);
		n.setPrev(prev);
		next.setPrev(n);
		n.setNext(next);
		return n;
	}

	private int searchPosition(Position<E> target) {
		if (this.isEmpty()) return -1;
		Position<E> p = this.first();
		int count = 0;
		while (p != target) {
			p = this.after(p);
			count++;
			if (p == null) return -1;
		}
		return count;
	}

	public Iterator<E> iterator() {
		return new PositionalListIterator<E>(this);
	}

}
