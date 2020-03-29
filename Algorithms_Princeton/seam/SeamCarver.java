import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Queue;
//import java.awt.Color;

public class SeamCarver {
    private int[][] rgb;
    private double[][] energy;
    private int V;
    private int width;
    private int height;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException("Argument is null");
        width = picture.width();
        height = picture.height();
        energy = new double[width][height];
        rgb = new int[width][height];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++)
                rgb[i][j] = picture.getRGB(i, j);
        }
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++)
                energy[i][j] = computeEnergy(i, j);
        }
        V = width * height + 2;
    }

    // current picture
    public Picture picture() {
        Picture p = new Picture(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                p.setRGB(i, j, rgb[i][j]);
            }
        }
        return p;
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height())
            throw new IllegalArgumentException("x or y is out of index");
        return energy[x][y];
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        trans();
        int[] list = findVerticalSeam();
        trans();
        return list;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[] distTo = new double[V];
        for (int i = 1; i < V; i++)
            distTo[i] = Double.MAX_VALUE;
        int[] edgeTo = new int[V];
        int[] list = new int[height()];
        int pixel = 0;
        double e;
        while (pixel < V) {
            for (Integer w : adj(pixel, true)) {
                e = 0;
                if (w < V - 1)
                    e = energy((w - 1) % width(), (w - 1) / width());
                if (distTo[w] > distTo[pixel] + e) {
                    distTo[w] = distTo[pixel] + e;
                    edgeTo[w] = pixel;
                }
            }
            pixel++;
        }
        int temp = V - 1;
        for (int i = list.length - 1; i >= 0; i--) {
            temp = edgeTo[temp];
            list[i] = (temp - 1) % width();
        }
        return list;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || seam.length != width())
            throw new IllegalArgumentException("Argument is null or length is wrong");
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] >= height() || seam[i] < 0)
                throw new IllegalArgumentException("out of index");
            if (i > 0) {
                if (seam[i] - seam[i - 1] > 1 || seam[i] - seam[i - 1] < -1)
                    throw new IllegalArgumentException();
            }
        }
        int[][] color = new int[width()][height() - 1];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height - 1; j++) {
                if (j >= seam[i])
                    color[i][j] = rgb[i][j + 1];
                else
                    color[i][j] = rgb[i][j];
            }
        }
        height--;
        rgb = color;
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++)
                energy[i][j] = computeEnergy(i, j);
        }
        V = width() * height() + 2;
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || seam.length != height())
            throw new IllegalArgumentException("Argument is null or length is wrong");
        for (int i = 0; i < seam.length; i++) {
            if (seam[i] >= width() || seam[i] < 0)
                throw new IllegalArgumentException("out of index");
            if (i > 0) {
                if (seam[i] - seam[i - 1] > 1 || seam[i] - seam[i - 1] < -1)
                    throw new IllegalArgumentException();
            }
        }
        int[][] color = new int[width() - 1][height()];
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < color[i].length; j++) {
                if (i >= seam[j])
                    color[i][j] = rgb[i + 1][j];
                else
                    color[i][j] = rgb[i][j];
            }
        }
        width--;
        rgb = color;
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++)
                energy[i][j] = computeEnergy(i, j);
        }
        V = width() * height() + 2;
    }

    private double computeEnergy(int x, int y) {
        if (x == 0 || y == 0 || x == width() - 1 || y == height() - 1)
            return 1000.0;
        int ctop = rgb[x][y - 1];
        int cdown = rgb[x][y + 1];
        int cleft = rgb[x - 1][y];
        int cright = rgb[x + 1][y];
        double square = Math.pow(((ctop >> 16) & 0xFF) - ((cdown >> 16) & 0xFF), 2)
                + Math.pow(((ctop >> 8) & 0xFF) - ((cdown >> 8) & 0xFF), 2)
                + Math.pow(((ctop >> 0) & 0xFF) - ((cdown >> 0) & 0xFF), 2)
                + Math.pow(((cleft >> 16) & 0xFF) - ((cright >> 16) & 0xFF), 2)
                + Math.pow(((cleft >> 8) & 0xFF) - ((cright >> 8) & 0xFF), 2)
                + Math.pow(((cleft >> 0) & 0xFF) - ((cright >> 0) & 0xFF), 2);
        return Math.sqrt(square);
    }

    private void trans() {
        int temp = width;
        width = height;
        height = temp;
        double[][] energyT = new double[width][height];
        int[][] rgbT = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                energyT[i][j] = energy[j][i];
                rgbT[i][j] = rgb[j][i];
            }
        }
        energy = energyT;
        rgb = rgbT;
    }

    private Iterable<Integer> adj(int v, boolean isVertical) {
        Queue<Integer> queue = new Queue<Integer>();
        if (isVertical) {
            if (v == 0) {
                for (int i = 1; i <= width; i++)
                    queue.enqueue(i);
            } else if ((v - 1) / width == height - 1) {
                queue.enqueue(V - 1);
            } else if (v >= V - 1) {
                return queue;
            } else if ((v - 1) % width == 0) {
                queue.enqueue(v + width + 1);
                queue.enqueue(v + width);
            } else if (v % width == 0) {
                queue.enqueue(v + width - 1);
                queue.enqueue(v + width);
            } else {
                queue.enqueue(v + width - 1);
                queue.enqueue(v + width + 1);
                queue.enqueue(v + width);
            }
        } else {
            if (v == 0) {
                for (int i = 0; i < height; i++)
                    queue.enqueue(i * width + 1);
            } else if (v % width == 0) {
                queue.enqueue(V - 1);
            } else if (v >= V - 1) {
                return queue;
            } else if ((v - 1) / width == 0) {
                queue.enqueue(v + 1);
                queue.enqueue(v + width + 1);
            } else if ((v - 1) / width == height() - 1) {
                queue.enqueue(v - width + 1);
                queue.enqueue(v + 1);
            } else {
                queue.enqueue(v - width + 1);
                queue.enqueue(v + width + 1);
                queue.enqueue(v + 1);
            }
        }
        return queue;
    }

    // unit testing (optional)
    public static void main(String[] args) {
        // In in=new In(args[0]);
        Picture p = new Picture(args[0]);
        SeamCarver s = new SeamCarver(p);
        int[] r = s.findHorizontalSeam();
        for (int i = 0; i < r.length; i++) {
            System.out.printf("%d, ", r[i]);
        }
        s.removeHorizontalSeam(r);
        r = s.findHorizontalSeam();
        for (int i = 0; i < r.length; i++) {
            System.out.printf("%d, ", r[i]);
        }
    }
}