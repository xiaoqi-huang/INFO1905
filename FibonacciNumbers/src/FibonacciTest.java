import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class FibonacciTest {

	@Test
	public void testNegative() {
		Stack<Integer> numbers = Fibonacci.getNumbers(-1);

		assertTrue(numbers.empty());
	}

	@Test
	public void testZero() {
		Stack<Integer> numbers = Fibonacci.getNumbers(0);

		assertEquals(1, numbers.size());
		assertEquals(new Integer(0), numbers.peek());
		numbers.pop();
		assertTrue(numbers.empty());
	}

	@Test
	public void testOne() {
		Stack<Integer> numbers = Fibonacci.getNumbers(1);

		assertEquals(2, numbers.size());
		assertEquals(new Integer(1), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(0), numbers.peek());
		numbers.pop();
		assertTrue(numbers.empty());
	}

	@Test
	public void testTwo() {
		Stack<Integer> numbers = Fibonacci.getNumbers(2);

		assertEquals(3, numbers.size());
		assertEquals(new Integer(1), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(1), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(0), numbers.peek());
		numbers.pop();
		assertTrue(numbers.empty());
	}

	@Test
	public void testThree() {
		Stack<Integer> numbers = Fibonacci.getNumbers(3);

		assertEquals(4, numbers.size());
		assertEquals(new Integer(2), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(1), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(1), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(0), numbers.peek());
		numbers.pop();
		assertTrue(numbers.empty());
	}

	@Test
	public void testFour() {
		Stack<Integer> numbers = Fibonacci.getNumbers(4);

		assertEquals(5, numbers.size());
		assertEquals(new Integer(3), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(2), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(1), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(1), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(0), numbers.peek());
		numbers.pop();
		assertTrue(numbers.empty());
	}

	@Test
	public void testFive() {
		Stack<Integer> numbers = Fibonacci.getNumbers(5);

		assertEquals(6, numbers.size());
		assertEquals(new Integer(5), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(3), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(2), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(1), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(1), numbers.peek());
		numbers.pop();
		assertEquals(new Integer(0), numbers.peek());
		numbers.pop();
		assertTrue(numbers.empty());
	}

	@Test
	public void testOneHundred() {
		Stack<Integer> numbers = Fibonacci.getNumbers(100);

		assertEquals(101, numbers.size());
	}

}
