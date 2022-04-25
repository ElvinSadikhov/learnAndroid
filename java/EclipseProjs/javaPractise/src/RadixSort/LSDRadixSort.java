package RadixSort;

// 2*W*N guarantee
// uses extra space N+R
// stable 

public class LSDRadixSort {

	// works with W fixed-length strings
	public void sort(String[] a, int W) {
		int R = 256; // radix(ASCII i guess)
		int N = a.length;
		String[] aux = new String[N];

		for (int d = W - 1; d >= 0; d--) {

			int[] count = new int[R + 1];
			// counting each character in count array, placing them at place x+1
			for (int i = 0; i < N; i++) {
				// charAt returns ASCII number of a char(out of 256)
				count[a[i].charAt(d) + 1]++;
			}
			// counting how many characters were BEFORE
			// there are R+1 indexes, so we use <R
			for (int r = 0; r < R; r++) {
				count[r + 1] += count[r];
			}

			// find a[i] in count array, place in that place into aux array, and increment
			// count array
			for (int i = 0; i < N; i++) {
				aux[count[a[i].charAt(d)]++] = a[i];
			}

			for (int i = 0; i < N; i++) {
				a[i] = aux[i];
			}
		}
	}

}
