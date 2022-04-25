package Algorithms;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

public class TopologicalSort {

	private boolean[] marked;
	private Stack<Integer> reversePost;

	public TopologicalSort(Digraph G) {
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v])
				dfs(G, v);
		}
	}
	
	public Iterable<Integer> reversePost(){
		return reversePost;
	}

	private boolean marked(int v) {
		return marked[v];
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w])
				dfs(G, w);
		}
		reversePost.push(v);
	}

}
