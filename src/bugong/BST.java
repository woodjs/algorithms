package bugong;

/**
 * 二叉搜索树
 */
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

        public Node(Node node) {

            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
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

    public Integer search(int key) {

        if (!contain(key)) return null;

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

            inorder(node.left);

            System.out.println("inorder - current node is: " + node.key);

            inorder(node.right);
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

            postorder(node.left);
            postorder(node.right);

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

            System.out.println("level order - current node is: " + node.key);

            if (node.left != null) queue.enqueue(node.left);
            if (node.right != null) queue.enqueue(node.right);
        }
    }

    public Integer minimum() {

        if (root == null) return null;

        Node minNode = minimum(root);

        return minNode.key;
    }

    private Node minimum(Node node) {

        if (node.left == null) return node;

        return minimum(node.left);
    }

    public Integer maximum() {

        if (root == null) return null;

        Node maxNode = maximum(root);

        return maxNode.key;
    }

    private Node maximum(Node node) {

        if (node.right == null) return node;

        return maximum(node.right);
    }

    public void removeMin() {

        if (root == null) return;

        root = removeMin(root);
    }

    private Node removeMin(Node node) {

        if (node.left == null) {

            Node rightNode = node.right;

            count--;

            return rightNode;
        }

        node.left = removeMin(node.left);

        return node;
    }

    public void removeMax() {

        if (root == null) return;

        root = removeMax(root);
    }

    private Node removeMax(Node node) {

        if (node.right == null) {

            Node leftNode = node.left;

            count--;

            return leftNode;
        }

        node.right = removeMax(node.right);

        return node;
    }

    public void remove(int key) {

        if (root == null) return;

        root = remove(root, key);
    }

    private Node remove(Node node, int key) {

        if (node == null) return null;

        if (node.key == key) {

            if (node.left == null) {

                Node rightNode = node.right;

                count--;

                return rightNode;
            }

            if (node.right == null) {

                Node leftNode = node.left;

                count--;

                return leftNode;
            }

            Node successor = new Node(minimum(node.right));  // successor，继任者

            successor.right = removeMin(node.right);
            successor.left = node.left;

            return successor;
        } else if (node.key < key) {

            node.right = remove(node.right, key);

            return node;
        } else {

            node.left = remove(node.left, key);

            return node;
        }
    }

    public static void main(String[] args) {

        BST bst = new BST();

        System.out.println("isEmpty: " + bst.isEmpty());

        bst.insert(0, 0);
        bst.insert(4, 4);
        bst.insert(6, 6);
        bst.insert(7, 7);
        bst.insert(3, 3);
        bst.insert(1, 1);
        bst.insert(2, 2);
        bst.insert(5, 5);

        bst.preorder();
        bst.inorder();
        bst.postorder();
        bst.levelOrder();

        System.out.println("isEmpty: " + bst.isEmpty());
        System.out.println("minimum: " + bst.minimum());
        System.out.println("maximum: " + bst.maximum());
        System.out.println("contain 7: " + bst.contain(7));
        System.out.println("contain 8: " + bst.contain(8));
        System.out.println("search 5: " + bst.search(5));
        System.out.println("size: " + bst.size());

        bst.removeMin();
        bst.removeMax();

        System.out.println("size: " + bst.size());
        System.out.println("minimum: " + bst.minimum());
        System.out.println("maximum: " + bst.maximum());

        System.out.println("search 3: " + bst.search(3));
        bst.remove(3);
        System.out.println("remove 3");
        System.out.println("search 3: " + bst.search(3));
    }
}
