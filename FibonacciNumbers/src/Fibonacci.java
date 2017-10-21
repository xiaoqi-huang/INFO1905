import java.util.Stack;

public class Fibonacci {

	public static Stack<Integer> getNumbers(int n) {

		if (n < 0) return new Stack<Integer>();

		Stack<Integer> numbers = new Stack<Integer>();

		Integer firstTmp, secondTmp;
		for (int i = 0; i <= n; i++) {
			if (i == 0) {
				numbers.push(new Integer(0));
			} else if (i == 1) {
				numbers.push(new Integer(1));
			} else {
                secondTmp = numbers.peek();
                numbers.pop();
                firstTmp = numbers.peek();
				numbers.push(secondTmp);
                numbers.push(firstTmp + secondTmp);
			}
		}

		return numbers;

	}

}
