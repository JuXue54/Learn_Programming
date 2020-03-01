import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    // constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw() {
        StdDraw.point(x, y);
    }

    // draws the line segment from this point to that point
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // string representation
    public String toString() {
        return "(" + x + ", " + y + ")";

    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y || (this.y == that.y && this.x < that.x))
            return -1;
        else if (this.x == that.x && this.y == that.y)
            return 0;
        else
            return 1;
    }

    // the slope between this point and that point
    public double slopeTo(Point that) {
        if (that == null)
            throw new NullPointerException("argument is null");
        if (this.compareTo(that) == 0)
            return Double.NEGATIVE_INFINITY;
        else if (this.x == that.x)
            return Double.POSITIVE_INFINITY;
        else if (this.y == that.y)
            return 0.0;
        else
            return ((double) (that.y - this.y)) / (that.x - this.x);
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder() {
        return new SlopeOrder();
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (a == null || b == null)
                throw new NullPointerException("argument is null");
            if (Math.abs(Point.this.slopeTo(a) - Point.this.slopeTo(b)) > 1E-8) {
                if (Point.this.slopeTo(a) < Point.this.slopeTo(b))
                    return -1;
                else
                    return 1;
            }
            return 0;
        }
    }

    // test
    public static void main(String[] args) {
        Point p = new Point(169, 48);
        Point q = new Point(409, 295);
        Point r = new Point(306, 189);
        // int c = p.compare(q,r);
        int c = 1;
        double e = p.slopeTo(r);
        String s = p.toString();
        System.out.printf("%s, %d, %f\n", s, c, e);
    }
}