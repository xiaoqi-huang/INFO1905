import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedPositionalListTest {

	@Test
	public void testFirstAddFirst() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		assertNull(ll.first());
		assertEquals("A", ll.addFirst("A").getElement());
		assertEquals("A", ll.first().getElement());
		assertEquals("B", ll.addFirst("B").getElement());
		assertEquals("B", ll.first().getElement());
		assertEquals("C", ll.addFirst("C").getElement());
		assertEquals("C", ll.first().getElement());
	}

	@Test
	public void testLastAddLast() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		assertNull(ll.last());
		assertEquals("A", ll.addLast("A").getElement());
		assertEquals("A", ll.last().getElement());
		assertEquals("B", ll.addLast("B").getElement());
		assertEquals("B", ll.last().getElement());
		assertEquals("C", ll.addLast("C").getElement());
		assertEquals("C", ll.last().getElement());
	}

	@Test
	public void testBeforeAfter() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		assertEquals("E", ll.addFirst("E").getElement());
		assertEquals("D", ll.addFirst("D").getElement());
		assertEquals("C", ll.addFirst("C").getElement());
		assertEquals("B", ll.addFirst("B").getElement());
		assertEquals("A", ll.addFirst("A").getElement());
		Position<String> p = ll.first();
		assertEquals("A", p.getElement());
		assertNull(ll.before(p));
		p = ll.after(p);
		assertEquals("B", p.getElement());
		p = ll.after(p);
		assertEquals("C", p.getElement());
		p = ll.before(p);
		assertEquals("B", p.getElement());
		p = ll.after(p);
		assertEquals("C", p.getElement());
		p = ll.after(p);
		assertEquals("D", p.getElement());
		p = ll.after(p);
		assertEquals("E", p.getElement());
		assertNull(ll.after(p));
		p = ll.before(p);
		assertEquals("D", p.getElement());
	}

	@Test
	public void testSet() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		Position<String> a = ll.addFirst("A");
		Position<String> b = ll.addFirst("B");
		Position<String> c = ll.addFirst("C");
		Position<String> d = ll.addFirst("D");
		Position<String> e = ll.addFirst("E");
		assertEquals("A", ll.set(a, "1"));
		assertEquals("B", ll.set(b, "2"));
		assertEquals("C", ll.set(c, "3"));
		assertEquals("D", ll.set(d, "4"));
		assertEquals("E", ll.set(e, "5"));
		assertEquals("1", a.getElement());
		assertEquals("2", b.getElement());
		assertEquals("3", c.getElement());
		assertEquals("4", d.getElement());
		assertEquals("5", e.getElement());
		Position<String> p = ll.first();
		assertNull(ll.before(p));
		assertEquals("5", p.getElement());
		p = ll.after(p);
		assertEquals("4", p.getElement());
		p = ll.after(p);
		assertEquals("3", p.getElement());
		p = ll.after(p);
		assertEquals("2", p.getElement());
		p = ll.after(p);
		assertEquals("1", p.getElement());
		assertNull(ll.after(p));
	}

	@Test
	public void testAddBefore() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		Position<String> a = ll.addFirst("A");    //A
		Position<String> b = ll.addBefore(a, "B");//BA
		Position<String> c = ll.addBefore(b, "C");//CBA
		Position<String> d = ll.addBefore(a, "D");//CBDA
		Position<String> e = ll.addBefore(d, "E");//CBEDA
		assertEquals("A", a.getElement());
		assertEquals("B", b.getElement());
		assertEquals("C", c.getElement());
		assertEquals("D", d.getElement());
		assertEquals("E", e.getElement());
		Position<String> p = ll.first();
		assertEquals("C", p.getElement());
		assertNull(ll.before(p));
		p = ll.after(p);
		assertEquals("B", p.getElement());
		p = ll.after(p);
		assertEquals("E", p.getElement());
		p = ll.after(p);
		assertEquals("D", p.getElement());
		p = ll.after(p);
		assertEquals("A", p.getElement());
		assertNull(ll.after(p));
		p = ll.before(p);
		assertEquals("D", p.getElement());
		p = ll.before(p);
		assertEquals("E", p.getElement());
		p = ll.before(p);
		assertEquals("B", p.getElement());
		p = ll.before(p);
		assertEquals("C", p.getElement());
		assertNull(ll.before(p));
	}

	@Test
	public void testAddAfter() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		Position<String> a = ll.addFirst("A");    //A
		Position<String> b = ll.addAfter(a, "B");//AB
		Position<String> c = ll.addAfter(b, "C");//ABC
		Position<String> d = ll.addAfter(a, "D");//ADBC
		Position<String> e = ll.addAfter(d, "E");//ADEBC
		assertEquals("A", a.getElement());
		assertEquals("B", b.getElement());
		assertEquals("C", c.getElement());
		assertEquals("D", d.getElement());
		assertEquals("E", e.getElement());
		Position<String> p = ll.first();
		assertEquals("A", p.getElement());
		assertNull(ll.before(p));
		p = ll.after(p);
		assertEquals("D", p.getElement());
		p = ll.after(p);
		assertEquals("E", p.getElement());
		p = ll.after(p);
		assertEquals("B", p.getElement());
		p = ll.after(p);
		assertEquals("C", p.getElement());
		assertNull(ll.after(p));
		p = ll.before(p);
		assertEquals("B", p.getElement());
		p = ll.before(p);
		assertEquals("E", p.getElement());
		p = ll.before(p);
		assertEquals("D", p.getElement());
		p = ll.before(p);
		assertEquals("A", p.getElement());
		assertNull(ll.before(p));
	}

	@Test
	public void testRemove() {
		LinkedPositionalList<String> ll = new LinkedPositionalList<String>();
		Position<String> e = ll.addFirst("E");
		Position<String> d = ll.addFirst("D");
		Position<String> c = ll.addFirst("C");
		Position<String> b = ll.addFirst("B");
		Position<String> a = ll.addFirst("A");
		assertEquals("C", ll.remove(c));
		assertEquals("A", ll.remove(a));
		assertEquals("E", ll.remove(e));
		assertEquals("B", ll.first().getElement());
		assertEquals("D", ll.last().getElement());
		assertEquals(d, ll.after(b));
		assertEquals(b, ll.before(d));
		assertNull(ll.before(b));
		assertNull(ll.after(d));
	}

	// @Test(expected = IndexOutOfBoundsException.class)
	// public void testInvalidRemove() {
	// 	LinkedPositionalList<String> ll1 = new LinkedPositionalList<String>();
	// 	ll1.addLast("A");
	// 	ll1.addLast("B");
	// 	ll1.addLast("C");
	// 	LinkedPositionalList<String> ll2 = new LinkedPositionalList<String>();
	// 	Position<String> p = ll2.addFirst("X");
	// 	ll1.remove(p);
	// }

}
