package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.model.EventCompletionReport;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.Notifications;
import c213.dosaoopproject.fahmida.util.SceneManager;
import c213.dosaoopproject.fahmida.util.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Club Advisor Goal: Submit a Student's Event Completion Report. Captures the
 * actual attendance and outcome for a completed event and stores it in the shared
 * DataStore, then lists all submitted reports below.
 */
public class U2G7_CompletionReportController {

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
    private TextField attendanceTF;
    @javafx.fxml.FXML
    private TextArea outcomeTA;
    @javafx.fxml.FXML
    private TableView<EventCompletionReport> reportsTV;
    @javafx.fxml.FXML
    private TableColumn<EventCompletionReport, Integer> rptIdTC;
    @javafx.fxml.FXML
    private TableColumn<EventCompletionReport, String> rptEventTC;
    @javafx.fxml.FXML
    private TableColumn<EventCompletionReport, Integer> rptAttendTC;
    @javafx.fxml.FXML
    private TableColumn<EventCompletionReport, String> rptOutcomeTC;
    @javafx.fxml.FXML
    private TableColumn<EventCompletionReport, String> rptStatusTC;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        rptIdTC.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        rptEventTC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        rptAttendTC.setCellValueFactory(new PropertyValueFactory<>("actualAttendance"));
        rptOutcomeTC.setCellValueFactory(new PropertyValueFactory<>("outcomeSummary"));
        rptStatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        reportsTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        eventBox.setItems(FXCollections.observableArrayList(DataStore.get().getEvents().stream()
                .map(ArrangeClubEvent::getEventName).collect(Collectors.toList())));
        eventBox.getSelectionModel().selectFirst();
        refresh();
    }

    private void refresh() {
        reportsTV.setItems(FXCollections.observableArrayList(DataStore.get().getCompletionReports()));
    }

    @javafx.fxml.FXML
    public void submitReportOA(ActionEvent actionEvent) {
        DataStore store = DataStore.get();
        List<String> events = store.getEvents().stream()
                .map(ArrangeClubEvent::getEventName).collect(Collectors.toList());
        if (events.isEmpty()) {
            Ui.info("Create an event first.");
            return;
        }
        String eventName = eventBox.getValue();
        String summary = outcomeTA.getText().trim();
        if (eventName == null || summary.isEmpty()) {
            Ui.info("Please choose an event and enter an outcome summary.");
            return;
        }
        int eventId = events.indexOf(eventName) + 1;
        int reportId = store.getCompletionReports().size() + 1;
        store.getCompletionReports().add(new EventCompletionReport(reportId, eventId, eventName,
                parseIntSafe(attendanceTF.getText()), summary, "Submitted"));
        logHistory("Submitted completion report for " + eventName);
        store.save();
        attendanceTF.clear();
        outcomeTA.clear();
        refresh();
        Ui.info("Completion report submitted for \"" + eventName + "\".");
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
        SceneManager.switchTo("U2G5_AssignVolunteer");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G6_ViewParticipants");
    }

    @javafx.fxml.FXML
    public void submitCompletionReportOA(ActionEvent actionEvent) {
        // already on Completion Report
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
