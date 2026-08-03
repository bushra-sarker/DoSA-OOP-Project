package c213.dosaoopproject.commonClass.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    //Replaces the content inside a dashboard container (Sub-view switching).

    public static FXMLLoader switchContent(Pane contentArea, String fxmlPath) {
        if (contentArea == null) {
            throw new IllegalArgumentException("Target contentArea Pane cannot be null.");
        }

        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            Parent root = loader.load();

            contentArea.getChildren().setAll(root);

            return loader;
        } catch (IOException e) {
            throw new RuntimeException("Unable to load FXML into content area: " + fxmlPath, e);
        }
    }

    //Switches the entire Stage (e.g., Login -> Dashboard or Logout).

    public static void switchScene(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Unable to switch scene: " + fxmlPath, e);
        }
    }
}