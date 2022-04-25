package Algorithms;

import java.util.NoSuchElementException;

public class Queue_ArrayImplementation<Type> {

	private static final int DEFAULT_SIZE = 8;

	private Type[] q;
	private int head; // index of first element
	private int tail; // index of available place
	private int size; // num of elements

	public Queue_ArrayImplementation() {
		q = (Type[]) new Object[DEFAULT_SIZE];
		head = 0;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void resize(int capacity) {
		System.out.println("resize " + capacity);
		Type[] copy = (Type[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			copy[i] = q[(head + i) % q.length]; // we do it because the array can be wrapped up, and the continuation
												// can be in the beginning of the array
		}
		q = copy;
		head = 0;
		tail = size;
	}

	public void enqueue(Type item) {
		if (size == q.length)
			resize(q.length * 2);
		q[tail++] = item;
		if (tail == q.length)
			tail = 0; // wrap around
		size++;
	}

	public Type dequeue() {

		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Type item = q[head];
		q[head++] = null;
		size--;
		if (head == q.length)
			head = 0; // if the item is the last and only last element, we make head index to be zero!
		if (size > 0 && size <= q.length)
			resize(q.length / 2);
		return item;
	}

	public void printQueue() {
		for (int i = 0; i < size; i++) {
			if (q[(head + i) % q.length] != null) {
				System.out.println(q[(head + i) % q.length]);
			}
		}
	}

	public Type heep() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		return q[head];
	}

	public static void main(String[] args) {
		Queue_ArrayImplementation<String> tester = new Queue_ArrayImplementation<>();
		tester.enqueue("to");
		tester.enqueue("be");
		tester.enqueue("or");
		tester.enqueue("not");
		tester.enqueue("to");
		tester.dequeue();
		tester.enqueue("be");
		tester.dequeue();
		tester.enqueue("check");
		tester.enqueue("check");
		tester.enqueue("check");
		tester.printQueue();
		System.out.println(tester.size);
	}
}
