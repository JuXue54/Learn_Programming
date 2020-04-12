
/**The algorithm WeightedQuickUnionUF is used in this class. 
*n*n+2 elements have been built and the index of element(row,col) is n(row-1)+col.
*Two virtual elements were built in the top and bottom of the grids respectively.
*/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int numofopen; // the number of open site
    private int n; // n-by-n grid
    private int[] idopen; // to see if the site is open.
    private WeightedQuickUnionUF grids; // use WeightedQuickUnionUF
    
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        grids = new WeightedQuickUnionUF(n * n + 2);
        this.n = n;
        numofopen = 0;
        idopen = new int[n * n + 2];
        idopen[0] = 1;
        idopen[n * n + 1] = 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException();
        int site = n * (row - 1) + col;
        if (idopen[site] == 0) {
            numofopen++;
            idopen[site] = 1;
        } else
            return;
        int top = n * (row - 2) + col > 0 ? n * (row - 2) + col : 0;
        int down = n * row + col < n * n + 1 ? n * row + col : n * n + 1;
        if (idopen[top] == 1) {
            grids.union(site, top);
        }
        if (idopen[down] == 1) {
            grids.union(site, down);
        }
        if (idopen[n * (row - 1) + col - 1] == 1 && col != 1) {
            grids.union(site, n * (row - 1) + col - 1);
        }
        if (idopen[n * (row - 1) + col + 1] == 1 && col != n) {
            grids.union(site, n * (row - 1) + col + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException();
        if (idopen[n * (row - 1) + col] == 1)
            return true;
        else
            return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n)
            throw new IllegalArgumentException();
        if (grids.find(n * (row - 1) + col) == grids.find(0))
            return true;
        else
            return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numofopen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (grids.find(0) == grids.find(n * n + 1))
            return true;
        else
            return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 50;
        Percolation metal = new Percolation(n);
        int randrow;
        int randcol;
        while (!metal.percolates()) {
            randrow = StdRandom.uniform(n) + 1;
            randcol = StdRandom.uniform(n) + 1;
            metal.open(randrow, randcol);
        }
        double fraction = metal.numberOfOpenSites() / (double) (n * n);
        System.out.println(fraction);
    }

}