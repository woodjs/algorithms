package bugong;

import java.util.Vector;

/**
 * 有权图最短路径，bellman ford算法，无负权环
 */
public class BellmanFordShortestPath {

    private WeightGraph graph;
    private int source;
    private int[] distTo;  // 存储起始点到各点最短路径长度
    private Edge[] from;  // 记录最短路径中，到达i点的边是哪一条
    private boolean hasNegativeCycle;  // 图中是否有负权环

    public BellmanFordShortestPath(WeightGraph g, int s) {

        graph = g;
        source = s;

        int nodeCount = graph.getNodeCount();

        distTo = new int[nodeCount];
        from = new Edge[nodeCount];

        distTo[source] = 0;
        from[source] = new Edge(source, source, 0);

        for (int step = 1; step < nodeCount; step++) {

            for (int i = 0; i < nodeCount; i++) {

                Iterable<Edge> edges = graph.getAdjacentEdges(i);

                for (Edge e : edges) {

                    if (from[e.getBeginPoint()] != null && (from[e.getEndPoint()]  == null || distTo[e.getBeginPoint()] + e.getWeight() < distTo[e.getEndPoint()])) {

                        distTo[e.getEndPoint()] = distTo[e.getBeginPoint()] + e.getWeight();
                        from[e.getEndPoint()] = e;
                    }
                }

            }
        }
    }

    public boolean detectNegativeCycle() {

        for (int i = 0; i < graph.getNodeCount(); i++) {

            Iterable<Edge> edges = graph.getAdjacentEdges(i);

            for (Edge e : edges) {
                if (from[e.getBeginPoint()] != null && (distTo[e.getBeginPoint()] + e.getWeight() < distTo[e.getEndPoint()])) {

                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasNegativeCycle() {

        return hasNegativeCycle;
    }

    public int getShortestPathWeight(int n) {

        return distTo[n];
    }

    public boolean hasPathTo(int n) {

        return from[n] != null;
    }

    public Vector<Edge> getShortestPath(int n) {

        if (hasNegativeCycle) {
            return null;
        }

        if (!hasPathTo(n)) {
            return null;
        }

        Stack<Edge> stack = new Stack();

        Edge e = from[n];

        while (e.getBeginPoint() != source) {
            stack.push(e);
            e = from[e.getBeginPoint()];
        }

        // TODO
        stack.push(e);

        Vector<Edge> result = new Vector();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    public static void main(String[] args) {

        WeightDenseGraph g = new WeightDenseGraph(8, false);

        g.addEdge(new Edge(0, 1, 1));
        g.addEdge(new Edge(0, 2, 1));
        g.addEdge(new Edge(1, 2, 1));
        g.addEdge(new Edge(1, 3, 1));
        g.addEdge(new Edge(2, 3, 1));
        g.addEdge(new Edge(3, 4, 1));
        g.addEdge(new Edge(4, 7, 1));
        g.addEdge(new Edge(1, 7, 1));

        BellmanFordShortestPath path = new BellmanFordShortestPath(g, 0);

        System.out.println("1 -> 7 shortest path is : " + path.getShortestPath(7));
        System.out.println("1 -> 7 shortest path weight : " + path.getShortestPathWeight(7));
    }
}
