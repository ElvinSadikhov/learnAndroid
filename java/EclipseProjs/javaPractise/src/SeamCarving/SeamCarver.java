package SeamCarving;

import java.util.HashMap;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {

	private Picture pic;
	private int width;
	private int height;

	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture) {
		if (picture == null)
			throw new IllegalArgumentException();
		this.pic = new Picture(picture);
		this.width = picture.width();
		this.height = picture.height();
	}

	// current picture
	public Picture picture() {
		return pic;
	}

	// width of current picture
	public int width() {
		return width;
	}

	// height of current picture
	public int height() {
		return height;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x < 0 || x >= width() || y < 0 || y >= height())
			throw new IllegalArgumentException();

		if ((x == 0 || x == width() - 1) || (y == 0 || y == height() - 1))
			return 1000;
//		// because get function take col,row ! NOT row,col
//		int temp = x;
//		x = y;
//		y = temp;

		int rX = pic.get(x, y + 1).getRed() - pic.get(x, y - 1).getRed();
		int gX = pic.get(x, y + 1).getGreen() - pic.get(x, y - 1).getGreen();
		int bX = pic.get(x, y + 1).getBlue() - pic.get(x, y - 1).getBlue();

		int rY = pic.get(x + 1, y).getRed() - pic.get(x - 1, y).getRed();
		int gY = pic.get(x + 1, y).getGreen() - pic.get(x - 1, y).getGreen();
		int bY = pic.get(x + 1, y).getBlue() - pic.get(x - 1, y).getBlue();

		double energyX = Math.pow(rX, 2) + Math.pow(gX, 2) + Math.pow(bX, 2);
		double energyY = Math.pow(rY, 2) + Math.pow(gY, 2) + Math.pow(bY, 2);
		double energyTotal = Math.sqrt(energyX + energyY);

		return energyTotal;
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
//		HashMap<String, String> edgeTo = new HashMap<>();
//		HashMap<String, Double> energyTo = new HashMap<>();
		String[][] edgeTo = new String[height()][width()];
		double[][] energyTo = new double[height()][width()];
		double cost = Double.MAX_VALUE;
		String cur, next, end = null;

		for (int i = 0; i < height(); i++) {
			for (int k = 0; k < width(); k++) {
				energyTo[i][k] = Double.MAX_VALUE;
			}
		}

		if (width() == 1) {
			for (int row = 1; row < width(); row++) {
				edgeTo[row][0] = pixel2str(row - 1, 0);
			}
			end = pixel2str(height() - 1, 0);
			return getSeam(edgeTo, end, "horizontal");
		}

		for (int col = 0; col < width() - 1; col++) {
			for (int row = 0; row < height(); row++) {
				cur = pixel2str(row, col);
				// if we are at col 0,then there cannot be way to them from above!
				if (col == 0) {
					edgeTo[row][col] = null;
					energyTo[row][col] = energy(col, row);
				}
				// look at three pixels below
				for (int rNext = row - 1; rNext <= row + 1; rNext++) {
					if (rNext >= 0 && rNext < height()) {
						next = pixel2str(rNext, col + 1);
						double newEnergy = energy(col + 1, rNext) + energyTo[row][col];
						// if we found a better way to get to next(or new way)
						if (newEnergy < energyTo[rNext][col + 1]) {
							energyTo[rNext][col + 1] = newEnergy;
							edgeTo[rNext][col + 1] = cur;

							// if we look to the last row, we try to find the shortest path(smallest cost)
							// and give that edge the name "end"
							if (col + 1 == width() - 1 && newEnergy < cost) {
								cost = newEnergy;
								end = next;
							}
						}
					}
				}

			}
		}
		return getSeam(edgeTo, end, "horizontal");
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
//		HashMap<String, String> edgeTo = new HashMap<>();
//		HashMap<String, Double> energyTo = new HashMap<>();
		String[][] edgeTo = new String[height()][width()];
		double[][] energyTo = new double[height()][width()];
		double cost = Double.MAX_VALUE;
		String cur, next, end = null;

		for (int i = 0; i < height(); i++) {
			for (int k = 0; k < width(); k++) {
				energyTo[i][k] = Double.MAX_VALUE;
			}
		}

		if (height() == 1) {
			for (int col = 1; col < width(); col++) {
				edgeTo[0][col] = pixel2str(0, col - 1);
			}
			end = pixel2str(0, width() - 1);
			return getSeam(edgeTo, end, "vertical");
		}

		for (int row = 0; row < height() - 1; row++) {
			for (int col = 0; col < width(); col++) {
				cur = pixel2str(row, col);
				// if we are at row 0,then there cannot be way to them from above!
				if (row == 0) {
					edgeTo[row][col] = null;
					energyTo[row][col] = energy(col, row);
				}
				// look at three pixels below
				for (int cNext = col - 1; cNext <= col + 1; cNext++) {
					if (cNext >= 0 && cNext < width()) {
						next = pixel2str(row + 1, cNext);
						double newEnergy = energy(cNext, row + 1) + energyTo[row][col];
						// if we found a better way to get to next(or new way)
						if (newEnergy < energyTo[row + 1][cNext]) {
							energyTo[row + 1][cNext] = newEnergy;
							edgeTo[row + 1][cNext] = cur;

							// if we look to the last row, we try to find the shortest path(smallest cost)
							// and give that edge the name "end"
							if (row + 1 == height() - 1 && newEnergy < cost) {
								cost = newEnergy;
								end = next;
							}
						}
					}
				}

			}
		}
		return getSeam(edgeTo, end, "vertical");
	}

	private int[] getSeam(String[][] edgeTo, String end, String mode) {
		int size;

		if (mode == "vertical") {
			size = height();
		} else if (mode == "horizontal") {
			size = width();
		} else
			throw new IllegalArgumentException();

		int[] path = new int[size];
		String cur = end;

		while (size > 0) {
			int[] coordinates = str2pixel(cur);
			if (mode == "horizontal")
				path[--size] = coordinates[0];
			else
				path[--size] = coordinates[1];
			cur = edgeTo[coordinates[0]][coordinates[1]];
		}

		return path;
	}

	private String pixel2str(int row, int col) {
		return row + " " + col;
	}

	private int[] str2pixel(String s) {
		int[] res = new int[2];
		res[0] = Integer.parseInt(s.split(" ")[0]);
		res[1] = Integer.parseInt(s.split(" ")[1]);
		return res;
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {
		if (height() <= 1 || !isValidSeam(seam) || seam.length != width() || seam == null) {
			throw new IllegalArgumentException();
		}

		Picture newPic = new Picture(width(), height() - 1);

		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height() - 1; row++) {
				if (seam[col] < 0 || seam[col] > height() - 1 || seam == null)
					throw new IllegalArgumentException();
				if (row < seam[col])
					newPic.set(col, row, pic.get(col, row));
				else
					newPic.set(col, row, pic.get(col, row + 1));

			}
		}
		this.pic = new Picture(newPic);
		this.width = pic.width();
		this.height = pic.height();
	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
		if (width() <= 1 || seam.length != height() || !isValidSeam(seam) || seam == null) {
			throw new IllegalArgumentException();
		}
		Picture newPic = new Picture(width() - 1, height());

		for (int row = 0; row < height(); row++) {
			for (int col = 0; col < width() - 1; col++) {
				if (seam[row] < 0 || seam[row] > width() - 1 || seam == null)
					throw new IllegalArgumentException();
				if (col < seam[row])
					newPic.set(col, row, pic.get(col, row));
				else
					newPic.set(col, row, pic.get(col + 1, row));

			}
		}
		this.pic = new Picture(newPic);
		this.width = pic.width();
		this.height = pic.height();
	}

	private boolean isValidSeam(int[] seam) {
		for (int i = 0; i < seam.length - 1; i++) {
			if ((Math.abs(seam[i] - seam[i + 1]) > 1) || seam[i] < 0 || seam[i + 1] < 0) {
				return false;
			}
		}
		return true;
	}
}

//for (int row = 0; row < height() - 1; row++) {
//	for (int col = 0; col < width(); col++) {
//		cur = pixel2str(row, col);
//		// if we are at row 0,then there cannot be way to them from above!
//		if (row == 0) {
//			edgeTo.put(cur, null);
//			energyTo.put(cur, energy(row, col));
//		}
//		// look at three pixels below
//		for (int cNext = col - 1; cNext <= col + 1; cNext++) {
//			if (cNext >= 0 && cNext < width()) {
//				next = pixel2str(row + 1, cNext);
//				double newEnergy = energy(row + 1, cNext) + energyTo.get(cur);
//				// if we found a better way to get to next(or new way)
//				if (energyTo.get(next) == null || energyTo.get(next) > newEnergy) {
//					energyTo.put(next, newEnergy);
//					edgeTo.put(next, cur);
//
//					// if we look to the last row, we try to find the shortest path(smallest cost)
//					// and give that edge the name "end"
//					if (row + 1 == height() - 1 && newEnergy < cost) {
//						cost = newEnergy;
//						end = next;
//					}
//				}
//			}
//		}
//
//	}
//}
