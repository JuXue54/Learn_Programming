
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 * PercolationStats
 * @author Jon Xue
 */
public class PercolationStats {

    private double[] threshold;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        threshold = new double[trials];
        Percolation sample;
        int randrow;
        int randcol;
        for (int i = 0; i < trials; i++) {
            sample = new Percolation(n);
            while (!sample.percolates()) {
                randrow = StdRandom.uniform(n) + 1;
                randcol = StdRandom.uniform(n) + 1;
                sample.open(randrow, randcol);
            }
            threshold[i] = sample.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = 200;
        int T = 100;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            T = Integer.parseInt(args[1]);
        }
        var example = new PercolationStats(n, T);
        System.out.printf("mean                    = %.6f\n", example.mean());
        System.out.printf("stddev                  = %.6f\n", example.stddev());
        System.out.printf("95%% confidence interval = [%.6f, %.6f]\n", example.confidenceLo(), example.confidenceHi());
    }
}