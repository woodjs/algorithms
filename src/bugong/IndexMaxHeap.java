package bugong;

/**
 * 最大索引堆
 */
public class IndexMaxHeap<Item extends Comparable> {

    private int count = 0;
    private int capacity;
    private Item[] data;
    private Integer[] indexes;
    private Integer[] reverse;  // 跟踪indexes中的元素

    public IndexMaxHeap(int capacity) {

        data = (Item[])new Comparable[capacity];
        indexes = new Integer[capacity];
        reverse = new Integer[capacity];

        this.capacity = capacity;
    }

    public int size() {

        return count;
    }

    public boolean isEmpty() {

        return count == 0;
    }

    public void insert(int index, Item item) {

        if (count >= capacity) return;

        data[index] = item;
        indexes[count] = index;
        reverse[index] = count;

        count++;

        shiftUp(count - 1);
    }

    public Item extractMax() {

        if (count == 0) return null;

        Item maxValue = data[indexes[0]];

        data[indexes[0]] = data[indexes[count - 1]];

        reverse[indexes[0]] = 0;
        reverse[indexes[count - 1]] = null;

        count--;

        shiftDown(0);

        return maxValue;
    }

    public Integer extractMaxIndex() {

        if (count == 0) return null;

        int maxIndex = indexes[0];

        indexes[0] = indexes[count - 1];

        reverse[indexes[0]] = 0;
        reverse[indexes[count - 1]] = null;

        count--;

        shiftDown(0);

        return maxIndex;
    }

    public Item getMax() {

        if (count == 0) return null;

        return data[indexes[0]];
    }

    public Integer getMaxIndex() {

        if (count == 0) return -1;

        return indexes[0];
    }

    public boolean contain(int index) {

        if (index >= 0 || index <= capacity) {
            return reverse[index] != null;
        }

        return false;
    }

    public Item getItem(int index) {

        if (!contain(index)) return null;

        return data[index];
    }

    public void change(int index, Item item) {

        data[index] = item;

        int pos = reverse[index];

        shiftUp(pos);
        shiftDown(pos);
    }

    private void shiftUp(int index) {

        int next;

        while (index - 1 >= 0 && data[indexes[index]] != null && data[indexes[index]].compareTo(data[indexes[(index - 1) / 2]]) > 0) {

            next = (index - 1) / 2;

            new Helper().swap(indexes, index, next);

            reverse[indexes[index]] = index;
            reverse[indexes[next]] = next;

            index = next;
        }
    }

    private void shiftDown(int index) {

        int next;

        while ((next = 2 * index + 1) < count) {

            if (next + 1 < count && data[indexes[next]].compareTo(data[indexes[next + 1]]) < 0) {
                next = next + 1;
            }

            if (data[indexes[index]].compareTo(data[indexes[next]]) > 0) break;

            new Helper().swap(indexes, index, next);

            reverse[indexes[index]] = index;
            reverse[indexes[next]] = next;

            index = next;
        }
    }

    public static void main(String[] args) {

        IndexMaxHeap heap = new IndexMaxHeap(100);

        System.out.println("maxheap is empty: " + heap.isEmpty());

        heap.insert(0, 10);
        heap.insert(1, 203);
        heap.insert(2, 13);
        heap.insert(3, 850);
        heap.insert(4, 140);
        heap.insert(5, 1340);
        heap.insert(6, 133);
        heap.insert(7, 333);

        System.out.println("maxheap is empty: " + heap.isEmpty());
        System.out.println("size of maxheap: " + heap.size());
        System.out.println("current max of maxheap: " + heap.getMax());
        System.out.println("extract max of maxheap: " + heap.extractMax());
        System.out.println("size of maxheap: " + heap.size());
        System.out.println("current max of maxheap: " + heap.getMax());
        System.out.println("current max index of maxheap: " + heap.getMaxIndex());
        System.out.println("extract max index of maxheap: " + heap.extractMaxIndex());
        System.out.println("size of maxheap: " + heap.size());
        System.out.println("current max of maxheap: " + heap.getMax());
        System.out.println("current max index of maxheap: " + heap.getMaxIndex());
        System.out.println("value of index 2: " + heap.getItem(2));
        heap.change(2, 8888);
        System.out.println("maxheap contain index 33: " + heap.contain(33));
        System.out.println("value of index 2: " + heap.getItem(2));
        System.out.println("current max of maxheap: " + heap.getMax());
        System.out.println("current max index of maxheap: " + heap.getMaxIndex());
    }
}
