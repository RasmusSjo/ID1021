package quick_sort;

import java.util.Random;

public class LinkedList {

    Node first;
    Node last;
    int length;

    public LinkedList() {
        this(0);
    }

    public LinkedList(int length) {
        if (length > 0) {
            Random random = new Random();
            first = last = new Node(random.nextInt(length), null);
            for (int i = 1; i < length; i++) {
                first = new Node(random.nextInt(length), first);
            }
        }
        else {
            first = last = null;
        }

        this.length = length;
    }

    public static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public void add(int item) {
        if (first == null) {
            first = last = new Node(item, null);
        }
        else {
            last.next = new Node(item, null);
            last = last.next;
        }
        length++;
    }

    public void add(Node node) {
        if (first == null) {
            // If the list is empty, the added node will both be first and last
            first = last = node;
        }
        else {
            // Else, add after the last node
            last.next = node;
            last = last.next;
        }
        // Dereference any nodes after since it's the last in the list
        last.next = null;
        length++;
    }

    public void append(LinkedList list) {
        // Return if the list we're appending is empty
        if (list.first == null) return;

        if (first == null) {
            // If the initial list is empty, the new first node will be
            // the one of the appended list
            first = list.first;
        }
        else {
            // Else, append the new list to the end of the current one
            last.next = list.first;
        }
        // The last node is now the last node in the appended list
        last = list.last;

        // Dereference
        list.first = list.last = null;
        length += list.length();
    }

    public int length() {
        return length;
    }

    public void display() {
        LinkedList.Node current = first;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    public int[] listToArr() {
        int[] array = new int[length];
        LinkedList.Node current = first;
        for (int i = 0; i < length; i++) {
            array[i] = current.value;
            current = current.next;
        }
        return array;
    }

    public static LinkedList createLinkedFromArray(int[] array) {
        if (array.length == 0) return new LinkedList();

        LinkedList list = new LinkedList();
        for (int value : array) {
            list.add(value);
        }
        return list;
    }
}
