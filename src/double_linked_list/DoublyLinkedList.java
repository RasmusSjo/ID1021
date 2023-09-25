package double_linked_list;

import java.util.Random;

public class DoublyLinkedList {

    Cell first;

    public DoublyLinkedList(int length) {
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            add(random.nextInt(length));
        }
    }

    public static class Cell {
        int head;
        Cell previous;
        Cell next;

        public Cell(int head, Cell previous, Cell next) {
            this.head = head;
            this.previous = previous;
            this.next = next;
        }
    }


    public void add(int value) {
        Cell newCell = new Cell(value, null, first);
        if (first !=  null) {
            first.previous = newCell;
        }
        first = newCell;
    }

    public int length() {
        int length = 0;
        Cell current = first;

        while (current != null) {
            current = current.next;
            length++;
        }

        return length;
    }

    public boolean find(int value) {
        Cell current = first;

        while (current != null) {
            if (current.head == value) return true;
            current = current.next;
        }
        return false;
    }

    public void remove(int value) {
        Cell current = first;

        while (current != null) {
            if (current.head == value) {
                if (current.previous == null) {
                    first = first.next;
                }
                else {
                    current.previous.next = current.next;
                }
                if (current.next != null) {
                    current.next.previous = current.previous;
                }
                // Dereference the pointers of the current cell
                current.previous = null;
                current.next = null;
            }
            current = current.next;
        }
    }

    public void unlink(Cell cell) {
        // If the cell doesn't have a cell before it's the first cell in the list
        if (cell.previous == null) {
            first = first.next;
            if (cell.next != null) {
                cell.next.previous = null;
            }
        }
        else if (cell.next == null) {
            cell.previous.next = null;
        }
        else {
            cell.previous.next = cell.next;
            cell.next.previous = cell.previous;
        }
        // Dereference the given cell
        cell.previous = null;
        cell.next = null;
    }

    public void insert(Cell cell) {
        if (first != null) {
            first.previous = cell;
        }
        cell.previous = null;
        cell.next = first;
        first = cell;
    }

    public Cell getCell(int index) {
        if (index < 0 || index >= length()) throw new IndexOutOfBoundsException();

        Cell current = first;

        int i = 0;
        while (index != i) {
            i++;
            current = current.next;
        }
        return current;
    }

    public Cell[] getCellArray() {
        Cell current = first;
        Cell[] cells = new Cell[length()];

        int i = 0;
        while (current != null) {
            cells[i++] = current;
            current = current.next;
        }
        return cells;
    }

    public void display() {
        Cell current = first;

        while (current != null) {
            System.out.print(current.head + " ");
            current = current.next;
        }
        System.out.println();
    }
}
