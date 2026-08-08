package c213.dosaoopproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Application entry point.
 *
 * <p>Opens the shared Login screen. After a successful login the login controller
 * routes the user to their role-specific dashboard.</p>
 */
public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/c213/dosaoopproject/fahmida/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DoSA — Division of Student Affairs");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
