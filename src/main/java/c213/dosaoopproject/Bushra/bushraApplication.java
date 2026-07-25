package c213.dosaoopproject.Bushra;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class bushraApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Start at the common Login Screen
        // ✅ Use absolute path starting with /
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/commonFXML/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

        stage.setTitle("DoSA Management System - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}