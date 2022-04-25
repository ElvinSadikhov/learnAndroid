package RadixSort;

public class MSDRadixSort {

	private static final int CUTOFF = 10;
	private String[] aux;
	private final static int R = 256; // radix(ASCII i guess)

	public void sort(String[] a) {
		aux = new String[a.length];
		sort(a, aux, 0, a.length - 1, 0);
	}

	private static void sort(String[] a, String[] aux, int lo, int hi, int d) {

		if (hi >= lo)
			return;

		if (hi <= lo + CUTOFF) {
			insertion(a, lo, hi, d);
			return;
		}

		int[] count = new int[R + 2]; // idc why +2 not +1
		// counting each character in count array, placing them at place x+1
		for (int i = lo; i < hi; i++) {
			// charAt returns ASCII number of a char(out of 256)
			count[charAt(a[i], d) + 2]++;
		}
		// counting how many characters were BEFORE
		// there are R+2 indexes, so we use <R+1
		for (int r = 0; r < R + 1; r++) {
			count[r + 1] += count[r];
		}

		// find a[i] in count array, place in that place into aux array, and increment
		// count array
		for (int i = lo; i < hi; i++) {
			aux[count[charAt(a[i], d)]++] = a[i];
		}

		for (int i = lo; i < hi; i++) {
			a[i] = aux[i - lo];
		}

		// recursive call
		for (int r = 0; r < R; r++) {
			sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);
		}
	}

	// insertion sort a[lo..hi], starting at dth character
	private static void insertion(String[] a, int lo, int hi, int d) {
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && less(a[j], a[j - 1], d); j--)
				exch(a, j, j - 1);
	}

	// exchange a[i] and a[j]
	private static void exch(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// is v less than w, starting at character d
	private static boolean less(String v, String w, int d) {
		// assert v.substring(0, d).equals(w.substring(0, d));
		for (int i = d; i < Math.min(v.length(), w.length()); i++) {
			if (v.charAt(i) < w.charAt(i))
				return true;
			if (v.charAt(i) > w.charAt(i))
				return false;
		}
		return v.length() < w.length();
	}

	private static int charAt(String s, int d) {
		if (d >= s.length())
			return -1;
		return s.charAt(d);
	}
}
