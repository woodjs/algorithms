package bugong;

public class Edge implements Comparable<Edge> {

    private int n, m;  // 边的两个端点
    private int weight;  // 边的权值

    public Edge(int a, int b, int w) {

        n = a;
        m = b;
        weight = w;
    }

    public Edge(Edge e) {

        n = e.n;
        m = e.m;
        weight = e.weight;
    }

    public int getBeginPoint() {

        return n;
    }

    public int getEndPoint() {

        return m;
    }

    public int getWeight() {

        return weight;
    }

    public int getOtherPoint(int node) {

        return node == n ? m : n;
    }

    public String toString() {

        return "" + n + " - " + m + ": " + weight;
    }

    public int compareTo(Edge e) {

        int temp =  e.getWeight();

        if (weight < temp) {
            return -1;
        } else if (weight > temp) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {

        Edge e1 = new Edge(1, 3, 5);
        Edge e2 = new Edge(1, 2, 4);
        Edge e3 = new Edge(e2);

        System.out.println("edge1: " + e1.toString());
        System.out.println("edge2: " + e2.toString());
        System.out.println("edge2: " + e3.toString());
        System.out.println("edge1 compareTo edge2: " + e1.compareTo(e2));
        System.out.println("edge2 compareTo edge3: " + e2.compareTo(e3));
        System.out.println("edge1 begin point: " + e1.getBeginPoint());
        System.out.println("edge1 end point: " + e1.getEndPoint());
        System.out.println("edge1 other point of 1: " + e1.getOtherPoint(1));
    }
}
