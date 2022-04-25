package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

	private LineSegment[] segments;

	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null)
			throw new java.lang.NullPointerException("null argument to constructor");

		checkForNull(points);

		ArrayList<LineSegment> segmentsList = new ArrayList<LineSegment>();

		Point[] copy = Arrays.copyOf(points, points.length);

		Arrays.sort(copy);

		checkForDublicates(copy);

		for (int p1 = 0; p1 < (copy.length - 3); ++p1) {
			for (int p2 = p1 + 1; p2 < (copy.length - 2); ++p2) {
				for (int p3 = p2 + 1; p3 < (copy.length - 1); ++p3) {
					for (int p4 = p3 + 1; p4 < (copy.length); ++p4) {
						if (copy[p1].slopeTo(copy[p2]) == copy[p1].slopeTo(copy[p4])
								&& copy[p1].slopeTo(copy[p2]) == copy[p1].slopeTo(copy[p3])) {
							LineSegment ls = new LineSegment(copy[p1], copy[p4]);
							if (!segmentsList.contains(ls)) {
								segmentsList.add(ls);
							}

						}
					}
				}
			}
		}
		segments = segmentsList.toArray(new LineSegment[segmentsList.size()]);
	}

	// the number of line segments
	public int numberOfSegments() {
		return segments.length;
	}

	// the line segments
	public LineSegment[] segments() {
		return segments;
	}

	private void checkForNull(Point[] points) {
		for (int i = 0; i < points.length - 1; i++) {
			if (points[i] == null)
				throw new java.lang.IllegalArgumentException();
		}
	}

	private void checkForDublicates(Point[] points) {
		for (int i = 0; i < points.length - 1; i++) {
			if (points[i] == null) {
				throw new java.lang.IllegalArgumentException();
			}
		}
	}
}
