import java.util.List;

public class BubbleSort {
	
	public static void sort(List<Integer> list) {
		boolean done = false;
		
		for (int iter = 0; iter < list.size(); iter++) {
			done = true;
			for (int i = 0; i < list.size() - iter - 1; i++) {
				if (list.get(i) > list.get(i + 1)) {
					int temp = list.get(i);
					list.set(i, list.get(i + 1));
					list.set(i + 1, temp);
					done = false;
				}
			}
			if (done == true) {
				break;
			}
		}
	}
}
