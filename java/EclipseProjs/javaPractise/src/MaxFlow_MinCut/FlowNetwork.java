package MaxFlow_MinCut;

import edu.princeton.cs.algs4.Bag;

public class FlowNetwork {

	private final int V;
	private Bag<FlowEdge>[] adj;

	public FlowNetwork(int V) {
		this.V = V;
		adj = (Bag<FlowEdge>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<FlowEdge>();
		}
	}

	public void adddEdge(FlowEdge e) {
		int v = e.from();
		int w = e.to();
		adj[v].add(e);// add forward edge
		adj[w].add(e);// add backward edge
	}

	public Iterable<FlowEdge> adj(int v) {
		return adj[v];
	}

	public int V() {
		return V;
	}

}
