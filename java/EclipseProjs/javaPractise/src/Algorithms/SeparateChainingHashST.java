package Algorithms;

/*
 * easier to implement delete
 * performance degrades gracefully
 * clustering less sensitive to poorly-designed funtion
 */
public class SeparateChainingHashST<Key, Value> {

	private int num; // num of key value pairs
	private int m; // size
	private Node[] st;

	private static class Node {
		private Object key;
		private Object val;
		private Node next;

		public Node(Object key, Object val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public SeparateChainingHashST() {
		this(997);
	}

	public SeparateChainingHashST(int m) {
		this.num = 0;
		this.m = m;
		st = new Node[m];
	}

	private int hash(Key key) {
		return (this.hashCode() & 0x7fffffff) % m;
	}

	public Value get(Key key) {
		int i = hash(key);
		for (Node x = st[i]; x != null; x = x.next)
			if (key.equals(x.key))
				return (Value) x.val;
		return null;
	}

	public void put(Key key, Value val) {
		int i = hash(key);
		for (Node x = st[i]; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		num++;
		st[i] = new Node(key, val, st[i]);
	}

}
