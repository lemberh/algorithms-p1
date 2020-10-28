/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONST = 1.96;
    private final int size;
    private final int trials;
    private final int gridSize;

    private double[] threasholds;

    private double mean = 0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        size = n;
        gridSize = size * size;
        threasholds = new double[trials];
        this.trials = trials;
        startTest();
    }

    private void startTest() {
        for (int trial = 0; trial < trials; trial++) {
            Percolation p = new Percolation(size);
            do {
                int row = StdRandom.uniform(1, size + 1);
                int col = StdRandom.uniform(1, size + 1);
                p.open(row, col);
            } while (!p.percolates());
            threasholds[trial] = p.numberOfOpenSites() / ((double) gridSize);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        if (mean == 0) {
            // for (int i = 0; i < threasholds.length; i++) {
            //     mean += threasholds[i];
            // }
            // mean = mean / threasholds.length;
            mean = StdStats.mean(threasholds);
        }
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        // double t = mean();
        // double sum = 0;
        // for (int i = 0; i < threasholds.length; i++) {
        //     sum += Math.pow(threasholds[i] - t, 2);
        // }
        // return Math.sqrt(sum / (trials - 1));
        return StdStats.stddev(threasholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONST * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONST * stddev() / Math.sqrt(trials);
    }


    // test client (see below)
    public static void main(String[] args) {
        int size = (args.length > 0) ? Integer.parseInt(args[0]) : 300;
        int trials = (args.length > 1) ? Integer.parseInt(args[1]) : 200;
        // Stopwatch stpw = new Stopwatch();
        PercolationStats t = new PercolationStats(size, trials);
        // System.out.println("Time elapsed:" + stpw.elapsedTime() + "s");
        System.out.println("mean() = " + t.mean());
        System.out.println("stddev() = " + t.stddev());
        System.out.println(
                "95% confidence interval = [" + t.confidenceLo() + "," + t.confidenceHi() + "]");
    }

}
