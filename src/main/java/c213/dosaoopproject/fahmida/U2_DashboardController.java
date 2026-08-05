package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.model.EventCompletionReport;
import c213.dosaoopproject.fahmida.model.EventRegistration;
import c213.dosaoopproject.fahmida.model.VolunteerAssignment;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.Notifications;
import c213.dosaoopproject.fahmida.util.SceneManager;
import c213.dosaoopproject.fahmida.util.Search;
import c213.dosaoopproject.fahmida.util.Ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Club Advisor (User-2) dashboard. The advisor FXML reuses the student handler
 * names, so each handler below maps to the advisor goal shown on its button.
 */
public class U2_DashboardController {

    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TextField searchOFCRTF;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        if (searchOFCRTF != null) {
            searchOFCRTF.setOnAction(e -> Ui.info(Search.query(searchOFCRTF.getText())));
        }
    }

    // "Update Club Information"
    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G1_updateClubinfo");
    }

    // "Post Club Notices and Announcements"
    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G2_PostClubNotice");
    }

    // "Review and Approve Club Membership Requests"
    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G3_ReviewandapproveClubMembership");
    }

    // "Arrange Club Events" — create a new event (feeds the students' lists).
    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
        Ui.prompt("Arrange Club Event", "Event name:").ifPresent(name -> {
            DataStore store = DataStore.get();
            int id = store.getEvents().size() + 1;
            store.getEvents().add(new ArrangeClubEvent(id, name,
                    "Created by advisor", LocalDate.now().plusDays(7), "TBA", "Upcoming"));
            logHistory("Created event: " + name);
            store.save();
            Ui.info("Event \"" + name + "\" created (Upcoming).");
        });
    }

    // "Assign Student Volunteers to Events"
    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        DataStore store = DataStore.get();
        List<String> events = eventNames(store);
        if (events.isEmpty()) {
            Ui.info("Create an event first.");
            return;
        }
        Ui.choose("Assign Volunteer", "Choose an event:", events).ifPresent(eventName ->
                Ui.prompt("Assign Volunteer", "Volunteer responsibility:").ifPresent(resp -> {
                    int eventId = events.indexOf(eventName) + 1;
                    String assignId = "VA" + (store.getVolunteerAssignments().size() + 1);
                    store.getVolunteerAssignments().add(new VolunteerAssignment(
                            assignId, 0, eventId, resp, "Assigned"));
                    logHistory("Assigned a volunteer to " + eventName);
                    store.save();
                    Ui.info("Volunteer assigned to \"" + eventName + "\".");
                }));
    }

    // "View Registered Participants for Events"
    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        String list = DataStore.get().getEventRegistrations().stream()
                .map(r -> r.getEventName() + " — student " + r.getStudentId()
                        + " (" + r.getStatus() + ")")
                .collect(Collectors.joining("\n"));
        Ui.info(list.isEmpty() ? "No participants registered yet." : list);
    }

    // "Advisor Submit Student's Event Completion Report"
    @javafx.fxml.FXML
    public void submitCompletionReportOA(ActionEvent actionEvent) {
        DataStore store = DataStore.get();
        List<String> events = eventNames(store);
        if (events.isEmpty()) {
            Ui.info("Create an event first.");
            return;
        }
        Ui.choose("Completion Report", "Choose an event:", events).ifPresent(eventName ->
                Ui.prompt("Completion Report", "Outcome summary:").ifPresent(summary -> {
                    int eventId = events.indexOf(eventName) + 1;
                    int reportId = store.getCompletionReports().size() + 1;
                    store.getCompletionReports().add(new EventCompletionReport(
                            reportId, eventId, eventName, 0, summary, "Submitted"));
                    logHistory("Submitted completion report for " + eventName);
                    store.save();
                    Ui.info("Completion report submitted for \"" + eventName + "\".");
                }));
    }

    // "View Club Activity History"
    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
        User user = Session.getCurrentUser();
        String history = DataStore.get().getHistory().stream()
                .filter(h -> user != null && h.getUserId() == user.getUserId())
                .map(h -> h.getDate() + " — " + h.getAction())
                .collect(Collectors.joining("\n"));
        Ui.info(history.isEmpty() ? "No activity yet." : history);
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) {
        String programs = DataStore.get().getCommunityPrograms().stream()
                .map(p -> "• " + p.getDetails())
                .collect(Collectors.joining("\n"));
        Ui.info(programs.isEmpty() ? "No community programs." : programs);
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2_Dashboard");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        Notifications.showForCurrentUser();
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you want to exit?", ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);
        confirm.showAndWait().ifPresent(choice -> {
            if (choice == ButtonType.YES) {
                Session.clear();
                SceneManager.switchTo("LoginView");
            }
        });
    }

    // --- helpers -------------------------------------------------------------

    private static List<String> eventNames(DataStore store) {
        return store.getEvents().stream()
                .map(ArrangeClubEvent::getEventName)
                .collect(Collectors.toList());
    }

    private static void logHistory(String action) {
        User user = Session.getCurrentUser();
        if (user != null) {
            DataStore.get().logHistory(user.getUserId(), action);
        }
    }
}
