package Algorithms;

public class SelectionSort {

	private static boolean less(Comparable[] list, int i, int k) {
		return list[i].compareTo(list[k]) < 0;
	}

	private static boolean isSorted(Comparable[] list) {
		for (int i = 1; i < list.length; i++) {
			if (less(list, i, i - 1))
				return false;
		}
		return true;
	}

	private static void exch(Comparable[] list, int i, int k) {
		Comparable swap = list[i];
		list[i] = list[k];
		list[k] = swap;
	}

	public void sort(Comparable[] list) {
		int min;
		for (int i = 0; i < list.length; i++) {
			min = i;
			for (int j = i + 1; j < list.length; j++) {
				if (less(list, j, min))
					min = j;
			}
			exch(list, i, min);
		}
	}

	public static void main(String[] args) {
		Comparable[] list = { 1, 3, 5, 2, 4, 5, 2, 4 };
		SelectionSort test = new SelectionSort();
		test.sort(list);
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i]+" ");
		}
	}

}
