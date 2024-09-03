package src.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private double trialCount;
    private double[] thresholds;
    private double mean = 0;
    private double stddev = 0;
    private double lo = 0;
    private double hi = 0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        trialCount = trials;
        thresholds = new double[trials];
        for (int i = 0; i < trials; ++i) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniformInt(n) + 1, StdRandom.uniformInt(n) + 1);
            }

            thresholds[i] = (double) perc.numberOfOpenSites() / (double) (n * n);
        }

        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);

        lo = mean - (CONFIDENCE_95 * stddev / Math.sqrt(trialCount));
        hi = mean + (CONFIDENCE_95 * stddev / Math.sqrt(trialCount));
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return lo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return hi;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percs = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean\t\t\t= " + percs.mean());
        System.out.println("stddev\t\t\t= " + percs.stddev());
        System.out.println("95% confidence interval\t= [" + percs.confidenceLo() + ", " + percs.confidenceHi() + "]");
    }

}