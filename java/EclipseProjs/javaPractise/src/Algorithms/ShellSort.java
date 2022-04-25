package Algorithms;

public class ShellSort {

	public void sort(Comparable[] list) {
		int N = list.length;

		// sequence 3*h+1 = 1,4,13,40...
		int h = 1;
		while (h < N / 3)
			h = h * 3 + 1;

		while (h > 0) {
			for (int i = h; i < N; i++) {
				for (int k = i; k >= h && less(list[k], list[k - h]); k -= h) {
					exch(list, k, k - h);
				}
			}
			h /= 3;
		}
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;

	}

	private static boolean isSorted(Comparable[] list) {
		for (int i = 1; i < list.length; i++) {
			if (less(list[i], list[i - 1]))
				return false;
		}
		return true;
	}

	private static void exch(Comparable[] list, int i, int k) {
		Comparable swap = list[i];
		list[i] = list[k];
		list[k] = swap;
	}

	public static void main(String[] args) {
		Comparable[] list = { 1, 3, 5, 2, 4, 5, 2, 4 };
		ShellSort test = new ShellSort();
		test.sort(list);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}

	}
}
