import static org.junit.Assert.*;

import org.junit.Test;

public class EnglishCalculatorTest {
	
	static final double epsilon = 0.000001;

	@Test
	public void plusTest() {
		assertEquals(12, EnglishCalculator.calculate("2 plus 10"), epsilon);
	}
	
	@Test
	public void minusTest() {
		assertEquals(-1, EnglishCalculator.calculate("6 minus 7"), epsilon);
	}
	
	@Test
	public void timesTest()  {
		assertEquals(20, EnglishCalculator.calculate("4 times 5"), epsilon);
	}
	
	@Test
	public void dividedByTest() {
		assertEquals(3, EnglishCalculator.calculate("15 divided by 5"), epsilon);
	}

}
