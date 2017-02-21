package bugong;

public class BST {

    private int count;
    private Node root;

    private class Node {

        private int key;
        private int value;
        private Node left;
        private Node right;

        public Node(int key, int value) {

            this.key = key;
            this.value = value;
        }
    }

    public int size() {

        return count;
    }

    public boolean isEmpty() {

        return count == 0;
    }

    public void insert(int key, int value) {


        root = insert(root, key, value);

    }

    private Node insert(Node node, int key, int value) {


        if (node == null) {

            node = new Node(key, value);

            count++;

            return node;
        }

        if (node.key == key) {

            node.value = value;

        } else if (node.key < key) {

            node.right = insert(node.right, key, value);

        } else {

            node.left = insert(node.left, key, value);
        }

        return node;
    }

    public boolean contain(int key) {


        return contain(root, key);
    }

    private boolean contain(Node node, int key) {

        if (node == null) return false;

        if (node.key == key) {

            return true;
        } else if (node.key < key) {

            return contain(node.right, key);
        } else {

            return contain(node.left, key);
        }
    }

    public int search(int key) {

        return search(root, key);
    }


    private Integer search(Node node, int key) {

        if (node == null) return null;

        if (node.key == key) {

            return node.value;
        } else if (node.key < key) {

            return search(node.right, key);
        } else {

            return search(node.left, key);
        }
    }

    public static void main(String[] args) {

    }
}
