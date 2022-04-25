package Algorithms;

import java.util.ArrayList;

//unordered one(linked list implementation)
public class SymbolTable<Key, Value> {

	private Node head;
	private int size;

	private class Node {
		private Node next;
		private Key key;
		private Value value;

		public Node(Key key, Value val) {
			this.key = key;
			this.value = val;
			this.next = null;
		}
	}

	public SymbolTable() {
	}

	public void put(Key key, Value val) {
		if (contains(key)) {
			Node curNode = head;
			while (curNode != null) {
				if (curNode.key.equals(key)) {
					curNode.value = val;
				}
				curNode = curNode.next;
			}
		} else {
			if (head == null) {
				head = new Node(key, val);
			} else {
				Node oldFirst = head;
				head = new Node(key, val);
				head.next = oldFirst;
			}
			size++;
		}
	}

	public Value get(Key key) {
		Node curNode = head;
		while (curNode != null) {
			if (curNode.key.equals(key))
				return curNode.value;
			curNode = curNode.next;
		}
		return null;
	}

	public void delete(Key key) {
		if (head == null)
			throw new NullPointerException("There is no key and value pair in memory!");
		else if (head.key.equals(key)) {
			Node oldHead = head;
			head = head.next;
			oldHead = null;
		} else {
			Node curNode = head;
			while (curNode.next != null) {
				if (curNode.next.key.equals(key)) {
					Node toDelete = curNode.next;
					curNode.next = toDelete.next;
					toDelete = null;
				}
			}
		}
		size--;
	}

	public boolean contains(Key key) {
		Node curNode = head;
		while (curNode != null) {
			if (curNode.key.equals(key))
				return true;
			curNode = curNode.next;
		}
		return false;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return size;
	}

	public Iterable<Key> keys() {
		ArrayList<Key> reversedList = new ArrayList<>();
		for (Node curNode = head; curNode != null; curNode = curNode.next) {
			reversedList.add(curNode.key);
		}
		ArrayList<Key> list = new ArrayList<>();
		for (int i = reversedList.size() - 1; i >= 0; i--) {
			list.add(reversedList.get(i));
		}
		return list;
	}

	// unit testing
	public static void main(String[] args) {
		SymbolTable<String, Integer> tester = new SymbolTable<>();
		tester.put("elvin", 1);
		System.out.println(tester.get("elvin"));
		System.out.println(tester.size());// 1
		tester.delete("elvin");
		System.out.println(tester.size());// 0
//		tester.delete("elvin");// exception WORKS!

		tester.put("revan", 2);
		tester.put("revan", 3);
		System.out.println(tester.get("aslan")); // null
		System.out.println(tester.get("revan")); // 3
		tester.delete("revan");
		System.out.println(tester.size());// 0

		tester.put("elvin", 0);
		tester.put("tofik", 1);
		tester.put("revan", 2);
		tester.put("aslan", 3);
		System.out.println(tester.size());
		Iterable<String> temp = tester.keys();
		for (String key : temp) {
			System.out.println("key :" + key + " value: " + tester.get(key));
		}
	}

}
