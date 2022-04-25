package Algorithms;

import java.util.Iterator;// interface
//Iterable interface we hav in java.lang

public class Queue_LinkedListImplementation<Type> implements Iterable<Type> {

	private Node head;
	private Node last;
	private int size;

	public Queue_LinkedListImplementation() {

		head = null;
		last = null;
		size = 0;
	}

	private class Node {

		private Type data;
		private Node next;

		private Node(Type item) {
			data = item;
			next = null;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void enqueue(Type item) {
		Node newNode = new Node(item);

		if (isEmpty()) {
			head = newNode;
		} else if (size == 1) {
			last = newNode;
			head.next = last;
		} else {
			Node newLast = newNode;
			last.next = newLast;
			last = newLast;
		}
		size++;
	}

	public Type dequeue() {
		if (this.isEmpty()) {
			return null;
		} else {
			Type item = head.data;
			head = head.next;
			size--;
			return item;
		}
	}

	public void printQueue() {
		if (isEmpty()) {
			System.out.println("It's empty");
			return;
		} else {
			Node curNode = head;
			while (curNode != null) {
				System.out.println(curNode.data);
				curNode = curNode.next;
			}
		}
	}

	public Iterator<Type> iterator(){
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Type> {

		private Node curNode = head;
		
		public boolean hasNext() {
			return curNode!=null;
		}

		public Type next() {
			Type item = curNode.data;
			curNode = curNode.next;
			return item;
		}

	}

	public static void main(String[] args) {
		Queue_LinkedListImplementation<String> tester = new Queue_LinkedListImplementation<>();
		tester.enqueue("to");
		tester.enqueue("be");
		tester.enqueue("or");
		tester.enqueue("not");
		tester.enqueue("to");
		tester.dequeue();
		tester.enqueue("be");
		tester.dequeue();
		//tester.printQueue();
		System.out.println(tester.size);
		
		Iterator<String> itr = tester.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

}
