package model;

import java.lang.reflect.Array;

public class Node {
    private Integer[][] map;
    private Node parent;
    private Integer deep;
    private Integer cost;
    private Integer item;
    private Integer[] place;

    private Integer points;
    private Integer heuristic = 0;


    /**
     * Constructor source node
     *
     * @param map   environment
     * @param place robot place
     * @param goals in map
     */
    public Node(Integer[][] map, Integer[] place, Integer[][] goals) {
        this.map = map;
        this.parent = null;
        this.deep = 0;
        this.cost = 0;
        this.item = 0;
        this.place = place;
        this.points = 0;
    }


    /**
     * Constructor child node
     *
     * @param parent    parent node
     * @param direction 1 right, 2 left, 3 up and other down
     */
    public Node(Node parent, Integer direction) {
        Integer[] nextPlace = parent.nextPlace(direction);
        this.parent = parent;
        this.deep = parent.getDeep() + 1;
        this.map = parent.nextMap(nextPlace);
        this.place = nextPlace;
    }


    public Node getParent() {
        return parent;
    }
    public Integer getDeep() {
        return deep;
    }

    public Integer[][] getMap() {
        return map;
    }

    public Integer[] getPlace() {
        return place;
    }

    public Integer getHeuristic() {
        return heuristic;
    }


    /**
     * In a map search robot place
     *
     * @param newMap environment
     * @return robot place
     */
    public static Integer[] initialPlace(Integer[][] newMap) {
        if (newMap == null) {
            return null;
        }
        Integer[] place = new Integer[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (newMap[i][j] == 2) {
                    place[0] = i;
                    place[1] = j;
                }
            }
        }
        return place;
    }

    /**
     * @param direction 1 right, 2 left, 3 up and other down
     * @return if a move can be made
     */
    public Boolean possibleMove(Integer direction) {
        return true;
    }


    /**
     * @param direction 1 right, 2 left, 3 up and other down
     * @return position on next move
     */
    public Integer[] nextPlace(Integer direction) {
        Integer[] nextPlace = new Integer[2];
        return nextPlace;
    }


    /**
     * @param nextPlace position on next move
     * @return map after movement
     */
    public Integer[][] nextMap(Integer[] nextPlace) {
        Integer[][] nextMap = new Integer[8][8];
        return nextMap;
    }

    public void viewMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * the sum of the manhattan distance to the goals, provided they exist
     *
     * @return value of heuristic
     */
    private Integer setHeuristic() {
       return 0;
    }
}
