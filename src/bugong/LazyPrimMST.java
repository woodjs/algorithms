package bugong;

import java.util.Vector;

/**
 * 最小生成树（minimum spanning tree），lazy prim算法实现
 */
public class LazyPrimMST {

    private WeightGraph graph;
    private MinHeap<Edge> priorityQueue;
    private boolean[] marked;  // 标记算法运行过程中，节点i是否被访问过
    private Vector<Edge> mst;  // 最小生成树包含的所有边
    private int mstWeight;  // 最小生成树的权值

    public LazyPrimMST(WeightGraph g) {

        graph = g;
        priorityQueue = new MinHeap<Edge>(graph.getEdgeCount());
        marked = new boolean[graph.getNodeCount()];
        mst = new Vector<Edge>();

        visit(0);

        while (!priorityQueue.isEmpty()) {

            Edge e = priorityQueue.extractMin();

            if (marked[e.getBeginPoint()] && marked[e.getEndPoint()]) {  // 如果该边的两个断点已经被访问过，则丢弃掉该边
                continue;
            }

            mst.add(e);

            if (!marked[e.getBeginPoint()]) {
                visit(e.getBeginPoint());
            } else {
                visit(e.getEndPoint());
            }
        }

        mstWeight = mst.elementAt(0).getWeight();

        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight + mst.elementAt(i).getWeight();
        }
    }

    private void visit(int n) {

        marked[n] = true;
        Iterable<Edge> edges = graph.getAdjacentEdges(n);

        for (Edge e : edges) {
            if (!marked[e.getOtherPoint(n)]) {
                priorityQueue.insert(e);
            }
        }
    }

    public Vector<Edge> getMstEdges() {

        return mst;
    }

    public int getMstWeight() {

        return mstWeight;
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

        LazyPrimMST mst = new LazyPrimMST(g);

        System.out.println("mst edges: " + mst.getMstEdges());
        System.out.println("mst weight: " + mst.getMstWeight());
    }
}
