import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF union;
    private int n;
    private boolean[] open;
    private int openCount = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        n = n;
        union = new WeightedQuickUnionUF(n * n + 2);
        open = new boolean[n * n];

        for (int i = 0; i < open.length; i++) {
            open[i] = false;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }

        if (isOpen(row, col)) {
            return;
        }

        open[indexInOpen(row, col)] = true;
        ++openCount;

        int index = indexInUnion(row, col);

        if (row > 1) {
            if (isOpen(row - 1, col))
                union.union(index, indexInUnion(row - 1, col));
        } else {
            union.union(index, 0);
        }
        if (row < n) {

            if (isOpen(row + 1, col)) {
                union.union(index, indexInUnion(row + 1, col));
            }
        } else {
            union.union(index, n * n + 1);
        }
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                union.union(index, indexInUnion(row, col - 1));
            }
        }
        if (col < n) {

            if (isOpen(row, col + 1)) {
                union.union(index, indexInUnion(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }

        return open[indexInOpen(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();

        }

        return union.find(indexInUnion(row, col)) == union.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return union.find(0) == union.find(n * n + 1);
    }

    private int indexInUnion(int row, int col) {
        return 1 + (row - 1) * n + col - 1;
    }

    private int indexInOpen(int row, int col) {
        return (row - 1) * n + col - 1;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation perc = new Percolation(5);
        perc.open(3, 3);
        perc.open(1, 1);
        System.out.println(perc.isFull(2, 1));
        perc.open(2, 1);
        perc.open(2, 2);
        perc.open(2, 2);
        perc.open(4, 3);
        perc.open(5, 3);
        System.out.println(perc.isFull(2, 1));
        System.out.println(perc.percolates());
    }
}
