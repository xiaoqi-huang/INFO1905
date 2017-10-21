import java.util.Iterator;
import java.util.NoSuchElementException;

public class PositionalListIterator<E> implements Iterator<E> {

	private PositionalList<E> pl;
	private Position<E> p;

	public PositionalListIterator(PositionalList<E> pl) {
		this.pl = pl;
		this.p = pl.first();
	}

	@Override
	public boolean hasNext() {
		if (p == null) return false;
		return true;
	}

	@Override
	public E next() {
		if (p == null) throw new NoSuchElementException();
		Position<E> tmp = p;
		p = pl.after(p);
		return tmp.getElement();
	}

}
