package ShortestPaths;

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedDigraphMine {

	private final int V;
	private final Bag<DirectedEdgeMine>[] adj;

	public EdgeWeightedDigraphMine(int V) {
		this.V = V;
		adj = new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
		}
	}

	public void addEdge(DirectedEdgeMine e) {
		int v = e.from();
		adj[v].add(e);
		;
	}

	public Iterable<DirectedEdgeMine> adj(int v) {
		return adj[v];
	}

	public int V() {
		return V;
	}

}
