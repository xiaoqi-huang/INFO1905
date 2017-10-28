import java.util.List;

public class CocktailSort {

	public static void sort(List<Integer> list) {
		boolean done = false;
        int direction = 0;
        while (!done) {
            done = true;
            if (direction == 0) {
                for (int i = 0; i < list.size() - 1; i++) {
    				if (list.get(i) > list.get(i + 1)) {
    					int temp = list.get(i);
    					list.set(i, list.get(i + 1));
    					list.set(i + 1, temp);
    					done = false;
    				}
    			}
                direction = 1;
            } else {
                for (int i = list.size() - 1; i > 0; i--) {
    				if (list.get(i - 1) > list.get(i)) {
    					int temp = list.get(i - 1);
    					list.set(i - 1, list.get(i));
    					list.set(i, temp);
    					done = false;
    				}
    			}
                direction = 0;
            }
        }
	}
}
