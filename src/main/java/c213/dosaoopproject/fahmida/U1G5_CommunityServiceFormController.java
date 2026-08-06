package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class U1G5_CommunityServiceFormController
{
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;

    // Section 1
    @javafx.fxml.FXML
    private TextField programNameTF1;
    @javafx.fxml.FXML
    private DatePicker collectionDateDP1;
    @javafx.fxml.FXML
    private TextField durationTF1;

    // Section 2
    @javafx.fxml.FXML
    private TextField programName2;
    @javafx.fxml.FXML
    private DatePicker collectionDateDP2;
    @javafx.fxml.FXML
    private TextField durationTF2;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);

        // the two sections are two fixed programs
        programNameTF1.setText("Winter Cloth Distribution");
        programName2.setText("Fundraising for Disaster Victims");
    }

    @javafx.fxml.FXML
    public void submitOA1(ActionEvent actionEvent) {
        if (programNameTF1.getText().trim().isEmpty()
                || collectionDateDP1.getValue() == null
                || durationTF1.getText().trim().isEmpty()) {
            Ui.info("Please fill in all required fields.");
            return;
        }

        Ui.info("Registered as volunteer successfully.");
        SceneManager.switchTo("U1G5_CommunityService");
    }

    @javafx.fxml.FXML
    public void submitaOA2(ActionEvent actionEvent) {
        if (programName2.getText().trim().isEmpty()
                || collectionDateDP2.getValue() == null
                || durationTF2.getText().trim().isEmpty()) {
            Ui.info("Please fill in all required fields.");
            return;
        }

        Ui.info("Registered as volunteer successfully.");
        SceneManager.switchTo("U1G5_CommunityService");
    }

    @javafx.fxml.FXML
    public void gotoDashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G1_ViewNotices");
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G2_EventList");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G3_ApplyForClub");
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G4_ViewEventSchedule");
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G5_CommunityService");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G6_SubmitComplaints");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G7_DownloadApproval");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G8_TrackHistory");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        Ui.info("No new notifications.");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Session.clear();
        SceneManager.switchTo("LoginView");
    }
}
