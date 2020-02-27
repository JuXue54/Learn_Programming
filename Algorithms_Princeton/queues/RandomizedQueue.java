import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] s;

    // construct an empty randomized queue
    public RandomizedQueue() {
        n = 0;
        s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (n == s.length)
            resize(2 * s.length);
        s[n++] = item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = s[i];
        s = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("RandomizedQueue underflow");
        int id = StdRandom.uniform(n);
        Item temp = s[id];
        s[id] = s[n - 1];
        s[--n] = null;
        if (n > 0 && n == s.length / 4)
            resize(s.length / 2);
        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("RandomizedQueue underflow");
        int id = StdRandom.uniform(n);
        return s[id];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int[] id;
        private int tem;

        public ArrayIterator() {
            id = new int[n];
            for (int i = 0; i < n; i++) {
                id[i] = i;
            }
            StdRandom.shuffle(id);
            tem = n;
        }

        public boolean hasNext() {
            return tem > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No element");
            return s[id[--tem]];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> pig = new RandomizedQueue();
        pig.enqueue("I");
        pig.enqueue("am");
        pig.enqueue("Jon");
        pig.enqueue("Xue");
        StdOut.println("delete " + pig.dequeue());
        StdOut.println("sample " + pig.sample());
        for (String i : pig) {
            StdOut.println(i + " ");
        }
    }
}