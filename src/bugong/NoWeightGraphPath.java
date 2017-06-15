package bugong;

import java.util.Vector;
/**
 * 无权图，路径
 */
public class NoWeightGraphPath {

    private Graph graph;
    private int source;
    private boolean[] visited;  // 记录dfs过程中，节点是否被访问过
    private int[] from;
    private int[] ord;  // 记录路径中节点的次序，ord[i]表示i节点在路径中的次序

    public NoWeightGraphPath(Graph g, int s) {

        graph = g;
        source = s;

        int nodeCount = graph.getNodeCount();

        visited = new boolean[nodeCount];
        from = new int[nodeCount];
        ord = new int[nodeCount];

        for (int i = 0; i < nodeCount; i++) {

            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }
    }

    public boolean hasPath(int n) {

        return visited[n];
    }

    public Vector<Integer> getPath(int n) {

        dfs(source);

        if (!hasPath(n)) {
            return null;
        }

        Stack<Integer> stack = new Stack();

        int next = n;

        while (next != -1) {
            stack.push(next);
            next = from[next];
        }

        Vector<Integer> result = new Vector();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
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

    public boolean hasShortestPath(int n) {

        return visited[n];
    }

    public Vector<Integer> getShortestPath(int n) {

        bfs();

        if (!hasShortestPath(n)) {
            return null;
        }

        Stack<Integer> stack = new Stack();

        int next = n;

        while (next != -1) {
            stack.push(next);
            next = from[next];
        }

        Vector<Integer> result = new Vector();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    /**
     * 广度优先遍历，无向图最短路径算法
     */
    private void bfs() {

        Queue<Integer> q = new Queue();

        q.enqueue(source);
        visited[source] = true;
        ord[source] = 0;

        while (!q.isEmpty()) {
            int node = q.dequeue();
            Iterable<Integer> nodes = graph.getAdjacentNodes(node);

            for (int i : nodes) {
                if (!visited[i]) {

                    q.enqueue(i);
                    visited[i] = true;
                    from[i] = node;
                    ord[i] = ord[node] + 1;
                }
            }
        }
    }

    public static void main(String[] args) {

        DenseGraph g = new DenseGraph(8, false);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(4, 7);

        NoWeightGraphPath path1 = new NoWeightGraphPath(g, 1);
        NoWeightGraphPath path2 = new NoWeightGraphPath(g, 1);

        System.out.println("1 -> 4 path is : " + path1.getPath(4));
        System.out.println("1 -> 4 shortest path is : " + path2.getShortestPath(4));
    }
}
