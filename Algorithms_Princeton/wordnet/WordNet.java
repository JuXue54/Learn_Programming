import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

public class WordNet {
    private Digraph g;
    private ST<String, Bag<Integer>> st;
    private String[] noun;
    private SAP sap;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null)
            throw new IllegalArgumentException("Argument is null");
        In synsets_txt = new In(synsets);
        In hypernyms_txt = new In(hypernyms);
        String[] synset = synsets_txt.readAllLines();
        String[] hypernym = hypernyms_txt.readAllLines();
        int num = synset.length;
        noun = new String[num];
        st = new ST<String, Bag<Integer>>();
        g = new Digraph(num);
        int k = 0;
        for (String str : synset) {
            String[] id = str.split(",");
            String[] nouni = id[1].split(" ");
            Integer idnum = Integer.parseInt(id[0]);
            for (int i = 0; i < nouni.length; i++) {
                if (st.contains(nouni[i]))
                    st.get(nouni[i]).add(idnum);
                else {
                    st.put(nouni[i], new Bag<Integer>());
                    st.get(nouni[i]).add(idnum);
                }
            }
            noun[k++] = id[1];
        }

        boolean[] isnotroot = new boolean[num];
        for (String str : hypernym) {
            String[] a = str.split(",");
            isnotroot[Integer.parseInt(a[0])] = true;
            for (int i = 1; i < a.length; i++)
                g.addEdge(Integer.parseInt(a[0]), Integer.parseInt(a[i]));
        }
        int roots = 0;
        for (int i = 0; i < num; i++)
            if (isnotroot[i] == false)
                roots++;
        DirectedCycle cyc = new DirectedCycle(g);
        if (cyc.hasCycle() || roots > 1)
            throw new IllegalArgumentException("It is not a rooted DAG");
        sap = new SAP(g);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return st.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException("Argument is null");
        return st.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("nounA or nounB is not in a worldnet");
        Bag<Integer> a = st.get(nounA);
        Bag<Integer> b = st.get(nounB);
        return sap.length(a, b);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA
    // and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("nounA or nounB is not in a worldnet");
        Bag<Integer> a = st.get(nounA);
        Bag<Integer> b = st.get(nounB);
        int ans = sap.ancestor(a, b);
        return noun[ans];
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }
}