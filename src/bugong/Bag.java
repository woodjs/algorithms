package bugong;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

    private int count;

    private Node first;

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

    public void add(Item item) {

        Node oldFirst = first;

        first = new Node();

        first.item = item;
        first.next = oldFirst;

        count++;
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

        Bag<Integer> bag = new Bag();

        System.out.println("bag is empty: " + bag.isEmpty());

        bag.add(1);
        bag.add(2);
        bag.add(3);
        bag.add(4);
        bag.add(5);

        for (int i : bag) {
            System.out.println(i);
        }

        System.out.println("bag is empty: " + bag.isEmpty());

        System.out.println("size of bag: " + bag.size());
    }
}
