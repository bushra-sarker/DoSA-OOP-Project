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
        String fxmlPath = "/commonFXML/LoginView.fxml";
        URL resource = getClass().getResource(fxmlPath);

        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}