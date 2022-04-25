package Algorithms;

/*
 * an algorithm for finding k'th largest or smallest number
 * we use k as the point where this number should be there
 * this is quick sort implementation
 */
public class QuickSelect {

	public Comparable select(Comparable[] list, int k) {

		int lo = 0, hi = list.length - 1;

		while (hi > lo) {

			int j = partition(list, lo, hi);
			// if the target in the left half
			if (k < j)
				hi = j - 1;
			// if the target in the right half
			else if (k > j)
				lo = j + 1;
			// if we found the index, we quit
			// j is k, because all left to j are smaller, and all right to j are bigger
			// so j is in the RIGHT PLACE
			else
				return list[k];
		}

		return list[k];
	}

	// helper functions
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

	private boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	private static void exch(Comparable[] list, int i, int k) {
		Comparable swap = list[i];
		list[i] = list[k];
		list[k] = swap;
	}
}
