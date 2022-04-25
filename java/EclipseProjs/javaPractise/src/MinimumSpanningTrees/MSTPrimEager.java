package MinimumSpanningTrees;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Queue;

public class MSTPrimEager {

	private Edge[] edgeTo;
	private double[] distTo;
	private boolean[] marked;
	private IndexMinPQMine<Double> pq;

	public MSTPrimEager(EdgeWeightedGraph G) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		pq = new IndexMinPQMine<>(G.V());

		for (int i = 0; i < G.V(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}

		for (int v = 0; v < G.V(); v++) {
			if (!marked[v])
				prim(G, v);
		}
	}

	private void prim(EdgeWeightedGraph G, int s) {
		distTo[s] = 0.0;
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			scan(G, v);
		}
	}

	private void scan(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			int w = e.other(v);
			if (marked[w])
				continue; // v-w is obsolete edge
			if (e.weight() < distTo[w]) {
				distTo[w] = e.weight();
				edgeTo[w] = e;
				if (pq.contains(w))
					pq.decreaseKey(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}

	public Iterable<Edge> edges() {
		Queue<Edge> mst = new Queue<Edge>();
		for (int v = 0; v < edgeTo.length; v++) {
			Edge e = edgeTo[v];
			if (e != null) {
				mst.enqueue(e);
			}
		}
		return mst;
	}

	public double weight() {
		double weight = 0.0;
		for (Edge e : edges())
			weight += e.weight();
		return weight;
	}
}
