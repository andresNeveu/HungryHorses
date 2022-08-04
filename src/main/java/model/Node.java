package model;

public class Node {
    private Integer[][] map;
    private Node parent;
    private Integer deep;
    private Integer[][] positions = new Integer[2][2];
    private Integer[] points = new Integer[2];
    private Integer utility = 0;
    private Integer kind; //0 max, 1 min


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
        this.positions = positions;
        this.kind = 0;
        this.utility = Integer.MIN_VALUE;
    }


    /**
     * Constructor children nodes
     *
     * @param parent    parent node
     * @param direction 1 right, 2 left, 3 up and other down
     */
    public Node(Node parent, Integer direction) {
        this.parent = parent;
        this.kind = parent.getKind() == 0? 1 : 0;
        this.utility = kind == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        this.deep = parent.getDeep() + 1;
        //this.map = parent.nextMap();
    }


    public Node getParent() {
        return parent;
    }

    public Integer getDeep() {
        return deep;
    }

    public Integer[][] getPositions() {
        return positions;
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
}
