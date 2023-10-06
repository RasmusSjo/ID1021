package single_linked_list;

import java.util.Random;

public class LinkedList {

    Cell first;

    public LinkedList() {
        this(0);
    }

    public LinkedList(int length) {
        first = null;
        if (length > 0) {
            Random random = new Random();
            for (int i = 0; i < length; i++) {
                first = new Cell(random.nextInt(length), first);
            }
        }
    }

    public static class Cell {
        int head;
        Cell tail;

        Cell(int head, Cell tail) {
            this.head = head;
            this.tail = tail;
        }
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

    public void unlink(Cell cell) {
        Cell current = first;
        Cell previous = null;

        while (current != cell) {
            previous = current;
            current = current.tail;
        }

        if (previous == null) {
            first = first.tail;
        }
        else {
            previous.tail = current.tail;
        }

        cell.tail = null;
    }

    public void insert(Cell cell) {
        cell.tail = first;
        first = cell;
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

    public Cell getCell(int index) {
        if (index < 0 || index >= length()) throw new IndexOutOfBoundsException();

        Cell current = first;

        int i = 0;
        while (i != index) {
            i++;
            current = current.tail;
        }

        return current;
    }

    public Cell[] getCellArray() {
        Cell current = first;
        Cell[] cells = new Cell[length()];

        int i = 0;
        while (current != null) {
            cells[i++] = current;
            current = current.tail;
        }
        return cells;
    }
}
