package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.commonClass.model.User;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SceneSwitcher;
import c213.dosaoopproject.commonClass.util.SessionManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class U07_HeadOfDoSAViewController {

    @javafx.fxml.FXML
    private StackPane contentArea;
    @javafx.fxml.FXML
    private Button btnExchangePrograms;
    @javafx.fxml.FXML
    private Button btnDisciplinaryAppeals;
    @javafx.fxml.FXML
    private Button btnBudgetManagement;
    @javafx.fxml.FXML
    private Button btnCrisisManagement;
    @javafx.fxml.FXML
    private ImageView ppImageView;
    @javafx.fxml.FXML
    private Button btnExternalRelations;
    @javafx.fxml.FXML
    private Button btnPendingScholarshipsCard;
    @javafx.fxml.FXML
    private Button btnDashboard;
    @javafx.fxml.FXML
    private Button btnReportsAnalytics;
    @javafx.fxml.FXML
    private TextField searchOFCRTF;
    @javafx.fxml.FXML
    private Button btnCoCurricularTranscripts;
    @javafx.fxml.FXML
    private Label nameLabel;
    @javafx.fxml.FXML
    private Label userIdLabel;
    @javafx.fxml.FXML
    private Button btnPendingEventsCard;

    @javafx.fxml.FXML
    public void initialize() {
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            nameLabel.setText(currentUser.getUserId()); // <--- Changed from getName()
            userIdLabel.setText("ID: " + currentUser.getUserId());
        }

        loadDashboardView();
    }

    private void loadDashboardView() {
        SceneSwitcher.switchContent(contentArea, "/c213/dosaoopproject/Bushra/U07/U07_dashboardOverview.fxml");
    }

    @javafx.fxml.FXML
    public void dashboardViewOA(ActionEvent actionEvent) {
        loadDashboardView();
    }

    @javafx.fxml.FXML
    public void majorEventViewOA(ActionEvent actionEvent) {
        SceneSwitcher.switchContent(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G1_eventApprovalQueue.fxml");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        AlertUtil.showInformation("Notifications", "No new unread system notifications.");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        SessionManager.getInstance().logout();
        SceneSwitcher.switchScene(actionEvent, "/c213/dosaoopproject/LoginView.fxml", "Academic Admin Portal - Login");
    }

    @javafx.fxml.FXML
    public void scholarshipViewOA(ActionEvent actionEvent) {
        AlertUtil.showInformation("Scholarships", "Scholarship Module selected.");
    }

    @javafx.fxml.FXML
    public void exchangeViewOA(ActionEvent actionEvent) {
        AlertUtil.showInformation("Exchange Programs", "Goal 6 Module selected.");
    }

    @javafx.fxml.FXML
    public void disciplinaryAppealsOA(ActionEvent actionEvent) {
        AlertUtil.showInformation("Disciplinary Appeals", "Goal 3 Module selected.");
    }

    @javafx.fxml.FXML
    public void partnershipViewOA(ActionEvent actionEvent) {
        AlertUtil.showInformation("Partnerships & MOUs", "Goal 8 Module selected.");
    }

    @javafx.fxml.FXML
    public void transcriptViewOA(ActionEvent actionEvent) {
        AlertUtil.showInformation("Transcript Requests", "Goal 7 Module selected.");
    }

    @javafx.fxml.FXML
    public void budgetManagementOA(ActionEvent actionEvent) {
        AlertUtil.showInformation("Budget Management", "Goal 2 Module selected.");
    }

    @javafx.fxml.FXML
    public void crisisViewOA(ActionEvent actionEvent) {
        AlertUtil.showInformation("Crisis Management", "Goal 5 Module selected.");
    }

    @javafx.fxml.FXML
    public void reportViewOA(ActionEvent actionEvent) {
        AlertUtil.showInformation("Reports & Analytics", "Goal 4 Module selected.");
    }
}