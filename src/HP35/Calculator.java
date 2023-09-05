package HP35;

public class Calculator {
    Item[] expr;
    int ip;
    static Stack stack;

    public Calculator(Item[] expr) {
        this.expr = expr;
        this.ip = 0;
        stack = new Static(expr.length);
        //this.stack = new Dynamic();
    }

    public int run() {
        for (Item item : expr) {
            if (item.getType() == ItemType.VALUE) {
                stack.push(item.getValue());
            } else {
                int valueTwo = stack.pop();
                int valueOne = stack.pop();
                int res = switch (item.getType()) {
                    case ADD -> valueOne + valueTwo;
                    case SUB -> valueOne - valueTwo;
                    case MUL -> valueOne * valueTwo;
                    case DIV -> valueOne / valueTwo;
                    default -> throw new IllegalStateException("Unexpected value: " + item.getType());
                };
                stack.push(res);
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {

        int exprNum = 0;

        Calculator calc = new Calculator(Expression.getExpr(exprNum));

        int res = calc.run();

        System.out.println(" Calculator: res = " + res);
    }
}
