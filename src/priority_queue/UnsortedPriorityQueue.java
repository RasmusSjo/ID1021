package priority_queue;

public class UnsortedPriorityQueue {

    Node first;

    public void add(int priority) {
        first = new Node(priority, first);
    }

    public int remove() {
        if (first == null) throw new IllegalStateException("Can't remove from an empty queue!");

        int smallest = first.value;
        Node smallestPrev = null;
        Node current = first;

        // Find the node that should be removed
        while (current.next != null) {
            if (current.next.value < smallest) {
                smallestPrev = current;
                smallest = current.next.value;
            }
            current = current.next;
        }

        if (smallestPrev == null) {
            first = first.next;
        }
        else {
            smallestPrev.next = smallestPrev.next.next;
        }

        return smallest;
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
