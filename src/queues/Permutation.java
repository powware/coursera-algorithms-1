import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(StdIn.readString());

        RandomizedQueue<String> ranque = new RandomizedQueue<String>();
        for (int i = 1; i < args.length - 1; i++) {
            ranque.enqueue(StdIn.readString());
        }

        Iterator<String> it = ranque.iterator();
        for (int i = 0; i < k; i++) {
            System.out.println(it.next());
        }

    }
}
