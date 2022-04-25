package puzzle8;

import java.util.ArrayList;

public class Board {

	private int n; // num of rows/columns
	private int[][] board; // our board

	// create a board from an n-by-n array of tiles,
	// where tiles[row][col] = tile at (row, col)
	public Board(int[][] tiles) {
		// we create a copy version of tiles[][]
		n = tiles.length;
		board = new int[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				board[r][c] = tiles[r][c];
			}
		}
	}

	// string representation of this board
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(n + "\n");
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				sb.append(" " + board[r][c]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	// board dimension n
	public int dimension() {
		return board.length;
	}

	// number of tiles out of place
	public int hamming() {
		int count = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				int num = board[r][c];
				if (num != 0 && num != (r * n + c + 1))
					count++;
			}
		}
		return count;
	}

	// sum of Manhattan distances between tiles and goal
	public int manhattan() {
		// manhattan distance is a sum of row and column distances to goal position
		int sum = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				int num = board[r][c];
				if (num == 0)
					continue;
				int distance = Math.abs(r - (num - 1) / n) + Math.abs(c - (num - 1) % n);
				sum += distance;
			}
		}
		return sum;
	}

	// is this board the goal board?
	public boolean isGoal() {
		// can use any of them
//		return manhattan() == 0;
		return hamming() == 0;
	}

	// does this board equal y?
	public boolean equals(Object y) {
		// checking for the same objects!
		if (this == y)
			return true;
		// if the object is null OR if they have different lengths!
		if (y == null || !(y instanceof Board) || ((Board) y).board.length != this.board.length)
			return false;

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (this.board[r][c] != ((Board) y).board[r][c])
					return false;
			}
		}
		return true;
	}

	// all neighboring boards
	public Iterable<Board> neighbors() {
		ArrayList<Board> neighbors = new ArrayList<>();

		int[] coordinatesOfBlank = new int[2];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (board[r][c] == 0) {
					coordinatesOfBlank[0] = r;
					coordinatesOfBlank[1] = c;
					break;
				}
			}
		}
		int R = coordinatesOfBlank[0];
		int C = coordinatesOfBlank[1];

		// checking positions
		if (R > 0)
			neighbors.add(new Board(swap(R, C, R - 1, C)));
		if (R < dimension() - 1)
			neighbors.add(new Board(swap(R, C, R + 1, C)));
		if (C > 0)
			neighbors.add(new Board(swap(R, C, R, C - 1)));
		if (C < dimension() - 1)
			neighbors.add(new Board(swap(R, C, R, C + 1)));

		return neighbors;
	}

	// a board that is obtained by exchanging any pair of tiles
	public Board twin() {
		Board temp = null;
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length - 1; c++) {
				if (board[r][c] != 0 && board[r][c + 1] != 0) {
					temp = new Board(swap(r, c, r, c + 1));
				}
			}
		}
		return temp;
	}

	// changing the positions of 2 given numbers
	private int[][] swap(int row1, int col1, int row2, int col2) {
		int[][] copy = new int[dimension()][dimension()];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				copy[r][c] = board[r][c];
			}
		}

		int tmp = copy[row1][col1];
		copy[row1][col1] = copy[row2][col2];
		copy[row2][col2] = tmp;

		return copy;
	}
}
