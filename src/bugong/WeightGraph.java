package bugong;

public interface WeightGraph {

    public int getNodeCount();
    public int getEdgeCount();
    public void addEdge(Edge e);
    public boolean hasEdge(int n, int m);
    public Iterable<Edge> getAdjacentEdges(int n);
}
