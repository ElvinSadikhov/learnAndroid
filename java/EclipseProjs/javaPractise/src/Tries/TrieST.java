package Tries;

import edu.princeton.cs.algs4.Queue;

public class TrieST<Value> {

	private static final int R = 256; // ASCII
	private Node root = new Node();

	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}

	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Value val, int d) {
		// if there is no node, we create one
		if (x == null)
			x = new Node();
		// if it is the last char of a string, then we put the value and quit
		if (d == key.length()) {
			x.val = val;
			return x;
		}
		// c is a char and index of it in ascii at the same time
		char c = key.charAt(d);
		x.next[c] = put(x.next[c], key, val, d + 1);
		return x;
	}

	public boolean contains(String key) {
		return get(key) != null;
	}

	public Value get(String key) {
		Node x = get(root, key, 0);
		return x == null ? null : (Value) x.val;
	}

	private Node get(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length())
			return x;
		char c = key.charAt(d);
		return get(x.next[c], key, d + 1);
	}

	public void delete(String key) {
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		root = delete(root, key, 0);
	}

	private Node delete(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length()) {
			x.val = null;
		} else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d + 1);
		}

		// remove subtrie rooted at x if it is completely empty
		if (x.val != null)
			return x;
		for (int c = 0; c < R; c++)
			if (x.next[c] != null)
				return x;
		return null;
	}

	public Iterable<String> keys() {
		Queue<String> q = new Queue<>();
		collect(root, "", q);
		return q;
	}

	private void collect(Node x, String prefix, Queue<String> q) {
		if (x == null)
			return;
		if (x.val != null)
			q.enqueue(prefix);
		for (char c = 0; c < R; c++) {
			collect(x.next[c], prefix + c, q);
		}
	}

	public String longestPrefixOf(String query) {
		int length = search(root, query, 0, 0);
		return query.substring(0, length);
	}

	private int search(Node x, String query, int d, int length) {
		if (x == null)
			return length;
		if (x.val != null)
			length = d;
		if (d == query.length())
			return length;
		char c = query.charAt(d);
		return search(x.next[c], query, d + 1, length);
	}

}
