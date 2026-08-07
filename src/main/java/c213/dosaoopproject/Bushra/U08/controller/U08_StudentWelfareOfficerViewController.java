package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.commonClass.model.User;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SessionManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static c213.dosaoopproject.commonClass.util.Navigation.navigate;
import static c213.dosaoopproject.commonClass.util.SubViewSwitcher.loadSubView;

public class U08_StudentWelfareOfficerViewController {

    @FXML private AnchorPane contentArea;
    @FXML private Label nameLabel;
    @FXML private Label userIdLabel;

    // Sidebar Navigation Buttons
    @FXML private Button btnDashboard;
    @FXML private Button btnScholarships;
    @FXML private Button btnFinancialAssistance;
    @FXML private Button btnGrievances;
    @FXML private Button btnHealthInsurance;
    @FXML private Button btnOrientation;
    @FXML private Button btnAccommodations;
    @FXML private Button btnFacilityAccess;
    @FXML private Button btnStudentFeedback;

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
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08_dashboardOverview.fxml");
    }

    private void resetSidebarStyles() {
        if (btnDashboard != null) btnDashboard.setStyle(DEFAULT_STYLE);
        if (btnScholarships != null) btnScholarships.setStyle(DEFAULT_STYLE);
        if (btnFinancialAssistance != null) btnFinancialAssistance.setStyle(DEFAULT_STYLE);
        if (btnGrievances != null) btnGrievances.setStyle(DEFAULT_STYLE);
        if (btnHealthInsurance != null) btnHealthInsurance.setStyle(DEFAULT_STYLE);
        if (btnOrientation != null) btnOrientation.setStyle(DEFAULT_STYLE);
        if (btnAccommodations != null) btnAccommodations.setStyle(DEFAULT_STYLE);
        if (btnFacilityAccess != null) btnFacilityAccess.setStyle(DEFAULT_STYLE);
        if (btnStudentFeedback != null) btnStudentFeedback.setStyle(DEFAULT_STYLE);
    }

    private void setActiveButton(Button activeButton) {
        resetSidebarStyles();
        if (activeButton != null) {
            activeButton.setStyle(ACTIVE_STYLE);
        }
    }

    @FXML
    public void dashboardViewOA(ActionEvent event) {
        setActiveButton(btnDashboard);
        loadDashboardView();
    }

    @FXML
    public void scholarshipManagementOA(ActionEvent event) {
        setActiveButton(btnScholarships);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G1_scholarshipManagement.fxml");
    }

    @FXML
    public void financialAssistanceOA(ActionEvent event) {
        setActiveButton(btnFinancialAssistance);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G2_financialAssistanceView.fxml");
    }

    @FXML
    public void grievancesOA(ActionEvent event) {
        setActiveButton(btnGrievances);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G3_grievancesView.fxml");
    }

    @FXML
    public void healthInsuranceOA(ActionEvent event) {
        setActiveButton(btnHealthInsurance);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G4_healthInsuranceView.fxml");
    }

    @FXML
    public void orientationOA(ActionEvent event) {
        setActiveButton(btnOrientation);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G5_orientationView.fxml");
    }

    @FXML
    public void accommodationsOA(ActionEvent event) {
        setActiveButton(btnAccommodations);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G6_accommodationsView.fxml");
    }

    @FXML
    public void studentFacilityAccessOA(ActionEvent event) {
        setActiveButton(btnFacilityAccess);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G7_facilityAccessView.fxml");
    }

    @FXML
    public void studentFeedbackOA(ActionEvent event) {
        setActiveButton(btnStudentFeedback);
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G8_studentFeedbackView.fxml");
    }

    @FXML
    public void logOutOA(ActionEvent event) {
        try {
            SessionManager.getInstance().logout();
            navigate(event, "/c213/dosaoopproject/LoginView.fxml");
        } catch (IOException e) {
            AlertUtil.showError("Navigation Error", "Could not return to the login screen.");
        }
    }
}