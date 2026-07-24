package c213.dosaoopproject.Bushra.bushraController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;

public class StudentWelfareOfficerViewController {

    @FXML private StackPane contentArea;
    @FXML private Button btnDashboard;
    @FXML private Button btnScholarshipMgmt;

    private final String ACTIVE_STYLE = "-fx-background-color: #36649B; -fx-text-fill: white; -fx-background-radius: 10;";
    private final String INACTIVE_STYLE = "-fx-background-color: #004675; -fx-text-fill: white; -fx-background-radius: 7;";
    @FXML
    private ImageView ppImageView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label userIdLabel;

    @FXML
    public void initialize() {
        // Event-2: Default view on login load
        loadSubView("/c213/dosaoopproject/bushraView/user8/DashboardOverview.fxml", btnDashboard);
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
        loadSubView("/c213/dosaoopproject/bushraView/user8/DashboardOverview.fxml", btnDashboard);
    }

    // Event-3: Click Scholarship Management
    @FXML
    private void scholarshipMgmtOA(ActionEvent event) {
        loadSubView("/c213/dosaoopproject/bushraView/user8/ScholarshipManagement.fxml", btnScholarshipMgmt);
    }

    @FXML
    private void logOutOA(ActionEvent event) {
        // Handle Logout logic here
    }
}