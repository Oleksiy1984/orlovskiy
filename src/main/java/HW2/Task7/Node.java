package HW2.Task7;

/**
 * @author Alexey
 */
public class Node {
    private int value;

    private Node nextNode;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public Node(Node nextNode, int value) {
        this.nextNode = nextNode;
        this.value = value;
    }

    public Object getValue() {

        return value;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", nextNode=" + nextNode +
                '}';
    }
}
