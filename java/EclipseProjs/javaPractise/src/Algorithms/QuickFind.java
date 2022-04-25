package Algorithms;

/*
 For information in QuickFind union method is very expensive
 and it can take N^2 time if we create N object and make N union statements(if i am not wrong)
*/
public class QuickFind {

	private int[] id;

	public QuickFind(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public void union(int p, int q) {
		int pValue = id[p];
		int qValue = id[q];
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pValue)
				id[i] = qValue;
		}
	}

	public boolean connected(int p, int q) {
		return (id[p] == id[q]);
	}

	public static void main(String[] args) {

	}

}
