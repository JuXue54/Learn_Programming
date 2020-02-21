import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first; // beginning of queue
    private Node<Item> last; // end of queue
    private int n; // number of elements
    // helper linked list class

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> last;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null || last == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        first.last = null;
        if (isEmpty())
            last = first;
        else
            oldfirst.last = first;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.last = oldlast;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())
            last = null;
        else
            first.last = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque underflow");
        Item item = last.item;
        last = last.last;
        n--;
        if (isEmpty())
            first = null;
        else
            last.next = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("No element");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> pig = new Deque<String>();
        pig.addFirst("Jon");
        pig.addLast("Xue");
        pig.addLast("!");
        pig.addFirst("is");
        pig.addFirst("name");
        pig.addFirst("My");
        pig.removeFirst();
        pig.removeFirst();
        pig.removeFirst();
        pig.addFirst("I am");
        pig.removeLast();
        pig.addLast(".");
        for (String item : pig)
            StdOut.print(item + " ");
        if (!pig.isEmpty())
            StdOut.println("\nThere are " + pig.size() + " items in the deque");
        else
            StdOut.println("\nThere are no items in the deque");
    }
}