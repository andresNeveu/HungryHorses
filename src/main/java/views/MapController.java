package views;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MapController {
    private Integer[][] map;
    private ArrayList<Tile> tiles;
    private int numApple = 0;
    private int numGrass = 0;
    private int numFlower = 0;
    private int whiteChess = 0;
    private int blackChess = 0;

    @FXML
    Pane paneGame;
    @FXML
    Button bTest;

    /**
     * Randomly generate a matriz of size 8x8
     */
    public void generateMap() {
        Integer[][] matriz = new Integer[8][8];
        for (int i = 0; i < 8; i +=1) {
            for (int j = 0; j < 8; j += 2) {
                int item = (int) Math.ceil(Math.random() * 6);
                if (blackChess < 1 && item == 1) {
                    matriz[i][j] = item;
                    blackChess++;
                }
                if (whiteChess < 1 && item == 2) {
                    matriz[i][j] = item;
                    whiteChess++;
                }
                if (numApple < 2 && item == 3) {
                    matriz[i][j] = item;
                    numApple++;
                }
                if (numGrass < 14 && item == 4) {
                    matriz[i][j] = item;
                    numGrass++;
                }
                if (numFlower < 5 && item == 5) {
                    matriz[i][j] = item;
                    numFlower++;
                }
            }
        }
        numApple = 0;
        whiteChess = 0;
        blackChess = 0;
        map = refinarMatriz(matriz, 4, 14, numGrass);
        map = refinarMatriz(map, 5, 5, numFlower);
    }

    /**
     * Add missing numbers in matrix
     * @param matriz matrix
     * @param type type of item
     * @param amount item quantity required
     * @param numItem current item quantity
     * @return Integer[8][8]
     */
    public Integer[][] refinarMatriz(Integer[][] matriz, int type, int amount, int numItem) {
        if (numberAppearances(type, amount, matriz)) {
            for (int i = 0; i < 8; i += 1) {
                for (int j = 0; j < 8; j += 1) {
                    if (matriz[i][j] == null && numItem < amount) {
                        matriz[i][j] = type;
                        numItem++;
                    }
                }
            }
        }
        if(type == 5) {
            numFlower = 0;
        } else {
            numGrass = 0;
        }
        return matriz;
    }

    /**
     * checks if a number exceeds the amount of occurrences in a matrix.
     * @param type type of item
     * @param amount item quantity required
     * @param matriz matrix
     * @return boolean
     */
    public boolean numberAppearances(int type, int amount, Integer[][] matriz) {
        int val = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (matriz[i][j] != null) {
                    if (matriz[i][j] == type) {
                        val++;
                    }
                }
            }
        }
        return val < amount;
    }

    /**
     * Generates a arrayList of tiles with the specified images on the map.
     */
    public void loadTile() {
        tiles = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Integer item = map[i][j];
                tiles.add(new Tile(item));
            }
        }
    }

    /**
     * Update map
     */
    public void updateMap() {
        ObservableList<Node> tilesMap = paneGame.getChildren();
        paneGame.getChildren().removeAll(tilesMap);
        generateMap();
        loadTile();
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(50 * (i % 8));
            tile.setTranslateY(50 * (i / 8));
            paneGame.getChildren().add(tile);
        }
    }

    @FXML
    public void onClickUpdate(ActionEvent event) {
        if (event.getSource() == bTest) {
            updateMap();
        }
    }
}
