package single_linked_list;

import java.util.NoSuchElementException;

public class LinkedList {

    Cell first;

    private static class Cell {
        int head;
        Cell tail;

        Cell(int head, Cell tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public int getHead() {
        if (length() == 0) throw new NoSuchElementException();
        return first.head;
    }

    public void add(int item) {
        first = new Cell(item, first);
    }

    public int length() {
        int length = 0;
        Cell temp = first;

        while (temp != null) {
            temp = temp.tail;
            length++;
        }

        return length;
    }

    public boolean find(int item) {
        Cell temp = first;

        while (temp != null) {
            if (temp.head == item) return true;
            temp = temp.tail;
        }
        return false;
    }

    public void remove(int item) {
        Cell prev = null;
        Cell temp = first;

        while (temp != null) {
            if (temp.head == item) {
                if (prev == null) {
                    first = first.tail;
                }
                else {
                    prev.tail = temp.tail;
                }
                return;
            }
            prev = temp;
            temp = temp.tail;
        }
    }

    public void display() {
        Cell current = first;
        while (current != null) {
            System.out.print(current.head + " ");
            current = current.tail;
        }
        System.out.println();
    }

    public void append(LinkedList list) {
        Cell next = first;

        // If the list doesn't contain any elements
        if (next == null) {
            first = list.first;
            // Optional, since we merge two lists it should be ok
            list.first = null;
            return;
        }

        // Go to the last element in the list
        while (next.tail != null) {
            next = next.tail;
        }

        next.tail = list.first;
        list.first = null;
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();

        linkedList.add(5);
        linkedList.add(7);

        linkedList2.add(3);
        linkedList2.add(8);
        linkedList2.add(13);

        linkedList.display();
        linkedList2.display();

        LinkedStack stack = new LinkedStack();

        stack.push(3);
        stack.push(89);
        stack.push(32);

        stack.display();
        System.out.println(stack.pop());
        stack.display();
    }
}
