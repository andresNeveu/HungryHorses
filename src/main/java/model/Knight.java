package model;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import views.MapController;

import java.util.ArrayList;
import java.util.Objects;

public class Knight {
    private Integer[] place;
    private int x;
    private int y;
    ArrayList<Integer[]> possibleMoves;

    public Knight(Integer[] place) {
        this.place = place;
        this.x = place[0];
        this.y = place[1];

    }

    private void addEvent() {
    }

    public Integer[] newPlace(int direction, Integer[][] map) {
        Integer[] nextPlace = new Integer[2];
        if (possibleMoves(direction, map)) {
            switch (direction) {
                //2,3
                case 1: //upper right - up
                    nextPlace[0] = place[0] - 2;
                    nextPlace[1] = place[1] + 1;
                    break;
                case 2: //upper right - down
                    nextPlace[0] = place[0] - 1;
                    nextPlace[1] = place[1] + 2;
                    break;
                case 3: //upper left - up
                    nextPlace[0] = place[0] - 2;
                    nextPlace[1] = place[1] - 1;
                    break;
                case 4: //upper left - down
                    nextPlace[0] = place[0] - 1;
                    nextPlace[1] = place[1] - 2;
                    break;
                case 5: //upper right - up
                    nextPlace[0] = place[0] + 1;
                    nextPlace[1] = place[1] + 2;
                    break;
                case 6: //upper right - down
                    nextPlace[0] = place[0] + 2;
                    nextPlace[1] = place[1] + 1;
                    break;
                case 7: //upper left - up
                    nextPlace[0] = place[0] + 1;
                    nextPlace[1] = place[1] - 2;
                    break;
                case 8: //upper left - down
                    nextPlace[0] = place[0] + 2;
                    nextPlace[1] = place[1] - 1;
                    break;
            }
        }
        return nextPlace;
    }

    public ArrayList<Integer[]> moveList(Integer[][] map) {
        ArrayList<Integer[]> move = new ArrayList<>();
        Integer[] temp = new Integer[2];
        for (int i = 1; i < 9; i++) {
            temp = newPlace(i,map);
            if (temp[0] != null && temp[1] != null) {
                move.add(temp);
            }
        }
        return move;
    }


    public boolean possibleMoves(Integer direction, Integer[][] map) {
        switch (direction) {
            case 1: //upper right - up
                if (place[0] == 0 || (place[0] == 7) || (place[0] - 1 == 0)
                        || (map[place[0] - 2][place[1] + 1] != 1)) {
                    return false;
                }
                break;
            case 2: //upper right - down
                if (place[0] == 0 || (place[0] == 7) || (place[1] + 1 == 7)
                        || (map[place[0] - 1][place[1] + 2] != 1)) {
                    return false;
                }
                break;
            case 3: //upper left - up
                if (place[0] == 0 || place[1] == 0 || (place[0] - 1 == 0)
                        || (map[place[0] - 2][place[1] - 1] != 1)) {
                    return false;
                }
                break;
            case 4: //upper left - down
                if (place[0] == 0 || place[1] == 0 || (place[1] - 1 == 0) ||
                        (map[place[0] - 1][place[1] - 2] != 1)) {
                    return false;
                }
                break;
            case 5: //lower right - up
                if (place[0] == 7 || (place[1] == 7) || (place[1] + 1 == 7)
                        || (map[place[0] + 1][place[1] + 2] != 1)) {
                    return false;
                }
                break;
            case 6: //lower right - down
                if (place[0] == 7 || (place[1] == 7) || (place[0] + 1 == 7)
                        || (map[place[0] + 2][place[1] + 1] != 1)) {
                    return false;
                }
                break;
            case 7: //lower left - up
                if (place[0] == 7 || (place[1] == 0) || (place[1] - 1 == 0)
                        || (map[place[0] + 1][place[1] - 2] != 1)) {
                    return false;
                }
                break;
            case 8: //lower left - down
                if (place[0] == 7 || (place[1] == 0) || (place[0] + 1 == 7)
                        || (map[place[0] + 2][place[1] - 1] != 1)) {
                    return false;
                }
                break;
        }
        return true;
    }
}
