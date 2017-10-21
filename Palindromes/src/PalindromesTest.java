import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromesTest {

	@Test
	public void testEmptyString() {
		assertTrue(Palindromes.isPalindrome(""));
	}

	@Test
	public void testBasicPalindromes() {
		assertTrue(Palindromes.isPalindrome("eye"));
		assertTrue(Palindromes.isPalindrome("racecar"));
	}

	@Test
	public void testBasicNonPalindromes() {
		assertFalse(Palindromes.isPalindrome("hello"));
		assertFalse(Palindromes.isPalindrome("book"));
	}

	@Test
	public void testCase() {
		assertTrue(Palindromes.isPalindrome("EyE"));
		assertFalse(Palindromes.isPalindrome("Eye"));
	}

	@Test
	public void testEmptyStringSentence() {
		assertTrue(Palindromes.isPalindromeSentence(""));
	}

	@Test
	public void testBasicPalindromeSentences() {
		assertTrue(Palindromes.isPalindromeSentence("Eye"));
		assertTrue(Palindromes.isPalindromeSentence("racecar"));
		assertTrue(Palindromes.isPalindromeSentence("Madam, I'm Adam"));
		assertTrue(Palindromes.isPalindromeSentence("Never odd or even"));
	}

	@Test
	public void testBasicNonPalindromeSentences() {
		assertFalse(Palindromes.isPalindromeSentence("Hello world"));
	}

}
