package Algorithms;

/*
 * used for arrays with a lot of duplicates
 * where it is possible to get N time complexity in best case
 */
public class QuickSort3Way {

	private void sort(Comparable[] list, int lo, int hi) {
		if (lo >= hi)
			return;
		int less = lo, greater = hi;
		Comparable key = list[lo];
		int i = lo;

		while (i <= greater) {
			int compar = list[i].compareTo(key);
			if (compar < 0)
				exch(list, i++, less++);
			else if (compar > 0)
				exch(list, i, greater--);
			else
				i++;
		}
		sort(list, lo, less - 1);
		sort(list, greater + 1, hi);
	}

	public void mainSort(Comparable[] list) {
		sort(list, 0, list.length - 1);
	}

	// helper methods
	private static void exch(Comparable[] list, int i, int k) {
		Comparable swap = list[i];
		list[i] = list[k];
		list[k] = swap;
	}

	public static void main(String[] args) {
		QuickSort3Way tester = new QuickSort3Way();
		Comparable[] list = { 12, 35, 346, 12, 4, 3, 53, 56, 76, 34, 34, 5, 54765, 8, 677, 5, 2, 24, 5, 6, 74, 56, 5,
				64, 66, 5857, 8, 56, 35, 34, 646, 757, 8, 54, 3, 53, 24, 4, 342, 546, 7, 567, 45, 234, 13, 5475, 763,
				43 };

		for (Comparable num : list) {
			System.out.print(num + " ");
		}
		System.out.println("\n");
		tester.mainSort(list);
		for (Comparable num : list) {
			System.out.print(num + " ");
		}
	}
}
