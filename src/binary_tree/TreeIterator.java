package binary_tree;

import queue.ArrayQueue;
import queue.LinkedQueue;
import queue.Queue;

import java.util.Iterator;

public class TreeIterator implements Iterator<Integer>{

    private final Queue<Node> queue;

    public TreeIterator(Node root) {
        queue = new LinkedQueue<>();
        queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Integer next() {
        Node next = queue.remove();

        if (next.left != null) queue.add(next.left);
        if (next.right != null) queue.add(next.right);

        return next.key;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
