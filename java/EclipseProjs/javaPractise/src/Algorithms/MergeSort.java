package Algorithms;

/*
 * stable but no in-place(requires double space to sort) 
 * mostly used as a sorting method for object types where it should be stable algorithm
 */
public class MergeSort {

	private final static int CUTOFF = 7; // we will use insertation sort for subarrays with length 7+- and less;

	private void merge(Comparable[] list, Comparable[] aux, int lo, int mid, int hi) {

		// NOW WE CAN SKIP THIS CODE CUZ IMPROVEMENT 3 DID IT ALREADY FOR US
		// copy values from list to aux
		/*
		 * for (int k = lo; k <= hi; k++) { aux[k] = list[k]; }
		 */

		// 2 starting points
		// i for left part, and j for right part
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {

			// if we already finished left part (from lo to mid) we will use only the
			// remaining right part
			if (i > mid)
				list[k] = aux[j++];

			// if we already finished right part (from mid+1 to hi) we will use only the
			// remaining left part
			else if (j > hi)
				list[k] = aux[i++];

			// comparing 2 values
			else if (less(aux[i], aux[j]))
				list[k] = aux[i++];
			else
				list[k] = aux[j++];
		}

	}

	private void sort(Comparable[] list, Comparable[] aux, int lo, int hi) {

		// IMPROVEMENT ¹1, WE USE INSERTATION SORT FOR TINY SUBARRAYS IN ORDER TO AVOID
		// RECURSIVE TIME COMPLEXITY
		if (lo + CUTOFF >= hi) {
			insertationSort(aux, lo, hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;

		// IMPROVEMENT ¹3,WE CHANGE THE ROLES (list and aux) IN ORDER TO WIN TIME IN
		// COPYING VALUES
		// (LOOK AT LINES 9-13)
		sort(aux, list, lo, mid);
		sort(aux, list, mid + 1, hi);

		// IMPROVEMENT ¹3, WE CHECK IF ALREADY PARTS ARE SORTED, THEN WE MAKE IT FASTER
		// BY AVOIDING MERGE METHOD
		if (!less(list[mid + 1], list[mid])) {
			for (int i = lo; i <= hi; i++)
				aux[i] = list[i];
			return;
		}

		merge(list, aux, lo, mid, hi);
	}

	public void mainSort(Comparable[] list) {
		Comparable[] aux = list.clone();
		sort(list, aux, 0, list.length - 1);
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

	public static void main(String[] args) {
		MergeSort tester = new MergeSort();
		Comparable[] list = { 12, 35, 346, 54, 3, 53, 24, 4356, 765, 2, 21, 342, 546, 7, 567, 45, 234, 13, 5475, 763,
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
