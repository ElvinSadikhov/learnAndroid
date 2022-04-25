package MaxFlow_MinCut;

import edu.princeton.cs.algs4.Queue;

public class FordFulkerson {

	private boolean[] marked;
	private FlowEdge[] edgeTo;
	private double value; // value of flow

	public FordFulkerson(FlowNetwork G, int s, int t) {
		value = 0.0;
		while (hasAugmentPath(G, s, t)) {

			double bottle = Double.POSITIVE_INFINITY;
			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
			}

			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				edgeTo[v].addResidualFlowTo(v, bottle);
			}
			value += bottle;
		}
	}

	public double value() {
		return value;
	}

	public boolean inCut(int v) {
		return marked[v];
	}

	private boolean hasAugmentPath(FlowNetwork G, int s, int t) {
		edgeTo = new FlowEdge[G.V()];
		marked = new boolean[G.V()];

		Queue<Integer> q = new Queue<>();
		q.enqueue(s);
		marked[s] = true;
		while (!q.isEmpty()) {
			int v = q.dequeue();

			for (FlowEdge e : G.adj(v)) {
				int w = e.other(v);
				if (e.residualCapacityTo(w) > 0 && !marked[w]) {
					edgeTo[w] = e;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
		return marked[t];

	}

}
