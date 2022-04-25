package MinimumSpanningTrees;

public class EdgeMine implements Comparable<EdgeMine> {

	private final int v, w;
	private final double weight;

	public EdgeMine(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	// returns either endpoint
	public int either() {
		return v;
	}

	// returns another endpoint
	public int other(int vertex) {
		return this.v == vertex ? w : v;
	}

	@Override
	public int compareTo(EdgeMine that) {
		if (this.weight < that.weight)
			return -1;
		else if (this.weight > that.weight)
			return 1;
		return 0;
	}

	public double weight() {
		return weight;
	}

//	public String toString() {
//	}

}
