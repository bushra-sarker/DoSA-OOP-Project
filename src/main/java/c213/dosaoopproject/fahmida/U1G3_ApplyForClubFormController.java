package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.model.ClubMembershipApplication;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;
import c213.dosaoopproject.fahmida.utility.Ui;
import commonClass.User;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

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

        for (ClubInfo club : DataStore.get().getClubs()) {
            clubNameCB.getItems().add(club.getClubName());
        }
        ClubInfo selected = Session.getSelectedClub();
        if (selected != null) {
            clubNameCB.setValue(selected.getClubName());
        }
    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) throws IOException {
        String name = studentNameTF.getText().trim();
        String id = studentIDTF.getText().trim();
        String reason = QLabel1.getText().trim();
        String skills = QLabel2.getText().trim();

        if (clubNameCB.getValue() == null || majorCB.getValue() == null
                || name.isEmpty() || id.isEmpty() || reason.isEmpty() || skills.isEmpty()) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please fill in all required fields.");
            return;
        }

        if (name.length() > 30) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Student name must be 30 characters or fewer.");
            return;
        }

        if (id.length() != 7 || !id.matches("\\d{7}")) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Student ID must be 7 digits.");
            return;
        }

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

        ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, "Application Submitted Successfully");
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G3_ApplyForClub.fxml");
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G2_EventList.fxml");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, "No new notifications.");
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G4_ViewEventSchedule.fxml");
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G5_CommunityService.fxml");
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G1_ViewNotices.fxml");
    }

    @javafx.fxml.FXML
    public void backtoDashboardOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1_Dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) throws IOException {
        Session.clear();
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/LoginView.fxml");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G3_ApplyForClub.fxml");
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1_Dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G6_SubmitComplaints.fxml");
    }
}
