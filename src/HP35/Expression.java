package HP35;

public class Expression {

    public static Item[] getExpr(int num) {
        Item[] expr = new Item[0];

        if (num == 0) {
            expr = new Item[] {
                    new Item(10),
                    new Item(2),
                    new Item(5),
                    new Item(ItemType.SUB),
                    new Item(ItemType.MUL)
            };
        }

        if (num == 1) {
            expr = new Item[] {
                    new Item(1),
                    new Item(2),
                    new Item(3),
                    new Item(4),
                    new Item(5),
                    new Item(6),
                    new Item(7),
                    new Item(8),
                    new Item(9),
                    new Item(10),
                    new Item(11),
                    new Item(12),
                    new Item(13),
                    new Item(14),
                    new Item(15),
                    new Item(16),
                    new Item(ItemType.ADD),
                    new Item(ItemType.MUL),
                    new Item(ItemType.ADD),
                    new Item(ItemType.MUL),
                    new Item(ItemType.ADD),
                    new Item(ItemType.MUL),
                    new Item(ItemType.ADD),
                    new Item(ItemType.MUL),
                    new Item(ItemType.ADD),
                    new Item(ItemType.MUL),
                    new Item(ItemType.ADD),
                    new Item(ItemType.MUL),
                    new Item(ItemType.ADD),
                    new Item(ItemType.MUL),
                    new Item(ItemType.ADD)
            };
        }

        if (num == 2) {
            expr = new Item[] {
                    new Item(2),
                    new Item(7),
                    new Item(ItemType.SUB),
                    new Item(4),
                    new Item(ItemType.MUL)
            };
        }

        return expr;
    }
}
