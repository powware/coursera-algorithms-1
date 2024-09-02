import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double trials_;
    private double[] thresholds_;
    private double mean_ = 0;
    private double stddev_ = 0;
    private double lo_ = 0;
    private double hi_ = 0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        trials_ = trials;
        thresholds_ = new double[trials];
        for (int i = 0; i < trials; ++i) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniformInt(n) + 1, StdRandom.uniformInt(n) + 1);
            }

            thresholds_[i] = (double) perc.numberOfOpenSites() / (double) (n * n);
            mean_ += thresholds_[i];
        }

        mean_ /= thresholds_.length;

        for (int i = 0; i < thresholds_.length; i++) {
            stddev_ += Math.pow(thresholds_[i] - mean_, 2);
        }

        stddev_ /= (double) (trials_ - 1);

        lo_ = (mean_ - 1.96 * stddev_) / Math.sqrt(trials_);
        hi_ = (mean_ + 1.96 * stddev_) / Math.sqrt(trials_);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean_;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev_;

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return lo_;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return hi_;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percs = new PercolationStats(200, 100);
        System.out.println(percs.mean());
        System.out.println(percs.stddev());
        System.out.println(percs.confidenceLo());
        System.out.println(percs.confidenceHi());
    }

}