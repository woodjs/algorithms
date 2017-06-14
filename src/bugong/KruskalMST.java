package bugong;

import java.util.Vector;

/**
 * 最小生成树（minimum spanning tree），kruskal算法实现
 */
public class KruskalMST {

    private WeightGraph graph;
    private MinHeap<Edge> priorityQueue;
    private Vector<Edge> mst;
    private int mstWeight;

    public KruskalMST(WeightGraph g) {

        graph = g;

        mst = new Vector();
        priorityQueue = new MinHeap(graph.getEdgeCount());

        for (int i = 0; i < graph.getNodeCount(); i++) {

            Iterable<Edge> edges = graph.getAdjacentEdges(i);

            for (Edge e : edges) {

                if (e.getBeginPoint() <= e.getEndPoint()) {

                    priorityQueue.insert(e);
                }
            }
        }

        UnionFind uf = new UnionFind(graph.getNodeCount());

        while (!priorityQueue.isEmpty() && mst.size() < graph.getNodeCount() - 1) {

            Edge e = priorityQueue.extractMin();

            if(uf.isConnected(e.getBeginPoint() , e.getEndPoint())) {
                continue;
            }

            mst.add(e);

            uf.unionElements(e.getBeginPoint(), e.getEndPoint());
        }

        mstWeight = mst.elementAt(0).getWeight();

        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight + mst.elementAt(i).getWeight();
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

        KruskalMST mst = new KruskalMST(g);

        System.out.println("mst edges: " + mst.getMstEdges());
        System.out.println("mst weight: " + mst.getMstWeight());
    }
}
