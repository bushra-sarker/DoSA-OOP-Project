package c213.dosaoopproject.Bushra.U07.controller;

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

public class U07_HeadOfDoSAViewController {

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
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07_dashboardOverview.fxml");
    }

    @FXML
    public void dashboardViewOA(ActionEvent actionEvent) {
        loadDashboardView();
    }

    @FXML
    public void majorEventViewOA(ActionEvent actionEvent) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G1_eventApprovalQueue.fxml");
    }

    @FXML
    public void budgetManagementOA(ActionEvent actionEvent) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G3_budgetView.fxml");
    }

    @FXML
    public void disciplinaryAppealsOA(ActionEvent actionEvent) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G4_appealsView.fxml");
    }

    @FXML
    public void reportViewOA(ActionEvent actionEvent) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G6_reportView.fxml");
    }

    @FXML
    public void crisisViewOA(ActionEvent actionEvent) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G5_crisisView.fxml");
    }

    @FXML
    public void exchangeViewOA(ActionEvent actionEvent) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G7_exchangeView.fxml");
    }

    @FXML
    public void transcriptViewOA(ActionEvent actionEvent) {
        loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G8_transcriptView.fxml");
    }

    @FXML
    public void partnershipViewOA(ActionEvent actionEvent) {
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