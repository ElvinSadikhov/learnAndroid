package Algorithms;

import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {

	private SET<Point2D> pointSet;

	// construct an empty set of points
	public PointSET() {
		pointSet = new SET<Point2D>();
	}

	// is the set empty?
	public boolean isEmpty() {
		return pointSet.size() == 0;
	}

	public int size() {
		return pointSet.size();
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		if (!pointSet.contains(p))
			pointSet.add(p);
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		return pointSet.contains(p);
	}

	// draw all points to standard draw
	public void draw() {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);

		for (Point2D point : pointSet) {
			point.draw();
		}
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null)
			throw new IllegalArgumentException();
		ArrayList<Point2D> pointsIn = new ArrayList<>();

		for (Point2D point : pointSet) {
			if (rect.contains(point))
				pointsIn.add(point);
		}
		return pointsIn;
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		if (pointSet.isEmpty())
			return null;
		Point2D nearest = null;
		for (Point2D curPoint : pointSet) {
			if (nearest == null) {
				nearest = curPoint;
				continue;
			}
			if (curPoint.distanceSquaredTo(p) < nearest.distanceSquaredTo(p))
				nearest = curPoint;
		}
		return nearest;
	}

	public static void main(String[] args) {
		PointSET temp = new PointSET();
		Point2D p = new Point2D(0.5, 0.5);
		temp.insert(p);
		System.out.println(temp.size());
		System.out.println(temp.pointSet);

	}
}
