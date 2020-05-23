import java.util.Arrays;
import java.util.Comparator;
/**
 * CircularSuffixArray
 * @author Jon Xue
 */
public class CircularSuffixArray {
    private Integer[] index;
    private int length;
    private char[] ch;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException("s is null");
        length = s.length();
        ch = new char[length];
        index = new Integer[length];
        for (int i = 0; i < length; i++) {
            ch[i] = s.charAt(i);
            index[i] = Integer.valueOf(i);
        }
        Arrays.sort(index, new Comparator<Integer>() {
            public int compare(Integer id1, Integer id2) {
                for (int i = 0; i < ch.length; i++) {
                    char c1 = ch[(i + id1) % length];
                    char c2 = ch[(i + id2) % length];
                    if (c1 < c2)
                        return -1;
                    else if (c1 > c2)
                        return 1;
                }
                return 0;
            }
        });

    }

    // length of s
    public int length() {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i >= length || i < 0)
            throw new IllegalArgumentException();
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        String s = "ABRACADABRA!";
        CircularSuffixArray csa = new CircularSuffixArray(s);
        for (int i = 0; i < csa.length(); i++) {
            System.out.print(csa.index(i) + " ");
        }
    }
}