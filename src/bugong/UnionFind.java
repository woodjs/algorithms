package bugong;

public class UnionFind {

    private int count;
    private int[] ids;

    public UnionFind(int capacity) {

        count = capacity;

        ids = new int[capacity];

        for (int i = 0; i < capacity; i++) {
            ids[i] = i;
        }
    }

    public Integer find(int p) {

        if (p < 0 || p >= count) return null;

        return ids[p];
    }

    public boolean isConnected(int p, int q) {

        return find(p) == find(q);
    }

    public void unionElements(int p, int q) {

        int pId = ids[p];
        int qId = ids[q];

        if (pId == qId) return;

        for (int i =  0; i < count; i++) {
            if (ids[i] == pId) ids[i] = qId;
        }
    }

    public static void main(String[] args) {

        UnionFind uf = new UnionFind(10);

        System.out.println(uf.find(2));
        System.out.println(uf.find(10));
        System.out.println(uf.find(9));
        System.out.println(uf.isConnected(1, 2));

        System.out.println(uf.isConnected(1, 2));
    }
}
