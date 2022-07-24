package views;

import hungryHorses.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class HomeController {
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
    Stage stage;
    public void instructionEvent(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Instrucciones");
        alert.setHeaderText(null);
        alert.setContentText("Infromatin Message");
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
}
