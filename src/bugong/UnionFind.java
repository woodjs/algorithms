package bugong;

/**
 * 并查集
 */
public class UnionFind {

    private int count;
    private int[] parent;
    private int[] rank;

    public UnionFind(int count) {

        this.count = count;

        parent = new int[count];

        rank = new int[count];

        for (int i = 0; i < count; i++) {

            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 返回分组号
     *
     * @param p
     * @return
     */
    public Integer find(int p) {

        if (p < 0 || p >= count) return null;

        while (p != parent[p]) {

            p = parent[p];
        }

        return p;
    }

    public boolean isConnected(int p, int q) {

        return find(p) == find(q);
    }

    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (rank[pRoot] > rank[qRoot]) {

            parent[qRoot] = pRoot;
        } else if (rank[pRoot] < rank[qRoot]) {

            parent[pRoot] = qRoot;
        }  else {

            parent[qRoot] = pRoot;

            rank[pRoot]++;
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
