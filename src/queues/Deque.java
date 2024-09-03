import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        private Node previous = null;
        private Node next = null;
        private Item value;

        private Node(Item value) {
            this.value = value;
        }

        private Node() {
        }
    }

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        ++size;

        Node node = new Node(item);

        if (first == null) {
            first = last = node;
        } else {
            node.next = first;
            first.previous = node;
            first = node;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        ++size;

        Node node = new Node(item);

        if (last == null) {
            first = last = node;
        } else {
            node.previous = last;
            last.next = node;
            last = node;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        --size;

        if (first.next != null) {
            first.next.previous = null;
        } else {
            last = null;
        }

        Node temp = first;
        first = first.next;

        return temp.value;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        --size;

        if (last.previous != null) {
            last.previous.next = null;
        } else {
            first = null;
        }

        Node temp = last;
        last = last.previous;

        return temp.value;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node node = first;

        public boolean hasNext() {
            return node != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node temp = node;

            node = node.next;

            return temp.value;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> test = new Deque<Integer>();
        test.addFirst(0);
        test.removeFirst();

        test.addLast(0);
        test.removeLast();

        test.addFirst(0);
        test.removeLast();

        for (Integer integer : test) {
            System.out.println(integer);
        }
    }

}
