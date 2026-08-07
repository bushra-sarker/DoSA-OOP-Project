package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.model.VolunteerAssignment;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Club Advisor Goal: Assign Student Volunteers to Events. Picks an existing
 * event, captures the volunteer's student ID and responsibility, and adds the
 * assignment to the shared DataStore, then lists all assignments below.
 */
public class U2G5_AssignVolunteerController {

    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private ComboBox<String> eventBox;
    @javafx.fxml.FXML
    private TextField volunteerTF;
    @javafx.fxml.FXML
    private TextField responsibilityTF;
    @javafx.fxml.FXML
    private TableView<VolunteerAssignment> assignmentsTV;
    @javafx.fxml.FXML
    private TableColumn<VolunteerAssignment, String> asgIdTC;
    @javafx.fxml.FXML
    private TableColumn<VolunteerAssignment, Integer> asgVolTC;
    @javafx.fxml.FXML
    private TableColumn<VolunteerAssignment, Integer> asgEventTC;
    @javafx.fxml.FXML
    private TableColumn<VolunteerAssignment, String> asgRespTC;
    @javafx.fxml.FXML
    private TableColumn<VolunteerAssignment, String> asgStatusTC;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        asgIdTC.setCellValueFactory(new PropertyValueFactory<>("assignmentId"));
        asgVolTC.setCellValueFactory(new PropertyValueFactory<>("volunteerId"));
        asgEventTC.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        asgRespTC.setCellValueFactory(new PropertyValueFactory<>("responsibility"));
        asgStatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        assignmentsTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        eventBox.setItems(FXCollections.observableArrayList(
                DataStore.get().getEvents().stream()
                        .map(ArrangeClubEvent::getEventName)
                        .collect(Collectors.toList())));
        eventBox.getSelectionModel().selectFirst();
        refresh();
    }

    private void refresh() {
        assignmentsTV.setItems(FXCollections.observableArrayList(DataStore.get().getVolunteerAssignments()));
    }

    @javafx.fxml.FXML
    public void assignOA(ActionEvent actionEvent) {
        DataStore store = DataStore.get();
        List<String> events = store.getEvents().stream()
                .map(ArrangeClubEvent::getEventName)
                .collect(Collectors.toList());
        if (events.isEmpty()) {
            Ui.info("Create an event first.");
            return;
        }
        String eventName = eventBox.getValue();
        String responsibility = responsibilityTF.getText().trim();
        if (eventName == null || responsibility.isEmpty()) {
            Ui.info("Please choose an event and enter a responsibility.");
            return;
        }
        int volunteerId = parseIntSafe(volunteerTF.getText());
        int eventId = events.indexOf(eventName) + 1;
        String assignId = "VA" + (store.getVolunteerAssignments().size() + 1);
        store.getVolunteerAssignments().add(new VolunteerAssignment(assignId, volunteerId, eventId, responsibility, "Assigned"));
        logHistory("Assigned a volunteer to " + eventName);
        store.save();
        volunteerTF.clear();
        responsibilityTF.clear();
        refresh();
        Ui.info("Volunteer assigned to \"" + eventName + "\".");
    }

    private static int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void logHistory(String action) {
        User user = Session.getCurrentUser();
        if (user != null) {
            DataStore.get().logHistory(user.getUserId(), action);
        }
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
        // already on Assign Volunteer
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
