import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
/**
 * SAP
 * To find shortest ancestral path
 * @author Jon Xue
 */
public class SAP {
    private Digraph g;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null)
            throw new IllegalArgumentException("G is null");
        g = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v >= g.V() || w < 0 || w >= g.V())
            throw new IllegalArgumentException("v or w is out of graph");
        BreadthFirstDirectedPaths vpath = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths wpath = new BreadthFirstDirectedPaths(g, w);
        int len = -1;
        for (int i = 0; i < g.V(); i++) {
            if (vpath.hasPathTo(i) && wpath.hasPathTo(i) && (vpath.distTo(i) + wpath.distTo(i) < len || len == -1)) {
                len = vpath.distTo(i) + wpath.distTo(i);
            }
        }
        return len;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path;
    // -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || v >= g.V() || w < 0 || w >= g.V())
            throw new IllegalArgumentException("v or w is out of graph");
        BreadthFirstDirectedPaths vpath = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths wpath = new BreadthFirstDirectedPaths(g, w);
        int len = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < g.V(); i++) {
            if (vpath.hasPathTo(i) && wpath.hasPathTo(i) && vpath.distTo(i) + wpath.distTo(i) < len) {
                len = vpath.distTo(i) + wpath.distTo(i);
                ans = i;
            }
        }
        return ans;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in
    // w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new IllegalArgumentException("Argument is null");
        for (Integer i : w)
            if (i == null)
                throw new IllegalArgumentException("Argument is null");
        for (Integer i : v)
            if (i == null)
                throw new IllegalArgumentException("Argument is null");
        BreadthFirstDirectedPaths vpath = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths wpath = new BreadthFirstDirectedPaths(g, w);
        int len = -1;
        for (int i = 0; i < g.V(); i++) {
            if (vpath.hasPathTo(i) && wpath.hasPathTo(i) && (vpath.distTo(i) + wpath.distTo(i) < len || len == -1)) {
                len = vpath.distTo(i) + wpath.distTo(i);
            }
        }
        return len;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such
    // path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new IllegalArgumentException("Argument is null");
        for (Integer i : w)
            if (i == null)
                throw new IllegalArgumentException("Argument is null");
        for (Integer i : v)
            if (i == null)
                throw new IllegalArgumentException("Argument is null");
        BreadthFirstDirectedPaths vpath = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths wpath = new BreadthFirstDirectedPaths(g, w);
        int len = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < g.V(); i++) {
            if (vpath.hasPathTo(i) && wpath.hasPathTo(i) && vpath.distTo(i) + wpath.distTo(i) < len) {
                len = vpath.distTo(i) + wpath.distTo(i);
                ans = i;
            }
        }
        return ans;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}