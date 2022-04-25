package Algorithms;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstPaths {

	private boolean[] marked;
	private int[] edgeTo;
	private int s;

	public DepthFirstPaths(Graph graph, int s) {
		marked = new boolean[graph.V()];
		edgeTo = new int[graph.V()];
		dfs(graph, s);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
				edgeTo[w] = v;
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}

}
