import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickSort {

    private static final Random r = new Random();

    public static void sort(List<Integer> list) {
        if (list.isEmpty() || list.size() == 1)
            return;

        Integer pivot = list.remove(r.nextInt(list.size()));
        List<Integer> smaller = new ArrayList<Integer>();
        List<Integer> larger = new ArrayList<Integer>();

        while (!list.isEmpty()) {
            Integer item = list.remove(0);
            if (item < pivot) {
                smaller.add(item);
            } else {
                larger.add(item);
            }
        }

        sort(smaller);
        sort(larger);

        while (!smaller.isEmpty()) {
            list.add(smaller.remove(0));
        }
        list.add(pivot);
        while (!larger.isEmpty()) {
            list.add(larger.remove(0));
        }
    }
}
