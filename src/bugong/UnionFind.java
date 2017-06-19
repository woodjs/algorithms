package bugong;

/**
 * 并查集
 */
public class UnionFind {

    private int capacity;
    private int[] parent;
    private int[] rank;

    public UnionFind(int capacity) {

        this.capacity = capacity;

        parent = new int[capacity];

        rank = new int[capacity];

        for (int i = 0; i < capacity; i++) {

            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 返回分组号
     */
    public Integer find(int n) {

        if (n < 0 || n >= capacity) return null;

        while (n != parent[n]) {

            n = parent[n];
        }

        return n;
    }

    public boolean isConnected(int n, int m) {

        return find(n) == find(m);
    }

    public void unionElements(int n, int m) {

        int nRoot = find(n);
        int mRoot = find(m);

        if (nRoot == mRoot) return;

        if (rank[nRoot] > rank[mRoot]) {  // 树nRoot比树mRoot高，则设置树mRoot的父节点为nRoot

            parent[mRoot] = nRoot;
        } else if (rank[mRoot] > rank[nRoot]) {

            parent[nRoot] = mRoot;
        }  else {

            parent[mRoot] = nRoot;
            rank[nRoot]++;
        }
    }

    public static void main(String[] args) {

        UnionFind uf = new UnionFind(10);

        System.out.println(uf.find(2));
        System.out.println(uf.find(10));
        System.out.println(uf.find(9));
        System.out.println(uf.isConnected(1, 2));
        uf.unionElements(1, 2);
        System.out.println(uf.isConnected(1, 2));
    }
}
