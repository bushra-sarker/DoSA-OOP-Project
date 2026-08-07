package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.EventRegistration;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * Club Advisor Goal: View Registered Participants for Events. Read-only screen
 * that lists every event registration from the shared DataStore (the same list
 * the students' registration screens write to) in a table.
 */
public class U2G6_ViewParticipantsController {

    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TableView<EventRegistration> participantsTV;
    @javafx.fxml.FXML
    private TableColumn<EventRegistration, String> prtEventTC;
    @javafx.fxml.FXML
    private TableColumn<EventRegistration, Integer> prtStudentTC;
    @javafx.fxml.FXML
    private TableColumn<EventRegistration, String> prtDeptTC;
    @javafx.fxml.FXML
    private TableColumn<EventRegistration, String> prtStatusTC;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        prtEventTC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        prtStudentTC.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        prtDeptTC.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        prtStatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        participantsTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        participantsTV.setItems(FXCollections.observableArrayList(DataStore.get().getEventRegistrations()));
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
    public void communityProgramOA(ActionEvent actionEvent) {
        String programs = DataStore.get().getCommunityPrograms().stream()
                .map(p -> "• " + p.getDetails())
                .reduce((a, b) -> a + "\n" + b).orElse("");
        Ui.info(programs.isEmpty() ? "No community programs." : programs);
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
        // already on View Participants
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
