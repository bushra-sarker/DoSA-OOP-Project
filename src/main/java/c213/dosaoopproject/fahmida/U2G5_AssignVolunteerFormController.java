package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.EventRegistration;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;
import commonClass.User;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Club Advisor Goal: finalize a volunteer assignment. Shows the registrant
 * picked on the Assign Volunteer list (read-only) and lets the advisor enter
 * a responsibility to confirm the assignment.
 */
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
    public void assignOA(ActionEvent actionEvent) {
        EventRegistration registration = Session.getSelectedRegistration();
        if (registration == null) {
            Ui.info("No registrant selected.");
            return;
        }

        String responsibility = responsibilityTF.getText().trim();
        if (responsibility.isEmpty()) {
            Ui.info("Please select a responsibility before confirming.");
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

        Ui.info("Volunteer assigned successfully.");
        SceneManager.switchTo("U2G5_AssignVolunteer");
    }

    @javafx.fxml.FXML
    public void backtoPreviousOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G5_AssignVolunteer");
    }

    // --- navigation ----------------------------------------------------------

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2_Dashboard");
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G1_updateClubinfo");
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G2_PostClubNotice");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G3_ReviewandapproveClubMembership");
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G4_ArrangeEvent");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G5_AssignVolunteer");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G6_ViewParticipants");
    }

    @javafx.fxml.FXML
    public void submitCompletionReportOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G7_CompletionReport");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G8_ActivityHistory");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        Notifications.showForCurrentUser();
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Session.clear();
        SceneManager.switchTo("LoginView");
    }
}
