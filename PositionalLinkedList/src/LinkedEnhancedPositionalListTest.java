import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LinkedEnhancedPositionalListTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	///////////////////////////////////////////////////////////////////
	// test exercise 1 (search)
	///////////////////////////////////////////////////////////////////

	@Test(timeout=100)
	public void exercise_1_testSearchEmpty() {
		EnhancedPositionalList<String> ll = new LinkedPositionalList<String>();
		assertNull(ll.search("A"));
	}

	@Test(timeout=100)
	public void exercise_1_testSearchNotFound() {
		EnhancedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addLast("B");
		ll.addLast("A");
		ll.addLast("R");
		assertNull(ll.search("X"));
	}

	@Test(timeout=100)
	public void exercise_1_testSearchFound() {
		EnhancedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addLast("B");
		ll.addLast("A");
		ll.addLast("R");
		assertEquals("B", ll.search("B").getElement());
		assertEquals("A", ll.search("A").getElement());
		assertEquals("R", ll.search("R").getElement());
	}

	///////////////////////////////////////////////////////////////////
	// test exercise 2 (add sublists)
	///////////////////////////////////////////////////////////////////

	////////////
	//addFirst()
	@Test(timeout=100)
	public void exercise_2_testAddFirstSize() {

		//set up some lists
		EnhancedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addFirst("X");
		PositionalList<String> sublistA = new LinkedPositionalList<String>();
		sublistA.addLast("X");
		sublistA.addLast("X");
		sublistA.addLast("X");
		PositionalList<String> sublistB = new LinkedPositionalList<String>();
		sublistB.addLast("X");
		sublistB.addLast("X");

		//check what happens to the size as we add the sublists
		assertEquals(1, ll.size());
		ll.addFirst(sublistA);
		assertEquals(4, ll.size());
		ll.addFirst(sublistB);
		assertEquals(6, ll.size());
	}

	@Test(timeout=100)
	public  void exercise_2_testAddFirstToEmptyList() {
		EnhancedPositionalList<String> ll  = new LinkedPositionalList<String>();
		PositionalList<String> sublist = new LinkedPositionalList<String>();
		sublist.addLast("A");
		sublist.addLast("B");
		ll.addFirst(sublist);
		assertEquals(2, ll.size());
		assertEquals("A", ll.first().getElement());
		assertEquals("B", ll.last().getElement());
	}

	@Test(timeout=100)
	public void exercise_2_testAddFirstCorrect() {

		//set up some lists
		EnhancedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addFirst("X");
		PositionalList<String> sublistA = new LinkedPositionalList<String>();
		sublistA.addLast("A");
		sublistA.addLast("B");
		sublistA.addLast("C");
		PositionalList<String> sublistB = new LinkedPositionalList<String>();
		sublistB.addLast("D");
		sublistB.addLast("E");
		sublistB.addLast("F");

		//add the sublists to the main list
		ll.addFirst(sublistA);
		ll.addFirst(sublistB);

		//check that all the elements in the list are connected correctly
		Position<String> p = ll.first();
		assertEquals("D", p.getElement());
		assertNull(ll.before(p)); //head

		p = ll.after(p);
		assertEquals("E", p.getElement());
		assertEquals("D", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("F", p.getElement());
		assertEquals("E", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("A", p.getElement());
		assertEquals("F", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("B", p.getElement());
		assertEquals("A", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("C", p.getElement());
		assertEquals("B", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("X", p.getElement());
		assertEquals("C", ll.before(p).getElement());

		assertNull(ll.after(p)); //tail
	}

	////////////
	//addLast()
	@Test(timeout=100)
	public void exercise_2_testAddLastSize() {

		//set up some lists
		EnhancedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addFirst("X");
		PositionalList<String> sublistA = new LinkedPositionalList<String>();
		sublistA.addLast("X");
		sublistA.addLast("X");
		sublistA.addLast("X");
		PositionalList<String> sublistB = new LinkedPositionalList<String>();
		sublistB.addLast("X");
		sublistB.addLast("X");

		//check what happens to the size as we add the sublists
		assertEquals(1, ll.size());
		ll.addLast(sublistA);
		assertEquals(4, ll.size());
		ll.addLast(sublistB);
		assertEquals(6, ll.size());
	}

	@Test(timeout=100)
	public  void exercise_2_testAddLastToEmptyList() {
		EnhancedPositionalList<String> ll  = new LinkedPositionalList<String>();
		PositionalList<String> sublist = new LinkedPositionalList<String>();
		sublist.addLast("A");
		sublist.addLast("B");
		ll.addLast(sublist);
		assertEquals(2, ll.size());
		assertEquals("A", ll.first().getElement());
		assertEquals("B", ll.last().getElement());
	}

	@Test(timeout=100)
	public void exercise_2_testAddLastCorrect() {

		//set up some lists
		EnhancedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addFirst("X");
		PositionalList<String> sublistA = new LinkedPositionalList<String>();
		sublistA.addLast("A");
		sublistA.addLast("B");
		sublistA.addLast("C");
		PositionalList<String> sublistB = new LinkedPositionalList<String>();
		sublistB.addLast("D");
		sublistB.addLast("E");
		sublistB.addLast("F");

		//add the sublists to the main list
		ll.addLast(sublistA);
		ll.addLast(sublistB);

		//check that all the elements in the list are connected correctly
		Position<String> p = ll.first();
		assertEquals("X", p.getElement());
		assertNull(ll.before(p)); //head

		p = ll.after(p);
		assertEquals("A", p.getElement());
		assertEquals("X", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("B", p.getElement());
		assertEquals("A", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("C", p.getElement());
		assertEquals("B", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("D", p.getElement());
		assertEquals("C", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("E", p.getElement());
		assertEquals("D", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("F", p.getElement());
		assertEquals("E", ll.before(p).getElement());

		assertNull(ll.after(p)); //tail
	}

	////////////
	//addBefore()
	@Test(timeout=100)
	public void exercise_2_testAddBeforeCorrect() {

		//set up some lists
		EnhancedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addFirst("X");
		PositionalList<String> sublistA = new LinkedPositionalList<String>();
		sublistA.addLast("A");
		sublistA.addLast("B");
		sublistA.addLast("C");
		PositionalList<String> sublistB = new LinkedPositionalList<String>();
		sublistB.addLast("D");
		sublistB.addLast("E");
		sublistB.addLast("F");

		//add the sublists to the main list
		ll.addBefore(ll.first(), sublistA);
		ll.addBefore(ll.first(), sublistB);

		//check that all the elements in the list are connected correctly
		Position<String> p = ll.first();
		assertEquals("D", p.getElement());
		assertNull(ll.before(p)); //head

		p = ll.after(p);
		assertEquals("E", p.getElement());
		assertEquals("D", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("F", p.getElement());
		assertEquals("E", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("A", p.getElement());
		assertEquals("F", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("B", p.getElement());
		assertEquals("A", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("C", p.getElement());
		assertEquals("B", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("X", p.getElement());
		assertEquals("C", ll.before(p).getElement());

		assertNull(ll.after(p)); //tail
	}

	////////////
	//addAfter()
	@Test(timeout=100)
	public void exercise_2_testAddAfterCorrect() {

		//set up some lists
		EnhancedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addFirst("X");
		PositionalList<String> sublistA = new LinkedPositionalList<String>();
		sublistA.addLast("A");
		sublistA.addLast("B");
		sublistA.addLast("C");
		PositionalList<String> sublistB = new LinkedPositionalList<String>();
		sublistB.addLast("D");
		sublistB.addLast("E");
		sublistB.addLast("F");

		//add the sublists to the main list
		ll.addAfter(ll.last(), sublistA);
		ll.addAfter(ll.last(), sublistB);

		//check that all the elements in the list are connected correctly
		Position<String> p = ll.first();
		assertEquals("X", p.getElement());
		assertNull(ll.before(p)); //head

		p = ll.after(p);
		assertEquals("A", p.getElement());
		assertEquals("X", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("B", p.getElement());
		assertEquals("A", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("C", p.getElement());
		assertEquals("B", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("D", p.getElement());
		assertEquals("C", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("E", p.getElement());
		assertEquals("D", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("F", p.getElement());
		assertEquals("E", ll.before(p).getElement());

		assertNull(ll.after(p)); //tail
	}

	@Test(timeout=100)
	public void exercise_2_testModifySublistAfterInsertion() {

		//set up some lists
		EnhancedPositionalList<String> ll = new LinkedPositionalList<String>();
		ll.addFirst("X");
		PositionalList<String> sublistA = new LinkedPositionalList<String>();
		sublistA.addLast("A");
		sublistA.addLast("B");
		sublistA.addLast("C");

		//add the sublist to the main list
		ll.addBefore(ll.first(), sublistA);

		//modify the sublist. This shouldn't affect the main list at all
		sublistA.remove(sublistA.first());
		sublistA.addFirst("Y");
		sublistA.addLast("Z");

		//check that the main list is still correct
		assertEquals(4, ll.size());

		Position<String> p = ll.first();
		assertEquals("A", p.getElement());
		assertNull(ll.before(p)); //head

		p = ll.after(p);
		assertEquals("B", p.getElement());
		assertEquals("A", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("C", p.getElement());
		assertEquals("B", ll.before(p).getElement());

		p = ll.after(p);
		assertEquals("X", p.getElement());
		assertEquals("C", ll.before(p).getElement());

		assertNull(ll.after(p)); //tail
	}

	///////////////////////////////////////////////////////////////////
	//test exercise 3 (distance)
	///////////////////////////////////////////////////////////////////

	@Test(timeout=100)
	public void exercise_3_testDistancePositive() {
		//set up a list
		EnhancedPositionalList<Integer> ll = new LinkedPositionalList<Integer>();
		for(int i = 1; i < 10; i++) {
			ll.addLast(i);
		}
		Position<Integer> a = ll.first();
		Position<Integer> b = ll.after(a);
		assertEquals(1, ll.distance(a, b));
		b = ll.after(b);
		assertEquals(2, ll.distance(a, b));
		b = ll.after(b);
		assertEquals(3, ll.distance(a, b));
		a = ll.after(a);
		assertEquals(2, ll.distance(a, b));
	}

	@Test(timeout=100)
	public void exercise_3_testDistanceNegative() {
		//set up a list
		EnhancedPositionalList<Integer> ll = new LinkedPositionalList<Integer>();
		for(int i = 1; i < 10; i++) {
			ll.addLast(i);
		}
		Position<Integer> b = ll.first();
		Position<Integer> a = ll.after(b);
		assertEquals(-1, ll.distance(a, b));
		a = ll.after(a);
		assertEquals(-2, ll.distance(a, b));
		b = ll.after(b);
		assertEquals(-1, ll.distance(a, b));
	}

	@Test(timeout=100)
	public void exercise_3_testDistanceSame() {
		//set up a list
		EnhancedPositionalList<Integer> ll = new LinkedPositionalList<Integer>();
		for(int i = 1; i < 10; i++) {
			ll.addLast(i);
		}
		Position<Integer> position = ll.first();
		assertEquals(0, ll.distance(position, position));
		position = ll.last();
		assertEquals(0, ll.distance(position, position));
		position = ll.before(position);
		assertEquals(0, ll.distance(position, position));
	}

	@Test(timeout=100)
	public void exercise_3_testDistanceInvalidPosition() {
		//set up a list
		EnhancedPositionalList<Integer> ll = new LinkedPositionalList<Integer>();
		for(int i = 1; i < 10; i++) {
			ll.addLast(i);
		}
		Position<Integer> first = ll.first();
		Position<Integer> last = ll.last();

		ll.remove(first);

		exception.expect(IllegalArgumentException.class);
		ll.distance(first,  last);
	}

	@Test
	public void testContains() {
		LinkedPositionalList<String> ll1 = new LinkedPositionalList<String>();
		ll1.addLast("A");
		ll1.addLast("B");
		ll1.addLast("C");
		LinkedPositionalList<String> ll2 = new LinkedPositionalList<String>();
		ll1.addLast("X");
		Position<String> p = ll2.first();
		assertFalse(ll1.contains(p));
	}

}
