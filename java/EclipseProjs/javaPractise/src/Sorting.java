import java.util.ArrayList;
import java.util.Scanner;

public class Sorting {

	private static ArrayList<Integer> createList(int len) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < len; i++) {
			list.add((int) (Math.random() * 100));
		}
		return list;
	}

	private static void swap(ArrayList<Integer> list, int index1, int index2) {
		int temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}

	private static ArrayList<Integer> convert(int[] list1) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < list1.length; i++) {
			list.add(list1[i]);
		}
		return list;
	}

	private static int[] convert(ArrayList<Integer> list) {
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}
		return res;
	}

	static ArrayList<Integer> bubbleSort(ArrayList<Integer> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - 1 - i; j++) {
				if (list.get(j) < list.get(j + 1)) {
					swap(list, j, j + 1);
				}
			}
		}

		return list;
	}

	static ArrayList<Integer> stoneSort(ArrayList<Integer> list) {
		for (int i = 1; i < list.size(); i++) {
			for (int j = list.size() - 1; j >= i; j--) {
				if (list.get(j) > list.get(j - 1)) {
					swap(list, j, j - 1);
				}
			}
		}
		return list;
	}

	static ArrayList<Integer> selectionSort(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			int indexOfMax = i;
			for (int j = i; j < list.size(); j++) {
				if (list.get(j) > list.get(indexOfMax)) {
					indexOfMax = j;
				}
			}
			swap(list, indexOfMax, i);
		}
		return list;
	}

	static void quickSort_1(ArrayList<Integer> list, int nStart, int nEnd) {
		// idk, sometimes it does not work!
		if (nStart >= nEnd)
			return;
		int L = nStart;
		int R = nEnd;
		int M = (L + R) / 2;
		while (L <= R) {
			while (list.get(L) < list.get(M))
				L += 1;
			while (list.get(R) > list.get(M))
				R -= 1;
			if (L <= R) {
				swap(list, L, R);
				L += 1;
				R -= 1;
			}
		}
		quickSort_1(list, nStart, R);
		quickSort_1(list, L, nEnd);

	}

	static ArrayList<Integer> quickSort_2(ArrayList<Integer> list) {
		// WORKS FINE!
		if (list.size() < 2)
			return list;
		int X = list.get(list.size() / 2);
		ArrayList<Integer> L = new ArrayList<>();
		ArrayList<Integer> M = new ArrayList<>();
		ArrayList<Integer> R = new ArrayList<>();
		for (int num : list) {
			if (num < X)
				L.add(num);
			else if (num == X)
				M.add(num);
			else
				R.add(num);
		}

		ArrayList<Integer> res = new ArrayList<>();
		for (int num : quickSort_2(L))
			res.add(num);
		for (int num : M)
			res.add(num);
		for (int num : quickSort_2(R))
			res.add(num);

		return res;

	}

	private static int findPos(ArrayList<Integer> list, int index) {
		boolean flag = false;
		for (int i = index - 1; i >= 0; i--) {
			if (list.get(i) > list.get(index)) {
				flag = true;
				continue;
			} else if (flag) {
				return i + 1;
			} else
				return -1;
		}
		return 0;
	}

	private static void changeStruct(ArrayList<Integer> list, int L, int R) {
		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(list.get(R));
		for (int index = L; index < R; index++) {
			temp.add(list.get(index));
		}
		int tempIndex = 0;
		for (int index = L; index <= R; index++) {
			list.set(index, temp.get(tempIndex));
			tempIndex += 1;
		}
		temp.clear();
	}

	static ArrayList<Integer> insertationSort(ArrayList<Integer> list) {
		for (int cur = 1; cur < list.size(); cur++) {
			int firstOccur = findPos(list, cur);
			if (firstOccur < 0)
				continue;
			changeStruct(list, firstOccur, cur);
		}
		return list;
	}

	private static int myFunc(int num, int place) {
		if (place == 0)
			return num;
		return (num / place) % 10;
	}

	static ArrayList<Integer> countingSort(ArrayList<Integer> list, int place) {
		int maxNum = 0;
		for (int num : list)
			if (num > maxNum)
				maxNum = num;
		if (place != 0)
			maxNum = 9;
		int[] count = new int[maxNum + 1];
		for (int i = 0; i < count.length; i++) {
			count[i] = 0;
		}

		for (int num : list) {
			count[myFunc(num, place)] += 1;
		}

		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}
		// идем с конца так как в radixSort если число одинаковое то оно должно ставить
		// сначала тот который он увидел первым
		// а тут было наоборот если не идти сконца(он ставил в начало каждое новое
		// одинаковое число!)
		int[] res = new int[list.size()];
		for (int i = list.size() - 1; i >= 0; i--) {
			res[count[myFunc(list.get(i), place)] - 1] = list.get(i);
			count[myFunc(list.get(i), place)] -= 1;
		}

		return convert(res);
	}

	private static int numOfDigits(int num) {
		int count = 0;
		while (num > 0) {
			count += 1;
			num = num / 10;
		}
		return count;
	}

	static ArrayList<Integer> radixSort(ArrayList<Integer> list) {
		int maxDigs = 0;
		for (int num : list)
			if (numOfDigits(num) > maxDigs)
				maxDigs = numOfDigits(num);

		for (int param = 1; param <= 10 * (maxDigs - 1); param *= 10) {
			list = countingSort(list, param);
		}

		return list;
	}

	private static int[] helper(int[] left, int[] right) {
		int[] res = new int[left.length + right.length];

		int leftPointer, rightPointer, resPointer;
		leftPointer = rightPointer = resPointer = 0;

		while (leftPointer < left.length || rightPointer < right.length) {

			if (leftPointer < left.length && rightPointer < right.length) {

				if (left[leftPointer] < right[rightPointer]) {
					res[resPointer++] = left[leftPointer++];
				} else {
					res[resPointer++] = right[rightPointer++];
				}

			} else if (leftPointer < left.length) {
				res[resPointer++] = left[leftPointer++];

			} else if (rightPointer < right.length) {
				res[resPointer++] = right[rightPointer++];
			}

		}
		return res;

	}

	static int[] mergeSort(int[] list) {
		if (list.length <= 1)
			return list;

		int mid = list.length / 2;

		int[] left = new int[mid];
		int[] right;

		if (list.length % 2 == 0)
			right = new int[mid];
		else
			right = new int[mid + 1];

		for (int i = 0; i < mid; i++)
			left[i] = list[i];
		for (int j = 0; j < right.length; j++)
			right[j] = list[j + mid];

		int[] res = new int[list.length];

		left = mergeSort(left);
		right = mergeSort(right);

		res = helper(left, right);
		return res;

	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.print("Type length of the list: ");
		int len = in.nextInt();

		ArrayList<Integer> list = createList(len);
		System.out.println("\nInitial list: " + list);
		System.out.println("BubbleSort: " + bubbleSort(list));

		list = createList(len);
		System.out.println("\nInitial list: " + list);
		System.out.println("StoneSort: " + stoneSort(list));

		list = createList(len);
		System.out.println("\nInitial list: " + list);
		System.out.println("SelectionSort: " + selectionSort(list));

		list = createList(len);
		System.out.println("\nInitial list: " + list);
		quickSort_1(list, 0, len - 1);
		System.out.println("QuickSort_1: " + list);

		list = createList(len);
		System.out.println("\nInitial list: " + list);
		System.out.println("QuickSort_2: " + quickSort_2(list));

		list = createList(len);
		System.out.println("\nInitial list: " + list);
		System.out.println("InsertationSort: " + insertationSort(list));

		list = createList(len);
		System.out.println("\nInitial list: " + list);
		System.out.println("CountingSort: " + countingSort(list, 0));

		list = createList(len);
		System.out.println("\nInitial list: " + list);
		System.out.println("RadixSort: " + radixSort(list));

		list = createList(len);
		System.out.println("\nInitial list: " + list);
		System.out.println("MergeSort: " + convert(mergeSort(convert(list))));
	}

}
