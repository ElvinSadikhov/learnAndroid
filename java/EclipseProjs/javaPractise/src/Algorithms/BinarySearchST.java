package Algorithms;

import java.util.ArrayList;

// binary serach implementation of Symbol Table
public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private static final int INIT_CAPACITY = 8;

	private Key[] keys;
	private Value[] values;
	private int n; // num of elements (index of first free space in array too)

	public BinarySearchST() {
		this(INIT_CAPACITY);
	}

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Comparable[capacity];
		n = 0;
	}

	public void put(Key key, Value val) {
		if (n >= values.length)
			resize(2 * n);
		if (contains(key)) {
			int index = binarySearch(key);
			values[index] = val;
			return;
		}
		// sliding all keys and values (from n)
		// until we find a correct place for it
		int i = n;
		while ((i > 0) && key.compareTo(keys[i - 1]) < 0) {
			keys[i] = keys[i - 1];
			values[i] = values[i - 1];
			i--;
		}
		keys[i] = key;
		values[i] = val;
		n++;
	}

	public Value get(Key key) {
		int index = binarySearch(key);
		return index < 0 ? null : values[index];
	}

	private boolean contains(Key key) {
		return binarySearch(key) != -1;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	private void resize(int capacity) {
		Key[] copy1 = (Key[]) new Comparable[capacity];
		Value[] copy2 = (Value[]) new Comparable[capacity];
		for (int i = 0; i < n; i++) {
			copy1[i] = keys[i];
			copy2[i] = values[i];
		}
		keys = copy1;
		values = copy2;
	}

	private int binarySearch(Key key) {
		int lo = 0;
		int hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int temp = keys[mid].compareTo(key);
			if (temp < 0)
				lo = mid + 1;
			else if (temp > 0)
				hi = mid - 1;
			else
				return mid;
		}
		return -1;
	}

	public Iterable<Key> keys() {
		ArrayList<Key> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(keys[i]);
		}
		return list;
	}

	public static void main(String[] args) {
		BinarySearchST<String, String> st = new BinarySearchST<String, String>();

		// insert some key-value pairs
		st.put("www.cs.princeton.edu", "128.112.136.11");
		st.put("www.cs.princeton.edu", "128.112.136.35");
		st.put("www.princeton.edu", "128.112.130.211");
		st.put("www.math.princeton.edu", "128.112.18.11");
		st.put("www.yale.edu", "130.132.51.8");
		st.put("www.amazon.com", "207.171.163.90");
		st.put("www.simpsons.com", "209.123.16.34");
		st.put("www.stanford.edu", "171.67.16.120");
		st.put("www.google.com", "64.233.161.99");
		st.put("www.ibm.com", "129.42.16.99");
		st.put("www.apple.com", "17.254.0.91");
		st.put("www.slashdot.com", "66.35.250.150");
		st.put("www.whitehouse.gov", "204.153.49.136");
		st.put("www.espn.com", "199.181.132.250");
		st.put("www.snopes.com", "66.165.133.65");
		st.put("www.movies.com", "199.181.132.250");
		st.put("www.cnn.com", "64.236.16.20");
		st.put("www.iitb.ac.in", "202.68.145.210");

		// search for IP addresses given URL
		System.out.println("size = " + st.size());
		System.out.println(st.get("www.cs.princeton.edu"));
		System.out.println(st.get("www.amazon.com"));
		System.out.println(st.get("www.amazon.edu"));
		System.out.println(st.get("www.yale.edu"));
		System.out.println();

	}

}
