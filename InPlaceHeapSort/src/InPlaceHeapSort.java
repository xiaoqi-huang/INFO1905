public class InPlaceHeapSort {

	private static int getLeft(int index, int[] array) {
		if (index < 0 || index > array.length || array[2 * index + 1] == null) {
            return -1;
        }
        return 2 * index + 1;
	}

	private static int getRight(int index, int[] array) {
		if (index < 0 || index > array.length || array[2 * index + 1] == null) {
			return -1;
		}
		return 2 * index + 2;
	}

	private static int getParent(int index, int[] array) {
		if (index < 0 || index > array.length || array[2 * index + 1] == null) {
            return -1;
        }
		return (int) Math.floor((index - 1) / 2);
	}

	public static void heapify(int[] array) {
        for (int i = 0; i < array.length; i++) {
        	if ((array[i] < array[getLeft()]) && (array[i] < array[getRight()])) {
        		continue;
        	} else if (array[getLeft()] < array[getRight()]) {
        		int tmp = array[i];
				array[i] = array[getLeft()];
				array[getLeft()] = tmp;
        	} else {
				int tmp = array[i];
				array[i] = array[getRight()];
				array[getRight()] = tmp;
			}
        }
    }

	public static void heapSort(int[] array) {
        //convert the array to a heap
        heapify(array);
        //TODO: in-place sort
        // each time you remove-min, the heap will get one value smaller
        // and the sorted section at the end of the array will get one more value
    }

}
