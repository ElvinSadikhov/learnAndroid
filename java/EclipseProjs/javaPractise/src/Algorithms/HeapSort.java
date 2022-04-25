package Algorithms;

/*
 * in-place
 * Guarantees 2N*logN compile time in worst case
 * BUT:
 * -inner loop longer than quick sort(too many things to do)
 * -makes poor usage of cashe (when the list is large)
 * -not stable!
 */

public class HeapSort {

	public void sort(Comparable[] list) {
		int N = list.length;

		// first we have to make it look like as a Binary Heap
		// we start looking for each parent starting from bottom right parent
		// and start to sink it all the way up
		for (int k = N / 2; k >= 1; k--) {
			sink(list, k, N);
		}

		// sorting by putting the head of the heap to the end
		// and heaping again all list
		int n = N;
		while (n > 1) {
			exch(list, 1, n--);
			sink(list, 1, n);
		}
	}

	// helper functions
	private void sink(Comparable[] list, int index, int len) {
		while (index * 2 <= len) {
			int j = index * 2;
			if (j < len && less(list, j, j + 1))
				j++;
			if (!less(list, index, j))
				break;
			exch(list, index, j);
			index = j;
		}
	}

	private boolean less(Comparable[] list, int a, int b) {
		return list[a - 1].compareTo(list[b - 1]) < 0;
	}

	private void exch(Comparable[] list, int a, int b) {
		Comparable temp = list[a - 1];
		list[a - 1] = list[b - 1];
		list[b - 1] = temp;
	}

	public static void main(String[] args) {
		Integer[] list = { 4, 1, 3, 5, 78, 6, 3, 357, 89, 65, 3, 2, 4, 67, 8, 9, 86, 34, 52, 54, 65, 88, 97 };
		HeapSort tester = new HeapSort();
		System.out.print("Before sort : ");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
		tester.sort(list);
		System.out.print("\nAfter sort : ");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}
	}
}