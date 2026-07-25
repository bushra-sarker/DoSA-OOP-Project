package c213.dosaoopproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DoSAJavaFXapplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // 1. Point to your main Login view path (adjust path/name if it's in commonFXML)
        String fxmlPath = "/commonFXML/LoginView.fxml";

        URL resource = getClass().getResource(fxmlPath);

        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }

    // Standard entry point
    public static void main(String[] args) {
        launch(args);
    }
}