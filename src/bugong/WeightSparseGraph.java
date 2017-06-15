package bugong;

import java.util.Vector;

/**
 * 有权稀疏图（邻接表）
 */
public class WeightSparseGraph implements WeightGraph {

    private int nodeCount;
    private int edgeCount;
    private boolean isDirected;  // 是否为有向图
    private Vector<Edge>[] graph;

    public WeightSparseGraph(int num, boolean isDirected) {

        nodeCount = num;
        edgeCount = 0;
        this.isDirected = isDirected;
        graph = new Vector[num];

        for (int i = 0; i < num; i++) {
            graph[i] = new Vector();
        }
    }

    public int getNodeCount() {

        return nodeCount;
    }

    public int getEdgeCount() {

        return edgeCount;
    }

    public void addEdge(Edge e) {

        int n = e.getBeginPoint();
        int m = e.getEndPoint();
        int w = e.getWeight();

        if (hasEdge(n, m)) {
            return;
        }

        graph[n].add(new Edge(n, m, w));

        if (!isDirected) {
            graph[m].add(new Edge(m, n, w));
        }

        edgeCount++;
    }

    public boolean hasEdge(int n, int m) {

        for (int i = 0; i < graph[n].size(); i++) {

            if (graph[n].elementAt(i).getOtherPoint(n) == m) {
                return true;
            }
        }

        return false;
    }

    public Iterable<Edge> getAdjacentEdges(int n) {

        return graph[n];
    }

    public static void main(String[] args) {

        WeightSparseGraph g = new WeightSparseGraph(8, false);

        g.addEdge(new Edge(0, 1, 1));
        g.addEdge(new Edge(0, 2, 1));
        g.addEdge(new Edge(1, 2, 1));
        g.addEdge(new Edge(3, 4, 1));
        g.addEdge(new Edge(4, 7, 1));

        System.out.println("0, 1 has edge: " + g.hasEdge(0, 1));
        System.out.println("2, 0 has edge: " + g.hasEdge(2, 0));
        System.out.println("3, 4 has edge: " + g.hasEdge(3, 4));
        System.out.println("5, 7 has edge: " + g.hasEdge(5, 7));
        System.out.println("node count: " + g.getNodeCount());
        System.out.println("edge count: " + g.getEdgeCount());
        System.out.println("adjacent nodes of 0: " + g.getAdjacentEdges(0));
    }
}
