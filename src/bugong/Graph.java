package bugong;

public interface Graph {

    int getNodeCount();
    int getEdgeCount();
    void addEdge(int n, int m);
    boolean hasEdge(int n, int m);
    Iterable<Integer> getAdjacentNodes(int n);
}
