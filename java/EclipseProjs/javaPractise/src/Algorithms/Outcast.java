package Algorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

	private WordNet wordnet;

	// constructor takes a WordNet object
	public Outcast(WordNet wordnet) {
		this.wordnet = wordnet;
	}

	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {
		int maxDist = -1;
		String res = null;
		for (String noun1 : nouns) {
			int sumOfDist = 0;
			for (String noun2 : nouns) {
				sumOfDist += wordnet.distance(noun1, noun2);
			}
			if (sumOfDist > maxDist) {
				maxDist = sumOfDist;
				res = noun1;
			}
		}
		return res;
	}

	// see test client below
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}

}
