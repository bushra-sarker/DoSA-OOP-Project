package c213.dosaoopproject.fahmida.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Central helper for switching the window between FXML screens.
 *
 * <p>All of Fahmida's views live under
 * {@code /c213/dosaoopproject/fahmida/}, so callers pass just the view name
 * (e.g. {@code "U1_Dashboard"}) and this class builds the full resource path.</p>
 */
public final class SceneManager {

    private static final String BASE = "/c213/dosaoopproject/fahmida/";

    private static Stage stage;

    private SceneManager() {
    }

    /** Called once at start-up with the application's primary stage. */
    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static Stage getStage() {
        return stage;
    }

    /**
     * Loads {@code <viewName>.fxml} and shows it in the primary stage.
     *
     * @param viewName file name without path or extension, e.g. "LoginView"
     */
    public static void switchTo(String viewName) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    SceneManager.class.getResource(BASE + viewName + ".fxml"));
            Parent root = loader.load();
            if (stage.getScene() == null) {
                stage.setScene(new Scene(root));
            } else {
                stage.getScene().setRoot(root);
            }
            stage.show();
        } catch (IOException e) {
            // Simple, visible failure for a college project.
            throw new RuntimeException("Could not load view: " + viewName, e);
        }
    }
}
