package Algorithms;

public class BinarySearch {

	public int binarySearch(int[] list, int target) {
		int L = 0;
		int R = list.length - 1;
		while (L <= R) {
			int M = L + (R - L) / 2;
			if (target < list[M])
				R = M - 1;
			else if (target > list[M])
				L = M + 1;
			else
				return M;
		}
		return -1;
	}
}
