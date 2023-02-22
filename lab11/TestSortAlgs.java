import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Integer> q = new Queue<>();
        for (int x = 0; x < 10; x++) {
            q.enqueue(StdRandom.uniform(0, 10));
        }
        Queue<Integer> sortedQ = QuickSort.quickSort(q);
        for (int x = 0; x < 5; x++) {
            Assert.assertTrue(sortedQ.dequeue() <= sortedQ.dequeue());

        }
        Assert.assertTrue(isSorted(q));
    }

    @Test
    public void testMergeSort() {
        Queue<Integer> q = new Queue<>();
        for (int x = 0; x < 10; x++) {
            q.enqueue(StdRandom.uniform(0, 100));
        }
        Queue<Integer> sortedQ = MergeSort.mergeSort(q);

            Assert.assertTrue(isSorted(sortedQ));

    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items A Queue of items
     * @return true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
