import static org.junit.Assert.*;

import org.junit.Test;

public class TriangleNumberTest {

	@Test
	public void testSmallTriangles() {
		assertEquals(1, TriangleNumber.triangle(1));
		assertEquals(3, TriangleNumber.triangle(2));
		assertEquals(6, TriangleNumber.triangle(3));
	}
	
	@Test
	public void testZeroTriangle() {
	    assertEquals(0, TriangleNumber.triangle(0));
	} 

}
