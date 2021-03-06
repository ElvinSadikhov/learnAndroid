package Algorithms;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

	private static final int xMin = 0, xMax = 1, yMin = 0, yMax = 1; // defaule rectangl
	private int size;
	private Node root;

	private class Node {
		private Point2D point;
		private RectHV rect;
		private Node left;
		private Node right;

		public Node(Point2D point, RectHV rect) {
			this.point = point;
			this.rect = rect;
			this.left = null;
			this.right = null;
		}
	}

	// construct an empty set of points
	public KdTree() {
		root = null;
		size = 0;
	}

	// is the set empty?
	public boolean isEmpty() {
		return size == 0;
	}

	// number of points in the set
	public int size() {
		return size;
	}

	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		root = insert(root, p, 0, xMin, xMax, yMin, yMax);
	}

	private Node insert(Node x, Point2D point, int level, double xMin, double xMax, double yMin, double yMax) {
		if (x == null) {
			size++;
			return new Node(point, new RectHV(xMin, yMin, xMax, yMax));
		}
		int cmp = compare(x.point, point, level);

		if (cmp < 0) {
			if (level % 2 == 0)
				x.left = insert(x.left, point, level + 1, xMin, x.point.x(), yMin, yMax);
			else
				x.left = insert(x.left, point, level + 1, xMin, xMax, yMin, x.point.y());
		} else if (cmp > 0) {
			if (level % 2 == 0)
				x.right = insert(x.right, point, level + 1, x.point.x(), xMax, yMin, yMax);
			else
				x.right = insert(x.right, point, level + 1, xMin, xMax, x.point.y(), yMax);
		}
		return x;
	}

	private int compare(Point2D a, Point2D b, int level) {
		if (level % 2 == 0) {
			return a.x() == b.x() ? (a.y() > b.y() ? -1 : (a.y() < b.y() ? 1 : 0)) : (a.x() > b.x() ? -1 : 1);
		} else {
			return a.y() == b.y() ? (a.x() > b.x() ? -1 : (a.x() < b.x() ? 1 : 0)) : (a.y() > b.y() ? -1 : 1);
		}
	}

	// does the set contain point p?
	public boolean contains(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		Node curNode = root;
		int level = 0;
		while (curNode != null) {
			int cmp = compare(curNode.point, p, level++);
			if (cmp == 0)
				return curNode.point.equals(p) ? true : false;
			else if (cmp < 0)
				curNode = curNode.left;
			else if (cmp > 0)
				curNode = curNode.right;
		}
		return false;

	}

	// draw all points to standard draw
	public void draw() {
		StdDraw.clear();
		drawLine(root, 0);
	}

	private void drawLine(Node x, int level) {
		if (x == null)
			return;

		if (level % 2 == 0) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(x.rect.xmin(), x.point.y(), x.rect.xmax(), x.point.y());
		} else {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(x.point.x(), x.rect.ymin(), x.point.x(), x.rect.ymax());
		}

		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setPenRadius(0.01);
		x.point.draw();

		drawLine(x.left, level + 1);
		drawLine(x.right, level + 1);
	}

	// all points that are inside the rectangle (or on the boundary)
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null)
			throw new IllegalArgumentException();
		Queue<Point2D> queue = new Queue<>();
		rangeAdd(root, rect, queue);
		return queue;
	}

	private void rangeAdd(Node x, RectHV rect, Queue<Point2D> queue) {
		if (x == null || !rect.intersects(x.rect))
			return;

		if (rect.contains(x.point))
			queue.enqueue(x.point);

		rangeAdd(x.left, rect, queue);
		rangeAdd(x.right, rect, queue);
	}

	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null)
			throw new IllegalArgumentException();
		return isEmpty() ? null : nearest(root, p, null);
	}

	private Point2D nearest(Node x, Point2D p, Point2D minSoFar) {
		if (p == null)
			throw new IllegalArgumentException();

		if (x == null)
			return minSoFar;

		if (minSoFar == null)
			minSoFar = x.point;

		if (x.rect.distanceSquaredTo(p) <= minSoFar.distanceSquaredTo(p)) {
			if (x.point.distanceSquaredTo(p) < minSoFar.distanceSquaredTo(p))
				minSoFar = x.point;

			if (x.right != null && x.right.rect.contains(p)) {
				minSoFar = nearest(x.right, p, minSoFar);
				minSoFar = nearest(x.left, p, minSoFar);
			} else {
				minSoFar = nearest(x.left, p, minSoFar);
				minSoFar = nearest(x.right, p, minSoFar);
			}
		}

		return minSoFar;
	}

	public static void main(String[] args) {
		KdTree temp = new KdTree();

		Point2D p1 = new Point2D(0.1875, 0.25);
		temp.insert(p1);
		Point2D p2 = new Point2D(0.4375, 0.4375);
		System.out.println(temp.size());
		Point2D p3 = new Point2D(0.1875, 0.25);
		temp.insert(p3);
		Point2D p4 = new Point2D(0.1875, 0.9375);
		temp.insert(p4);

		Point2D p5 = new Point2D(0.5, 0.5);
		System.out.println(temp.nearest(p5));

	}
}
