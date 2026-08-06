package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.commonClass.model.User;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SessionManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static c213.dosaoopproject.commonClass.util.Navigation.navigate;
import static c213.dosaoopproject.commonClass.util.SubViewSwitcher.loadSubView;

public class U08_StudentWelfareOfficerViewController {

    @FXML private AnchorPane contentArea;
    @FXML private ImageView ppImageView;
    @FXML private Label nameLabel;
    @FXML private Label userIdLabel;

    @FXML
    public void initialize() {
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            nameLabel.setText(currentUser.getUserId());
            userIdLabel.setText("ID: " + currentUser.getUserId());
        }

        loadDashboardView();
    }

    private void loadDashboardView() {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08_dashboardOverview.fxml");
    }

    @FXML
    public void dashboardViewOA(ActionEvent event) {
        loadDashboardView();
    }

    @FXML
    public void scholarshipReviewOA(ActionEvent event) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G1_scholarshipReviewView.fxml");
    }

    @FXML
    public void financialAssistanceOA(ActionEvent event) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G2_financialAssistanceView.fxml");
    }

    @FXML
    public void grievancesOA(ActionEvent event) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G3_grievancesView.fxml");
    }

    @FXML
    public void healthInsuranceOA(ActionEvent event) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G4_healthInsuranceView.fxml");
    }

    @FXML
    public void orientationOA(ActionEvent event) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G5_orientationView.fxml");
    }

    @FXML
    public void accommodationsOA(ActionEvent event) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G6_accommodationsView.fxml");
    }

    @FXML
    public void studentFacilityAccessOA(ActionEvent event) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G7_facilityAccessView.fxml");
    }

    @FXML
    public void studentFeedbackOA(ActionEvent event) {
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