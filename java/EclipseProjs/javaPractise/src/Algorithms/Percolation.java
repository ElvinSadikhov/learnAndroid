package Algorithms;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*
 * It is assignment of week 1 of Algorithms part 1
 */

public class Percolation {

	private final int size;
	private boolean[][] grid;// grid which determine whether cell is opened or not
	private final WeightedQuickUnionUF qf;
	private final int bottom;
	private static final int TOP = 0;
	private int openSites;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		if (n < 1)
			throw new IllegalArgumentException();

		size = n;
		bottom = size * size + 1;// as our top is 0 and our grid is from 1 to size^2, so size^2+1 is bottom index
		qf = new WeightedQuickUnionUF(size * size + 2);// because of top and bottom
		grid = new boolean[size][size];
		openSites = 0;
	}

	private int getQuickFindIndex(int row, int col) {
		return size * (row - 1) + col;
	}

	private void checkException(int row, int col) {
		if (row <= 0 || row > size || col <= 0 || col > size) {
			throw new IllegalArgumentException();
		}
	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		checkException(row, col);

		grid[row - 1][col - 1] = true;
		++openSites;

		// if it is on the top of the grid => we union it with TOP
		if (row == 1) {
			qf.union(getQuickFindIndex(row, col), TOP);
		}

		// if it is on the bottom of the grid => we union it with bottom
		if (row == size) {
			qf.union(getQuickFindIndex(row, col), bottom);
		}

		// checking for middle positions and connecting them to the not opened neighbors
		if (row > 1 && isOpen(row - 1, col)) { // check for left
			qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row - 1, col));
		}
		if (row < size && isOpen(row + 1, col)) { // check for right
			qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row + 1, col));
		}
		if (col > 1 && isOpen(row, col - 1)) { // check for bottom
			qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col - 1));
		}
		if (col < size && isOpen(row, col + 1)) { // check for top
			qf.union(getQuickFindIndex(row, col), getQuickFindIndex(row, col + 1));
		}

	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		checkException(row, col);
		return grid[row - 1][col - 1];
	}

	// is the site (row, col) full?(is there any water or is it connected to the
	// top)
	public boolean isFull(int row, int col) {
		// find method finds the root of any index, so if they are equal, then they are
		// connected
		if ((row > 0 && row <= size) && (col > 0 && col <= size))
			return qf.find(TOP) == qf.find(getQuickFindIndex(row, col));
		else
			throw new IllegalArgumentException();
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		return openSites;
	}

	// does the system percolate?
	public boolean percolates() { // If top is connected to bottom, then system percolates
		return qf.find(TOP) == qf.find(bottom);
	}

}
