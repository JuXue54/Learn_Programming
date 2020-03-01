import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private Point[] points;
    private LineSegment[] res;
    private int n;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Argument is Null");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("Arugument is Null");
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("repeated points");
            }
        }
        this.points = points;
        res = null;
        n = 0;
    }

    // the number of line segments
    public int numberOfSegments() {
        if (res != null)
            return n;
        else
            segments();
        return n;
    }

    // the line segments
    public LineSegment[] segments() {
        if (res != null)
            return res;
        LineSegment[] r = new LineSegment[1];
        int id;
        double record;
        Point[] sub;
        double[] slopes;
        Point[][] temp = new Point[1][4];
        int s = 1;
        // regard a new point i as an original point
        for (int i = 0; i < points.length - 3; i++) {
            sub = Arrays.copyOfRange(points, i + 1, points.length);
            Arrays.sort(sub, points[i].slopeOrder());
            slopes = new double[sub.length];
            for (int j = 0; j < sub.length; j++)
                slopes[j] = points[i].slopeTo(sub[j]);
            record = slopes[0];
            id = 1;
            for (int j = 1; j < slopes.length; j++) {
                if (!(Math.abs(slopes[j] - record) > 1E-8))
                    id++;
                else {
                    id = 1;
                    record = slopes[j];
                }
                if (n == temp.length) {
                    Point[][] copy = new Point[n * 2][];
                    for (int k = 0; k < n; k++) {
                        copy[k] = temp[k];
                    }
                    temp = copy;
                }
                if (id < 3)
                    continue;
                else if (id == 3)
                    temp[n] = new Point[] { points[i], sub[j - 2], sub[j - 1], sub[j] };
                else if (id > 3 && s == 0) {
                    continue;
                } else {
                    temp[n - 1] = append(temp[n - 1], sub[j]);
                    n--;
                }
                s = 1;
                Arrays.sort(temp[n]);
                for (int j2 = 0; j2 < n; j2++) {
                    if (issubset(temp[j2], temp[n])) {
                        s = 0;
                        break;
                    }
                }
                if (s == 0) {
                    continue;
                }
                if (n == r.length)
                    r = resize(r, r.length * 2);
                r[n] = new LineSegment(temp[n][0], temp[n][temp[n].length - 1]);
                n++;
            }
        }
        res = Arrays.copyOfRange(r, 0, n);
        return res;
    }

    // Is the subset of another subset?
    private boolean issubset(Point[] a, Point[] sub) {
        int k = 0;
        int i = 0;
        while (i < sub.length) {
            if (k == a.length)
                return false;
            if (sub[i].compareTo(a[k]) == 0) {
                k++;
                i++;
            } else
                k++;
        }
        return true;
    }

    // append a point
    private Point[] append(Point[] a, Point b) {
        Point[] copy = new Point[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b)
                throw new IllegalArgumentException("append extra points");
            copy[i] = a[i];
        }
        copy[a.length] = b;
        return copy;
    }

    // resize res if it is full.
    private LineSegment[] resize(LineSegment[] r, int num) {
        LineSegment[] copy = new LineSegment[num];
        for (int i = 0; i < r.length; i++)
            copy[i] = r[i];
        return copy;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            // segment.draw();
        }
        StdOut.printf("The number of segments is %d\n", collinear.numberOfSegments());
        StdDraw.show();
    }
}