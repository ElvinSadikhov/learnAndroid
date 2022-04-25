package Algorithms;

/*
 Trees can get too long and root method can get a lot of time and it will become too slow
 so it is also not so effective
 */
public class QuickUnion {

	private int[] id;

	public QuickUnion(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	private int root(int i) {
		while (id[i] != i) {
			i = id[i];
		}
		return i;
	}

	public boolean connected(int p, int q) {
		return (root(p) == root(q));
	}

	public void union(int p, int q) {
		int pRoot = id[p];
		int qRoot = id[q];
		id[pRoot] = qRoot;
	}

	public static void main(String[] args) {

	}

}
