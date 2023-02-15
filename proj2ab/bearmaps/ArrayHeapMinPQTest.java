package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void addTest(){
        NaiveMinPQ<Integer> NHeap = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();

        for(int x = 0; x < 100; x++){
            AHeap.add(x,x);
            NHeap.add(x,x);
        }
        assertEquals(AHeap.size(),NHeap.size());
        assertEquals(AHeap.getSmallest(),NHeap.getSmallest());
    }

    @Test
    public void containsTest(){
        NaiveMinPQ<Integer> NHeap = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();

        for(int x = 2; x < 5; x++){
            AHeap.add(x,x);
            NHeap.add(x,x);
        }
        assertEquals(NHeap.getSmallest(),AHeap.getSmallest());
        assertEquals(NHeap.getSmallest(),AHeap.getSmallest());
        for (int x = 2; x < 5; x++){
            assertTrue(AHeap.contains(x));
            assertTrue(NHeap.contains(x));
        }
        AHeap.add(12,0.5);
        NHeap.add(12,0.5);
        assertEquals(NHeap.getSmallest(),AHeap.getSmallest());
        System.out.println(AHeap.getSmallest());
    }


    @Test
    public void getSmallestTest(){
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();
        AHeap.add(5,1);
        AHeap.add(6,2);
        AHeap.add(7,2);
        AHeap.add(8,2);
        assertEquals(5,(int) AHeap.getSmallest());

    }
    @Test
    public void removeSmallestTest(){
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();
        AHeap.add(5,1);
        AHeap.add(6,2);
        AHeap.add(7,3);
        AHeap.add(8,4);
        assertEquals((int) AHeap.removeSmallest(),5);
        assertEquals((int)AHeap.getSmallest(),6);
        assertEquals((int)AHeap.removeSmallest(),6);
        assertEquals((int)AHeap.getSmallest(),7);
        assertFalse(AHeap.contains(6));
        assertFalse(AHeap.contains(5));
    }
    @Test
    public void randomTest(){
        NaiveMinPQ<Integer> NHeap = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> AHeap = new ArrayHeapMinPQ<>();
        int randomCount;
        for(int x = 0; x < 500; x ++){
            System.out.println(x);
            randomCount = StdRandom.uniform(0,4);
            int randVal = StdRandom.uniform(0,100);
            int randValPrior = StdRandom.uniform(0,50);
            if(randomCount == 0&& !AHeap.contains(randVal)){
                NHeap.add(randVal,randValPrior);
                AHeap.add(randVal,randValPrior);
            }else if(randomCount == 1 && AHeap.contains(randVal) && AHeap.size() > 5){
                AHeap.changePriority(randVal,randValPrior);
                NHeap.changePriority(randVal,randValPrior);

            }else if(randomCount == 2 && AHeap.size() > 5){
                assertEquals(NHeap.getSmallest(),AHeap.getSmallest());
            }else if(randomCount == 3 && AHeap.size() > 5){
                System.out.println(AHeap.getSmallest()+" "+ NHeap.getSmallest());
                assertEquals(NHeap.removeSmallest(),AHeap.removeSmallest());
            }
        }
    }
}
