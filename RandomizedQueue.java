import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int first = -1;
    private int last = -1;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return first < 0;
    }

    public int size() {
        return last - first + 1;
    }

    // public void addFirst(Item item) {
    // if (item == null) {
    // throw new IllegalArgumentException();
    // }

    // if (first == -1) {
    // items[0] = item;
    // first = last = 0;
    // return;
    // }

    // if (first - 1 < 0) {
    // increaseLeft();
    // }

    // items[--first] = item;
    // }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (first == -1) {
            items[0] = item;
            first = last = 0;
            return;
        }

        if (last + 1 > items.length - 1) {
            increaseRight();
        }

        items[++last] = item;
    }

    // public Item removeFirst() {
    // if (isEmpty()) {
    // throw new NoSuchElementException();
    // }

    // Item temp = items[first];

    // items[first++] = null;

    // return temp;
    // }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item temp = items[last];

        items[last--] = null;

        return temp;
    }

    public Item sample() {
        return items[last];
    }

    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private int i = first;

        public boolean hasNext() {
            return i <= last;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return items[i++];
        }
    }

    private void increaseLeft() {
        Item[] new_items = (Item[]) new Object[items.length * 2];

        for (int i = first; i < last - first + 1; i++) {
            new_items[items.length + i] = items[i];
        }

        first += items.length;
        last += items.length;

        items = new_items;
    }

    private void increaseRight() {
        Item[] new_items = (Item[]) new Object[items.length * 2];

        for (int i = first; i <= last; i++) {
            new_items[i] = items[i];
        }

        items = new_items;
    }

    // private void decrease() {

    // }

    public static void main(String[] args) {
        RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
    }

}
