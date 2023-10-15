package priority_queue;

public class SortedPriorityQueue {

    Node first;

    public void add(int value) {
        Node prev = null;
        Node current = first;

        // Find the location for the new node
        while (current != null) {
            if (value < current.value) break;
            prev = current;
            current = current.next;
        }

        if (prev == null) {
            first = new Node(value, first);
        }
        else {
            prev.next = new Node(value, current);
        }
    }

    public int remove() {
        if (first == null) throw new IllegalStateException("Can't remove from an empty queue!");
        int value = first.value;
        first = first.next;
        return value;
    }

    public void print() {
        Node current = first;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}
