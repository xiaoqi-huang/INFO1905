import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleHashMapTest {

	@Test
	public void testHashFunction_StringKey() {
		SimpleHashMap<String, String> shm = new SimpleHashMap(5);
		
		shm.put("K", "V");
		assertEquals("V", shm.get("K"));
	}

	@Test
	public void testHashFunction_IntegerKey() {
		SimpleHashMap<Integer, String> shm = new SimpleHashMap(5);
		
		shm.put(-3, "X");
		assertEquals("X", shm.get(-3));
	}
}
