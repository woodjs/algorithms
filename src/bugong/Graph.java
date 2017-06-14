package bugong;

public interface Graph {

    public int getNodeCount();
    public int getEdgeCount();
    public void addEdge(int n, int m);
    public boolean hasEdge(int n, int m);
    public Iterable<Integer> getAdjacentNodes(int n);
}
