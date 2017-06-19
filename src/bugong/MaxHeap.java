package bugong;

/**
 * 最大堆
 */
public class MaxHeap<Item extends Comparable> {

    private int count = 0;
    private int capacity;
    private Item[] data;

    public MaxHeap(int capacity) {

        data = (Item[])new Comparable[capacity];
        this.capacity = capacity;
    }

    public MaxHeap(Item[] arr) {  // heapify

        int len = arr.length;

        data = (Item[])new Comparable[len];
        this.capacity = len;

        for (int i = 0; i < len; i++) {
            data[i] = arr[i];
        }

        count = len;

        for (int j = count / 2 - 1; j >= 0; j--) {  // count / 2 - 1，最后一个非叶子节点，在数组中的索引
            shiftDown(j);
        }
    }

    public int size() {

        return count;
    }

    public boolean isEmpty() {

        return count == 0;
    }

    public void insert(Item item) {

        if (count >= capacity) return;

        data[count++] = item;

        shiftUp(count - 1);
    }

    public Item extractMax() {

        if (count == 0) return null;

        Item maxItem = data[0];

        new Helper().swap(data, 0, count - 1);

        count--;

        shiftDown(0);

        return maxItem;
    }

    public Item getMax() {

        if (count == 0) return null;

        return data[0];
    }

    private void shiftUp(int index) {

        int next;  // 父节点，(index - 1) / 2
        Helper helper = new Helper();

        while ((index > 0) && data[index].compareTo(data[next = (index - 1) / 2]) > 0) {

            helper.swap(data, index, next);

            index = next;
        }
    }

    private void shiftDown(int index) {

        int next;
        Helper helper = new Helper();

        while ((next = (2 * index + 1)) < count) {  // 左孩子，2 * index + 1

            if (next + 1 < count && data[next + 1].compareTo(data[next]) > 0) {  // 判断右孩子是否大于左孩子
                next = next + 1;
            }

            if (data[index].compareTo(data[next]) >= 0) break;

            helper.swap(data, index, next);

            index = next;
        }
    }

    public static void main(String[] args) {

        MaxHeap heap = new MaxHeap(100);

        System.out.println("maxheap is empty: " + heap.isEmpty());

        heap.insert(10);
        heap.insert(203);
        heap.insert(13);
        heap.insert(850);
        heap.insert(140);

        System.out.println("maxheap is empty: " + heap.isEmpty());
        System.out.println("size of maxheap: " + heap.size());
        System.out.println("current max of maxheap: " + heap.getMax());
        System.out.println("extract max of maxheap: " + heap.extractMax());
        System.out.println("size of maxheap: " + heap.size());
        System.out.println("current max of maxheap: " + heap.getMax());
        System.out.println("extract max of maxheap: " + heap.extractMax());
        System.out.println("size of maxheap: " + heap.size());
        System.out.println("current max of maxheap: " + heap.getMax());

        Integer[] arr= new Integer[]{1, 32, 43, 5, 511, 4};

        MaxHeap heap2 = new MaxHeap(arr);

        System.out.println("current max of heap2: " + heap2.getMax());
    }
}
