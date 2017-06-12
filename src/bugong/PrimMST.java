package bugong;

import java.util.Vector;

/**
 * 最小生成树（minimum spanning tree），prim算法实现
 */
public class PrimMST {

    private WeightDenseGraph graph;
    private IndexMinHeap<Integer> priorityQueue;
    private Edge[] edgeTo;
    private boolean[] marked;  // 标记算法运行过程中，节点i是否被访问过
    private Vector<Edge> mst;  // 最小生成树包含的所有边
    private int mstWeight;  // 最小生成树的权值

    public PrimMST(WeightDenseGraph g) {

        graph = g;
        priorityQueue = new IndexMinHeap<Integer>(graph.getNodeCount());
        edgeTo = new Edge[graph.getNodeCount()];
        marked = new boolean[graph.getNodeCount()];
        mst = new Vector<Edge>();

        for (int i = 0; i < graph.getNodeCount(); i++) {

            marked[i] = false;
            edgeTo[i] = null;
        }

        visit(0);

        while (!priorityQueue.isEmpty()) {

            Integer index = priorityQueue.extractMinIndex();

            if (edgeTo[index] != null) {
                mst.add(edgeTo[index]);
            }

            visit(index);
        }

        mstWeight = mst.elementAt(0).getWeight();

        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight + mst.elementAt(i).getWeight();
        }
    }

    private void visit(int n) {

        if (marked[n]) {
            return;
        }

        marked[n] = true;
        Iterable<Edge> edges = graph.getAdjacentEdges(n);

        for (Edge e : edges) {
            int m = e.getOtherPoint(n);

            if (!marked[m]) {

                if (edgeTo[m] == null) {

                    edgeTo[m] = e;
                    priorityQueue.insert(m, e.getWeight());

                } else if (e.getWeight() < edgeTo[m].getWeight()) {

                    edgeTo[m] = e;
                    priorityQueue.change(m , e.getWeight());
                }
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

        PrimMST mst = new PrimMST(g);

        System.out.println("mst edges: " + mst.getMstEdges());
        System.out.println("mst weight: " + mst.getMstWeight());
    }
}
