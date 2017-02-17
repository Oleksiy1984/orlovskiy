package HW2.Task7;



/**
 * Задание 6
 * @author Alexey
 */
public class Task7 {
    public static void print() {

            Node root = new Node(1);
            Node firstElement = new Node(2);
            root.setNextNode(firstElement);
            Node secondElement = new Node(3);
            firstElement.setNextNode(secondElement);
        System.out.println("Print linked list:");
            print(root);
        System.out.println("Print linked list in reverse order:");
            print(revers(root));
        }


    public static void print (Node node){
        Node elem = node;
        while(elem!= null){
            System.out.print(elem.getValue()+" elem + link: "+elem.getNextNode()+" | ");
            elem = elem.getNextNode();
        }
        System.out.println();
    }

    public static Node revers(Node node) {

        Node newRoot = null;
        Node elem = node;
        Node nextElemForIteration = null;
        Node nextElem = null;

        while(elem!= null){
            nextElem = newRoot;
            newRoot = elem;
            nextElemForIteration = elem.getNextNode();
            newRoot.setNextNode(nextElem);
            elem = nextElemForIteration;
        }
        return newRoot;
    }

}
