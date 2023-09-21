package single_linked_list;

import java.util.EmptyStackException;

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
            int value = stack.get(0);
            stack.remove(value);
            return value;
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }

    public void display() {
        stack.display();
    }
}
