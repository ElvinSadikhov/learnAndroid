package Algorithms;

/*
 * fixed size!
 */
import java.util.Iterator; //interface
// Iterable interface we already have built-in in java.lang

//we can write without generics( <Type> )  and actually it is more safe,
// as we cannot create arrays using generics( ugly cast )
public class Stack_ArrayImplementation<Type> implements Iterable<Type> {

	private Type[] s;
	private int N = 0;

	public Stack_ArrayImplementation(int capacity) {
		s = (Type[]) new Object[capacity]; // (Type[]) is an ugly cast, and it is recommended to have NO CASTS in your
											// code
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void push(Type item) {
		if (s.length == N)
			resize(2 * s.length);

		s[N++] = item;// first put item to N position, then increment N by 1
	}

	public Type pop() {
		if (N <= s.length / 4)
			resize(s.length / 2);
		// avoids loitering(efficient usage of memory)
		s[N] = null;
		return s[--N];// first decrement N by 1, then return item now on position N-1
	}

	private void resize(int capacity) {
		Type[] copy = (Type[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = s[i];
		}
		s = copy;
	}

	public Iterator<Type> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<Type> {
		private int i = N;

		public boolean hasNext() {
			return i > 0;
		}

		public Type next() {
			return s[--i];
		}
	}
}
