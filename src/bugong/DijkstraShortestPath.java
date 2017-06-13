package bugong;

import java.util.Vector;

/**
 * 有权图最短路径，dijkstra算法
 */
public class DijkstraShortestPath {

    private WeightDenseGraph graph;
    private int source;
    private int[] distTo;  // 存储起始点到各点最短路径长度
    private boolean[] marked;  // 记录节点是否被访问过
    private Edge[] from;  // 记录最短路径中，到达i点的边是哪一条

    public DijkstraShortestPath(WeightDenseGraph g, int s) {

        graph = g;
        source = s;

        int nodeCount = graph.getNodeCount();

        distTo = new int[nodeCount];
        marked = new boolean[nodeCount];
        from = new Edge[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            distTo[i] = 0;
            marked[i] = false;
            from[i] = null;
        }

        IndexMaxHeap priorityQueue = new IndexMaxHeap(nodeCount);

        distTo[source] = 0;
        from[source] = new Edge(source, source, 0);
        priorityQueue.insert(source, distTo[source]);
        marked[source] = true;

        while (!priorityQueue.isEmpty()) {

            int n = priorityQueue.extractMaxIndex();

            marked[n] = true;

            Iterable<Edge> edges = graph.getAdjacentEdges(n);

            for (Edge e : edges) {
                int m = e.getOtherPoint(n);

                if (!marked[m]) {
                    if (from[m] == null || (distTo[n] + e.getWeight() < distTo[m])) {
                        distTo[m] = distTo[n] + e.getWeight();
                        from[m] = e;

                        if (priorityQueue.contain(m)) {
                            priorityQueue.change(m, distTo[m]);
                        } else {
                            priorityQueue.insert(m, distTo[m]);
                        }
                    }
                }
            }
        }
    }

    public int getShortestPathTo(int n) {

        return distTo[n];
    }

    public boolean hasPathTo(int n) {

        return marked[n];
    }

    public Vector<Edge> getShortestPath(int n) {

        if (!hasPathTo(n)) {
            return null;
        }

        Stack<Edge> stack = new Stack<Edge>();

        Edge e = from[n];

        while (e.getBeginPoint() != source) {
            stack.push(e);
            e = from[e.getBeginPoint()];
        }

        Vector<Edge> result = new Vector<Edge>();

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

        DijkstraShortestPath path = new DijkstraShortestPath(g, 0);

        System.out.println("1 -> 4 shortest path is : " + path.getShortestPath(4));
    }
}
