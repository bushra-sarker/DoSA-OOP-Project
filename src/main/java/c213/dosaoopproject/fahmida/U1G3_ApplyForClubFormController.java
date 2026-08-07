package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.model.ClubMembershipApplication;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;
import commonClass.User;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class U1G3_ApplyForClubFormController
{
    @javafx.fxml.FXML
    private TextField studentNameTF;
    @javafx.fxml.FXML
    private TextField QLabel1;   // Why do you want to join?
    @javafx.fxml.FXML
    private TextField QLabel2;   // Any relevant skills or experience?
    @javafx.fxml.FXML
    private Label applyforclubLabel;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ComboBox<String> clubNameCB;
    @javafx.fxml.FXML
    private TextField studentIDTF;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private ComboBox<String> majorCB;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);

        // major options
        majorCB.getItems().addAll("CSE", "EEE", "BBA");

        // club name options (all clubs), then auto-select the one picked on the previous screen
        for (ClubInfo club : DataStore.get().getClubs()) {
            clubNameCB.getItems().add(club.getClubName());
        }
        ClubInfo selected = Session.getSelectedClub();
        if (selected != null) {
            clubNameCB.setValue(selected.getClubName());
        }
    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) {
        String name = studentNameTF.getText().trim();
        String id = studentIDTF.getText().trim();
        String reason = QLabel1.getText().trim();
        String skills = QLabel2.getText().trim();

        // 1) all fields must be filled
        if (clubNameCB.getValue() == null || majorCB.getValue() == null
                || name.isEmpty() || id.isEmpty() || reason.isEmpty() || skills.isEmpty()) {
            Ui.info("Please fill in all required fields.");
            return;
        }

        // 2) student name must be 30 characters or fewer
        if (name.length() > 30) {
            Ui.info("Student name must be 30 characters or fewer.");
            return;
        }

        // 3) student ID must be exactly 7 digits
        if (id.length() != 7 || !id.matches("\\d{7}")) {
            Ui.info("Student ID must be 7 digits.");
            return;
        }

        // save the application so the Club Advisor can review it later
        String clubName = clubNameCB.getValue().toString();
        String major = majorCB.getValue().toString();
        DataStore store = DataStore.get();
        store.getMembershipApplications().add(new ClubMembershipApplication(
                store.getMembershipApplications().size() + 1,
                Integer.parseInt(id), clubName, major, reason, skills));
        store.save();

        User user = Session.getCurrentUser();
        if (user != null) {
            store.logHistory(user.getUserId(), "Applied to club: " + clubName);
        }
        store.notifyRole("Club Advisor",
                "New membership application from " + name + " for " + clubName + ".");

        Ui.info("Application Submitted Successfully");
        SceneManager.switchTo("U1G3_ApplyForClub");
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G2_EventList");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        Ui.info("No new notifications.");
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
    public void viewNoticesOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G1_ViewNotices");
    }

    @javafx.fxml.FXML
    public void backtoDashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Session.clear();
        SceneManager.switchTo("LoginView");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G7_DownloadApproval");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G3_ApplyForClub");
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G8_TrackHistory");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G6_SubmitComplaints");
    }
}
