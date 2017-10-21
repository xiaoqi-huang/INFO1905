public class ArrayHeapChecker {

    private static Integer getLeft(int index, Integer[] array) {
        if (index < 0 || index > array.length || array[2 * index + 1] == null) {
            return -1;
        }
        return 2 * index + 1;
    }

    private static int getRight(int index, Integer[] array) {
        if (index < 0 || index > array.length || array[2 * index + 2] == null) {
            return -1;
        }
        return 2 * index + 2;
    }

    private static int getParent(int index, Integer[] array) {
        if (index < 0 || index > array.length || array[(index - 1) / 2] == null) {
            return -1;
        }
        return (int) Math.floor((index - 1) / 2);
    }

    //Check if the given array is a representation of a binary tree
    public static boolean isBinaryTree(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            if (getParent(i, array) == -1 && array[i] != null) {
                return false;
            }
        }
        return true;
    }

    //Check if the given array is a complete binary tree
    public static boolean isCompleteBinaryTree(Integer[] array) {
        if(!isBinaryTree(array)) {
            return false;
        }
        int last = -1;
        for (int index = 0; index < array.length; index++) {
            if (array[index] == null) {
                last = index;
                break;
            }
        }
        if (last != -1) {
            for (int index = last; index < array.length; index++) {
                if (array[index] != null) return false;
            }
        }
        return true;
    }

    //Check if the given array is a min-heap
    public static boolean isMinHeap(Integer[] array) {
        if(!isCompleteBinaryTree(array)) {
            return false;
        }
        for (int index = 1; index < array.length; index++) {
            if (array[index] == null) break;
            if (array[getParent(index, array)] > array[index]) return false;
        }
        return true;
    }

}
