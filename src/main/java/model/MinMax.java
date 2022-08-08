package model;

import java.util.ArrayList;
import java.util.Stack;

public class MinMax {

    private final Integer[][] map;
    private final Integer objective;

    public MinMax(Integer[][] map, Integer objective) {
        this.map = map;
        this.objective = objective;
    }

    public Node getSolution(Integer pointIA, Integer pointMe, Integer[][] lastPlace) {
        Stack<Node> stack = new Stack<>();
        Integer[][] places = Node.initialPlace(map);
        Node source = new Node(map, places, pointIA, pointMe, lastPlace);
        stack.push(source);
        while (true) {
            if (stack.isEmpty()) {
                System.out.println(source.getUtility() + " = " + source.getPositionAnswer()[0] + " : " + source.getPositionAnswer()[1]);
                return source;
            }
            Node node = stack.pop();
            if (node.getDeep() == objective) {
                node.calcUtility();
            } else {
                ArrayList<Node> nodes = node.expandNode();
                for (Node aNode : nodes) {
                    stack.push(aNode);
                }
            }
        }
    }
}
