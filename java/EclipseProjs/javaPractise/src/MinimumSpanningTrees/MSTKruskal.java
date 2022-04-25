package MinimumSpanningTrees;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/* Kruskal's Algorithm
 * we sort the edges by weights
 * and connected them until it is V-1
 * but we skip the ones which creates circles
 * for detection of which we use unionfind API
 * which takes time propotional to E*log(E)
 */

public class MSTKruskal {

	private Queue<Edge> mst = new Queue<>();

	@SuppressWarnings("deprecation")
	public MSTKruskal(EdgeWeightedGraph G) {

		MinPQ<Edge> pq = new MinPQ<>();
		for (Edge e : G.edges()) {
			pq.insert(e);
		}

		UF uf = new UF(G.V());

		// loop until pq is empty, or mst have V-1 edges in it(then we stop)
		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			if (!uf.connected(v, w)) {
				uf.union(v, w);
				mst.enqueue(e);
			}
		}
	}

	public Iterable<Edge> edges() {
		return mst;
	}

}
