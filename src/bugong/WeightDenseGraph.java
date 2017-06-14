package bugong;

import java.util.Vector;

/**
 * 有权稠密图（邻接矩阵）
 */
public class WeightDenseGraph implements WeightGraph {

    private int nodeCount;
    private int edgeCount;
    private boolean isDirected;  // 是否为有向图
    private Edge[][] graph;

    public WeightDenseGraph(int num, boolean isDirected) {

        nodeCount = num;
        edgeCount = 0;
        this.isDirected = isDirected;
        graph = new Edge[num][num];
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

        graph[n][m] = new Edge(n, m, w);

        if (!isDirected) {
            graph[m][n] = new Edge(m, n, w);
        }

        edgeCount++;
    }

    public boolean hasEdge(int n, int m) {

        return graph[n][m] != null;
    }

    public Iterable<Edge> getAdjacentEdges(int n) {

        Vector<Edge> adjNodes = new Vector<Edge>();

        for (int i = 0; i < nodeCount; i++) {

            if (graph[n][i] != null) {
                adjNodes.add(graph[n][i]);
            }
        }

        return adjNodes;
    }

    public static void main(String[] args) {

        WeightDenseGraph g = new WeightDenseGraph(8, false);

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
