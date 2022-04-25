
package Algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] q;
	private int N;
	private static final int DEFAULT_SIZE = 8;

	// construct an empty randomized queue
	public RandomizedQueue() {
		q = (Item[]) new Object[DEFAULT_SIZE];
		N = 0;
	}

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = q[i];
		}
		q = copy;
	}

	// is the randomized queue empty?
	public boolean isEmpty() {
		return N == 0;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		if (N == q.length)
			resize(2 * q.length);
		q[N++] = item;
	}

	// remove and return a random item
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();
		if (N <= (q.length / 4))
			resize(q.length / 2);
		int randIndex = StdRandom.uniform(N);
		Item res = q[randIndex];
		q[randIndex] = q[--N];
		q[N] = null;
		return res;
	}

	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException();
		int randIndex = StdRandom.uniform(N);
		return q[randIndex];
	}

	public int size() {
		return N;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<Item> {

		private int i = 0;

		@Override
		public boolean hasNext() {
			return (i < q.length && q[i] != null);
		}

		@Override
		public Item next() {
			return q[i++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("-"))
				StdOut.print(rq.dequeue());
			else
				rq.enqueue(s);
		}
	}
}