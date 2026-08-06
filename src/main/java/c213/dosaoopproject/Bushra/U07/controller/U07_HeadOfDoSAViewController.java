package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.commonClass.model.User;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SessionManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static c213.dosaoopproject.commonClass.util.Navigation.navigate;
import static c213.dosaoopproject.commonClass.util.SubViewSwitcher.loadSubView;

public class U07_HeadOfDoSAViewController {

    @FXML private AnchorPane contentArea;
    @FXML private ImageView ppImageView;
    @FXML private Label nameLabel;
    @FXML private Label userIdLabel;

    // Sidebar Navigation Buttons
    @FXML private Button btnDashboard;
    @FXML private Button btnMajorEvents;
    @FXML private Button btnBudgetManagement;
    @FXML private Button btnDisciplinaryAppeals;
    @FXML private Button btnReports;
    @FXML private Button btnCrisisManagement;
    @FXML private Button btnExchangePrograms;
    @FXML private Button btnTranscriptRequests;
    @FXML private Button btnPartnerships;

    private static final String DEFAULT_STYLE = "-fx-background-color: transparent; -fx-text-fill: white;";
    private static final String ACTIVE_STYLE = "-fx-background-color: #052a3f; -fx-text-fill: white; -fx-font-weight: bold;";

    @FXML
    public void initialize() {
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            nameLabel.setText(currentUser.getUserId());
            userIdLabel.setText("ID: " + currentUser.getUserId());
        }

        // Set Dashboard active on load
        setActiveButton(btnDashboard);
        loadDashboardView();
    }

    private void loadDashboardView() {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07_dashboardOverview.fxml");
    }

    private void resetSidebarStyles() {
        if (btnDashboard != null) btnDashboard.setStyle(DEFAULT_STYLE);
        if (btnMajorEvents != null) btnMajorEvents.setStyle(DEFAULT_STYLE);
        if (btnBudgetManagement != null) btnBudgetManagement.setStyle(DEFAULT_STYLE);
        if (btnDisciplinaryAppeals != null) btnDisciplinaryAppeals.setStyle(DEFAULT_STYLE);
        if (btnReports != null) btnReports.setStyle(DEFAULT_STYLE);
        if (btnCrisisManagement != null) btnCrisisManagement.setStyle(DEFAULT_STYLE);
        if (btnExchangePrograms != null) btnExchangePrograms.setStyle(DEFAULT_STYLE);
        if (btnTranscriptRequests != null) btnTranscriptRequests.setStyle(DEFAULT_STYLE);
        if (btnPartnerships != null) btnPartnerships.setStyle(DEFAULT_STYLE);
    }

    private void setActiveButton(Button activeButton) {
        resetSidebarStyles();
        if (activeButton != null) {
            activeButton.setStyle(ACTIVE_STYLE);
        }
    }

    @FXML
    public void dashboardViewOA(ActionEvent actionEvent) {
        setActiveButton(btnDashboard);
        loadDashboardView();
    }

    @FXML
    public void majorEventViewOA(ActionEvent actionEvent) {
        setActiveButton(btnMajorEvents);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G1_eventApprovalQueue.fxml");
    }

    @FXML
    public void budgetManagementOA(ActionEvent actionEvent) {
        setActiveButton(btnBudgetManagement);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G2_budgetManagement.fxml");
    }

    @FXML
    public void disciplinaryAppealsOA(ActionEvent actionEvent) {
        setActiveButton(btnDisciplinaryAppeals);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G3_appealsList.fxml");
    }

    @FXML
    public void reportViewOA(ActionEvent actionEvent) {
        setActiveButton(btnReports);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G6_reportView.fxml");
    }

    @FXML
    public void crisisViewOA(ActionEvent actionEvent) {
        setActiveButton(btnCrisisManagement);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G5_crisisView.fxml");
    }

    @FXML
    public void exchangeViewOA(ActionEvent actionEvent) {
        setActiveButton(btnExchangePrograms);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G7_exchangeView.fxml");
    }

    @FXML
    public void transcriptViewOA(ActionEvent actionEvent) {
        setActiveButton(btnTranscriptRequests);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G8_transcriptView.fxml");
    }

    @FXML
    public void partnershipViewOA(ActionEvent actionEvent) {
        setActiveButton(btnPartnerships);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G8_partnershipView.fxml");
    }

    @FXML
    public void logOutOA(ActionEvent actionEvent) {
        try {
            SessionManager.getInstance().logout();
            navigate(actionEvent, "/c213/dosaoopproject/LoginView.fxml");
        } catch (IOException e) {
            AlertUtil.showError("Navigation Error", "Could not return to the login screen.");
        }
    }
}