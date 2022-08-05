package model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Node {
    private final Integer[][] map;
    private final Node parent;
    private final Integer deep;
    private final Integer[] positionAnswer = new Integer[2];
    private final Integer[] points = new Integer[2]; //[0] para maquina,[1] para jugador
    private Integer utility;
    private final Integer kind; //0 max, 1 min

    private final Knight[] knights = new Knight[2]; //[0] para maquina,[1] para jugador


    /**
     * Constructor source node
     *
     * @param map environment
     */
    public Node(Integer[][] map, Integer[][] positions) {
        this.map = map;
        this.parent = null;
        this.deep = 0;
        this.points[0] = 0;
        this.points[1] = 0;
        this.knights[0] = new Knight(positions[0][0], positions[0][1]);
        this.knights[1] = new Knight(positions[1][0], positions[0][1]);
        this.kind = 0;
        this.utility = Integer.MIN_VALUE;
    }


    /**
     * Constructor children nodes
     *
     * @param parent    parent node
     */
    public Node(Node parent, Integer[] position) {
        this.parent = parent;
        this.kind = parent.getKind() == 0 ? 1 : 0;
        this.utility = kind == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        this.deep = parent.getDeep() + 1;
        this.knights[0] = kind == 0 ? new Knight(parent.getKnights()[0].getPlace()[0], parent.getKnights()[0].getPlace()[1]) : new Knight(position[0], position[1]);
        this.knights[1] = kind == 0 ? new Knight(position[0], position[1]) : new Knight(parent.getKnights()[1].getPlace()[0], parent.getKnights()[1].getPlace()[1]);
        this.map = parent.nextMap(position);
        this.points[0] = kind == 0 ? parent.getPoints()[0] : parent.getPoints()[0] + parent.nextPoint(position);
        this.points[1] = kind == 0 ? parent.getPoints()[1] + parent.nextPoint(position) : parent.getPoints()[1];
    }

    public Knight[] getKnights() {
        return knights;
    }

    public Node getParent() {
        return parent;
    }

    public Integer getDeep() {
        return deep;
    }

    public Integer[] getPositionAnswer() {
        return positionAnswer;
    }

    public Integer[] getPoints() {
        return points;
    }

    public Integer getKind() {
        return kind;
    }

    public Integer[][] getMap() {
        return map;
    }

    public Integer getUtility() {
        return utility;
    }


    /**
     * In a map search robot place
     *
     * @param newMap environment
     * @return robot place
     */
    public static Integer[][] initialPlace(Integer[][] newMap) {
        if (newMap == null) {
            return null;
        }
        Integer[][] place = new Integer[2][2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (newMap[i][j] == 2) {
                    place[0][0] = i;
                    place[0][1] = j;
                } else if (newMap[i][j] == 1) {
                    place[1][0] = i;
                    place[1][1] = j;
                }
            }
        }
        return place;
    }

    public Integer nextPoint(Integer[] position) {
        if (map[position[0]][position[1]] > 2) {
            return map[position[0]][position[1]];
        }
        return 0;
    }

    /**
     * @param position position on next move
     * @return map after movement
     */
    public Integer[][] nextMap(Integer[] position) {
        Integer[][] nextMap = new Integer[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(map[i], 0, nextMap[i], 0, 8);
        }
        if (kind == 0) {
            nextMap[position[0]][position[1]] = 2;
            nextMap[knights[0].getPlace()[0]][knights[0].getPlace()[1]] = 0;
        } else {
            nextMap[position[0]][position[1]] = 1;
            nextMap[knights[1].getPlace()[0]][knights[1].getPlace()[1]] = 0;
        }
        return nextMap;
    }

    public void viewMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void useUtility(Integer value, Integer x, Integer y) {
        if (kind == 0) {
            if (value > utility) {
                utility = value;
                setSolution(x, y);
            }
        } else {
            if (value < utility) {
                utility = value;
                setSolution(x, y);
            }
        }
    }

    public void calcUtility() {
        utility = points[0] - points[1];
        setSolution(knights[0].getPlace()[0], knights[0].getPlace()[1]);
        resolveUtility();
    }

    public void resolveUtility() {
        Node node = this;
        while (node.getParent() != null) {
            node.getParent().useUtility(node.getUtility(), node.getKnights()[0].getPlace()[0], node.getKnights()[0].getPlace()[1]);
            node = node.getParent();
        }
    }

    public void setSolution(Integer x, Integer y) {
        positionAnswer[0] = x;
        positionAnswer[1] = y;
    }

    public ArrayList<Node> expandNode(){
        ArrayList<Integer[]> list = knights[kind].moveList(map);
        ArrayList<Node> nodes = new ArrayList<>();
        for(Integer[] place: list){
            nodes.add(new Node(this, place));
        }
        return nodes;
    }
}
