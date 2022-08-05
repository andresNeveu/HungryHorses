package model;

import views.MapController;

import java.util.ArrayList;
import java.util.Stack;

public class MinMax {

    private final Integer[][] map;
    private final Integer objective;

    public MinMax(Integer[][] map, Integer objective) {
        this.map = map;
        this.objective = objective;
    }

    public Node getSolution(){
        Stack<Node> stack = new Stack<>();
        Integer[][] places = Node.initialPlace(map);
        Node source = new Node(map, places);
        stack.push(source);
        while (true) {
            if(stack.isEmpty()){
                return source;
            }
            Node node = stack.pop();
            if (node.getDeep() == objective) {
                node.calcUtility();
            }
            else{
                ArrayList<Node> nodes = node.expandNode();
                for (Node aNode: nodes
                     ) {
                    stack.push(aNode);
                }
            }
        }
    }

    public static void main(String[] args) {
        MapController mapController = new MapController();
        mapController.generateInitMap();
        MinMax minMax = new MinMax(mapController.getMap(), 4);
        Node otherNode = new Node(mapController.getMap(), Node.initialPlace(mapController.getMap()));
        otherNode.viewMap();
        Node node = minMax.getSolution();
        System.out.println(node.getPositionAnswer()[0] + " : " + node.getPositionAnswer()[1]);
    }
}
