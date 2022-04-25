package Algorithms;

import edu.princeton.cs.algs4.Bag;

public class GraphMine {

	private final int V; // verticies
	private int E; // edges
	private Bag<Integer>[] adj;

	public GraphMine(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be non-negative");
		this.V = V;
		this.E = 0;
		this.adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

	public int degree(int v) {
		return adj[v].size();
	}

	public int maxDegree() {
		int max = 0;
		for (int v = 0; v < V; v++) {
			int curDegree = degree(v);
			if (curDegree > max)
				max = curDegree;
		}
		return max;
	}

	public double averageDegree() {
		return 2.0 * E / V;
	}

	public int numberOfSelfLoops() {
		int count = 0;
		for (int v = 0; v < V; v++) {
			for (int w : adj(v))
				if (v == w)
					count++;
		}
		return count;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

}
