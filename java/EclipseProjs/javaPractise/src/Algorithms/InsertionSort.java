package Algorithms;

public class InsertionSort {

	public void sort(Comparable[] list) {
		for (int i = 1; i < list.length; i++) {
			for (int k = i; k > 0 && less(list[k], list[k - 1]); k--) {
				exch(list, k - 1, k);
			}
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
		InsertionSort test = new InsertionSort();
		test.sort(list);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i]+" ");
		}

	}
}
