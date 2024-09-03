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
        return size > 0;
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

        first.next.previous = null;
        Node temp = first;
        first = first.next;

        return temp.value;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        --size;

        last.previous.next = null;
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
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addFirst(4);
        test.addFirst(5);
        test.addFirst(6);
        test.addFirst(7);
        test.addLast(-1);
        test.addLast(-2);
        test.addLast(-3);
        test.addLast(-4);
        test.addLast(-5);
        test.addLast(-6);
        test.addLast(-7);
        test.addLast(-8);
        test.addLast(-9);
        test.addLast(-10);
        test.addLast(-11);
        test.addLast(-12);

        for (Integer integer : test) {
            System.out.println(integer);
        }
    }

}
