public class InPlaceHeapSort {

	private static int getLeft(int index, int[] array) {
		if (index < 0 || index >= array.length || (2 * index + 1) >= array.length) {
            return -1;
        }
        return 2 * index + 1;
	}

	private static int getRight(int index, int[] array) {
		if (index < 0 || index >= array.length || (2 * index + 2) >= array.length) {
			return -1;
		}
		return 2 * index + 2;
	}

	private static int getParent(int index, int[] array) {
		if (index <= 0 || index >= array.length || (int) Math.floor((index - 1) / 2) < 0) {
            return -1;
        }
		return (int) Math.floor((index - 1) / 2);
	}

	public static void heapify(int[] array) {
		int tmp;
		int currIndex, parentIndex;
		// Bottom up
        for (int i = array.length - 1; i > 0; i--) {
			currIndex = i;
			parentIndex = getParent(currIndex, array);
			while (parentIndex != -1) {
				if (array[currIndex] < array[parentIndex]) {
					tmp = array[currIndex];
					array[currIndex] = array[parentIndex];
					array[parentIndex] = tmp;
					currIndex = getParent(parentIndex, array);
					parentIndex = getParent(currIndex, array);
	        	} else {
					break;
				}
			}
        }
    }

	public static void heapSort(int[] array) {
        // Convert the array to a heap
        heapify(array);

		int tmp;
		int currIndex, leftIndex, rightIndex;

		for (int i = 0; i < array.length; i++) {
			// Switch the position of the first and the last element
			tmp = array[0];
			array[0] = array[array.length - i - 1];
			array[array.length - i - 1] = tmp;
			// Down heap
			currIndex = 0;
			leftIndex = getLeft(currIndex, array);
			rightIndex = getRight(currIndex, array);
			while (leftIndex != -1 && rightIndex != -1 && leftIndex < array.length - i - 1 && rightIndex < array.length - i - 1) {
				if (array[currIndex] < array[leftIndex] && array[currIndex] < array[rightIndex]) {
					break;
				} else if (array[leftIndex] < array[rightIndex]) {
	        		tmp = array[currIndex];
					array[currIndex] = array[leftIndex];
					array[leftIndex] = tmp;
					currIndex = leftIndex;
	        	} else {
					tmp = array[currIndex];
					array[currIndex] = array[rightIndex];
					array[rightIndex] = tmp;
					currIndex = rightIndex;
				}
				leftIndex = getLeft(currIndex, array);
				rightIndex = getRight(currIndex, array);
			}
		}
    }

}
