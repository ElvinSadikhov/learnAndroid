package Algorithms;

import java.util.ArrayList;

public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value> {

	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class Node {

		private Key key;
		private Value val;
		private Node left, right;
		private int count;
		private boolean color;

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}

	// DONE
	public void put(Key key, Value val) {
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
		root.color = BLACK;
	}

	// done
	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else {
			x.val = val;
		}
		// fixing-up any right-leaning links
		if (isRed(x.right) && !isRed(x.left))
			x = rotateLeft(x);
		if (isRed(x.left) && isRed(x.left.left))
			x = rotateRight(x);
		if (isRed(x.left) && isRed(x.right))
			flipColors(x);

		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	// done
	public Value get(Key key) {
		Node x = root;
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				return x.val;
		}
		return null;
	}

	private Node min(Node x) {
		while (x.left != null) {
			x = x.left;
		}
		return x;
	}

	private Node max(Node x) {
		while (x.right != null) {
			x = x.right;
		}
		return x;
	}

	public Key floor(Key key) {
		Node x = floor(root, key);
		return x == null ? null : x.key;
	}

	private Node floor(Node x, Key key) {
		// first we find in which sub tree it is.if it is in the left one so it will
		// check also the right subtree for floor number
		// if there is any key which is less or equals to given key it is the answer, if
		// not the root is the answer
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0)
			return floor(x.left, key);

		Node t = floor(x.right, key);
		return t != null ? t : x;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		return x != null ? x.count : 0;
	}

	public int rank(Key key) {
		return rank(key, root);
	}

	private int rank(Key key, Node x) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(key, x.left);
		else if (cmp > 0)
			return 1 + size(x.left) + rank(key, x.right);
		else
			return size(x.left);
	}

	public Iterable<Key> iterator() {
		ArrayList<Key> list = new ArrayList<>();
		inorder(root, list);
		return list;
	}

	private void inorder(Node x, ArrayList<Key> list) {
		if (x == null)
			return;
		inorder(x.left, list);
		list.add(x.key);
		inorder(x.right, list);
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.count = size(x.left) + size(x.right) - 1;
		return balance(x);
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;

		int cmp = key.compareTo(x.key);
		// continue to search
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			// if they have one children
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;

			Node t = x;
			x = min(t.right);
			x.right = deleteMin(x.right);
			x.left = t.left;
		}
		x.count = 1 + size(x.left) + size(x.right);
		return balance(x);
	}

	private boolean isRed(Node x) {
		return x == null ? false : x.color == RED;
	}

	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}

	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}

	private void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}

	private Node balance(Node h) {
		// assert (h != null);

		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);

		h.count = size(h.left) + size(h.right) + 1;
		return h;
	}

}
