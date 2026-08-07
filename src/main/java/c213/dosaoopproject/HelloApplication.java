package c213.dosaoopproject;

import c213.dosaoopproject.fahmida.utility.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application entry point.
 *
 * <p>Registers the primary stage with {@link SceneManager} and opens the shared
 * Login screen. After a successful login the login controller routes the user to
 * their role-specific dashboard.</p>
 */
public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);
        stage.setTitle("DoSA — Division of Student Affairs");
        SceneManager.switchTo("LoginView");
    }

    public static void main(String[] args) {
        launch();
    }
}
