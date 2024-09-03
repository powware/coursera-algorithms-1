package src.queues;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size = 0;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size + 1 > items.length - 1) {
            resize(items.length * 2);
        }

        items[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int i = StdRandom.uniformInt(size);

        Item temp = items[i];

        items[i] = items[size - 1];

        items[size--] = null;

        if (size < items.length / 4) {
            resize(items.length / 2);
        }

        return temp;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int i = StdRandom.uniformInt(size);

        return items[i];
    }

    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private int i = 0;
        private int[] indexes;

        public RandomQueueIterator() {
            indexes = StdRandom.permutation(size);
        }

        public boolean hasNext() {
            return i < indexes.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return items[indexes[i++]];
        }
    }

    private void resize(int new_size) {
        Item[] new_items = (Item[]) new Object[new_size];

        for (int i = 0; i < size; i++) {
            new_items[i] = items[i];
        }

        items = new_items;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
        test.enqueue(5);
        test.enqueue(4);
        test.enqueue(3);
        test.enqueue(2);
        test.enqueue(1);

        for (Integer integer : test) {
            System.out.println(integer);
        }
        for (Integer integer : test) {
            System.out.println(integer);
        }

    }

}
