package c213.dosaoopproject.fahmida.utility;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Central helper for switching the window between FXML screens.
 */
public class SceneManager {

    public static void navigate(ActionEvent actionEvent, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneManager.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA — Division of Student Affairs");
        stage.setScene(scene);
        stage.show();
    }

    public static void newStage(ActionEvent actionEvent, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneManager.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
