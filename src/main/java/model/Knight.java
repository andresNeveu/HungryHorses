package model;

import java.util.ArrayList;

public class Knight {
    private final Integer[] place = new Integer[2];

    public Knight(int x, int y) {
        place[0] = x;
        place[1] = y;
    }

    public Integer[] newPlace(int direction, Integer[][] map) {
        Integer[] nextPlace = new Integer[2];
        if (possibleMoves(direction, map)) {
            switch (direction) {
                //2,3
                case 1 -> { //upper right - up
                    nextPlace[0] = place[0] - 2;
                    nextPlace[1] = place[1] + 1;
                }
                case 2 -> { //upper right - down
                    nextPlace[0] = place[0] - 1;
                    nextPlace[1] = place[1] + 2;
                }
                case 3 -> { //upper left - up
                    nextPlace[0] = place[0] - 2;
                    nextPlace[1] = place[1] - 1;
                }
                case 4 -> { //upper left - down
                    nextPlace[0] = place[0] - 1;
                    nextPlace[1] = place[1] - 2;
                }
                case 5 -> { //upper right - up
                    nextPlace[0] = place[0] + 1;
                    nextPlace[1] = place[1] + 2;
                }
                case 6 -> { //upper right - down
                    nextPlace[0] = place[0] + 2;
                    nextPlace[1] = place[1] + 1;
                }
                case 7 -> { //upper left - up
                    nextPlace[0] = place[0] + 1;
                    nextPlace[1] = place[1] - 2;
                }
                case 8 -> { //upper left - down
                    nextPlace[0] = place[0] + 2;
                    nextPlace[1] = place[1] - 1;
                }
            }
        }
        return nextPlace;
    }

    public ArrayList<Integer[]> moveList(Integer[][] map) {
        ArrayList<Integer[]> move = new ArrayList<>();
        Integer[] temp;
        for (int i = 1; i < 9; i++) {
            temp = newPlace(i, map);
            if (temp[0] != null && temp[1] != null) {
                move.add(temp);
            }
        }
        return move;
    }

    public boolean possibleMoves(Integer direction, Integer[][] map) {
        switch (direction) {
            case 1 -> { //upper right - up
                if (place[0] == 0 || (place[1] == 7) || (place[0] - 1 == 0)) {
                    return false;
                }
                if (place[0] > 1 && place[1] < 7) {
                    if (map[place[0] - 2][place[1] + 1] == 1) {
                        return false;
                    }
                }
            }
            case 2 -> { //upper right - down
                if (place[0] == 0 || (place[1] == 7) || (place[1] + 1 == 7)) {
                    return false;
                }
                if (place[0] > 0 && place[1] < 6) {
                    if (map[place[0] - 1][place[1] + 2] == 1) {
                        return false;
                    }
                }
            }
            case 3 -> { //upper left - up
                if (place[0] == 0 || place[1] == 0 || (place[0] - 1 == 0)) {
                    return false;
                }
                if (place[0] > 1 && place[1] > 0) {
                    if (map[place[0] - 2][place[1] - 1] == 1) {
                        return false;
                    }
                }
            }
            case 4 -> { //upper left - down
                if (place[0] == 0 || place[1] == 0 || (place[1] - 1 == 0)) {
                    return false;
                }
                if (place[0] > 0 && place[1] > 1) {
                    if (map[place[0] - 1][place[1] - 2] == 1) {
                        return false;
                    }
                }
            }
            case 5 -> { //lower right - up
                if (place[0] == 7 || (place[1] == 7) || (place[1] + 1 == 7)) {
                    return false;
                }
                if (place[0] < 7 && place[1] < 6) {
                    if (map[place[0] + 1][place[1] + 2] == 1) {
                        return false;
                    }
                }
            }
            case 6 -> { //lower right - down
                if (place[0] == 7 || (place[1] == 7) || (place[0] + 1 == 7)) {
                    return false;
                }
                if (place[0] < 6 && place[1] < 7) {
                    if (map[place[0] + 2][place[1] + 1] == 1) {
                        return false;
                    }
                }
            }
            case 7 -> { //lower left - up
                if (place[0] == 7 || (place[1] == 0) || (place[1] - 1 == 0)) {
                    return false;
                }
                if (place[0] < 7 && place[1] > 1) {
                    if (map[place[0] + 1][place[1] - 2] == 1) {
                        return false;
                    }
                }
            }
            case 8 -> { //lower left - down
                if (place[0] == 7 || (place[1] == 0) || (place[0] + 1 == 7)) {
                    return false;
                }
                if (place[0] < 6 && place[1] > 0) {
                    if (map[place[0] + 2][place[1] - 1] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Integer[] getPlace() {
        return place;
    }
}
