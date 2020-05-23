import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
/**
 * PointSET
 * To use red-black tree to store the points
 * @author Jon Xue
 */
public class PointSET {
    private SET<Point2D> tree;

    // construct an empty set of points
    public PointSET() {
        tree = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    // number of points in the set
    public int size() {
        return tree.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("None pointer");
        if (!contains(p))
            tree.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("None pointer");
        return tree.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D p : tree) {
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException("null argument");
        Queue<Point2D> queue = new Queue<Point2D>();
        for (Point2D p : tree) {
            if (rect.contains(p))
                queue.enqueue(p);
        }
        return queue;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("null argument");
        double min = Double.POSITIVE_INFINITY;
        Point2D near = null;
        for (Point2D q : tree) {
            double dis = p.distanceSquaredTo(q);
            if (dis < min) {
                min = dis;
                near = q;
            }
        }
        return near;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        PointSET set = new PointSET();
        set.insert(new Point2D(0.2, 0.3));
        set.insert(new Point2D(0.1, 0.4));
        set.insert(new Point2D(0.5, 0.5));
        RectHV rect = new RectHV(0.1, 0.2, 0.3, 0.4);
        set.draw();
        StdOut.printf("Is empty?: %s\nPointSet size: %d\nDoes it contain (0.2,0.3)?: %s\n", set.isEmpty(), set.size(),
                set.contains(new Point2D(0.2, 0.3)));
        for (Point2D p : set.range(rect)) {
            StdOut.println(p);
        }
        StdOut.println(set.nearest(new Point2D(0.6, 0.6)));
    }
}