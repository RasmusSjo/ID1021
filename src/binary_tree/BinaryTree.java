package binary_tree;

import utils.ArrayGenerator;

import java.util.Iterator;
import java.util.Random;

public class BinaryTree implements Iterable<Integer> {

    private final static Random RANDOM = new Random();

    private Node root;

    @Override
    public Iterator<Integer> iterator() {
        return new TreeIterator(root);
    }

    public void add(Integer key){
        add(key, RANDOM.nextInt());
    }

    public void add(Integer key, Integer value) {
        if (root == null) {
            root = new Node(key, value);
            return;
        }

        Node current = root;
        boolean added = false;

        while (!added) {
            if (key.equals(current.key)) {
                current.value = value;
                added = true;
            }
            else if (key < current.key) {
                if (current.left == null) {
                    current.left = new Node(key, value);
                    added = true;
                }
                else {
                    current = current.left;
                }
            }
            else {
                if (current.right == null) {
                    current.right = new Node(key, value);
                    added = true;
                }
                else {
                    current = current.right;
                }
            }
        }
    }

    public void addRec(Integer key){
        root = addRec(key, RANDOM.nextInt(), root);
    }


    public void addRec(Integer key, Integer value) {
        root = addRec(key, value, root);
    }

    public Node addRec(Integer key, Integer value, Node node) {
        if (node == null) {
            return new Node(key, value);
        }

        if (key.equals(node.key)) {
            node.value = value;
        }
        else if (key < node.key) {
            node.left = addRec(key, value, node.left);
        }
        else {
            node.right = addRec(key, value, node.right);
        }
        return node;
    }

    public Integer lookup(Integer key) {
        Node current = root;

        while (current != null) {
            if (key.equals(current.key)) {
                return current.value;
            }
            else if (key < current.key) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        return null;
    }

    public boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public void createTree(int a, int b) {
        root = constructTree(a, b);
    }

    private Node constructTree(int a, int b) {
        if (b < a) return null;

        Node node = new Node((a + b) / 2, (a + b) / 2);
        node.left = constructTree(a, (a + b) / 2 - 1);
        node.right = constructTree((a + b) / 2 + 1, b);

        return node;
    }

    public void constructTreeFromArray(int[] array, int start, int end) {
        if (end < start) return;

        int middle = (start + end) / 2;
        add(array[middle]);
        constructTreeFromArray(array, start, middle - 1);
        constructTreeFromArray(array, middle + 1, end);
    }

    public void printTree() {
        printTree(root, 1);
    }

    private void printTree(Node node, int depth) {
        if (node == null) {
            return;
        }

        // Print right subtree with increased depth
        printTree(node.right, depth + 1);

        // Print the current node
        for (int i = 0; i < depth; i++) {
            System.out.print("    "); // Indentation based on depth
        }
        System.out.println(node.key); // Print the node value

        // Print left subtree with increased depth
        printTree(node.left, depth + 1);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        int[] treeArray = ArrayGenerator.sorted(40);
        tree.constructTreeFromArray(treeArray, 0, treeArray.length - 1);
        tree.printTree();
    }
}
