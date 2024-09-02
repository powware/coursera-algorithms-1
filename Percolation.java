import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF union_;
    private int n_;
    private boolean[] open_;

    private int open_count_ = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        n_ = n;
        union_ = new WeightedQuickUnionUF(n * n + 2);
        open_ = new boolean[n * n];

        for (int i = 0; i < open_.length; i++) {
            open_[i] = false;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n_ || col < 1 || col > n_) {
            throw new IllegalArgumentException();
        }

        if (isOpen(row, col)) {
            return;
        }

        open_[indexInOpen(row, col)] = true;
        ++open_count_;

        int index = indexInUnion(row, col);

        if (row > 1) {
            if (isOpen(row - 1, col))
                union_.union(index, indexInUnion(row - 1, col));
        } else {
            union_.union(index, 0);
        }
        if (row < n_) {

            if (isOpen(row + 1, col)) {
                union_.union(index, indexInUnion(row + 1, col));
            }
        } else {
            union_.union(index, n_ * n_ + 1);
        }
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                union_.union(index, indexInUnion(row, col - 1));
            }
        }
        if (col < n_) {

            if (isOpen(row, col + 1)) {
                union_.union(index, indexInUnion(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n_ || col < 1 || col > n_) {
            throw new IllegalArgumentException();
        }

        return open_[indexInOpen(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n_ || col < 1 || col > n_) {
            throw new IllegalArgumentException();

        }

        return union_.find(indexInUnion(row, col)) == union_.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return open_count_;
    }

    // does the system percolate?
    public boolean percolates() {
        return union_.find(0) == union_.find(n_ * n_ + 1);
    }

    private int indexInUnion(int row, int col) {
        return indexInOpen(row, col) + 1;
    }

    private int indexInOpen(int row, int col) {
        return (row - 1) * n_ + col - 1;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation perc = new Percolation(3);
        perc.open(1, 1);
        perc.open(2, 1);
        System.out.println(perc.isFull(1, 1));
        perc.open(2, 2);
        perc.open(3, 2);
        perc.open(3, 3);
        System.out.println(perc.percolates());
    }
}
