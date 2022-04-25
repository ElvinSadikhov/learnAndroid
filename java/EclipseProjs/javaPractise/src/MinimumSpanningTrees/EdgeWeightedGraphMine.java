package MinimumSpanningTrees;

import edu.princeton.cs.algs4.Bag;

public class EdgeWeightedGraphMine {

	private final int V;
	private final Bag<EdgeMine>[] adj;

	public EdgeWeightedGraphMine(int V) {
		this.V = V;
		this.adj = (Bag<EdgeMine>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<EdgeMine>();
		}
	}

	// adds the given edge to adj array, to both the endpoints of it
	public void addEdge(EdgeMine e) {
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}

	// returns a bag of edges with the given vertex as an endpoint
	public Iterable<EdgeMine> adj(int v) {
		return adj[v];
	}
}
