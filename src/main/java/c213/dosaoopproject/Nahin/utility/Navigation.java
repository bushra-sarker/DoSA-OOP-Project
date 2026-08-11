package c213.dosaoopproject.Nahin.utility;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    public static void navigate(ActionEvent actionEvent, String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource(fxml));
            if (fxmlLoader.getLocation() == null) {
                return;
            }
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("DoSA Management Simulation");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void newStage(ActionEvent actionEvent, String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}