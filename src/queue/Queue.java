package queue;

import java.util.NoSuchElementException;

public class Queue {

    Node head;
    Node last;

    private static class Node {

        Integer item;
        Node next;

        public Node(Integer item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public void add(Integer item) {
        if (last == null) {
            head = last = new Node(item, null);
        }
        else {
            last.next = new Node(item, null);
            last = last.next;
        }
    }

    public Integer remove() {
        if (head == null) throw new NoSuchElementException();
        Integer value = head.item;

        if (head == last) {
            last = null;
        }
        head = head.next;

        return value;
    }

    public void printQueue() {
        System.out.println("Queue from first- to last added:");
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queue queue = new Queue();

        queue.add(1);
        queue.add(20);
        queue.add(5);

        int test = queue.remove();
        queue.remove();
        queue.remove();
        queue.remove();

        queue.printQueue();
        System.out.println(test);
    }
}
