import java.util.Scanner;

public class MatrixMunipulations {

	public int[][] createMatrix(int R, int C) {
		int[][] matrix = new int[R][C];
		return matrix;
	}

	public int[][] fillMatrix(int[][] matrix) {

		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
				matrix[r][c] = (int) (Math.random() * 100);
			}
		}
		return matrix;
	}

	public void printMatrix(int[][] matrix) {
		System.out.println();
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
				System.out.print(String.format("%4d", matrix[r][c]));
			}
			System.out.println();
		}
	}

	public int[][] mainDioganal(int[][] matrix) {
		System.out.println();
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c <= r; c++) { // by deleting = we will touch only the part under the dioganal
				matrix[r][c] = 0;
			}
		}
		return matrix;
	}

	public int[][] secondDioganal(int[][] matrix) {
		System.out.println();
		for (int r = 0; r < matrix.length; r++) {
			for (int c = r; c < matrix.length; c++) { //by adding 1 to int c = r + 1 will not touch dioganal itself
				matrix[r][c] = 0;
			}
		}
		return matrix;
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		System.out.println("Type number of rows: ");
		int R = in.nextInt();

		System.out.println("Type number of columns: ");
		int C = in.nextInt();

		MatrixMunipulations obj = new MatrixMunipulations();
		int[][] matrix = obj.fillMatrix(obj.createMatrix(R, C));

		obj.printMatrix(matrix);

		obj.printMatrix(obj.mainDioganal(matrix));
		
		matrix = obj.fillMatrix(obj.createMatrix(R, C));
		
		obj.printMatrix(obj.secondDioganal(matrix));
	
	}

}