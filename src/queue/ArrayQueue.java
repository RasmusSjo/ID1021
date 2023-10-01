package queue;

public class ArrayQueue<T> implements Queue<T> {

    // The array in which the object are queued
    private T[] queue;

    // Index of the next element to be removed (FIFO - first added)
    private int first;
    // Index of the next free position
    private int last;
    // Length of the array
    private int length;
    private final static int DEFAULT_SIZE = 4;

    public ArrayQueue() {
        this(DEFAULT_SIZE);
    }

    public ArrayQueue(int length) {
        queue = (T[]) new Object[Math.max(length, DEFAULT_SIZE)];
        this.length = length;
        first = last = 0;
    }

    @Override
    public void add(T item) {
        queue[last] = item;
        last = (last + 1) % length;

        if (last == first) {
            increaseSize();
        }
    }

    private void increaseSize() {
        T[] copiedQueue = (T[]) new Object[2 * length];
        int newIndex = 0;

        // Copy array from first – length - 1
        for (int oldIndex = first; oldIndex < length; newIndex++) {
            copiedQueue[newIndex] = queue[oldIndex++];
        }

        // Copy array from 0 – first - 1
        for (int oldIndex = 0; oldIndex < last; newIndex++) {
            copiedQueue[newIndex] = queue[oldIndex++];
        }

        // Update indices and length
        first = 0;
        last = length;
        length *= 2;

        queue = copiedQueue;
    }

    @Override
    public T remove() {
        if (isEmpty()) return null;

        // Get the next value in the queue
        T item = queue[first];
        queue[first] = null;

        // If first is the length of the queue the end has been reached
        // and the next item can be found at first = 0
        first = (first + 1) % length;

        // Calculate the number of items in the queue
        int size;
        if (first < last) {
            size = last - first;
        }
        else {
            size = length - (first - last);
        }

        if (size <= length / 4 && length / 2 >= DEFAULT_SIZE) decreaseSize();

        return item;
    }

    private void decreaseSize() {
        T[] copiedQueue = (T[]) new Object[length / 2];
        int newIndex = 0;

        if (first < last) {
            // Copy array from first to last - 1
            for (int oldIndex = first; oldIndex < last; newIndex++) {
                copiedQueue[newIndex] = queue[oldIndex++];
            }
        }
        else {
            // Copy array from first to length - 1
            for (int oldIndex = first; oldIndex < length; newIndex++) {
                copiedQueue[newIndex] = queue[oldIndex++];
            }

            // Copy array from 0 to first - 1
            for (int oldIndex = 0; oldIndex < last; newIndex++) {
                copiedQueue[newIndex] = queue[oldIndex++];
            }
        }

        // Update indices and length
        first = 0;
        last = newIndex;
        length /= 2;

        queue = copiedQueue;
    }

    @Override
    public boolean isEmpty() {
        return first == last;
    }

    @Override
    public void printQueue() {
        for (T item : queue) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
