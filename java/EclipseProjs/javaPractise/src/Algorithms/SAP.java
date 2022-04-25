package Algorithms;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SAP {

	private Digraph graph;

	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		this.graph = new Digraph(G);
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		BreadthFirstDirectedPaths g1 = new BreadthFirstDirectedPaths(this.graph, v);
		BreadthFirstDirectedPaths g2 = new BreadthFirstDirectedPaths(this.graph, w);

		int result = -1;
		for (int i = 0; i < graph.V(); i++) {
			if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
				int cur = g1.distTo(i) + g2.distTo(i);
				if (result < 0)
					result = cur;
				else
					result = cur < result ? cur : result;
			}
		}

		return result;
	}

	// a common ancestor of v and w that participates in a shortest ancestral path;
	// -1 if no such path
	public int ancestor(int v, int w) {
		BreadthFirstDirectedPaths g1 = new BreadthFirstDirectedPaths(this.graph, v);
		BreadthFirstDirectedPaths g2 = new BreadthFirstDirectedPaths(this.graph, w);

		int index = -1;
		int result = -1;
		for (int i = 0; i < graph.V(); i++) {
			if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
				int cur = g1.distTo(i) + g2.distTo(i);
				if (result < 0) {
					result = cur;
					index = i;
				} else if (cur < result) {
					index = i;
					result = cur;
				}
			}
		}

		return index;
	}

	// length of shortest ancestral path between any vertex in v and any vertex in
	// w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		BreadthFirstDirectedPaths g1 = new BreadthFirstDirectedPaths(this.graph, v);
		BreadthFirstDirectedPaths g2 = new BreadthFirstDirectedPaths(this.graph, w);

		int result = -1;
		for (int i = 0; i < graph.V(); i++) {
			if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
				int cur = g1.distTo(i) + g2.distTo(i);
				if (result < 0)
					result = cur;
				else
					result = cur < result ? cur : result;
			}
		}

		return result;
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such
	// path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		BreadthFirstDirectedPaths g1 = new BreadthFirstDirectedPaths(this.graph, v);
		BreadthFirstDirectedPaths g2 = new BreadthFirstDirectedPaths(this.graph, w);

		int index = -1;
		int result = -1;
		for (int i = 0; i < graph.V(); i++) {
			if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
				int cur = g1.distTo(i) + g2.distTo(i);
				if (result < 0) {
					result = cur;
					index = i;
				} else if (cur < result) {
					index = i;
					result = cur;
				}
			}
		}

		return index;
	}

}
