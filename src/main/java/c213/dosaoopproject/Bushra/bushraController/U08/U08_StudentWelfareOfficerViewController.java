package c213.dosaoopproject.Bushra.bushraController.U08;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView; // Corrected JavaFX import
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class U08_StudentWelfareOfficerViewController {

    @FXML private StackPane contentArea;
    @FXML private Button btnDashboard;
    @FXML private Button btnScholarshipMgmt;

    private final String ACTIVE_STYLE = "-fx-background-color: #36649B; -fx-text-fill: white; -fx-background-radius: 10;";
    private final String INACTIVE_STYLE = "-fx-background-color: #004675; -fx-text-fill: white; -fx-background-radius: 7;";

    @FXML private ImageView ppImageView;
    @FXML private Label nameLabel;
    @FXML private Label userIdLabel;

    @FXML
    public void initialize() {
        // Default view on login load
        loadSubView("/c213/dosaoopproject/bushraView/U08/U08SWO_DashboardOverview.fxml", btnDashboard);
    }

    private void loadSubView(String fxmlPath, Button activeBtn) {
        URL resource = getClass().getResource(fxmlPath);
        if (resource == null) {
            System.err.println("Error: FXML not found at " + fxmlPath);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(resource);
            Parent node = loader.load();

            contentArea.getChildren().clear();
            contentArea.getChildren().add(node);

            resetButtonStyles();
            if (activeBtn != null) {
                activeBtn.setStyle(ACTIVE_STYLE);
            }
        } catch (IOException e) {
            System.err.println("Failed to load sub-view: " + fxmlPath);
            e.printStackTrace();
        }
    }

    private void resetButtonStyles() {
        if (btnDashboard != null) btnDashboard.setStyle(INACTIVE_STYLE);
        if (btnScholarshipMgmt != null) btnScholarshipMgmt.setStyle(INACTIVE_STYLE);
    }

    @FXML
    private void dashboardViewOA(ActionEvent event) {
        loadSubView("/c213/dosaoopproject/bushraView/U08/U08SWO_DashboardOverview.fxml", btnDashboard);
    }

    @FXML
    private void scholarshipMgmtOA(ActionEvent event) {
        loadSubView("/c213/dosaoopproject/bushraView/U08/U08G1_ScholarshipManagement.fxml", btnScholarshipMgmt);
    }

    @FXML
    private void logOutOA(ActionEvent event) {
        try {
            URL resource = getClass().getResource("/c213/dosaoopproject/commonFXML/LoginView.fxml");
            if (resource == null) {
                System.err.println("Error: LoginView.fxml not found!");
                return;
            }
            Parent root = FXMLLoader.load(resource);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("DoSA Management System - Login");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to return to Login View.");
            e.printStackTrace();
        }
    }
}