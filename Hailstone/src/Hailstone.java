import java.util.ArrayList;
import java.util.List;

public class Hailstone {

    public static List<Integer> hailstone(int n) {

        List<Integer> result = new ArrayList<Integer>();
        result.add(n);

        if (n == 1) {
            return result;
        }

        int next;
        if (n % 2 == 0) {
            next = n / 2;
        } else {
            next = 3 * n + 1;
        }

        result.addAll(hailstone(next));
        return result;

    }

}
