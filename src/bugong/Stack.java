package bugong;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

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


    public void push(Item item) {

        Node oldFirst = first;

        first = new Node();

        first.item = item;
        first.next = oldFirst;

        count++;
    }

    public Item pop() {

        Item item = first.item;

        first = first.next;

        count--;

        return item;
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

        Stack<Integer> stack = new Stack();

        System.out.println("stack is empty: " + stack.isEmpty());

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        for (int i : stack) {
            System.out.println(i);
        }

        System.out.println("stack is empty: " + stack.isEmpty());

        System.out.println("size of stack: " + stack.size());

        System.out.println("pop: " + stack.pop());

        System.out.println("pop: " + stack.pop());

        System.out.println("size of stack: " + stack.size());
    }
}
