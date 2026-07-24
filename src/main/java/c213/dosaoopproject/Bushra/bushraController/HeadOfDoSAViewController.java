package c213.dosaoopproject.Bushra.bushraController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;

public class HeadOfDoSAViewController {

    @FXML private StackPane contentArea;
    @FXML private Button btnDashboard;
    @FXML private Button btnPendingEventsCard;
    @FXML private Button btnBudgetManagement;

    private final String ACTIVE_STYLE = "-fx-background-color: #36649B; -fx-text-fill: white; -fx-background-radius: 10;";
    private final String INACTIVE_STYLE = "-fx-background-color: #004675; -fx-text-fill: white; -fx-background-radius: 7;";

    @FXML
    public void initialize() {
        // Load the Dashboard Overview sub-view on app launch
        loadSubView("/c213/dosaoopproject/bushraView/user7/DashboardOverview.fxml", btnDashboard);
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
        loadSubView("/c213/dosaoopproject/bushraView/user7/DashboardOverview.fxml", btnDashboard);
    }

    @FXML
    private void majorEventViewOA(ActionEvent event) {
        loadSubView("/c213/dosaoopproject/bushraView/user7/MajorEventsView.fxml", btnPendingEventsCard);
    }

    @FXML
    private void budgetManagementOA(ActionEvent event) {
        loadSubView("/c213/dosaoopproject/bushraView/BudgetManagementView.fxml", btnBudgetManagement);
    }

    // Placeholders
    @FXML private void notificationOA(ActionEvent event) {}
    @FXML private void scholarshipViewOA(ActionEvent event) {}
    @FXML private void disciplinaryAppealsOA(ActionEvent event) {}
    @FXML private void crisisViewOA(ActionEvent event) {}
    @FXML private void reportViewOA(ActionEvent event) {}
    @FXML private void exchangeViewOA(ActionEvent event) {}
    @FXML private void partnershipViewOA(ActionEvent event) {}
    @FXML private void transcriptViewOA(ActionEvent event) {}
    @FXML private void viewAllNotificationLinkOA(ActionEvent event) {}
    @FXML private void logOutOA(ActionEvent event) {}
}