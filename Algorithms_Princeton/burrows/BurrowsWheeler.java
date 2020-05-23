import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
/**
 * BurrowsWheeler
 * @author Jon Xue
 */
public class BurrowsWheeler {
    private static final int R = 256;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String input = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(input);
        int first = 0;
        int length = input.length();
        for (int i = 0; i < length; i++) {
            if (csa.index(i) == 0)
                first = i;
        }
        BinaryStdOut.write(first);
        for (int i = 0; i < length; i++) {
            int lastid = (csa.index(i) - 1 + length) % length;
            BinaryStdOut.write(input.charAt(lastid));
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String lastCol = BinaryStdIn.readString();
        int length = lastCol.length();
        int[] next = new int[length];
        int[] count = new int[R + 1];
        char[] firstCol = new char[length];
        for (int i = 0; i < length; i++)
            count[lastCol.charAt(i) + 1]++;
        for (int i = 0; i < R; i++)
            count[i + 1] += count[i];
        for (int i = 0; i < length; i++) {
            int posi = count[lastCol.charAt(i)]++;
            firstCol[posi] = lastCol.charAt(i);
            next[posi] = i;
        }
        for (int i = 0; i < length; i++) {
            BinaryStdOut.write(firstCol[first]);
            first = next[first];
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-"))
            BurrowsWheeler.transform();
        if (args[0].equals("+"))
            BurrowsWheeler.inverseTransform();
    }
}