package bearmaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static java.lang.Math.min;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private class PriorityNode {
        private T item;
        private double priority;

        public PriorityNode(T i, double p) {
            item = i;
            priority = p;
        }
    }

    private ArrayList<PriorityNode> items = new ArrayList<>();

    private HashMap<T, Integer> itemIndexMap = new HashMap<>();
    private int size;

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private int rightChild(int i) {
        return i * 2 + 2;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int minChild(int k) {
        int leftChild = leftChild(k);
        int rightChild = rightChild(k);
        if (leftChild >= size) {
            return k;
        }
        if (rightChild>= size) {
            return k;
        }

        PriorityNode rChild = items.get(rightChild);
        PriorityNode lChild = items.get(leftChild);
        if (rChild.priority < lChild.priority) {
            return rightChild;
        }
        return leftChild;
    }

    private void swap(int child, int parent) {
        T tempItem = items.get(child).item;
        T parentItem = items.get(parent).item;
        double tempPriority = items.get(child).priority;
        itemIndexMap.put(tempItem, parent);
        itemIndexMap.put(parentItem, child);
        items.get(child).item = items.get(parent).item;
        items.get(child).priority = items.get(parent).priority;
        items.get(parent).item = tempItem;
        items.get(parent).priority = tempPriority;

    }

    private void swimUp(int k) {
        if (items.get(parent(k)).priority > items.get(k).priority) {
            swap(k, parent(k));
            swimUp(parent(k));
        }
    }

    private void swimDown(int k) {
        if (items.get(minChild(k)).priority < items.get(k).priority) {
            int minChild = minChild(k);
            swap(minChild, k);
            swimDown(minChild);

        }
    }

    @Override
    public void add(T item, double priority) {
        if (itemIndexMap.containsKey(item)) {

            throw new IllegalArgumentException();
        }
        itemIndexMap.put(item, size);
        items.add(new PriorityNode(item, priority));
        swimUp(size);

        size++;
    }

    @Override
    public boolean contains(T item) {
        return itemIndexMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return items.get(0).item;
    }

    @Override
    public T removeSmallest() {
        T smallest = items.get(0).item;
        PriorityNode biggestNode = items.get(size - 1);
        if (size == 1) {
            itemIndexMap = new HashMap<>();
            items = new ArrayList<>();
            size--;
            return smallest;
        }
        if (size == 0) {
            throw new NoSuchElementException();
        }

        itemIndexMap.remove(smallest);
        itemIndexMap.put(biggestNode.item, 0);
        items.set(0, biggestNode);

        items.remove(size - 1);
        size--;
        swimDown(0);
        return smallest;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        int itemIndex = itemIndexMap.get(item);
        double itemPriority = items.get(itemIndex).priority;
        items.get(itemIndex).priority = priority;
        if (priority > itemPriority) {
            items.get(itemIndex).priority = priority;
            swimDown(itemIndex);
        } else if(priority < itemPriority) {
            swimUp(itemIndex);
        }
        else{
            items.get(itemIndex).priority = priority;
        }
    }
}
