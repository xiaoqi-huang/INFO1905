public class EnglishCalculator {

	/*
	 * calculate() takes a String of the calculation to perform, such as:
	 *     4 times 5 (which gives 20)
	 *     2 plus 10 (which gives 12)
	 *     6 minus 7 (which gives -1)
	 *     15 divided by 5 (which gives 3)
	 * and calls the expected method.
	 * You can assume the string will be valid, and only be in one of the forms listed above.
	 * However, the digits can be any number of characters.
	 */
	public static double calculate(String s) {
		String[] token = s.split(" ");
		double x = Double.parseDouble(token[0]);
		double y = Double.parseDouble(token[token.length - 1]);
		switch (token[1]) {
			case "plus" : return x + y;
			case "minus" : return x - y;
			case "times" : return x * y;
			case "divided" : return x / y;
			default: return Integer.MIN_VALUE;
		}
	}

}
