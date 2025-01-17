package dijkstra;

public class PriorityQueue {

    private static final int DEFAULT_SIZE = 16;
    Path[] heap;
    int free;

    public PriorityQueue() {
        this(DEFAULT_SIZE);
    }

    public PriorityQueue(int size) {
        heap = new Path[size];
        free = 0;
    }

    public void add(Path path) {
        // Increase size of array if needed
        if (free >= heap.length) {
            resize();
        }

        // Insert new path as rightmost leaf and update queue index of it
        heap[free] = path;
        path.index = free;

        bubble(free);

        free++;
    }

    public Path poll() {
        if (isEmpty()) throw new IllegalStateException("Can't poll from an empty queue!");
        Path path = heap[0];

        // Place the rightmost element at the top and update its queue index
        heap[0] = heap[--free];
        heap[0].index = 0;
        heap[free] = null;

        // Sink the element to its correct position
        sink(0);

        return path;
    }


    public void bubble(int child) {
        int parent = parent(child);

        bubble(parent, child);
    }

    private void bubble(int parent, int child) {
        // Bubble a path towards the top if its distance is smaller than its parents distance
        while (heap[parent].distance > heap[child].distance) {
            swap(parent, child);

            // Find index of parent
            child = parent;
            parent = parent(child);
        }
    }

    private void sink(int parent) {
        int left = leftChild(parent);
        int right = rightChild(parent);

        // If either child is out of bounds or if the left branch is null, the index belongs to a leaf
        if (left >= heap.length || right >= heap.length || heap[left] == null) return;

        if (heap[right] == null) {
            // If the parents distance is smaller the element is sorted
            if (heap[parent].distance <= heap[left].distance) return;
            swap(parent, left);
            parent = left;
        }
        else {
            // Get the index of the branch with the smallest distance
            int smallestIndex = left;
            if (heap[left].distance > heap[right].distance) {
                smallestIndex = right;
            }

            // If the parent is larger than the smallest child, swap them, else it's sorted
            if (heap[parent].distance > heap[smallestIndex].distance) {
                swap(parent, smallestIndex);
                parent = smallestIndex;
            }
            else return;
        }

        sink(parent);
    }

    private void swap(int parent, int child) {
        // Swap the queue index of the nodes
        int tempIndex = heap[parent].index;
        heap[parent].index = heap[child].index;
        heap[child].index = tempIndex;

        // Swap the nodes places in the queue
        Path tempPath = heap[parent];
        heap[parent] = heap[child];
        heap[child] = tempPath;
    }

    private void resize() {
        double factor = 2;
        Path[] temp = new Path[(int) (heap.length * factor)];
        System.arraycopy(heap, 0, temp, 0, free);
        heap = temp;
    }

    private int parent(int child) {
        return (child - 1) / 2;
    }

    private int leftChild(int parent) {
        return parent * 2 + 1;
    }

    private int rightChild(int parent) {
        return parent * 2 + 2;
    }

    public boolean isEmpty() {
        return free == 0;
    }

    public void clear() {
        // Clear the index of all paths
        for (int index = 0; index < free; index++) {
            heap[index].index = null;
        }

        // Clear the heap and set the free slot to 0
        heap = new Path[heap.length];
        free = 0;
    }
}
