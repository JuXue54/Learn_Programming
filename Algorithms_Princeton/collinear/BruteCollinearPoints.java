import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/**
 * BruteCollinearPoints
 * To find line segments containing only 4 points at a lower rate
 * @author Jon Xue
 */
public class BruteCollinearPoints {
    private Point[] points;
    private LineSegment[] res;
    private int n;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Argument is Null");
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("Points in array is Null");
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
        int len = points.length;
        LineSegment[] r = new LineSegment[1];
        Point[] temp = new Point[4];
        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                for (int k = j + 1; k < len - 1; k++) {
                    if (Math.abs(points[i].slopeTo(points[j]) - points[j].slopeTo(points[k])) > 1E-8)
                        continue;
                    for (int k2 = k + 1; k2 < len; k2++) {
                        if (Math.abs(points[i].slopeTo(points[k]) - points[k].slopeTo(points[k2])) > 1E-8)
                            continue;
                        if (n == r.length)
                            r = resize(r, r.length * 2);
                        temp[0] = points[i];
                        temp[1] = points[j];
                        temp[2] = points[k];
                        temp[3] = points[k2];
                        Arrays.sort(temp);
                        r[n++] = new LineSegment(temp[0], temp[3]);
                    }
                }
            }
        }
        res = Arrays.copyOfRange(r, 0, n);
        return res;
    }

    // resize the res if it is full.
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdOut.printf("The number of segments is %d\n", collinear.numberOfSegments());
        StdDraw.show();
    }
}