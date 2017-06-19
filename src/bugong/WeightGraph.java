package bugong;

public interface WeightGraph {

    int getNodeCount();
    int getEdgeCount();
    void addEdge(Edge e);
    boolean hasEdge(int n, int m);
    Iterable<Edge> getAdjacentEdges(int n);
}
