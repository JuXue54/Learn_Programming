import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;
/**
 * BoggleSolver
 * To find all valid words in a given boggle board
 * @author Jon Xue
 */
public class BoggleSolver {
    private static final int R = 26;
    private Node root;
    private BoggleBoard board;
    private Adj[] adj;
    private int r;
    private int c;
    private boolean[] marked;

    private static class Node {
        private Node[] next = new Node[R];
        private boolean isString;
    }

    private static class Adj {
        private int num = 0;
        private int[] n_r = new int[8];
        private int[] n_c = new int[8];
    }

    // Initializes the data structure using the given array of strings as the
    // dictionary.
    // (You can assume each word in the dictionary contains only the uppercase
    // letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        if (dictionary == null)
            throw new IllegalArgumentException();
        for (int i = 0; i < dictionary.length; i++) {
            add(dictionary[i]);
        }
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        if (board == null)
            throw new IllegalArgumentException();
        r = board.rows();
        c = board.cols();
        this.board = board;
        computeAdj();
        SET<String> words = DFS();
        return words;
    }

    // Returns the score of the given word if it is in the dictionary, zero
    // otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (word == null)
            throw new IllegalArgumentException("the argument to scoreOf() is null\n");
        if (!contains(word))
            return 0;
        else if (word.length() < 3)
            return 0;
        else if (word.length() < 5)
            return 1;
        else if (word.length() == 5)
            return 2;
        else if (word.length() == 6)
            return 3;
        else if (word.length() == 7)
            return 5;
        else
            return 11;
    }

    private boolean contains(String key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        Node x = get(root, key, 0);
        if (x == null)
            return false;
        return x.isString;
    }

    private Node get(Node x, String key, int d) {
        if (x == null)
            return null;
        if (d == key.length())
            return x;
        char c = key.charAt(d);
        return get(x.next[c - 'A'], key, d + 1);
    }

    private void add(String key) {
        if (key == null)
            throw new IllegalArgumentException("argument to add() is null");
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null)
            x = new Node();
        if (d == key.length()) {
            x.isString = true;
        } else {
            char c = key.charAt(d);
            x.next[c - 'A'] = add(x.next[c - 'A'], key, d + 1);
        }
        return x;
    }

    private SET<String> DFS() {
        SET<String> words = new SET<String>();
        int num = c * r;
        for (int i = 0; i < num; i++) {
            marked = new boolean[num];
            StringBuilder sb = new StringBuilder();
            DFS(i / c, i % c, sb, words, root);
        }
        return words;
    }

    private void DFS(int i, int j, StringBuilder sb, SET<String> words, Node x) {
        char ch = board.getLetter(i, j);
        Node next = x.next[ch - 'A'];
        if (ch == 'Q' && next != null)
            next = next.next['U' - 'A'];
        if (next == null)
            return;
        if (ch == 'Q')
            sb.append("QU");
        else
            sb.append(ch);
        marked[i * c + j] = true;
        String str = sb.toString();
        if (next.isString && str.length() > 2) {
            words.add(str);
        }
        for (int k = 0; k < adj[i * c + j].num; k++) {
            int ii = adj[i * c + j].n_r[k];
            int jj = adj[i * c + j].n_c[k];
            if (!marked[ii * c + jj])
                DFS(ii, jj, new StringBuilder(str), words, next);
        }
        marked[i * c + j] = false;
    }

    private void computeAdj() {
        adj = new Adj[c * r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int k = i * c + j;
                adj[k] = new Adj();
                if (i > 0) {
                    adj[k].n_r[adj[k].num] = i - 1;
                    adj[k].n_c[adj[k].num++] = j;
                    if (j > 0) {
                        adj[k].n_r[adj[k].num] = i - 1;
                        adj[k].n_c[adj[k].num++] = j - 1;
                    }
                    if (j < c - 1) {
                        adj[k].n_r[adj[k].num] = i - 1;
                        adj[k].n_c[adj[k].num++] = j + 1;
                    }
                }
                if (i < r - 1) {
                    adj[k].n_r[adj[k].num] = i + 1;
                    adj[k].n_c[adj[k].num++] = j;
                    if (j > 0) {
                        adj[k].n_r[adj[k].num] = i + 1;
                        adj[k].n_c[adj[k].num++] = j - 1;
                    }
                    if (j < c - 1) {
                        adj[k].n_r[adj[k].num] = i + 1;
                        adj[k].n_c[adj[k].num++] = j + 1;
                    }
                }
                if (j > 0) {
                    adj[k].n_r[adj[k].num] = i;
                    adj[k].n_c[adj[k].num++] = j - 1;
                }
                if (j < c - 1) {
                    adj[k].n_r[adj[k].num] = i;
                    adj[k].n_c[adj[k].num++] = j + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
    }
}
