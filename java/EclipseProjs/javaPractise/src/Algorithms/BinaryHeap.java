package Algorithms;

import java.util.NoSuchElementException;

public class BinaryHeap<Key extends Comparable> {

	private int N;
	private Key[] list;

	public BinaryHeap() {
		this(1); // calls the constructor with 1 as a capacity
	}

	public BinaryHeap(int capacity) {
		list = (Key[]) new Comparable[capacity + 1];
		N = 0;
	}

	public void insert(Key value) {
		if (N == list.length - 1)
			resize(list.length * 2);
		
		list[++N] = value;
		swim(N);
	}

	public Key delMax() {
		if (isEmpty())
			throw new NoSuchElementException("There is no value in the heap");
		Key max = list[1];
		exch(1, N--);
		sink(1);
		list[N + 1] = null; // avoiding lattering
		if (N > 0 && N <= (list.length - 1) / 4)
			resize(list.length / 2);
		return max;
	}

	// helper functions
	private void swim(int index) {
		while (index>1 && less(list[index / 2], list[index])) {
			exch(index / 2, index);
			index = index / 2;
		}
	}

	private void sink(int index) {
		while (2 * index <= N) {
			int childIndex = index * 2;
			if (childIndex < N && less(list[childIndex], list[childIndex + 1]))
				childIndex++;
			if (!less(list[index], list[childIndex]))
				break;
			exch(index, childIndex);
			index = childIndex;
		}
	}

	private void resize(int newCapacity) {
		System.out.println("N is " + N + " and curSize is " + list.length + " resizing to " + newCapacity);

		Key[] copy = (Key[]) new Comparable[newCapacity];
		for (int i = 1; i <= N; i++) {
			copy[i] = list[i];
		}
		list = copy;
	}

	private void exch(int a, int b) {
		Key temp = list[a];
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

	public void print() {
		for (int i = 1; i <=N; i++) {
			System.out.println(list[i] + ".");
		}
	}

	public static void main(String[] args) {
		BinaryHeap<Integer> tester = new BinaryHeap<>(14);
		tester.insert(3);
		tester.insert(5);
		tester.insert(2);
		System.out.println("delete "+tester.delMax());
		tester.insert(1);
		tester.insert(0);
		tester.insert(-3);
		//tester.delMax();
		tester.insert(8);
		tester.insert(2);
		tester.insert(6);
		//tester.delMax();
		tester.print();
		System.out.println("delete "+tester.delMax());
		System.out.println("delete "+tester.delMax());
		tester.print();
	}
}
