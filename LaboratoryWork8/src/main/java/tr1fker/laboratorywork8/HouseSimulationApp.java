package tr1fker.laboratorywork8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HouseSimulationApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HouseSimulation.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Домик с дымом");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}