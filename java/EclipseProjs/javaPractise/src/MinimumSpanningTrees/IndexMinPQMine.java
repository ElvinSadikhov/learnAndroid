package MinimumSpanningTrees;

import java.util.NoSuchElementException;

public class IndexMinPQMine<Key extends Comparable<Key>> {

	private Key[] keys;
	private int[] pq;
	private int[] qp;
	private int n;

	public IndexMinPQMine(int maxN) {
		keys = (Key[]) new Object[maxN + 1];
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		n = 0;
		for (int i = 0; i < maxN; i++) {
			qp[i] = -1;
		}

	}

	public void insert(int i, Key key) {
		if (contains(i))
			throw new IllegalArgumentException();
		n++;
		pq[n] = i;
		qp[i] = n;
		keys[i] = key;
		swim(n);
	}

	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	private void exch(int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}

	private boolean greater(int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
	}

	public void decreaseKey(int i, Key key) {
		if (!contains(i))
			throw new NoSuchElementException("index is not in the priority queue");
		if (keys[i].compareTo(key) == 0)
			throw new IllegalArgumentException(
					"Calling decreaseKey() with a key equal to the key in the priority queue");
		if (keys[i].compareTo(key) < 0)
			throw new IllegalArgumentException(
					"Calling decreaseKey() with a key strictly greater than the key in the priority queue");
		keys[i] = key;
		swim(qp[i]);
	}

	public boolean contains(int i) {
		return qp[i] != -1;
	}

	public int delMin() {
		if (n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		int min = pq[1];
		exch(1, n--);
		sink(1);
		qp[min] = -1; // delete
		keys[min] = null; // to help with garbage collection
		pq[n + 1] = -1; // not needed
		return min;
	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && greater(j, j + 1))
				j++;
			if (!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

}
