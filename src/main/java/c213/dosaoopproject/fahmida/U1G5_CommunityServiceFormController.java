package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.CommunityServiceProgram;
import c213.dosaoopproject.fahmida.model.EventRegistration;
import c213.dosaoopproject.fahmida.model.Student;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;
import commonClass.User;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
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
    @javafx.fxml.FXML
    private TextField studentNameTF;
    @javafx.fxml.FXML
    private DatePicker collectionDateDP;
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private TextField campaignDurationTF;
    @javafx.fxml.FXML
    private TextField phoneNumberTF;
    @javafx.fxml.FXML
    private ComboBox<String> eventNameCB;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);

        for (CommunityServiceProgram program : DataStore.get().getCommunityPrograms()) {
            eventNameCB.getItems().add(program.getProgramName());
        }

        // pre-fill from the program picked on the previous screen, if any
        CommunityServiceProgram selected = Session.getSelectedProgram();
        if (selected != null) {
            eventNameCB.setValue(selected.getProgramName());
            campaignDurationTF.setText(selected.getCampaignDuration());
        }

        // pre-fill from the logged-in student
        User user = Session.getCurrentUser();
        if (user != null) {
            studentNameTF.setText(user.getFullName());
        }
        if (user instanceof Student student) {
            emailTF.setText(student.getEmail());
        }
    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) {
        String name = studentNameTF.getText().trim();
        String email = emailTF.getText().trim();
        String phone = phoneNumberTF.getText().trim();
        String duration = campaignDurationTF.getText().trim();
        String eventName = eventNameCB.getValue();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || duration.isEmpty()
                || eventName == null || collectionDateDP.getValue() == null) {
            Ui.info("Please fill in all required fields.");
            return;
        }

        if (name.length() > 30) {
            Ui.info("Student name must be 30 characters or fewer.");
            return;
        }

        if (!phone.matches("\\d{7,15}")) {
            Ui.info("Please enter a valid phone number.");
            return;
        }

        User user = Session.getCurrentUser();
        String department = (user instanceof Student student) ? student.getDepartment() : "";
        int studentId = user != null ? user.getUserId() : 0;

        DataStore store = DataStore.get();
        store.getEventRegistrations().add(new EventRegistration(
                studentId, eventName, department, email, phone, "Pending"));
        store.save();

        if (user != null) {
            store.logHistory(user.getUserId(), "Registered as volunteer for: " + eventName);
        }
        store.notifyRole("Club Advisor",
                name + " signed up to volunteer for \"" + eventName + "\".");

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
