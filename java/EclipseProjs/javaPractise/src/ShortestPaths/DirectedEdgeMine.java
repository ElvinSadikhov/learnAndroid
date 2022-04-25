package ShortestPaths;

public class DirectedEdgeMine {

	private final int v, w;
	private final double weight;

	public DirectedEdgeMine(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int from() {
		return v;
	}

	public int to() {
		return w;
	}

	public double weight() {
		return weight;
	}

}
