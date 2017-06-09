package bugong;

/**
 * 无权图联通分量
 */
public class GraphComponent {

    private DenseGraph graph;
    private int componentCount;
    private int[] componentId;  // 每个节点对应的连通分量id
    private boolean[] visited;  // 记录dfs过程中，节点是否被访问过

    public GraphComponent(DenseGraph g) {

        graph = g;
        componentCount = 0;

        int nodeCount = graph.getNodeCount();

        componentId = new int[nodeCount];
        visited = new boolean[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            componentId[i] = -1;
            visited[i] = false;
        }

        for (int j = 0; j < nodeCount; j++) {
            if (!visited[j]) {
                dfs(j);
                componentCount++;
            }
        }
    }

    private void dfs(int n) {
        Iterable<Integer> nodes = graph.getAdjacentEdges(n);

        visited[n] = true;
        componentId[n] = componentCount;

        for (int i : nodes) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public int getComponentCount() {

        return componentCount;
    }

    public boolean isConnected(int n, int m) {

        return componentId[n] == componentId[m];
    }

    public static void main(String[] args) {

        DenseGraph g = new DenseGraph(8, false);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(4, 7);

        GraphComponent gComponent = new GraphComponent(g);

        System.out.println("graph component count: " + gComponent.getComponentCount());
        System.out.println("2 and 3 isConnected ?: " + gComponent.isConnected(2, 3));
        System.out.println("3 and 4 isConnected ?: " + gComponent.isConnected(3, 4));
    }
}
