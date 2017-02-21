package bugong;

import java.util.ArrayList;

public class MaxHeap {

    private int count = 0;
    private int capacity;
    private int[] data;

    public MaxHeap(int capacity) {

        data = new int[capacity];
        this.capacity = capacity;
    }

    public int size() {

        return count;
    }

    public boolean isEmpty() {

        return count == 0;
    }

    public void insert(int item) {

        if (count > capacity) return;

        data[count++] = item;

        shiftUp(count - 1);
    }

    public Integer extractMax() {

        if (count == 0) return null;

        int maxValue = data[0];

        data[0] = data[count - 1];

        count--;

        shiftDown(0);

        return maxValue;
    }

    private void shiftUp(int index) {

        int next;  // 父节点，(index - 1) / 2

        while ((index - 1 >= 0) && data[index] > data[next = (index - 1) / 2]) {

            Helper.swap(data, index, next);

            index = next;
        }
    }

    private void shiftDown(int index) {

        int next;

        while ((next = (2 * index + 1)) < count) {  // 左孩子，2 * index + 1

            if (next + 1 < count && data[next + 1] > data[next]) {  // 判断右孩子是否大于左孩子
                next = next + 1;
            }

            if (data[index] >= data[next]) break;

            Helper.swap(data, index, next);

            index = next;
        }
    }

    public static void main(String[] args) {

        MaxHeap heap = new MaxHeap(100);

        System.out.println("maxheap is empty: " + heap.isEmpty());

        heap.insert(10);
        heap.insert(20);
        heap.insert(50);
        heap.insert(80);
        heap.insert(40);

        System.out.println("maxheap is empty: " + heap.isEmpty());
        System.out.println("size of maxheap: " + heap.size());
        System.out.println("max value of maxheap: " + heap.extractMax());
        System.out.println("size of maxheap: " + heap.size());
        System.out.println("max value of maxheap: " + heap.extractMax());
        System.out.println("size of maxheap: " + heap.size());
    }
}
