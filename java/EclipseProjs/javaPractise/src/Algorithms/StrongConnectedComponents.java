package Algorithms;

import edu.princeton.cs.algs4.Digraph;

public class StrongConnectedComponents {

	private int count;
	private boolean[] marked;
	private int[] id;

	public StrongConnectedComponents(Digraph G) {
		count = 0;
		marked = new boolean[G.V()];
		id = new int[G.V()];
		TopologicalSort dfs = new TopologicalSort(G.reverse());
		for (int v : dfs.reversePost()) { // give us a result of dfs from REVERSED digraph
			if (!marked[v]) {
				dfs(G, v);
				count++;
			}
		}
	}

	public boolean strongConnected(int v, int w) {
		return id[v] == id[w];
	}

	private boolean marked(int v) {
		return marked[v];
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v)) {
			if (!marked[w])
				dfs(G, w);
		}
	}

}
