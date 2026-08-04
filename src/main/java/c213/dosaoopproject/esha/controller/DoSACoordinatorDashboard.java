package c213.dosaoopproject.esha.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
public class DoSACoordinatorDashboard
{ @FXML private Label titleLabel;
    @FXML private Label welcomeLabel;
    @FXML private StackPane contentArea;
    private int coordinatorId;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button navGenerateCertificateBtn;
    @FXML
    private Button navVolunteerHoursBtn;
    @FXML
    private Button navServiceOpportunityBtn;
    @FXML
    private Button navClubRegistrationBtn;
    @FXML
    private Button navAnnouncementBtn;
    @FXML
    private Button navAssignVolunteersBtn;
    @FXML
    private Button navImpactReportBtn;
    @FXML
    private Button navTaskCompletionReportBtn;
    @FXML
    private Button navManageClubsBtn;


    public void initSession(int coordinatorId,String coordinatorName) {
        this.coordinatorId = coordinatorId;
        welcomeLabel.setText("Welcome, " + coordinatorName);
    }
    // ===================== NAV HANDLERS =====================

    @FXML
    private void showServiceOpportunity(ActionEvent event) {
        loadView("/views/ServiceOpportunity.fxml");
    }

    @FXML
    private void showVolunteerHours(ActionEvent event) {
        loadView("/views/VolunteerHourApproval.fxml");
    }

    @FXML
    private void showAssignVolunteers(ActionEvent event) {
        loadView("/views/VolunteerAssignment.fxml");
    }

    @FXML
    private void showGenerateCertificate(ActionEvent event) {
        loadView("/views/GenerateCertificate.fxml");
    }

    @FXML
    private void showClubRegistration(ActionEvent event) {
        loadView("/views/ClubRegistrationApproval.fxml");
    }

    @FXML
    private void showManageClubs(ActionEvent event) {
        loadView("/views/ManageActiveClubs.fxml");
    }

    @FXML
    private void showImpactReport(ActionEvent event) {
        loadView("/views/ImpactReport.fxml");
    }

    @FXML
    private void showAnnouncement(ActionEvent event) {
        loadView("/views/PostAnnouncement.fxml");
    }

    @FXML
    private void showTaskCompletionReport(ActionEvent event) {
        loadView("/views/VolunteerTaskCompletionReport.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(loginRoot);
        } catch (IOException e) {
            e.printStackTrace();
            showFallbackError("Could not return to login screen.");
        }
    }

    // ===================== HELPER =====================

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent view = loader.load();
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
            showFallbackError("Could not load: " + fxmlPath);
        }
    }

    private void showFallbackError(String message) {
        contentArea.getChildren().setAll(new Label(message));
}}