package puzzle8;

import java.util.ArrayList;
import java.util.Scanner;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {

	private Move lastMove; // will use later

	private class Move implements Comparable<Move> {

		private Move prevMove; // info about previous move
		private Board board; // board
		private int moves = 0; // number of moves done yet
		// here we use instance variable to store this value in order to avoid endless
		// number of checkings in compareTo method
		private int manhattan; // manhattan distance of the board

		public Move(Board board) {
			this.board = board;
			this.manhattan = board.manhattan();
		}

		public Move(Board board, Move prevMove) {
			this(board);
			this.prevMove = prevMove;
			this.moves = prevMove.moves + 1;
		}

		@Override
		public int compareTo(Move move) {
			// priority = manhattan + numOfMovesYet
			return (this.manhattan - move.manhattan) + (this.moves - move.moves);
		}

	}

	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if (initial == null)
			throw new IllegalArgumentException();

		MinPQ<Move> mainPQ = new MinPQ<>();
//		PriorityQueueMine<Move> mainPQ = new PriorityQueueMine<>(initial.dimension() * initial.dimension());
		mainPQ.insert(new Move(initial));
		MinPQ<Move> twinPQ = new MinPQ<>();
//		PriorityQueueMine<Move> twinPQ = new PriorityQueueMine<>(initial.dimension() * initial.dimension());
		twinPQ.insert(new Move(initial.twin()));

		// here we loop over UNTIL we got to the moment when one of the boards will be
		// solved
		while (true) {
			// THE POINT is that, the board is unsolvable if the twin version of it is
			// solvable
			// therefore, if twin version soled earlier then the given board is unsolvable!
			lastMove = nextMove(mainPQ);
			if (lastMove != null || nextMove(twinPQ) != null)
				return;
		}
	}

	// here we add take the best move (the one with the least priority)
	// and add next moves to priority queue
	private Move nextMove(MinPQ<Move> moves) {
//	private Move nextMove(PriorityQueueMine<Move> moves) {
		if (moves.isEmpty())
			return null;

		Move bestMove = moves.delMin();

		// in case we found the NEEDED solved version
		if (bestMove.board.isGoal())
			return bestMove;

		for (Board neighbor : bestMove.board.neighbors()) {
			// we skip the one which is the same as previous board (comes back)
			if (bestMove.prevMove == null || !neighbor.equals(bestMove.prevMove.board)) {
				moves.insert(new Move(neighbor, bestMove));
			}
		}

		return null;
	}

	// is the initial board solvable? (see below)
	public boolean isSolvable() {
		return lastMove != null;
	}

	// min number of moves to solve initial board; -1 if unsolvable
	public int moves() {
		// if it is unsolvable we return -1
		return isSolvable() ? lastMove.moves : -1;
	}

	// sequence of boards in a shortest solution; null if unsolvable
	public Iterable<Board> solution() {
		if (!isSolvable())
			return null;

		// we create an Iterable and add all the boards (fastest way)
		// starting from the last one! so, it is reversed
		ArrayList<Board> reversedOrder = new ArrayList<>();
		Move temp = lastMove;
		while (temp != null) {
			reversedOrder.add(temp.board);
			temp = temp.prevMove;
		}

		// here we just reverse the order, in order to get the needed sequence of moves
		ArrayList<Board> moves = new ArrayList<>();
		for (int i = reversedOrder.size() - 1; i >= 0; i--) {
			moves.add(reversedOrder.get(i));
		}
		return moves;
	}

	// test client (see below)
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.print("Type the number of rows and columns (n): ");
		int n = in.nextInt();

		int[][] tiles = new int[n][n];
		int temp = n * n;

		System.out.println(String.format("Type the number (%d times):", temp));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tiles[i][j] = in.nextInt();
			}
		}
		Board initial = new Board(tiles);

//		// create initial board from file
//		In in = new In(args[0]);
//		int n = in.readInt();
//		int[][] tiles = new int[n][n];
//		for (int i = 0; i < n; i++)
//			for (int j = 0; j < n; j++)
//				tiles[i][j] = in.readInt();
//		Board initial = new Board(tiles);
//
		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			System.out.println("No solution possible");
		else {
			System.out.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				System.out.println(board);
		}
	}
}