package HP35;

import java.util.EmptyStackException;

public abstract class Stack {

    protected int[] stack;
    protected int stackPointer = 0;

    public Stack(int size) {
        this.stack = new int[size];
    }

    public abstract void push(int value);

    public int pop() {
        if (stackPointer == 0) {
            throw new EmptyStackException();
        }

        return stack[--stackPointer];
    }
}
