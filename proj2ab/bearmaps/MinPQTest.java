package bearmaps;


import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;



public class MinPQTest {

        @Test
        public void sanitySizeTest() {
            ArrayHeapMinPQ<Integer> PQ = new ArrayHeapMinPQ<>();
            for (int i = 0; i < 500; i++) {
                assertEquals(i, PQ.size());
                PQ.add(i, i);
                assertEquals(i+1, PQ.size());
            }

            for (int i = 500; i > 0; i--) {
                assertEquals(i, PQ.size());
                PQ.removeSmallest();
                assertEquals(i-1, PQ.size());
            }
        }

        @Test
        public void sanityRemoveTest() {
            ArrayHeapMinPQ<Character> PQ = new ArrayHeapMinPQ<>();
            assertEquals(0, PQ.size());
            PQ.add('a', 10);
            assertEquals('a', (Object) PQ.getSmallest());
            assertEquals(1, PQ.size());
            assertEquals('a', (Object) PQ.removeSmallest());
            assertEquals(0, PQ.size());
        }

        @Test
        public void randomRemoveTest() {
            ArrayHeapMinPQ<Integer> PQ = new ArrayHeapMinPQ<>();
            for (int i = 0; i < 100; i++) {
                PQ.add(i, StdRandom.uniform(0, 10));
            }
            for (int i = 0; i < 25; i++) {
                assertEquals(PQ.getSmallest(), PQ.removeSmallest());
            }
        }
    }

