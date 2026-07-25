package c213.dosaoopproject.Bushra.Controller.U07;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class U07_HeadOfDoSAViewController {

    @FXML private StackPane contentArea;
    @FXML private Button btnDashboard;
    @FXML private Button btnPendingEventsCard;
    @FXML private Button btnBudgetManagement;

    private final String ACTIVE_STYLE = "-fx-background-color: #36649B; -fx-text-fill: white; -fx-background-radius: 10;";
    private final String INACTIVE_STYLE = "-fx-background-color: #004675; -fx-text-fill: white; -fx-background-radius: 7;";

    @FXML
    public void initialize() {
        // Load the Dashboard Overview sub-view on app launch
        loadSubView("/c213/dosaoopproject/bushraView/U07/U07HOD_DashboardOverview.fxml", btnDashboard);
    }

    private void loadSubView(String fxmlPath, Button activeBtn) {
        URL resource = getClass().getResource(fxmlPath);
        if (resource == null) {
            System.err.println("Error: Cannot find FXML at: " + fxmlPath);
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
            System.err.println("Failed to load view: " + fxmlPath);
            e.printStackTrace();
        }
    }

    private void resetButtonStyles() {
        if (btnDashboard != null) btnDashboard.setStyle(INACTIVE_STYLE);
        if (btnPendingEventsCard != null) btnPendingEventsCard.setStyle(INACTIVE_STYLE);
        if (btnBudgetManagement != null) btnBudgetManagement.setStyle(INACTIVE_STYLE);
    }

    // Nav Handlers
    @FXML
    private void dashboardViewOA(ActionEvent event) {
        loadSubView("/c213/dosaoopproject/bushraView/U07/U07HOD_DashboardOverview.fxml", btnDashboard);
    }

    @FXML
    private void majorEventViewOA(ActionEvent event) {
        loadSubView("/c213/dosaoopproject/bushraView/U07/U07G1_MajorEventsView.fxml", btnPendingEventsCard);
    }

    @FXML
    private void budgetManagementOA(ActionEvent event) {
        loadSubView("", btnBudgetManagement);
    }

    @FXML
    private void logOutOA(ActionEvent event) {
        try {
            URL resource = getClass().getResource("/commonFXML/LoginView.fxml");
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

    // Placeholders for additional UI bindings
    @FXML private void notificationOA(ActionEvent event) {}
    @FXML private void scholarshipViewOA(ActionEvent event) {}
    @FXML private void disciplinaryAppealsOA(ActionEvent event) {}
    @FXML private void crisisViewOA(ActionEvent event) {}
    @FXML private void reportViewOA(ActionEvent event) {}
    @FXML private void exchangeViewOA(ActionEvent event) {}
    @FXML private void partnershipViewOA(ActionEvent event) {}
    @FXML private void transcriptViewOA(ActionEvent event) {}
    @FXML private void viewAllNotificationLinkOA(ActionEvent event) {}
}