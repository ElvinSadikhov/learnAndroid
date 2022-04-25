package Algorithms;

/*
 * in-place
 * not stable
 * mostly used for primitive types
 * actually faster than merge sort 
 * worst case run-time is N^2, but it is not common(sorted/reverse)
 */
public class QuickSort {

	private final static int CUTOFF = 10; // we will use insertation sort for subarrays with length 7+- and less;

	private int partition(Comparable[] list, int lo, int hi) {
		Comparable key = list[lo];
		int L = lo;
		int R = hi + 1;
		while (true) {
			while (less(list[++L], key))
				if (L == hi)
					break;
			while (less(key, list[--R]))
				if (R == lo)
					break;

			if (L >= R)
				break;
			exch(list, L, R);
		}
		exch(list, lo, R);
		return R;
	}

	private void sort(Comparable[] list, int lo, int hi) {
		// IMPROVEMENT ¹1, WE USE INSERTATION SORT FOR TINY SUBARRAYS IN ORDER TO AVOID
		// RECURSIVE TIME COMPLEXITY
		if (lo + CUTOFF - 1 >= hi) {
			insertationSort(list, lo, hi);
			return;
		}

		// IMPROVEMENT ¹2, we choose the median VALUE to be the key,
		// and in this case the number of compares will slightly decrease
		// and the number of exchanges will slightly increase
		int median = medianOf3(list, lo, lo + (hi - lo) / 2, hi);
		exch(list, lo, median);

		int mid = partition(list, lo, hi);
		sort(list, lo, mid - 1);
		sort(list, mid + 1, hi);
	}

	public void mainSort(Comparable[] list) {
		// ArrayList<Comparable> temp = new ArrayList<>();
		// Collections.shuffle(temp);
		// temp.toArray(list);

		sort(list, 0, list.length - 1);

	}

	// helper functions
	private void insertationSort(Comparable[] list, int L, int R) {
		for (int i = L; i <= R; i++) {
			for (int k = i; k > 0 && less(list[k], list[k - 1]); k--) {
				exch(list, k - 1, k);
			}
		}
	}

	private boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	private static void exch(Comparable[] list, int i, int k) {
		Comparable swap = list[i];
		list[i] = list[k];
		list[k] = swap;
	}

	private int medianOf3(Comparable[] list, int a, int b, int c) {
		return (less(list[a], list[b]) ? (less(list[b], list[c]) ? b : less(list[a], list[c]) ? c : a)
				: (less(list[c], list[b]) ? b : (less(list[c], list[a]) ? c : a)));
	}

	public static void main(String[] args) {
		QuickSort tester = new QuickSort();
		Comparable[] list = { 12, 35, 346, 12, 4, 3, 53, 56, 76, 34, 34, 5, 54765, 8, 677, 5, 2, 24, 5, 6, 74, 56, 5,
				64, 66, 5857, 8, 56, 35, 34, 646, 757, 8, 54, 3, 53, 24, 4, 342, 546, 7, 567, 45, 234, 13, 5475, 763,
				43 };

		for (Comparable num : list) {
			System.out.print(num + " ");
		}
		System.out.println();
		tester.mainSort(list);
		for (Comparable num : list) {
			System.out.print(num + " ");
		}
	}
}
