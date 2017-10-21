import java.util.Stack;

public class Palindromes {

	public static boolean isPalindrome(String word) {

		if (word.length() == 0) return true;

		Stack<Character> chars = new Stack<Character>();

		for (int i = 0; i < Math.ceil(word.length() / 2d); i++) {
			chars.push(word.charAt(i));
		}

		for (int i = word.length() / 2; i < word.length(); i++) {
			if(word.charAt(i) == chars.peek()) {
				chars.pop();
			} else {
				return false;
			}
		}

		return true;

	}

	public static boolean isPalindromeSentence(String sentence) {

		String newSentence = "";

		for (int i = 0; i < sentence.length(); i++) {
			if (!Character.isAlphabetic(sentence.charAt(i))) continue;
			newSentence += Character.toLowerCase(sentence.charAt(i));
		}

		return isPalindrome(newSentence);

	}

}
