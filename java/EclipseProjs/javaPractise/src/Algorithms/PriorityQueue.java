package Algorithms;

//UnorderedArrayMaxPQ

public class PriorityQueue<Key extends Comparable> {

	private int N;
	private Key[] list;

	@SuppressWarnings("unchecked")
	public PriorityQueue(int capacity) {
		N = 0;
		list = (Key[]) new Comparable[capacity];
	}

	public void insert(Key value) {
		list[N++] = value;
	}

	public Key delMin() {
		int min = 0;
		for (int i = 1; i < N; i++) {
			if (less(list[i], list[min]))
				min = i;
		}
		exch(list, min, N - 1);
		return list[--N];
	}

	// helper functions
	private void exch(Comparable[] list, int a, int b) {
		Comparable temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}

	private boolean isEmpty() {
		return N == 0;
	}

	private boolean less(Key a, Key b) {
		return a.compareTo(b) < 0;
	}

	private int size() {
		return N;
	}

	public static void main(String[] args) {
		PriorityQueue<Integer> tester = new PriorityQueue<>(10);
		tester.insert(2);
		tester.insert(5);
		tester.insert(3);
		System.out.println(tester.delMin());
		tester.insert(1);
		tester.insert(0);
		tester.insert(-3);
		System.out.println(tester.delMin());
		System.out.println(tester.delMin());

	}
}
