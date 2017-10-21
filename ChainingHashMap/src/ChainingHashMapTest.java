import static org.junit.Assert.*;

import org.junit.Test;

public class ChainingHashMapTest {

	@Test
	public void testCollisions() {
		ChainingHashMap<Integer, String> chm = new ChainingHashMap<Integer, String>(3);

		chm.put(0, "X");
		chm.put(3, "Y");
		chm.put(6, "Z");

		assertEquals(3, chm.size());
		assertEquals("X", chm.get(0));
		assertEquals("Y", chm.get(3));
		assertEquals("Z", chm.get(6));

		assertEquals("Y", chm.remove(3));
		assertNull(chm.get(3));

		assertEquals("X", chm.remove(0));
		assertNull(chm.get(0));

		assertNull(chm.remove(0));
		assertNull(chm.remove(3));

		assertEquals("Z", chm.remove(6));
		assertNull(chm.get(6));

		assertEquals(0, chm.size());
	}

}
