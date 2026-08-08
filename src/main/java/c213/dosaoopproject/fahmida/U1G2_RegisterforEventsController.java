package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class U1G2_RegisterforEventsController
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
    private TextField studentNameTF;
    @javafx.fxml.FXML
    private TextField phonneNumTF;
    @javafx.fxml.FXML
    private Label regForEventsLabel;
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private ComboBox<String> eventNameCB;
    @javafx.fxml.FXML
    private ComboBox<String> deptCB;
    @javafx.fxml.FXML
    private TextField IDTF;

    @javafx.fxml.FXML
    public void initialize() {
        deptCB.getItems().addAll("CSE", "EEE", "BBA");
        eventNameCB.getItems().addAll(
                "Robotics",
                "Hackathon",
                "Cultural Night",
                "Sports Day",
                "Career Fair",
                "Science Olympiad"
        );
    }
    @javafx.fxml.FXML
    public void confirmRegOA(ActionEvent actionEvent) {

        String name = studentNameTF.getText();
        String id = IDTF.getText();
        String phone = phonneNumTF.getText();
        String email = emailTF.getText();

        if (name.isEmpty() || id.isEmpty() || phone.isEmpty() || email.isEmpty()
                || deptCB.getValue() == null || eventNameCB.getValue() == null) {
            showAlert("Please fill in all fields.");
            return;
        }

        if (name.length() > 30) {
            showAlert("Student name must be 30 characters or fewer.");
            return;
        }

        if (id.length() != 7) {
            showAlert("Student ID must be 7 digits.");
            return;
        }

        // success alert
        showAlert("You're registered for this event.");

        studentNameTF.clear();
        IDTF.clear();
        phonneNumTF.clear();
        emailTF.clear();
        deptCB.setValue(null);
        eventNameCB.setValue(null);
    }

    private void showAlert(String message) {
        javafx.scene.control.Alert alert =
                new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Registration");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) {
        //On this page
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
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
    public void logOutOA(ActionEvent actionEvent) throws IOException {
        Session.clear();
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/LoginView.fxml");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G3_ApplyForClub.fxml");
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G6_SubmitComplaints.fxml");
    }

    @javafx.fxml.FXML
    public void backtoDashboardOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1_Dashboard.fxml");
    }
}
