package binary_tree;

public class Node {

    public Integer key;
    public Integer value;
    public Node left, right;

    public Node(Integer key, Integer value) {
        this.key = key;
        this.value = value;
        this.left = this.right = null;
    }
}
