
public class TriangleNumber {

	public static int triangle(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return n + triangle(n - 1);
	}

}
