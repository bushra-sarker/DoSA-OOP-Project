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
        loadView("/views/UpdateClubInfo.fxml");
    }

    @FXML
    private void showArrangeEvent(ActionEvent event) {
        loadView("/views/ArrangeClubEvent.fxml");
    }

    @FXML
    private void showEventRequest(ActionEvent event) {
        loadView("/views/EventRequest.fxml");
    }

    @FXML
    private void showVenueBooking(ActionEvent event) {
        loadView("/views/VenueBooking.fxml");
    }

    @FXML
    private void showSponsorshipRequest(ActionEvent event) {
        loadView("/views/SponsorshipRequest.fxml");
    }

    @FXML
    private void showResourceRequest(ActionEvent event) {
        loadView("/views/ResourceRequest.fxml");
    }

    @FXML
    private void showPostNotice(ActionEvent event) {
        loadView("/views/PostNotice.fxml");
    }

    @FXML
    private void showMembershipApplications(ActionEvent event) {
        loadView("/views/MembershipApplications.fxml");
    }

    @FXML
    private void showCompletionReport(ActionEvent event) {
        loadView("/views/EventCompletionReport.fxml");
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