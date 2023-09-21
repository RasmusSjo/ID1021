package single_linked_list;

import java.util.Random;

public class LinkedList {

    Cell first;

    public LinkedList() {}

    public LinkedList(int length) {
        Random random = new Random();
        Cell last = null;
        for (int i = 0; i < length; i++) {
            last = new Cell(random.nextInt(length), last);
        }
        first = last;
    }

    private static class Cell {
        int head;
        Cell tail;

        Cell(int head, Cell tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public int get(int index) {
        if (index < 0 || index >= length()) throw new IndexOutOfBoundsException();

        Cell current = first;

        int i = 0;
        while (current.tail != null) {
            i++;
            if (i == index) {
                break;
            }
            else {
                current = current.tail;
            }
        }

        return current.head;
    }

    public void add(int item) {
        first = new Cell(item, first);
    }

    public int length() {
        int length = 0;
        Cell current = first;

        while (current != null) {
            current = current.tail;
            length++;
        }

        return length;
    }

    public boolean find(int item) {
        Cell current = first;

        while (current != null) {
            if (current.head == item) return true;
            current = current.tail;
        }
        return false;
    }

    public void remove(int item) {
        Cell prev = null;
        Cell current = first;

        while (current != null) {
            if (current.head == item) {
                if (prev == null) {
                    first = first.tail;
                }
                else {
                    prev.tail = current.tail;
                }
                return;
            }
            prev = current;
            current = current.tail;
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
        Cell current = first;

        // If the list doesn't contain any elements
        if (current == null) {
            first = list.first;
            return;
        }

        // Go to the last element in the list
        while (current.tail != null) {
            current = current.tail;
        }

        current.tail = list.first;
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
