import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
/**
 * BaseballElimination
 * 
 * @author Jon Xue
 */
public class BaseballElimination {
    private int n;
    private ST<String, Integer> team;
    private String[] teamname;
    private int[] w;
    private int[] l;
    private int[] r;
    private int[][] g;
    private int remaingame;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);
        n = in.readInt();
        w = new int[n];
        l = new int[n];
        r = new int[n];
        g = new int[n][n];
        team = new ST<String, Integer>();
        teamname = new String[n];
        for (int i = 0; i < n; i++) {
            teamname[i] = in.readString();
            // StdOut.println(teamname[i]);
            team.put(teamname[i], Integer.valueOf(i));
            w[i] = in.readInt();
            l[i] = in.readInt();
            r[i] = in.readInt();
            for (int j = 0; j < n; j++)
                g[i][j] = in.readInt();
        }

    }

    // number of teams
    public int numberOfTeams() {
        return n;
    }

    // all teams
    public Iterable<String> teams() {
        return team.keys();
    }

    // number of wins for given team
    public int wins(String team) {
        if (!this.team.contains(team))
            throw new IllegalArgumentException();
        int i = this.team.get(team).intValue();
        return w[i];
    }

    // number of losses for given team
    public int losses(String team) {
        if (!this.team.contains(team))
            throw new IllegalArgumentException();
        int i = this.team.get(team).intValue();
        return l[i];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        if (!this.team.contains(team))
            throw new IllegalArgumentException();
        int i = this.team.get(team).intValue();
        return r[i];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (!this.team.contains(team1) || !this.team.contains(team2))
            throw new IllegalArgumentException();
        int i = this.team.get(team1).intValue();
        int j = this.team.get(team2).intValue();
        return g[i][j];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (!this.team.contains(team))
            throw new IllegalArgumentException();
        int k = this.team.get(team).intValue();
        for (int i = 0; i < n; i++) {
            if (i != k && w[k] + r[k] < w[i])
                return true;
        }
        FlowNetwork f = createFlowNetwork(k);
        FordFulkerson ford = new FordFulkerson(f, 0, f.V() - 1);
        /*
         * for (FlowEdge e : f.adj(0)) { if (e.capacity() != e.flow()) return true; }
         */
        return ford.value() < remaingame;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (!this.team.contains(team))
            throw new IllegalArgumentException();
        boolean isEliminated = false;
        ST<String, Integer> st = new ST<String, Integer>();
        int k = this.team.get(team).intValue();
        for (int i = 0; i < n; i++) {
            if (i != k && w[k] + r[k] < w[i]) {
                isEliminated = true;
                st.put(teamname[i], i);
            }
        }
        if (isEliminated)
            return st.keys();
        FlowNetwork f = createFlowNetwork(k);
        FordFulkerson ford = new FordFulkerson(f, 0, f.V() - 1);
        if (ford.value() < remaingame)
            isEliminated = true;
        for (int i = 0; i < n; i++) {
            if (ford.inCut(i + 1) && i < k) {
                st.put(teamname[i], i);
            } else if (ford.inCut(i) && i > k) {
                st.put(teamname[i], i);
            }
        }
        if (isEliminated)
            return st.keys();
        return null;
    }

    private FlowNetwork createFlowNetwork(int k) {
        FlowNetwork f = new FlowNetwork((n * n - n + 4) / 2);
        int id = n;
        remaingame = 0;
        for (int i = 0; i < n; i++) {
            if (i == k)
                continue;
            else if (i < k)
                f.addEdge(new FlowEdge(i + 1, f.V() - 1, w[k] + r[k] - w[i]));
            else
                f.addEdge(new FlowEdge(i, f.V() - 1, w[k] + r[k] - w[i]));
            for (int j = i + 1; j < n; j++) {
                if (j == k)
                    continue;
                if (j < k)
                    f.addEdge(new FlowEdge(id, j + 1, Double.POSITIVE_INFINITY));
                else
                    f.addEdge(new FlowEdge(id, j, Double.POSITIVE_INFINITY));
                if (i < k)
                    f.addEdge(new FlowEdge(id, i + 1, Double.POSITIVE_INFINITY));
                else
                    f.addEdge(new FlowEdge(id, i, Double.POSITIVE_INFINITY));
                remaingame += g[i][j];
                f.addEdge(new FlowEdge(0, id++, g[i][j]));
            }
        }
        assert id == f.V() - 1;
        return f;
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}