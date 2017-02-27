package bugong;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    int count;
    Node first;
    Node last;

    private class Node {

        Item item;
        Node next;
    }

    public boolean isEmpty() {

        return count == 0;
    }

    public int size() {

        return count;
    }

    public void enqueue(Item item) {

        Node oldLast = last;

        last = new Node();

        last.item = item;
        last.next = null;

        if (isEmpty()) {

            first = last;
        } else {

            oldLast.next = last;
        }

        count++;
    }

    public Item dequeue() {

        Item oldFirst = first.item;

        first = first.next;

        count--;

        if (isEmpty()) last = null;

        return oldFirst;
    }

    public Iterator<Item> iterator() {

        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;


        public boolean hasNext() {

            return current != null;
        }

        public Item next() {

            Item item = current.item;

            current = current.next;

            return item;
        }
    }

    public static void main(String[] args) {

        Queue<Integer> queue = new Queue();

        System.out.println("queue is empty: " + queue.isEmpty());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        for (int i : queue) {
            System.out.println(i);
        }

        System.out.println("queue is empty: " + queue.isEmpty());

        System.out.println("size of queue: " + queue.size());

        System.out.println("dequeue: " + queue.dequeue());

        System.out.println("dequeue: " + queue.dequeue());

        System.out.println("size of queue: " + queue.size());
    }
}
