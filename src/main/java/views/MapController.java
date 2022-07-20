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
    ArrayList<Tile> tiles;

    @FXML
    Pane paneGame;
    @FXML
    Button bTest;

    public void generateMap() {
        Integer[][] matriz = new Integer[8][8];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                matriz[i][j] = (int) Math.floor(Math.random() * 6);
            }
        }
        map = matriz;
    }

    public void loadTile() {
        tiles = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Integer item = map[i][j];
                tiles.add(new Tile(item));
            }
        }
    }

    public void updateMap () {
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

    public Integer[][] getMap() {
        return map;
    }

    @FXML
    public void onClickUpdate(ActionEvent event) {
        if(event.getSource() == bTest) {
            updateMap();
        }
    }
}
