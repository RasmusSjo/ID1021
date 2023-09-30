package binary_tree;

import java.util.Iterator;
import java.util.Stack;

public class TreeIterator implements Iterator<Integer>{

    private Node next;
    private final Stack<Node> stack;

    public TreeIterator(Node root) {
        stack = new Stack<>();
        Node leftMost = root;

        while (leftMost != null) {
            stack.push(leftMost);
            leftMost = leftMost.left;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        next = stack.pop();

        if (next.right != null) {
            Node leftMost = next.right;
            while (leftMost != null) {
                stack.push(leftMost);
                leftMost = leftMost.left;
            }
        }
        return next.key;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
