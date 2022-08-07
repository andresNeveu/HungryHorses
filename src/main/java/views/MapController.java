package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MinMax;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapController implements Initializable {
    private final Integer[][] map = new Integer[8][8];
    private ArrayList<Tile> tiles;
    @FXML
    private Label labelIA;
    @FXML
    private Label labelMyPoints;
    @FXML
    private Pane paneGame;
    @FXML
    private Button bStart;
    @FXML
    private Button bInstruction;
    @FXML
    private Button bClean;
    @FXML
    private Button bExit;
    @FXML
    private ComboBox<String> cbSelect;
    private final int[] place = new int[2];
    private int totalPoints = 39;
    private int pointsIA = 0;
    private int pointMe = 0;
    private int level;
    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMap();
        findPlace();
        initCB();
    }

    /**
     * Init combobox options
     */
    public void initCB() {
        ObservableList<String> option =
                FXCollections.observableArrayList("", "Principiante", "Amateur", "Experto");
        cbSelect.setItems(option);
        cbSelect.getSelectionModel().selectFirst();
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
                //setupTileEvent(tile);
                tiles.add(tile);
            }
        }
    }

    /**
     * Clean current map a paint a new map
     */
    public void newMap() {
        findPlace();
        ObservableList<Node> tilesMap = paneGame.getChildren();
        paneGame.getChildren().removeAll(tilesMap);
        paintMap();
    }

    /**
     * Paint a new map
     */
    public void paintMap() {
        loadTile();
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(50 * (i % 8.0));
            tile.setTranslateY(50 * (i / 8.0));
            playPlayer(tile);
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

    /**
     * Find place of player piece
     */
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

    /**
     * Determines if the player's piece can be moved onto a tile.
     * @param newPlace Integer[] target position.
     * @return boolean true if it can be moved, false otherwise.
     */
    public boolean possibleMovePlayer(Integer[] newPlace) {
        int x = (newPlace[0] > place[0]) ? newPlace[0] - place[0] : place[0] - newPlace[0];
        int y = (newPlace[1] > place[1]) ? newPlace[1] - place[1] : place[1] - newPlace[1];
        return ((x == 1 && y == 2) || (x == 2 && y == 1)) && (map[newPlace[0]][newPlace[1]] != 2);
    }

    /**
     * Configure mouse event on tiles that allows player movement.
     * @param tile Tile
     */
    private void playPlayer(Tile tile) {
        tile.setOnMouseClicked(mouseEvent -> {
            Integer[] tilePLace = tile.getPlace();
            if (possibleMovePlayer(tilePLace)) {
                winner();
                int point = map[tilePLace[0]][tilePLace[1]];
                pointMe += points(point);
                totalPoints -= points(point);
                updatePoints();
                map[tilePLace[0]][tilePLace[1]] = 1;
                map[place[0]][place[1]] = 0;
                newMap();
                playIA();
            }
        });

    }

    /**
     * Play IA using minimax algorithm.
     */
    public void playIA() {
        model.Node node;
        Integer[] playWhite;
        MinMax minMax = new MinMax(map, level);
        node = minMax.getSolution();
        playWhite = node.getPositionAnswer();
        totalPoints -= points(map[playWhite[0]][playWhite[1]]);
        pointsIA += points(map[playWhite[0]][playWhite[1]]);
        updatePoints();
        map[playWhite[0]][playWhite[1]] = 2;
        Integer[] place2 = node.getKnights()[0].getPlace();
        map[place2[0]][place2[1]] = 0;
        newMap();
    }

    /**
     * Determine the level of difficulty.
     * @param lvl String
     */
    public void setLevel(String lvl) {
        if (lvl.equalsIgnoreCase("Principiante")) {
            level = 2;
        }
        if (lvl.equalsIgnoreCase("Amateur")) {
            level = 4;
        }
        if (lvl.equalsIgnoreCase("Experto")) {
            level = 6;
        }
    }

    /**
     * Start game
     */
    public void game() {
        totalPoints = 39;
        setLevel(cbSelect.getSelectionModel().getSelectedItem());
        playIA();
    }

    /**
     *
     * @param type
     * @return
     */
    public int points(int type) {
        int pst = 0;
        switch (type) {
            case 3 -> pst = 5;
            case 4 -> pst = 1;
            case 5 -> pst = 3;
        }
        return pst;
    }


    public void instructionEvent() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Instrucciones");
        alert.setHeaderText(null);
        alert.setContentText("""
                Hungry Horses
                Se tendran 3 niveles de juego(Principiante, Amateur, Experto).
                Dentro del tablero habran diferentes tipos de casillas:
                \t14 casillas con césped
                \t5 casillas con flores
                \t2 casillas con manzanas
                Las cuales tendran diferente puntuacion al ser comidas por un caballo:
                \t1 punto si es un césped
                \t3 puntos si es una flor
                \t5 puntos si es una manzana.
                ¡El ganador sera quien obtenga el mayor numero de puntos posibles!""");
        alert.showAndWait();
    }

    public void exitEvent() {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Salir");
        exitAlert.setHeaderText("Vas a salir");
        exitAlert.setContentText("¿Realmente quieres salir?");
        if (exitAlert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) paneGame.getScene().getWindow();
            stage.close();
        }
    }

    private void changeColorUpdateButton() {
        if (checkCombo()) {
            bStart.setDisable(false);
            paneGame.setDisable(false);
            bStart.setStyle("-fx-background-color: lightgreen; ");
        }
    }

    private boolean checkCombo() {
        return !cbSelect.getSelectionModel().getSelectedItem().isEmpty();
    }

    public void resetGUI() {
        cbSelect.getSelectionModel().selectFirst();
        paneGame.setDisable(true);
        bStart.setDisable(true);
        bStart.setStyle("-fx-background-color: silver;");
        labelIA.setText("0");
        labelMyPoints.setText("0");
        pointMe = 0;
        pointsIA = 0;
        totalPoints = 39;
        loadMap();
    }

    public void updatePoints() {
        labelIA.setText(" " + pointsIA);
        labelMyPoints.setText(" " + pointMe);
    }

    /**
     * Determine the winner
     */
    public void winner() {
        boolean total = totalPoints == 0;
        boolean IA = pointsIA > pointMe + totalPoints;
        boolean Me = pointMe > pointsIA + totalPoints;
        String winner;
        winner = pointsIA > pointMe ? "IA" : "ME";
        if (total || IA || Me) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Ganador");
            alert.setHeaderText(null);
            alert.setContentText(winner);
            alert.showAndWait();
            paneGame.setDisable(true);
        }
    }

    @FXML
    public void onClick(ActionEvent event) {
        if (event.getSource() == bStart) {
            bStart.setDisable(true);
            cbSelect.setDisable(true);
            game();
        }
        if (event.getSource() == cbSelect) {
            changeColorUpdateButton();
        }
        if (event.getSource() == bClean) {
            resetGUI();
        }
        if (event.getSource() == bInstruction) {
            instructionEvent();
        }
        if (event.getSource() == bExit) {
            exitEvent();
        }
    }
}