import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256;

    // apply move-to-front encoding, reading from standard input and writing to
    // standard output
    public static void encode() {
        char[] alphabet = new char[R];
        for (int i = 0; i < R; i++)
            alphabet[i] = (char) i;
        String input = BinaryStdIn.readString();
        int index;
        for (int i = 0; i < input.length(); i++) {
            index = move(alphabet, input.charAt(i));
            BinaryStdOut.write((char) index);
        }
        BinaryStdOut.close();
    }

    private static int move(char[] alphabet, char c) {
        int i;
        for (i = 0; i < R; i++) {
            if (alphabet[i] == c) {
                for (int j = i; j > 0; j--) {
                    alphabet[j] = alphabet[j - 1];
                }
                alphabet[0] = c;
                break;
            }
        }
        return i;
    }

    // apply move-to-front decoding, reading from standard input and writing to
    // standard output
    public static void decode() {
        char[] alphabet = new char[R];
        for (int i = 0; i < R; i++)
            alphabet[i] = (char) i;
        while (!BinaryStdIn.isEmpty()) {
            int input = BinaryStdIn.readInt(8);
            BinaryStdOut.write(alphabet[input]);
            move(alphabet, alphabet[input]);
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-"))
            MoveToFront.encode();
        if (args[0].equals("+"))
            MoveToFront.decode();
    }
}