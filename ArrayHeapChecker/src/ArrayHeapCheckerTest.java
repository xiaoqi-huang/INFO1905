import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayHeapCheckerTest {

	@Test(timeout=100)
    public void testIsBinaryTree_ExampleTest() {

        //These are all binary trees
        assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] { 0, 1, 2, 3, 4, 5, 6 }));
        assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] { 8, 8, 8, 8, 8, 8, 8 }));
        assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] {0, 1, null, 3, 4, null, null}));
        assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] {0, 1, 2, null, 4, null, 6}));
        assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] {0, null, null}));
        assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] {0}));
        assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] {null}));
        assertTrue(ArrayHeapChecker.isBinaryTree(new Integer[] {}));

        //These are not binary trees (because nodes exist which don't have parents)
        assertFalse(ArrayHeapChecker.isBinaryTree(new Integer[] {null, 1, 2, 3, 4, 5, 6}));
        assertFalse(ArrayHeapChecker.isBinaryTree(new Integer[] {0, 1, null, 3, 4, 5, null}));
        assertFalse(ArrayHeapChecker.isBinaryTree(new Integer[] {0, 1, null, null, 4, 5, 6}));
        assertFalse(ArrayHeapChecker.isBinaryTree(new Integer[] {null, 0}));
    }

}
