package ShortestPaths;

import java.util.Stack;

import MinimumSpanningTrees.IndexMinPQMine;

// Shortest Path
public class SPMine {

	private DirectedEdgeMine[] edgeTo;
	private double[] distTo;
	private IndexMinPQMine<Double> pq;

	public SPMine(EdgeWeightedDigraphMine G, int s) {
		edgeTo = (DirectedEdgeMine[]) new Object[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQMine<>(G.V());
		for (int i = 0; i < G.V(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;

		pq.insert(s, 0.0);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			for (DirectedEdgeMine e : G.adj(v)) {
				relax(e);
			}
		}
	}

	private void relax(DirectedEdgeMine e) {
		int v = e.from(), w = e.to();
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			// updating pq
			if (pq.contains(w))
				pq.decreaseKey(w, distTo[w]);
			else
				pq.insert(w, distTo[w]);
		}
	}

	public double distTo(int v) {
		return distTo[v];
	}

	public Iterable<DirectedEdgeMine> pathTo(int v) {
		Stack<DirectedEdgeMine> path = new Stack<>();
		for (DirectedEdgeMine e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.add(e);
		}
		return path;
	}
}
