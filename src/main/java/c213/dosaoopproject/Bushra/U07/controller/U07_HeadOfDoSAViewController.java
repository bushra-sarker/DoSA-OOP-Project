package c213.dosaoopproject.Bushra.U07.controller;

import commonClass.model.User;
import commonClass.util.SceneSwitcher;
import commonClass.util.SessionManager;
import commonClass.util.SubViewSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class U07_HeadOfDoSAViewController {

    @FXML private StackPane contentArea;
    @FXML private Button btnExchangePrograms;
    @FXML private Button btnDisciplinaryAppeals;
    @FXML private Button btnBudgetManagement;
    @FXML private Button btnCrisisManagement;
    @FXML private ImageView ppImageView;
    @FXML private Button btnExternalRelations;
    @FXML private Button btnPendingScholarshipsCard;
    @FXML private Button btnDashboard;
    @FXML private Button btnReportsAnalytics;
    @FXML private TextField searchOFCRTF;
    @FXML private Button btnCoCurricularTranscripts;
    @FXML private Label nameLabel;
    @FXML private Label userIdLabel;
    @FXML private Button btnPendingEventsCard;

    // FXML file path constants for subviews
    private static final String DASHBOARD_SUBVIEW_FXML = "/c213/dosaoopproject/Bushra/U07/U07_dashboardOverview.fxml";
    private static final String MAJOR_EVENT_SUBVIEW_FXML = "/c213/dosaoopproject/Bushra/U07/U07G1_eventApprovalQueue.fxml";

    @FXML
    public void initialize() {
        // 1. Populate user info from SessionManager
        User user = SessionManager.getInstance().getCurrentUser();
        if (user != null) {
            if (userIdLabel != null) {
                userIdLabel.setText("ID: " + user.getUserId());
            }
            if (nameLabel != null) {
                nameLabel.setText(user.getRole());
            }
        }

        // 2. Load the default Dashboard subview into contentArea on launch
        SubViewSwitcher.loadSubView(contentArea, DASHBOARD_SUBVIEW_FXML);
    }

    // --- Action Handlers ---

    @FXML
    public void dashboardViewOA(ActionEvent actionEvent) {
        // Loads default dashboard view back into contentArea
        SubViewSwitcher.loadSubView(contentArea, DASHBOARD_SUBVIEW_FXML);
    }

    @FXML
    public void majorEventViewOA(ActionEvent actionEvent) {
        SubViewSwitcher.loadSubView(contentArea, MAJOR_EVENT_SUBVIEW_FXML);
    }


    @FXML
    public void logOutOA(ActionEvent actionEvent) {
        SessionManager.getInstance().setCurrentUser(null);
        SceneSwitcher.switchScene(actionEvent, "/commonFXML/LoginView.fxml", "login");
    }

    // --- Other Handlers (Add SubViewSwitcher calls here as you build them) ---

    @FXML public void notificationOA(ActionEvent actionEvent) {}
    @FXML public void scholarshipViewOA(ActionEvent actionEvent) {}
    @FXML public void exchangeViewOA(ActionEvent actionEvent) {}
    @FXML public void disciplinaryAppealsOA(ActionEvent actionEvent) {}
    @FXML public void partnershipViewOA(ActionEvent actionEvent) {}
    @FXML public void transcriptViewOA(ActionEvent actionEvent) {}
    @FXML public void budgetManagementOA(ActionEvent actionEvent) {}
    @FXML public void crisisViewOA(ActionEvent actionEvent) {}
    @FXML public void reportViewOA(ActionEvent actionEvent) {}
}