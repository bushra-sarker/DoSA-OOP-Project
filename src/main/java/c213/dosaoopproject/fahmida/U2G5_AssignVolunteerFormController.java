package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.EventRegistration;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;
import c213.dosaoopproject.fahmida.utility.Ui;
import commonClass.User;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class U2G5_AssignVolunteerFormController
{
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TextField eventNameTF;
    @javafx.fxml.FXML
    private TextField studentNameTF;
    @javafx.fxml.FXML
    private TextField volunteerIdTF;
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private TextField responsibilityTF;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);

        EventRegistration registration = Session.getSelectedRegistration();
        if (registration == null) {
            return;
        }

        eventNameTF.setText(registration.getEventName());
        volunteerIdTF.setText(String.valueOf(registration.getStudentId()));
        emailTF.setText(registration.getEmail());
        phoneTF.setText(registration.getPhone());
        responsibilityTF.setText(registration.getResponsibility());

        String studentName = DataStore.get().getUsers().stream()
                .filter(u -> u.getUserId() == registration.getStudentId())
                .map(User::getFullName)
                .findFirst().orElse("");
        studentNameTF.setText(studentName);
    }

    @javafx.fxml.FXML
    public void assignOA(ActionEvent actionEvent) throws IOException {
        EventRegistration registration = Session.getSelectedRegistration();
        if (registration == null) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "No registrant selected.");
            return;
        }

        String responsibility = responsibilityTF.getText().trim();
        if (responsibility.isEmpty()) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please select a responsibility before confirming.");
            return;
        }

        registration.setResponsibility(responsibility);
        registration.updateStatus("Assigned");

        DataStore store = DataStore.get();
        store.save();

        User user = Session.getCurrentUser();
        if (user != null) {
            store.logHistory(user.getUserId(), "Assigned volunteer (ID "
                    + registration.getStudentId() + ") to " + registration.getEventName()
                    + " as " + responsibility + ".");
        }
        store.notify(registration.getStudentId(), "You've been assigned as \""
                + responsibility + "\" for " + registration.getEventName() + ".");

        ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, "Volunteer assigned successfully.");
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G5_AssignVolunteer.fxml");
    }

    @javafx.fxml.FXML
    public void backtoPreviousOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G5_AssignVolunteer.fxml");
    }

    // --- navigation ----------------------------------------------------------

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2_Dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G1_updateClubinfo.fxml");
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G2_PostClubNotice.fxml");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G3_ReviewandapproveClubMembership.fxml");
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G4_ArrangeEvent.fxml");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G5_AssignVolunteer.fxml");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G6_ViewParticipants.fxml");
    }

    @javafx.fxml.FXML
    public void submitCompletionReportOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G7_CompletionReport.fxml");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G8_ActivityHistory.fxml");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        Notifications.showForCurrentUser();
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) throws IOException {
        Session.clear();
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/LoginView.fxml");
    }
}
