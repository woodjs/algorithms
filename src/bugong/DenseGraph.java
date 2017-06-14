package bugong;

import java.util.Vector;

/**
 * 稠密图（邻接矩阵）
 */
public class DenseGraph implements Graph {

    private int nodeCount;
    private int edgeCount;
    private boolean isDirected;  // 是否为有向图
    private boolean[][] graph;

    public DenseGraph(int num, boolean isDirected) {

        nodeCount = num;
        edgeCount = 0;
        this.isDirected = isDirected;
        graph = new boolean[num][num];
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

        graph[n][m] = true;

        if (!isDirected) {
            graph[m][n] = true;
        }

        edgeCount++;
    }

    public boolean hasEdge(int n, int m) {

        return graph[n][m];
    }

    public Iterable<Integer> getAdjacentNodes(int n) {

        Vector<Integer> adjNodes = new Vector<Integer>();

        for (int i = 0; i < nodeCount; i++) {

            if (graph[n][i]) {
                adjNodes.add(i);
            }
        }

        return adjNodes;
    }

    public static void main(String[] args) {

        DenseGraph g = new DenseGraph(8, false);

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
        System.out.println("adjacent nodes of 0: " + g.getAdjacentNodes(0));
    }
}
