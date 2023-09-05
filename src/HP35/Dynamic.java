package HP35;

public class Dynamic extends Stack {


    private static final int DEFAULT_SIZE = 8;

    public Dynamic() {
        super(DEFAULT_SIZE);
    }

    public Dynamic(int size) {
        super(size);
    }

    @Override
    public void push(int value) {
        if (stackPointer >= stack.length) {
            resize(stack.length * 2);
        }

        stack[stackPointer++] = value;
    }

    @Override
    public int pop() {
        int ret = super.pop();

        if (stackPointer <= stack.length / 4 && stack.length > 8) {
            resize(stack.length / 2);
        }

        return ret;
    }

    private void resize(int size) {
        int[] newStack = new int[size];
        System.arraycopy(stack, 0, newStack, 0, stackPointer);
        stack = newStack;
    }
}
