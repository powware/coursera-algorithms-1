
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {

        double read = 1;
        String survivor = "none";
        while (!StdIn.isEmpty()) {
            String candidate = StdIn.readString();
            if (StdRandom.bernoulli(1 / read)) {
                survivor = candidate;
            }
            read += 1;
        }

        StdOut.println(survivor);
    }
}
