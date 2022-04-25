package Algorithms;

public class LinkedListMine {

	private Node head;

	private class Node {

		String data;
		Node next;

		public Node(String item) {
			this.data = item;
			this.next = null;
		}
	}

	public void insert(String item) {
		// we create a node for new item and initialize next node to null
		Node newNode = new Node(item);
		newNode.next = null;

		// if the list is empty, we create a head for it and quit the function via
		// return statement
		if (this.head == null) {
			this.head = newNode;
		} else {
			Node curNode = this.head;
			while (curNode.next != null) {
				curNode = curNode.next;
			}
			// now curNode is the lastNode of the linkedList, and so we can add an item to
			// the end of it
			curNode.next = newNode;
			newNode.next = null;
		}
	}

	public void insert(String item, int index) {
		// if the position is 0, then we change the head of the list
		if (index == 0) {
			Node curHead = this.head;
			Node newNode = new Node(item);
			this.head = newNode;
			newNode.next = curHead;
		} else {
			Node prevNode = this.head;
			Node curNode = prevNode.next;
			int curPos = 1;
			while (curNode != null) {
				if (curPos == index) {
					Node newNode = new Node(item);
					prevNode.next = newNode;
					newNode.next = curNode;
					// newNode.next = curNode.next;
					// curNode.next = newNode;
					return;// quit if done
				}
				prevNode = curNode;
				curNode = curNode.next;
				curPos++;
			}
			// if we didn't find such index, we must throw an exception
			throw new IndexOutOfBoundsException();
		}
	}

	public void delete(int index) {
		if (this.head == null)
			throw new IndexOutOfBoundsException();
		if (index == 0)
			this.head = this.head.next;
		int curPos = 1;
		Node prevNode = this.head;
		Node curNode = prevNode.next;
		while (curNode != null) {
			if (curPos == index) {
				prevNode.next = curNode.next;
				return;
			}
			prevNode = curNode;
			curNode = curNode.next;
			curPos++;
		}

		// if the index is not fount
		throw new IndexOutOfBoundsException();
	}

	public void delete(String target) {
		if (this.head == null)
			throw new IndexOutOfBoundsException();

		Node prevNode = this.head;
		Node curNode = prevNode.next;
		while (curNode != null) {
			if (curNode.data.equals(target)) {
				prevNode.next = curNode.next;
				return;
			}
			prevNode = curNode;
			curNode = curNode.next;
		}
	}

	public void printList() {
		if (this.head == null)
			return;

		Node curNode = this.head;
		while (curNode != null) {
			System.out.println(curNode.data);
			curNode = curNode.next;
		}
	}

	public static void main(String[] args) {
		LinkedListMine tester = new LinkedListMine();
		tester.insert("elvin");
		tester.insert("tofik");
		tester.insert("tofik");
		tester.insert("revan", 1);
		tester.insert("aslan", 3);

		tester.printList();

		tester.delete(3); // revan delete
		tester.delete("tofik");// first occurance

		System.out.println();
		tester.printList();

		tester.delete(3);

	}
}
