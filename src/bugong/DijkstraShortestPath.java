package bugong;

import java.util.Vector;

/**
 * 有权图最短路径，dijkstra算法，无负权边
 */
public class DijkstraShortestPath {

    private WeightGraph graph;
    private int source;
    private int[] distTo;  // 存储起始点到各点最短路径长度
    private boolean[] marked;  // 记录节点是否被访问过
    private Edge[] from;  // 记录最短路径中，到达i点的边是哪一条

    public DijkstraShortestPath(WeightGraph g, int s) {

        graph = g;
        source = s;

        int nodeCount = graph.getNodeCount();

        distTo = new int[nodeCount];
        marked = new boolean[nodeCount];
        from = new Edge[nodeCount];

        IndexMinHeap priorityQueue = new IndexMinHeap(nodeCount);

        distTo[source] = 0;
        from[source] = new Edge(source, source, 0);
        priorityQueue.insert(source, distTo[source]);
        marked[source] = true;

        while (!priorityQueue.isEmpty()) {

            int n = priorityQueue.extractMinIndex();

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

    public int getShortestPathWeight(int n) {

        return distTo[n];
    }

    public boolean hasPathTo(int n) {

        return marked[n];
    }

    public Vector<Edge> getShortestPath(int n) {

        if (!hasPathTo(n)) {
            return null;
        }

        Stack<Edge> stack = new Stack();

        Edge e = from[n];

        while (e.getBeginPoint() != source) {
            stack.push(e);
            e = from[e.getBeginPoint()];
        }

        stack.push(e);  // 起始点为源点的边

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

        DijkstraShortestPath path = new DijkstraShortestPath(g, 0);

        System.out.println("1 -> 4 shortest path is : " + path.getShortestPath(4));
        System.out.println("1 -> 4 shortest path weight : " + path.getShortestPathWeight(4));
    }
}
