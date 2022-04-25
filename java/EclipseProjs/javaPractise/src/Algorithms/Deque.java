
package Algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

	private Node head;
	private Node tail;
	private int size;

	private class Node {

		private Item data;
		private Node prev;
		private Node next;

		public Node(Item item) {
			data = item;
			prev = null;
			next = null;
		}
	}

	// construct an empty deque
	public Deque() {
		head = null;
		tail = null;
		size = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// return the number of items on the deque
	public int size() {
		return size;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		Node newNode = new Node(item);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else if (size() == 1) {
			head = newNode;
			head.next = tail;
			tail.prev = head;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		size++;
	}

	// add the item to the back
	public void addLast(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		Node newNode = new Node(item);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		} else if (size() == 1) {
			tail = newNode;
			head.next = tail;
			tail.prev = head;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();

		Node oldHead = head;
		if (size() == 1) {
			head = null;
			tail = null;
		} else {
			head = oldHead.next;
			head.prev = null;
		}
		size--;
		return oldHead.data;
	}

	// remove and return the item from the back
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		Node oldTail = tail;
		// tail.prev = tail;
		// tail.next = null;
		if (size() == 1) {
			head = null;
			tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return oldTail.data;
	}

	// return an iterator over items in order from front to back
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {

		private Node curNode = head;

		public boolean hasNext() {

			return curNode != null;
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more objects to iterate through");
			}
			Node res = curNode;
			curNode = curNode.next;
			return res.data;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {
		Deque<String> deque = new Deque<>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("-"))
				StdOut.print(deque.removeLast());
			else
				deque.addFirst(s);
		}

	}
}
