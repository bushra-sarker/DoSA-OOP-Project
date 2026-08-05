package c213.dosaoopproject.esha.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class RoleSelectionController {

    @FXML
    private void openClubExecutive(ActionEvent event) {
        loadDashboard("/c213/dosaoopproject/esha/ClubExecutiveDashboard.fxml",
                "DoSA OOP Project — Club Executive Dashboard", event);
    }

    @FXML
    private void openDoSACoordinator(ActionEvent event) {
        loadDashboard("/c213/dosaoopproject/esha/DoSACoordinatorDashboard.fxml",
                "DoSA OOP Project — DoSA Coordinator Dashboard", event);
    }

    private void loadDashboard(String fxmlPath, String title, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1000, 650));
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
