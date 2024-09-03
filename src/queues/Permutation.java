package src.queues;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> ranque = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            ranque.enqueue(StdIn.readString());
        }

        Iterator<String> it = ranque.iterator();
        for (int i = 0; i < k; i++) {
            System.out.println(it.next());
        }

    }
}
