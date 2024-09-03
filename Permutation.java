import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> ranque = new RandomizedQueue<String>();
        for (int i = 1; i < 1 + k; i++) {
            ranque.enqueue(StdIn.readString());
        }

        for (String i : ranque) {
            System.out.println(i);
        }

    }
}
