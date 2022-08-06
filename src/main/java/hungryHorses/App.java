package hungryHorses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(loadFXML("/views/map"));
        stage.setScene(scene);
        stage.setTitle("Hungry Horses");
        stage.show();
        stage.setResizable(false);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml+ ".fxml"));
        return fxmlLoader.load();
    }
    public static void setStage(String addressFxml){
        try{
            Scene newScene;
            newScene = new Scene(loadFXML(addressFxml));
            Stage newStage = new Stage();
            newStage.setScene(newScene);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
