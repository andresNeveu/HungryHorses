package views;

import hungryHorses.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;


public class HomeController implements Initializable {
    @FXML
    Pane pane;
    @FXML
    HBox scenePane;
    @FXML
    Button bInstruction;
    @FXML
    Button bStart;
    @FXML
    Button bExit;
    @FXML
    ComboBox<String> comboBox;

    Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCB();
    }

    public void initCB() {
        ObservableList<String> option =
                FXCollections.observableArrayList("","Principiante", "Amateur", "Experto");
        comboBox.setItems(option);
        comboBox.getSelectionModel().selectFirst();
    }

    public void selectEvent(ActionEvent actionEvent) {
        changeColorUpdateButton();
    }

    public void instructionEvent(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Instrucciones");
        alert.setHeaderText(null);
        alert.setContentText("Hungry Horses" + "\n" +
                "Se tendran 3 niveles de juego(Principiante, Amateur, Experto)." + "\n" +
                "Dentro del tablero habran diferentes tipos de casillas:" + "\n" +
                "\t14 casillas con césped"  + "\n" +
                "\t5 casillas con flores" + "\n" +
                "\t2 casillas con manzanas" + "\n" +
                "Las cuales tendran diferente puntuacion al ser comidas por un caballo:" + "\n" +
                "\t1 punto si es un césped"  + "\n" +
                "\t3 puntos si es una flor"  + "\n" +
                "\t5 puntos si es una manzana."+ "\n" +
                "¡El ganador sera quien obtenga el mayor numero de puntos posibles!");
        alert.showAndWait();
    }

    public void startEvent(ActionEvent actionEvent) {
        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
        App.setStage("/views/map");

    }

    public void exitEvent(ActionEvent actionEvent) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Salir");
        exitAlert.setHeaderText("Vas a salir");
        exitAlert.setContentText("¿Realmente quieres salir?");
        if(exitAlert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }
    private void changeColorUpdateButton(){
        if(checkCombo()){
            bStart.setStyle("-fx-background-color: lightgreen; ");
        } else{
            bStart.setStyle("-fx-background-color: silver; ");
        }
    }
    private boolean checkCombo(){
        return !comboBox.getSelectionModel().getSelectedItem().isEmpty();
    }
}
