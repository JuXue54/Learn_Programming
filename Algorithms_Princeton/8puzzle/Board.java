import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int[][] tiles;
    private final int n;
    private int ham;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                this.tiles[i][j] = tiles[i][j];
        ham = -1;
    }

    // string representation of this board
    public String toString() {
        var str = new StringBuilder(2 * n * n + n + 1);
        str.append(n);
        for (int i = 0; i < n; i++) {
            str.append("\n");
            for (int j = 0; j < n; j++)
                str.append(String.format("%3d ", tiles[i][j]));
        }
        str.append("\n");
        return str.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        ham = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != i * n + j + 1 && tiles[i][j] != 0)
                    ham++;
            }
        }
        return ham;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int distance = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != i * n + j + 1 && tiles[i][j] != 0)
                    distance += Math.abs((tiles[i][j] - 1) / n - i) + Math.abs((tiles[i][j] - 1) % n - j);
            }
        }
        return distance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        if (ham == -1)
            return (hamming() == 0);
        else
            return (ham == 0);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof Board))
            return false;
        if (this.n != ((Board) y).n)
            return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != ((Board) y).tiles[i][j])
                    return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int i0 = 0;
        int j0 = 0;
        Stack<Board> neighbor = new Stack<Board>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                    break;
                }
            }
            if (tiles[i0][j0] == 0) {
                break;
            }
        }
        if (i0 != 0)
            neighbor.push(exch(tiles, i0, j0, i0 - 1, j0));
        if (i0 != n - 1)
            neighbor.push(exch(tiles, i0, j0, i0 + 1, j0));
        if (j0 != 0)
            neighbor.push(exch(tiles, i0, j0, i0, j0 - 1));
        if (j0 != n - 1)
            neighbor.push(exch(tiles, i0, j0, i0, j0 + 1));
        return neighbor;
    }

    // change the tile, return a new Board
    private Board exch(int[][] input, int oi, int oj, int ni, int nj) {
        int temp = input[oi][oj];
        input[oi][oj] = input[ni][nj];
        input[ni][nj] = temp;
        Board neighbor = new Board(input);
        input[ni][nj] = input[oi][oj];
        input[oi][oj] = temp;
        return neighbor;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (tiles[0][0] != 0 && tiles[0][1] != 0)
            return exch(tiles, 0, 0, 0, 1);
        else
            return exch(tiles, 1, 0, 1, 1);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] a = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = i * 3 + j;
            }
        }
        int[][] mat = new int[][] { { 1, 2, 3 }, { 4, 0, 5 }, { 7, 8, 9 } };
        Board b = new Board(mat);
        Board c = null;
        StdOut.print(b);
        StdOut.printf(
                "The dimension is %d,\nhamming is %d,\nmanhattan is %d,\nIs it equals to null?:%s\nThe twin is %s\n",
                b.dimension(), b.hamming(), b.manhattan(), b.equals(c), b.twin());
        for (Board neighbor : b.neighbors()) {
            StdOut.print(neighbor);
        }
    }
}