import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
    private static final boolean red = true;
    private static final boolean blue = false;
    private Node root;

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the set
    public int size() {
        return size(root);
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("None pointer");
        root = add(root, p, blue);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("None pointer");
        return contains(root, p);
    }

    // draw all points to standard draw
    public void draw() {
        if (!isEmpty())
            draw(root, 0, 0, 1, 1);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new IllegalArgumentException("null argument");
        Queue<Point2D> queue = new Queue<Point2D>();
        recta(root, rect, queue);
        return queue;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("null argument");
        if (isEmpty())
            return null;
        return nearest(root, p, root.p, new RectHV(0, 0, 1, 1));
    }

    // Node
    private class Node {
        private boolean color;
        private Point2D p;
        private Node left, right;
        private int size;

        Node(Point2D point, boolean color, int size) {
            p = point;
            this.color = color;
            this.size = size;
        }
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.size;
    }

    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color == red;
    }

    private Node add(Node x, Point2D p, boolean color) {
        if (x == null && color == red)
            x = new Node(p, blue, 1);
        else if (x == null && color == blue)
            x = new Node(p, red, 1);
        else if (x.p.compareTo(p) == 0)
            return x;
        else if (isRed(x) && p.x() < x.p.x())
            x.left = add(x.left, p, red);
        else if (isRed(x) && p.x() >= x.p.x())
            x.right = add(x.right, p, red);
        else if (!isRed(x) && p.y() < x.p.y())
            x.left = add(x.left, p, blue);
        else if (!isRed(x) && p.y() >= x.p.y())
            x.right = add(x.right, p, blue);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private boolean contains(Node x, Point2D p) {
        if (x == null)
            return false;
        else if (x.p.compareTo(p) == 0)
            return true;
        else if (isRed(x) && x.p.x() <= p.x())
            return contains(x.right, p);
        else if (isRed(x) && x.p.x() > p.x())
            return contains(x.left, p);
        else if (!isRed(x) && p.y() < x.p.y())
            return contains(x.left, p);
        else if (!isRed(x) && p.y() >= x.p.y())
            return contains(x.right, p);
        else
            return false;
    }

    /*
     * private Iterable<Point2D> all() { if (root == null) throw new
     * IllegalArgumentException("null argument"); Queue<Point2D> queue = new
     * Queue<Point2D>(); keys(root, queue); return queue; }
     * 
     * private void keys(Node x, Queue<Point2D> queue) { keys(x.left, queue);
     * queue.enqueue(x.p); keys(x.right, queue); }
     */
    private void recta(Node x, RectHV rect, Queue<Point2D> queue) {
        if (x == null)
            return;
        else if (isRed(x) && rect.xmin() > x.p.x())
            recta(x.right, rect, queue);
        else if (isRed(x) && rect.xmax() < x.p.x())
            recta(x.left, rect, queue);
        else if (!isRed(x) && rect.ymin() > x.p.y())
            recta(x.right, rect, queue);
        else if (!isRed(x) && rect.ymax() < x.p.y())
            recta(x.left, rect, queue);
        else {
            recta(x.left, rect, queue);
            if (rect.contains(x.p))
                queue.enqueue(x.p);
            recta(x.right, rect, queue);
        }
    }

    private Point2D nearest(Node x, Point2D p, Point2D min, RectHV rect) {
        if (x == null)
            return min;
        double current = min.distanceSquaredTo(p);
        if (current < rect.distanceSquaredTo(p))
            return min;
        if (x.p.distanceSquaredTo(p) < current)
            min = x.p;
        RectHV lrect, rrect;
        if (isRed(x)) {
            lrect = new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax());
            rrect = new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
        } else {
            lrect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y());
            rrect = new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax());
        }
        if (isRed(x) && p.x() < x.p.x()) {
            min = nearest(x.left, p, min, lrect);
            min = nearest(x.right, p, min, rrect);
        } else if (isRed(x) && p.x() >= x.p.x()) {
            min = nearest(x.right, p, min, rrect);
            min = nearest(x.left, p, min, lrect);
        } else if (!isRed(x) && p.y() < x.p.y()) {
            min = nearest(x.left, p, min, lrect);
            min = nearest(x.right, p, min, rrect);
        } else {
            min = nearest(x.right, p, min, rrect);
            min = nearest(x.left, p, min, lrect);
        }
        return min;
    }

    private void draw(Node x, double xmin, double ymin, double xmax, double ymax) {
        if (x == null)
            return;
        if (isRed(x)) {
            draw(x.left, xmin, ymin, x.p.x(), ymax);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(x.p.x(), x.p.y());
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), ymin, x.p.x(), ymax);
            draw(x.right, x.p.x(), ymin, xmax, ymax);
        } else {
            draw(x.left, xmin, ymin, xmax, x.p.y());
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(x.p.x(), x.p.y());
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(xmin, x.p.y(), xmax, x.p.y());
            draw(x.right, xmin, x.p.y(), xmax, ymax);
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        KdTree set = new KdTree();
        set.insert(new Point2D(0.2, 0.3));
        set.insert(new Point2D(0.1, 0.4));
        set.insert(new Point2D(0.5, 0.5));
        set.insert(new Point2D(0.6, 0.6));
        set.insert(new Point2D(0.6, 0.6));
        RectHV rect = new RectHV(0.1, 0.2, 0.3, 0.4);
        set.draw();
        rect.draw();
        StdOut.printf("Is empty?: %s\nPointSet size: %d\nDoes it contain (0.6,0.6)?: %s\n", set.isEmpty(), set.size(),
                set.contains(new Point2D(0.6, 0.6)));
        for (Point2D p : set.range(rect)) {
            StdOut.println(p);
        }
        StdOut.println(set.nearest(new Point2D(0.6, 0.6)));
    }
}