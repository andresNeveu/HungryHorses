package views;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MapController implements Initializable {
    private final Integer[][] map = new Integer[8][8];
    private ArrayList<Tile> tiles;
    private Image imgBlackChess = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/blackChess.png")));

    @FXML
    private Pane paneGame;
    @FXML
    private Button bTest;
    private Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/blackChess.png")));
    private int[] place = new int[2];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMap();
    }


    /**
     * Randomly generate a matriz of size 8x8
     */
    public void generateInitMap() {
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
        findPlace();
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
                Integer[] place = new Integer[2];
                place[0] = i;
                place[1] = j;
                Tile tile = new Tile(item, place);
                setupTileEvent(tile);
                tiles.add(tile);
            }
        }
    }

    public void newMap() {
        findPlace();
        ObservableList<Node> tilesMap = paneGame.getChildren();
        paneGame.getChildren().removeAll(tilesMap);
        paintMap();
    }

    public void paintMap() {
        loadTile();
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(50 * (i % 8));
            tile.setTranslateY(50 * (i / 8));
            paneGame.getChildren().add(tile);
        }
    }

    /**
     * Update map
     */
    public void loadMap() {
        ObservableList<Node> tilesMap = paneGame.getChildren();
        paneGame.getChildren().removeAll(tilesMap);
        generateInitMap();
        paintMap();
    }

    public void findPlace() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == 1) {
                    place[0] = i;
                    place[1] = j;
                }
            }
        }
    }

    public boolean possibleMovePlayer(Integer[] newPlace) {
        Integer x = (newPlace[0] > place[0]) ? newPlace[0] - place[0] : place[0] - newPlace[0];
        Integer y = (newPlace[1] > place[1]) ? newPlace[1] - place[1] : place[1] - newPlace[1];
        if (((x == 1) && (y == 2)) || ((x == 2) && (y == 1))) {
            return true;
        }
        return false;
    }

    @FXML
    public void onClickUpdate(ActionEvent event) {
        if (event.getSource() == bTest) {
            loadMap();
        }
    }

    private void setupTileEvent(Tile tile) {
        tile.setOnMouseClicked(mouseEvent -> {
            Integer[] tempPLace = tile.getPlace();
            if(possibleMovePlayer(tempPLace)) {
                System.out.println("x: "  + tempPLace[0]);
                System.out.println("y: " + tempPLace[1]);
                map[tempPLace[0]][tempPLace[1]] = 1;
                map[place[0]][place[1]] = 0;
                newMap();
            }
        });
    }

    public Integer[][] getMap() {
        return map;
    }
}