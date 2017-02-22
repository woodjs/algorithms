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

    /**
     * 前序遍历
     */
    public void preorder() {

        if (root == null) return;

        preorder(root);
    }

    private void preorder(Node node) {

        if (node != null) {

            System.out.println("preorder - current node is: " + node.key);

            preorder(node.left);
            preorder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inorder() {

        if (root == null) return;

        inorder(root);
    }

    private void inorder(Node node) {

        if (node != null) {

            preorder(node.left);

            System.out.println("inorder - current node is: " + node.key);

            preorder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void postorder() {

        if (root == null) return;

        postorder(root);
    }

    private void postorder(Node node) {

        if (node != null) {

            preorder(node.left);
            preorder(node.right);

            System.out.println("postorder - current node is: " + node.key);
        }
    }

    /**
     * 层序遍历（广度优先遍历）
     */
    public void levelOrder() {

        if (root == null) return;

        Queue<Node> queue = new Queue();

        queue.enqueue(root);

        while (!queue.isEmpty()) {

            Node node = queue.dequeue();

            System.out.println("current node is: " + node.key);

            if (node.left != null) queue.enqueue(node.left);
            if (node.right != null) queue.enqueue(node.right);
        }
    }

    public static void main(String[] args) {

    }
}
