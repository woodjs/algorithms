package bugong;

import java.util.Vector;

/**
 * 稀疏图（邻接表）
 */
public class SparseGraph implements Graph {

    private int nodeCount;
    private int edgeCount;
    private boolean isDirected;  // 是否为有向图
    private Vector<Integer>[] graph;

    public SparseGraph(int num, boolean isDirected) {

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

    public void addEdge(int n, int m) {

        if (hasEdge(n, m)) {
            return;
        }

        graph[n].add(m);

        if (!isDirected) {
            graph[m].add(n);
        }

        edgeCount++;
    }

    public boolean hasEdge(int n, int m) {

        for (int i = 0; i < graph[n].size(); i++) {

            if (graph[n].elementAt(i) == m) {
                return true;
            }
        }

        return false;
    }

    public Iterable<Integer> getAdjacentNodes(int n) {

        return graph[n];
    }

    public static void main(String[] args) {

        SparseGraph g = new SparseGraph(8, false);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(4, 7);

        System.out.println("0, 1 has edge: " + g.hasEdge(0, 1));
        System.out.println("2, 0 has edge: " + g.hasEdge(2, 0));
        System.out.println("3, 4 has edge: " + g.hasEdge(3, 4));
        System.out.println("5, 7 has edge: " + g.hasEdge(5, 7));
        System.out.println("node count: " + g.getNodeCount());
        System.out.println("edge count: " + g.getEdgeCount());
        System.out.println("adjacent nodes of 1: " + g.getAdjacentNodes(0));
    }
}
