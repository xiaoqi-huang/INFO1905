import java.util.List;

public class CombSort {

	public static void sort(List<Integer> list) {
		boolean done = false;
		int gap = list.size();

        while (!done || gap != 1) {
            done = true;
            gap = (int) (gap / 1.3);
            if (gap < 1) {
                gap = 1;
            }
            for (int i = 0; i + gap < list.size(); i++) {
				if (list.get(i) > list.get(i + gap)) {
					int temp = list.get(i);
					list.set(i, list.get(i + gap));
					list.set(i + gap, temp);
					done = false;
				}
			}
        }
    }
}
