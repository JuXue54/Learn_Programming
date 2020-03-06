import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Comparator;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    //private final Comparator<Node> BY_HAM = new HamComparator();
    private final Comparator<Node> BY_MAN = new ManComparator();
    private int move;
    private boolean issolve;
    private Node currentNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null)
            throw new IllegalArgumentException("The initial Point is null");
        currentNode = new Node(initial, null, 0);
        Node currentTwin = new Node(initial.twin(), null, 0);
        MinPQ<Node> queue = new MinPQ<Node>(BY_MAN);
        MinPQ<Node> twinqueue = new MinPQ<Node>(BY_MAN);
        move = 0;
        int moveTwin = 0;
        while (!(currentNode.current.isGoal() || currentTwin.current.isGoal())) {
            for (Board neighbor : currentNode.current.neighbors()) {
                if (!((currentNode.previous != null) && (neighbor.equals(currentNode.previous.current))))
                    queue.insert(new Node(neighbor, currentNode, move + 1));
            }
            for (Board neighborT : currentTwin.current.neighbors()) {
                if (!((currentTwin.previous != null) && (neighborT.equals(currentTwin.previous.current))))
                    twinqueue.insert(new Node(neighborT, currentTwin, moveTwin + 1));
            }
            currentNode = queue.delMin();
            currentTwin = twinqueue.delMin();
            move = currentNode.moves;
            moveTwin = currentTwin.moves;
        }
        if (currentNode.current.isGoal())
            issolve = true;
        else {
            issolve = false;
            move = -1;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return issolve;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (!issolve)
            return -1;
        return move;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        Stack<Board> results = new Stack<Board>();
        Node temp;
        if (issolve)
            temp = currentNode;
        else
            return null;
        while (temp != null) {
            results.push(temp.current);
            temp = temp.previous;
        }
        return results;
    }

    private class Node implements Comparable<Node> {
        private Board current;
        private Node previous;
        private int moves;
        private int hamprior;
        private int manprior;

        Node(Board input, Node previous, int moves) {
            current = input;
            this.previous = previous;
            this.moves = moves;
            hamprior = moves + current.hamming();
            manprior = moves + current.manhattan();
        }

        public int compareTo(Node that) {
            return Integer.compare(this.manprior, that.manprior);
        }
    }

    private class ManComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.manprior, o2.manprior);
        }
    }

    private class HamComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.hamprior, o2.hamprior);
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}