package Algorithms;

import java.util.Iterator; // it is an interface (look at declaration)
// Iterable interface is in java.lang

public class Stack_LlinkedListImplementation<Type> implements Iterable<Type> {

	private Node head = null;

	private class Node {
		Type data;
		Node next;
	}

	public boolean isEmpty() {
		return this.head == null;
	}

	public void push(Type item) {
		Node oldFirst = this.head;
		head = new Node();
		head.data = item;
		head.next = oldFirst;
	}

	public Type pop() {
		Type item = head.data;
		head = head.next;
		return item;
	}

	// main method!!!
	public Iterator<Type> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Type> {

		private Node curNode = head;

		public boolean hasNext() {
			return curNode != null;
		}

		public Type next() {
			Type item = curNode.data;
			curNode = curNode.next;
			return item;
		}

	}

}
