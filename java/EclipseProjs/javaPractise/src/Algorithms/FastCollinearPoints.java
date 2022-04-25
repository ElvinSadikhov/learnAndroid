package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

	private LineSegment[] segments;

	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points) {

		checkForNull(points);

		Point[] copyPoints1 = Arrays.copyOf(points, points.length);
		Point[] copyPoints2 = Arrays.copyOf(points, points.length);
		ArrayList<LineSegment> segmentsList = new ArrayList<>();

		Arrays.sort(copyPoints1);
		checkForDublicates(copyPoints1);

		for (int i = 0; i < copyPoints1.length; i++) {

			Point origin = copyPoints1[i];
			Point lineBeginning = null;
			int count = 1;
			Arrays.sort(copyPoints2);
			Arrays.sort(copyPoints2, origin.slopeOrder());

			for (int j = 0; j < copyPoints2.length - 1; j++) {
				if (copyPoints2[j].slopeTo(origin) == copyPoints2[j + 1].slopeTo(origin)) {
					count++;
					if (count == 2) {
						lineBeginning = copyPoints2[j];
						count++;
					} else if (count >= 4 && j + 1 == copyPoints2.length - 1) {
						if (lineBeginning.compareTo(origin) > 0) {
							segmentsList.add(new LineSegment(origin, copyPoints2[j + 1]));
						}
						count = 1;
					}
				} else if (count >= 4) {
					if (lineBeginning.compareTo(origin) > 0) {
						segmentsList.add(new LineSegment(origin, copyPoints2[j]));
					}
					count = 1;
				} else {
					count = 1;
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
		for (int i = 0; i < points.length; ++i) {
			if (points[i] == null) {
				throw new java.lang.IllegalArgumentException();
			}
		}
	}

	private void checkForDublicates(Point[] points) {
		for (int i = 0; i < points.length - 1; ++i) {
			if (points[i].compareTo(points[i + 1]) == 0) {
				throw new java.lang.IllegalArgumentException();
			}
		}
	}
}
