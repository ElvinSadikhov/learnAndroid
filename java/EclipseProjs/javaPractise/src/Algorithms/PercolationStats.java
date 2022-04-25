package Algorithms;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private final double[] results;
	private final int numOfExperiments;
	private static final double CONFIDENCE = 1.96;

	public PercolationStats(int n, int e) {
		if (n <= 0 || e <= 0) {
			throw new IllegalArgumentException();
		}

		numOfExperiments = e;
		results = new double[numOfExperiments];
		for (int exp = 0; exp < numOfExperiments; exp++) {
			Percolation pr = new Percolation(n);
			int openedSites = 0;
			while (!pr.percolates()) {
				int i = StdRandom.uniform(1, n + 1);
				int k = StdRandom.uniform(1, n + 1);
				if (!pr.isOpen(i, k)) {
					pr.open(i, k);
					openedSites++;
				}
			}
			double result = (double) openedSites / (n * n);
			results[exp] = result;
		}

	}

	public double mean() {
		return StdStats.mean(results);
	}

	public double stddev() {
		return StdStats.stddev(results);
	}

	public double confidenceLo() {
		return mean() - ((CONFIDENCE * stddev()) / Math.sqrt(numOfExperiments));
	}

	public double confidenceHi() {
		return mean() + ((CONFIDENCE * stddev()) / Math.sqrt(numOfExperiments));
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int e = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(n, e);
		String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + confidence);

	}

}
