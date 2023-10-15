package heap;

public class ArrayHeap {

    private final static int HEAP_DEF_SIZE = 16;

    Integer[] heap;
    int free;

    public ArrayHeap() {
        this(HEAP_DEF_SIZE);
    }

    public ArrayHeap(int size) {
        heap = new Integer[size];
        free = 0;
    }

    public void add(int value) {
        // Insert new value, will be put as rightmost leaf
        heap[free] = value;

        int prev = free;
        int parent = (prev - 1) / 2;
        while (heap[parent] > heap[prev]) {
            swap(parent, prev);

            // Find index of parent
            prev = parent;
            parent = (parent - 1) / 2;
        }

        free++;
    }

    public int remove() {
        if (heap[0] == null) throw new IllegalStateException("The heap is empty");

        // Value of root node
        int value = heap[0];

        // Remove root and move up the rightmost leaf
        heap[0] = heap[--free];
        heap[free] = null;
        sink(0);

        return value;
    }

    public void increment(int incr) {
        heap[0] += incr;
        sink(0);
    }

    private void sink(int index) {
        int parent = index;
        int leftChild, rightChild;

        while (true) {
            leftChild = parent * 2 + 1;
            rightChild = parent * 2 + 2;
            if (leftChild >= heap.length || rightChild >= heap.length) break;

            // If the node doesn't have a left branch, it won't
            // have a right branch -> break
            if (heap[leftChild] == null) break;
            else if (heap[rightChild] == null) {
                if (heap[parent] <= heap[leftChild]) break;
                swap(parent, leftChild);
                parent = leftChild;
            }
            else {
                // Swap the value at index n with the value in the "smallest"
                // branch or break if it is sorted
                if (heap[leftChild] < heap[rightChild]) {
                    if (heap[parent] > heap[leftChild]) {
                        swap(parent, leftChild);
                        parent = leftChild;
                    }
                    else break;
                }
                else {
                    if (heap[parent] > heap[rightChild]) {
                        swap(parent, rightChild);
                        parent = rightChild;
                    }
                    else break;
                }
            }
        }
    }

    private int leftChild(int pos) {
        return pos * 2 + 1;
    }

    private int rightChild(int pos) {
        return pos * 2 + 2;
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private boolean isLeaf(int pos) {
        return pos >= (free - 1) / 2;
    }


    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    public void print() {
        for (Integer value : heap) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
