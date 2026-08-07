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

public class ClubExecutiveDashboard
{ @FXML private Label clubNameLabel;
    @FXML private Label welcomeLabel;
    @FXML private StackPane contentArea;
    private int executiveId;
    private String clubName;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button navCompletionReportBtn;
    @FXML
    private Button navArrangeEventBtn;
    @FXML
    private Button navUpdateClubInfoBtn;
    @FXML
    private Button navMembershipBtn;
    @FXML
    private Button navEventRequestBtn;
    @FXML
    private Button navSponsorshipBtn;
    @FXML
    private Button navNoticeBtn;
    @FXML
    private Button navVenueBookingBtn;
    @FXML
    private Button navResourceRequestBtn;

    public void initSession(int executiveId,String clubName) {
        this.executiveId = executiveId;
        this.clubName = clubName;
        clubNameLabel.setText(clubName + " — Executive Dashboard");
        welcomeLabel.setText("Welcome, Executive #" + executiveId);
    }
    // ===================== NAV HANDLERS =====================

    @FXML
    private void showUpdateClubInfo(ActionEvent event) {
        showFallbackError("Update Club Info — Coming Soon");
    }

    @FXML
    private void showArrangeEvent(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u5_G1_ArrangeClubEvent.fxml");
    }

    @FXML
    private void showEventRequest(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u5_G2_EventRequest.fxml");
    }

    @FXML
    private void showVenueBooking(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u5_G3_VenueBooking.fxml");
    }

    @FXML
    private void showSponsorshipRequest(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u5_G4_SponsorshipRequest.fxml");
    }

    @FXML
    private void showResourceRequest(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u5_G5_ResourceRequest.fxml");
    }

    @FXML
    private void showPostNotice(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u5_G6_PostNotice.fxml");
    }

    @FXML
    private void showMembershipApplications(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u5_G7_MembershipApplications.fxml");
    }

    @FXML
    private void showCompletionReport(ActionEvent event) {
        loadView("/c213/dosaoopproject/esha/u5_G8_EventCompletion.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/c213/dosaoopproject/esha/RoleSelection.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(loader.load(), 500, 400));
            stage.setTitle("DoSA OOP Project — Select Role");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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