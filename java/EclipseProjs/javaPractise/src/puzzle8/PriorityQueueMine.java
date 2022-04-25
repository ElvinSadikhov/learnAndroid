package puzzle8;

//UnorderedArrayMaxPQ

public class PriorityQueueMine<Key extends Comparable> {

	private int N;
	private Key[] list;

	@SuppressWarnings("unchecked")
	public PriorityQueueMine(int capacity) {
		N = 0;
		list = (Key[]) new Comparable[capacity + 1];
	}

	public void insert(Key value) {
		if (N >= list.length - 1)
			resize(list.length * 2);
		list[N++] = value;
	}

	public Key delMin() {
		int min = 0;
		for (int i = 1; i < N; i++) {
			if (less(list[i], list[min]))
				min = i;
		}
		exch(list, min, N - 1);
		if (N <= list.length / 4)
			resize(list.length / 2);
		return list[--N];
	}

	// helper functions
	private void resize(int newCapacity) {
		System.out.println("N is " + N + " and curSize is " + list.length + " resizing to " + newCapacity);

		Key[] copy = (Key[]) new Comparable[newCapacity];
		for (int i = 1; i <= N; i++) {
			copy[i] = list[i];
		}
		list = copy;
	}

	private void exch(Comparable[] list, int a, int b) {
		Comparable temp = list[a];
		list[a] = list[b];
		list[b] = temp;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	private boolean less(Key a, Key b) {
		return a.compareTo(b) < 0;
	}

	public int size() {
		return N;
	}

	public static void main(String[] args) {
		PriorityQueueMine<Integer> tester = new PriorityQueueMine<>(10);
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
