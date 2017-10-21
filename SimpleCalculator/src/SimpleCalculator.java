
public class SimpleCalculator implements Arithmetic {

	@Override
	public double add(double a, double b) {
		return a + b;
	}

	@Override
	public double subtract(double a, double b) {
		return a - b;
	}

	@Override
	public double multiply(double a, double b) {
		return a * b;
	}

	@Override
	public double divide(double a, double b) {
		if (b == 0) return 0;
		return a / b;
	}


}
