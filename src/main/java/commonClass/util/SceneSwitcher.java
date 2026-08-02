package commonClass.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    // --- SWAP CONTENT INSIDE A DASHBOARD SHELL (Use for contentArea) ---
    public static FXMLLoader loadIntoContentArea(Pane contentArea, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            Parent view = loader.load();

            contentArea.getChildren().clear();
            contentArea.getChildren().add(view);

            return loader; // Return loader in case you need to access controller
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // --- FULL STAGE SWITCHING (Use for Logouts, Role Switching, or New Windows) ---
    public static void switchTo(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}