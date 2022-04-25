package Algorithms;

import java.util.Scanner;

public class WeightedQuickUnion {

	private int[] parent;
	private int[] height;
	private int count;// number of sets

	public WeightedQuickUnion(int N) {
		count = N;
		parent = new int[N];
		height = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
			height[i] = 0;
		}
	}

	public int count() {
		return count;
	}

	public int findRoot(int i) {
		while (parent[i] != i)
			i = parent[i];
		return i;
	}

	public boolean connected(int p, int q) {
		return (findRoot(p) == findRoot(q));
	}

	public void union(int p, int q) {
		int pRoot = findRoot(p);
		int qRoot = findRoot(q);

		if (pRoot == qRoot)
			return;

		if (height[pRoot] < height[qRoot])
			parent[pRoot] = qRoot;
		else if (height[pRoot] > height[qRoot])
			parent[qRoot] = pRoot;
		else {
			parent[qRoot] = pRoot;
			height[pRoot] += 1;
		}
		count -= 1;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		WeightedQuickUnion uf = new WeightedQuickUnion(N);
		while (N>0) {
			int p = in.nextInt();
			int q = in.nextInt();
			if (uf.findRoot(p) == uf.findRoot(q))
				continue;
			uf.union(p, q);
			System.out.println(p + " " + q);
		}
		System.out.println(uf.count() + " components");

	}

}
