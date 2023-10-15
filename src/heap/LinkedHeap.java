package heap;

public class LinkedHeap {

    Node root;
    int size;

    public static class Node {

        int value;
        Node left;
        Node right;
        int size;

        public Node(int value) {
            this.value = value;
            left = right = null;
            size = 1;
        }
    }

    public LinkedHeap() {
        root = null;
        size = 0;
    }

    public void add(int value) {
        size++;

        // Are we adding the first item to the heap?
        if (root == null) {
            root = new Node(value);
            return;
        }

        Node current = root;
        while (true) {
            current.size++;

            // Swap new value with current value if new value is larger if new prio is higher than prio of current
            if (value <= current.value) {
                int temp = current.value;
                current.value = value;
                value = temp;
            }

            if (current.left == null) {
                // Add to left branch if there isn't any
                current.left = new Node(value);
                break;
            }
            else if (current.right == null) {
                // Add to right branch if there isn't any
                current.right = new Node(value);
                break;
            }
            else {
                // Add to the branch that contains the fewest items
                if (current.left.size <= current.right.size) {
                    current = current.left;
                }
                else {
                    current = current.right;
                }
            }
        }
    }

    public int remove() {
        if (isEmpty()) throw new IllegalStateException();
        size--;

        int value = root.value;

        Node prev = null;
        Node current = root;
        while (true) {
            current.size--;

            if (current.left == null) {
                if (current.right == null) {
                    // If node is a leaf it should be removed
                    removeBranch(prev);
                    break;
                }
                // Continue to right branch and pull value up
                current.value = current.right.value;
                prev = current;
                current = current.right;
            }
            else if (current.right == null) {
                // Continue to left branch and pull value up
                current.value = current.left.value;
                prev = current;
                current = current.left;
            }
            else {
                // Continue to the branch with the value that gets pulled up
                prev = current;
                if (current.left.value <= current.right.value) {
                    current.value = current.left.value;
                    current = current.left;
                }
                else {
                    current.value = current.right.value;
                    current = current.right;
                }
            }
        }

        return value;
    }

    private void removeBranch(Node node) {
        if (size == 0) {
            root = null;
            return;
        }

        if (node.left != null && node.left.size == 0) {
            node.left = null;
        }
        else if (node.right != null && node.right.size == 0) {
            node.right = null;
        }
        else throw new IllegalStateException("The node doesn't have any empty branches!");
    }

    public int push(Integer incr) {
        if (isEmpty()) throw new IllegalStateException("You tried to push a non existing item");

        int depth = 0;

        Node current = root;
        current.value += incr;
        while (true) {
            depth++;

            if (current.left == null) {
                // If node is a leaf or the value is in the correct place we are done
                if (current.right == null || current.value <= current.right.value) break;

                // Else, swap the current node with the right one and continue to right branch
                swap(current, current.right);
                current = current.right;
            }
            else if (current.right == null) {
                // Swap values if current value is larger than that of the left node
                if (current.value > current.left.value) {
                    swap(current, current.left);
                    current = current.left;
                }
                else break; // If not the value is in the correct place
            }
            else {
                // Swap value with the correct branch or break if the value is in the correct place
                if (current.value > current.left.value) {
                    swap(current, current.left);
                    current = current.left;
                }
                else if (current.value > current.right.value) {
                    swap(current, current.right);
                    current = current.right;
                }
                else break;
            }
        }

        return depth;
    }

    private void swap(Node parent, Node child) {
        int temp = parent.value;
        parent.value = child.value;
        child.value = temp;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void print() {
        if (root == null) {
            System.out.println("Heap is empty.");
            return;
        }

        printHeapAsTree(root, "", true);
    }

    private void printHeapAsTree(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.print(prefix);
            System.out.print(isLeft ? "├── " : "└── ");
            System.out.println(node.value);

            String childPrefix = prefix + (isLeft ? "│   " : "    ");
            printHeapAsTree(node.left, childPrefix, true);
            printHeapAsTree(node.right, childPrefix, false);
        }
    }
}
