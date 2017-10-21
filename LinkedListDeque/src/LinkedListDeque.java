public class LinkedListDeque<E> implements Deque<E> {

	private class Node<E> {

		Node<E> prev;
		Node<E> next;
		E element;

		public Node(E element) {
			this.prev = null;
			this.next = null;
			this.element = element;
		}

		public Node<E> getPrev() {return prev;}
		public Node<E> getNext() {return next;}
		public E getElement() {return element;}
		public void setPrev(Node<E> prev) {this.prev = prev;}
		public void setNext(Node<E> next) {this.next = next;}
		public void setElement(E element) {this.element = element;}

	}

	private Node<E> header;
	private Node<E> trailer;
	int size;

	public LinkedListDeque() {
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
		return size == 0;
	}

	@Override
	public E peekFirst() {
		if (isEmpty()) return null;
		return header.getNext().getElement();
	}

	@Override
	public E peekLast() {
		if (isEmpty()) return null;
		return trailer.getPrev().getElement();
	}

	@Override
	public void addFirst(E element) {
		Node<E> n = new Node<E>(element);
		n.setPrev(header);
		n.setNext(header.getNext());
		header.getNext().setPrev(n);
		header.setNext(n);
		size++;
	}

	@Override
	public void addLast(E element) {
		Node<E> n = new Node<E>(element);
		n.setPrev(trailer.getPrev());
		n.setNext(trailer);
		trailer.getPrev().setNext(n);
		trailer.setPrev(n);
		size++;
	}

	@Override
	public E pollFirst() {
		if (isEmpty()) return null;
		E tmp = header.getNext().getElement();
		header.setNext(header.getNext().getNext());
		header.getNext().setPrev(header);
		size--;
		return tmp;
	}

	@Override
	public E pollLast() {
		if (isEmpty()) return null;
		E tmp = trailer.getPrev().getElement();
		trailer.setPrev(trailer.getPrev().getPrev());
		trailer.getPrev().setNext(trailer);
		size--;
		return tmp;
	}

}
