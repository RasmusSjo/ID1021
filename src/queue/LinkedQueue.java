package queue;

import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Queue<T> {

    Node<T> head;
    Node<T> last;

    private static class Node<T> {

        T item;
        Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public void add(T item) {
        if (last == null) {
            head = last = new Node<>(item, null);
        }
        else {
            last.next = new Node<>(item, null);
            last = last.next;
        }
    }

    @Override
    public T remove() {
        if (isEmpty()) throw new NoSuchElementException();
        T item = head.item;

        if (head == last) {
            last = null;
        }
        head = head.next;

        return item;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void printQueue() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
