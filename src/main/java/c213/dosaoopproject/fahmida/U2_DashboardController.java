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

    // "Arrange Club Events" — dedicated screen (feeds the students' event lists).
    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G4_ArrangeEvent");
    }

    // "Assign Student Volunteers to Events"
    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G5_AssignVolunteer");
    }

    // "View Registered Participants for Events"
    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G6_ViewParticipants");
    }

    // "Advisor Submit Student's Event Completion Report"
    @javafx.fxml.FXML
    public void submitCompletionReportOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G7_CompletionReport");
    }

    // "View Club Activity History"
    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G8_ActivityHistory");
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
