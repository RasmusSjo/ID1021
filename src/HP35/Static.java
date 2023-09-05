package HP35;

public class Static extends Stack {

    public Static(int size) {
        super(size);
    }

    @Override
    public void push(int value) {
        if (stackPointer >= stack.length) {
            throw new StackOverflowError("The stack is full");
        }

        stack[stackPointer++] = value;
    }
}
