package single_linked_list;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class LinkedStack {

    private final LinkedList stack;

    public LinkedStack() {
        stack = new LinkedList();
    }

    public void push(int value) {
        stack.add(value);
    }

    public int pop() {
        try{
            int value = stack.getHead();
            stack.remove(value);
            return value;
        } catch (NoSuchElementException e) {
            throw new EmptyStackException();
        }
    }

    public void display() {
        stack.display();
    }
}
