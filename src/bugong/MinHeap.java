package bugong;

/**
 * 最小堆
 */
public class MinHeap<Item extends Comparable> {

    private int count = 0;
    private int capacity;
    private Item[] data;

    public MinHeap(int capacity) {

        data = (Item[])new Comparable[capacity];
        this.capacity = capacity;
    }

    public MinHeap(Item[] arr) {  // heapify

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

        data[count] = item;

        count++;

        shiftUp(count - 1);
    }

    public Item extractMin() {

        if (count == 0) return null;

        Item minItem = data[0];

        new Helper().swap(data, 0, count - 1);

        count--;

        shiftDown(0);

        return minItem;
    }

    public Item getMin() {

        if (count == 0) return null;

        return data[0];
    }

    private void shiftUp(int index) {

        int next;  // 父节点，(index - 1) / 2
        Helper helper = new Helper();

        while ((index > 0) && (data[index].compareTo(data[next = (index - 1) / 2]) < 0)) {

            helper.swap(data, index, next);

            index = next;
        }
    }

    private void shiftDown(int index) {

        int next;
        Helper helper = new Helper();

        while ((next = (2 * index + 1)) < count) {  // 左孩子，2 * index + 1

            if (next + 1 < count && (data[next + 1].compareTo(data[next]) < 0) ) {  // 判断右孩子是否小于左孩子
                next = next + 1;
            }

            if (data[index].compareTo(data[next]) <= 0 ) break;

            helper.swap(data, index, next);

            index = next;
        }
    }

    public static void main(String[] args) {

        MinHeap heap = new MinHeap(100);

        System.out.println("minheap is empty: " + heap.isEmpty());

        heap.insert(10);
        heap.insert(203);
        heap.insert(13);
        heap.insert(850);
        heap.insert(140);

        System.out.println("minheap is empty: " + heap.isEmpty());
        System.out.println("size of minheap: " + heap.size());
        System.out.println("current min of minheap: " + heap.getMin());
        System.out.println("extract min of minheap: " + heap.extractMin());
        System.out.println("size of minheap: " + heap.size());
        System.out.println("current min of minheap: " + heap.getMin());
        System.out.println("extract min of minheap: " + heap.extractMin());
        System.out.println("size of minheap: " + heap.size());
        System.out.println("current min of minheap: " + heap.getMin());

        Integer[] arr= new Integer[]{1, 32, 43, 5, 511, 4};

        MinHeap heap2 = new MinHeap(arr);

        System.out.println("current min of heap2: " + heap2.getMin());
    }
}
