package bugong;

import java.util.Vector;
/**
 * 无权图，路径
 */
public class NoWeightGraphPath {

    private DenseGraph graph;
    private int source;
    private boolean[] visited;  // 记录dfs过程中，节点是否被访问过
    private int[] from;

    public NoWeightGraphPath(DenseGraph g, int s) {

        graph = g;
        source = s;

        int nodeCount = graph.getNodeCount();

        visited = new boolean[nodeCount];
        from = new int[nodeCount];

        for (int i = 0; i < nodeCount; i++) {

            visited[i] = false;
            from[i] = -1;
        }

        dfs(s);
    }

    /**
     * 深度遍历，寻路算法
     */
    private void dfs(int n) {

        Iterable<Integer> nodes = graph.getAdjacentNodes(n);

        visited[n] = true;

        for (int i : nodes) {
            if (!visited[i]) {
                from[i] = n;
                dfs(i);
            }
        }
    }

    public boolean hasPath(int n) {

        return visited[n];
    }

    public Vector<Integer> getPath(int n) {

        if (!hasPath(n)) {
            return null;
        }

        Stack<Integer> stack = new Stack<Integer>();

        int next = n;

        while (next != -1) {
            stack.push(next);
            next = from[next];
        }

        Vector<Integer> result = new Vector<Integer>();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    public static void main(String[] args) {

        DenseGraph g = new DenseGraph(8, false);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(4, 7);

        NoWeightGraphPath path = new NoWeightGraphPath(g, 1);

        System.out.println("1 -> 4 path is : " + path.getPath(4));
    }
}
