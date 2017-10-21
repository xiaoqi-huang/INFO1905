import static org.junit.Assert.*;

import org.junit.Test;

public class PhoneBookTest {

	@Test
	public void testEmpty() {
		PhoneBook pb = new PhoneBook();
		assertNull(pb.getNumber("Bob"));
	}

	@Test
	public void testAdd() {
		PhoneBook pb = new PhoneBook();
		pb.addNumber("Bob", 92450184);
		assertEquals(new Integer(92450184), pb.getNumber("Bob"));
	}

	@Test
	public void testRaplace() {
		PhoneBook pb = new PhoneBook();
		pb.addNumber("Bob", 92450184);
		pb.addNumber("Bob", 91112222);
		assertEquals(new Integer(91112222), pb.getNumber("Bob"));
	}

	@Test
	public void testDelete() {
		PhoneBook pb = new PhoneBook();
		pb.addNumber("Bob", 92450184);
		pb.deleteContact("Bob");
		assertNull(pb.getNumber("Bob"));
	}
}
