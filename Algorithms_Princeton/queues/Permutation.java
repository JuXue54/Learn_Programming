import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> pig = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String porks = StdIn.readString();
            if (!porks.equals("-"))
                pig.enqueue(porks);
            else
                break;
        }
        for (int i = 0; i < k; i++) {
            StdOut.print(pig.dequeue() + " ");
        }
    }
}