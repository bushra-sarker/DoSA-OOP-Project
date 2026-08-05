package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.model.ClubAdvisor;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.model.EventCompletionReport;
import c213.dosaoopproject.fahmida.model.EventRegistration;
import c213.dosaoopproject.fahmida.model.VolunteerAssignment;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.Notifications;
import c213.dosaoopproject.fahmida.util.SceneManager;
import c213.dosaoopproject.fahmida.util.Search;
import c213.dosaoopproject.fahmida.util.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
    private Label welcomeUserLabel;
    @javafx.fxml.FXML
    private Label clubMembersCardLabel;
    @javafx.fxml.FXML
    private Label pendingRequestsCardLabel;
    @javafx.fxml.FXML
    private Label upcomingEventsCardLabel;
    @javafx.fxml.FXML
    private Label volunteerCardLabel;

    @javafx.fxml.FXML
    public void initialize() {
        User user = Session.getCurrentUser();
        Ui.greet(nameLabel11, userIdLabel11);
        if (welcomeUserLabel != null && user != null) {
            welcomeUserLabel.setText("Welcome, " + user.getFullName() + "!");
        }
        if (searchOFCRTF != null) {
            searchOFCRTF.setOnAction(e -> Ui.info(Search.query(searchOFCRTF.getText())));
        }
        showCardCounts(user);
    }

    /** Fills the four summary cards with real counts from the data store. */
    private void showCardCounts(User user) {
        DataStore store = DataStore.get();
        if (clubMembersCardLabel != null) {
            int members = 0;
            if (user instanceof ClubAdvisor advisor) {
                members = store.getClubs().stream()
                        .filter(c -> c.getClubId() == advisor.getClubId())
                        .mapToInt(ClubInfo::getTotalMembers).findFirst().orElse(0);
            }
            clubMembersCardLabel.setText(String.valueOf(members));
        }
        if (pendingRequestsCardLabel != null) {
            long n = store.getMembershipApplications().stream()
                    .filter(a -> "Pending".equalsIgnoreCase(a.getStatus())).count();
            pendingRequestsCardLabel.setText(String.valueOf(n));
        }
        if (upcomingEventsCardLabel != null) {
            long n = store.getEvents().stream()
                    .filter(e -> "Upcoming".equalsIgnoreCase(e.getStatus())).count();
            upcomingEventsCardLabel.setText(String.valueOf(n));
        }
        if (volunteerCardLabel != null) {
            volunteerCardLabel.setText(String.valueOf(store.getVolunteerAssignments().size()));
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
        // Single modal capturing all event fields.
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Arrange Club Event");
        dialog.setHeaderText("Create a new event");
        ButtonType createType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(createType, ButtonType.CANCEL);

        TextField nameField = new TextField();
        nameField.setPromptText("Event name");
        TextArea descArea = new TextArea();
        descArea.setPromptText("Description");
        descArea.setPrefRowCount(3);
        descArea.setWrapText(true);
        DatePicker datePicker = new DatePicker(LocalDate.now().plusDays(7));
        TextField venueField = new TextField();
        venueField.setPromptText("Venue");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.add(new Label("Event Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descArea, 1, 1);
        grid.add(new Label("Date:"), 0, 2);
        grid.add(datePicker, 1, 2);
        grid.add(new Label("Venue:"), 0, 3);
        grid.add(venueField, 1, 3);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(bt -> bt);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isEmpty() || result.get() != createType) {
            return; // cancelled
        }

        String name = nameField.getText().trim();
        if (name.isEmpty() || datePicker.getValue() == null) {
            Ui.info("Please enter at least an event name and date.");
            return;
        }

        DataStore store = DataStore.get();
        int id = store.getEvents().size() + 1;
        String venue = venueField.getText().isBlank() ? "TBA" : venueField.getText().trim();
        String desc = descArea.getText().isBlank() ? "Created by advisor" : descArea.getText().trim();
        store.getEvents().add(new ArrangeClubEvent(id, name, desc,
                datePicker.getValue(), venue, "Upcoming"));
        logHistory("Created event: " + name);
        store.save();
        Ui.info("Event \"" + name + "\" created (Upcoming).");
    }

    // "Assign Student Volunteers to Events" — single modal for all fields.
    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        DataStore store = DataStore.get();
        List<String> events = eventNames(store);
        if (events.isEmpty()) {
            Ui.info("Create an event first.");
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Assign Volunteer");
        dialog.setHeaderText("Assign a student volunteer to an event");
        ButtonType assignType = new ButtonType("Assign", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(assignType, ButtonType.CANCEL);

        ComboBox<String> eventBox = new ComboBox<>(FXCollections.observableArrayList(events));
        eventBox.getSelectionModel().selectFirst();
        TextField volunteerField = new TextField();
        volunteerField.setPromptText("Volunteer student ID (e.g. 1001)");
        TextField responsibilityField = new TextField();
        responsibilityField.setPromptText("Responsibility");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.add(new Label("Event:"), 0, 0);
        grid.add(eventBox, 1, 0);
        grid.add(new Label("Volunteer ID:"), 0, 1);
        grid.add(volunteerField, 1, 1);
        grid.add(new Label("Responsibility:"), 0, 2);
        grid.add(responsibilityField, 1, 2);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(bt -> bt);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isEmpty() || result.get() != assignType) {
            return; // cancelled
        }

        String eventName = eventBox.getValue();
        String responsibility = responsibilityField.getText().trim();
        if (eventName == null || responsibility.isEmpty()) {
            Ui.info("Please choose an event and enter a responsibility.");
            return;
        }
        int volunteerId = parseIntSafe(volunteerField.getText());
        int eventId = events.indexOf(eventName) + 1;
        String assignId = "VA" + (store.getVolunteerAssignments().size() + 1);
        store.getVolunteerAssignments().add(new VolunteerAssignment(
                assignId, volunteerId, eventId, responsibility, "Assigned"));
        logHistory("Assigned a volunteer to " + eventName);
        store.save();
        Ui.info("Volunteer assigned to \"" + eventName + "\".");
    }

    private static int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
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

    // "Advisor Submit Student's Event Completion Report" — single modal.
    @javafx.fxml.FXML
    public void submitCompletionReportOA(ActionEvent actionEvent) {
        DataStore store = DataStore.get();
        List<String> events = eventNames(store);
        if (events.isEmpty()) {
            Ui.info("Create an event first.");
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Event Completion Report");
        dialog.setHeaderText("Submit a completion report");
        ButtonType submitType = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(submitType, ButtonType.CANCEL);

        ComboBox<String> eventBox = new ComboBox<>(FXCollections.observableArrayList(events));
        eventBox.getSelectionModel().selectFirst();
        TextField attendanceField = new TextField();
        attendanceField.setPromptText("Actual attendance (number)");
        TextArea outcomeArea = new TextArea();
        outcomeArea.setPromptText("Outcome summary");
        outcomeArea.setPrefRowCount(3);
        outcomeArea.setWrapText(true);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));
        grid.add(new Label("Event:"), 0, 0);
        grid.add(eventBox, 1, 0);
        grid.add(new Label("Attendance:"), 0, 1);
        grid.add(attendanceField, 1, 1);
        grid.add(new Label("Outcome:"), 0, 2);
        grid.add(outcomeArea, 1, 2);
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(bt -> bt);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isEmpty() || result.get() != submitType) {
            return; // cancelled
        }

        String eventName = eventBox.getValue();
        String summary = outcomeArea.getText().trim();
        if (eventName == null || summary.isEmpty()) {
            Ui.info("Please choose an event and enter an outcome summary.");
            return;
        }
        int eventId = events.indexOf(eventName) + 1;
        int reportId = store.getCompletionReports().size() + 1;
        store.getCompletionReports().add(new EventCompletionReport(
                reportId, eventId, eventName, parseIntSafe(attendanceField.getText()),
                summary, "Submitted"));
        logHistory("Submitted completion report for " + eventName);
        store.save();
        Ui.info("Completion report submitted for \"" + eventName + "\".");
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
