package Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {

	private HashMap<Integer, String> int2string;
	private HashMap<String, HashSet<Integer>> string2int;
	private Digraph graph;

	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		if (synsets == null || hypernyms == null)
			throw new IllegalArgumentException();

		int2string = new HashMap<>();
		string2int = new HashMap<>();

		// first we will fill int2string hash map
		In synSets = new In(synsets);
		while (synSets.hasNextLine()) {
			String[] line = synSets.readLine().split(",");
			if (line.length < 2)
				continue; // idk
			int index = Integer.parseInt(line[0]);

			int2string.put(index, line[1]);

			String[] words = line[1].split("\\s++");
			for (String word : words) {
				if (!string2int.containsKey(word)) {
					string2int.put(word, new HashSet<>());
				}
				string2int.get(word).add(index);
			}
		}

		// creating a digraph
		this.graph = new Digraph(int2string.size());

		// now we create connections in digraph
		In hyperNyms = new In(hypernyms);
		while (hyperNyms.hasNextLine()) {
			String[] line = hyperNyms.readLine().split(",");
			if (line.length < 2)
				continue; // idk

			int index1 = Integer.parseInt(line[0]);
			for (int i = 1; i < line.length; i++) {
				int index2 = Integer.parseInt(line[i]);
				graph.addEdge(index1, index2);
			}

		}

		if (hasCycle()) {
			throw new IllegalArgumentException("Given graph is not a DAG.");
		}
	}

	private boolean hasCycle() {
		ArrayList<Integer> rootArr = new ArrayList<Integer>();
		for (int i = 0; i < graph.V(); i++) {
			if (!graph.adj(i).iterator().hasNext()) {
				rootArr.add(i);
			}
		}
//	        there should be exactly one root
		if (rootArr.size() == 0 || rootArr.size() > 1) {
			StdOut.println("There is no root or more than one root.");
			StdOut.println("Size: " + rootArr.size());
			return true;
		}
		DirectedCycle diCycle = new DirectedCycle(graph);
		return diCycle.hasCycle();
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return string2int.keySet();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		if (word == null)
			throw new IllegalArgumentException();
		return string2int.containsKey(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
//		if (!string2int.containsKey(nounA) || !string2int.containsKey(nounB)) {
//			throw new IllegalArgumentException();
//		}

		Iterable<Integer> iterableA = string2int.get(nounA);
		Iterable<Integer> iterableB = string2int.get(nounB);

		BreadthFirstDirectedPaths g1 = new BreadthFirstDirectedPaths(this.graph, iterableA);
		BreadthFirstDirectedPaths g2 = new BreadthFirstDirectedPaths(this.graph, iterableB);

		int result = -1;
		for (int i : int2string.keySet()) {
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

	// a synset (second field of synsets.txt) that is the common ancestor of nounA
	// and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
//		if (!string2int.containsKey(nounA) || !string2int.containsKey(nounB)) {
//			throw new IllegalArgumentException();
//		}

		Iterable<Integer> iterableA = string2int.get(nounA);
		Iterable<Integer> iterableB = string2int.get(nounB);

		BreadthFirstDirectedPaths g1 = new BreadthFirstDirectedPaths(this.graph, iterableA);
		BreadthFirstDirectedPaths g2 = new BreadthFirstDirectedPaths(this.graph, iterableB);

		int result = -1;
		int index = 0;
		for (int i : int2string.keySet()) {
			if (g1.hasPathTo(i) && g2.hasPathTo(i)) {
				int cur = g1.distTo(i) + g2.distTo(i);
				if (result < 0) {
					index = i;
					result = cur;
				} else if (cur < result) {
					index = i;
					result = cur;
				}
			}
		}

		return int2string.get(index);

	}

}
