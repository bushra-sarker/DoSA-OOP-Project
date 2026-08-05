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
    @FXML
    private Button navPendingRequestsBtn;


    public void initSession(int coordinatorId,String coordinatorName) {
        this.coordinatorId = coordinatorId;
        welcomeLabel.setText("Welcome, " + coordinatorName);
    }
    // ===================== NAV HANDLERS =====================

    @FXML
    private void showPendingRequests(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/PendingRequests.fxml");
    }

    @FXML
    private void showServiceOpportunity(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u6_G1_PostServiceOpportunity.fxml");
    }

    @FXML
    private void showVolunteerHours(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u6_G2_ApproveVolunteerHour.fxml");
    }

    @FXML
    private void showAssignVolunteers(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u6_G3_AssignVolunteers.fxml");
    }

    @FXML
    private void showGenerateCertificate(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u6_G4_GenerateCertificate.fxml");
    }

    @FXML
    private void showClubRegistration(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u6_G5_ClubRegistrationApproval.fxml");
    }

    @FXML
    private void showManageClubs(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u6_G6_ManageActiveClubs.fxml");
    }

    @FXML
    private void showImpactReport(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u6_G7_ImpactReport.fxml");
    }

    @FXML
    private void showAnnouncement(ActionEvent event) {
        showFallbackError("Post Announcement — Coming Soon");
    }

    @FXML
    private void showTaskCompletionReport(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u6_G8_VolunteerTaskCompletionReport.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
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