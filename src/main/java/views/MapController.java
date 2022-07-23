package views;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MapController {
    private final Integer[][] map = new Integer[8][8];
    private ArrayList<Tile> tiles;

    @FXML
    Pane paneGame;
    @FXML
    Button bTest;

    /**
     * Randomly generate a matriz of size 8x8
     */
    public void generateMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                map[i][j] = 0;
            }
        }
        for (int i = 5; i > 0; i--) {
            switch (i) {
                case 1, 2 -> refinarMatriz(i, 1);
                case 3 -> refinarMatriz(i, 2);
                case 4 -> refinarMatriz(i, 14);
                case 5 -> refinarMatriz(i, 5);
            }
        }
    }

    /**
     * Add missing numbers in matrix
     *
     * @param type   type of item
     * @param amount item quantity required
     */
    public void refinarMatriz(int type, int amount) {
        for (int k = 0; k < amount; ) {
            if (!(numberAppearances(type, amount, map))) {
                int i = (int) Math.floor(Math.random() * 8);
                int j = (int) Math.floor(Math.random() * 8);
                if (map[i][j] == 0) {
                    map[i][j] = type;
                    k++;
                }
            }
        }
    }

    /**
     * checks if a number exceeds the amount of occurrences in a matrix.
     *
     * @param type   type of item
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
        return val > amount;
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
