package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.model.ClubAdvisor;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Search;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
            searchOFCRTF.setOnAction(e -> ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, Search.query(searchOFCRTF.getText())));
        }
        showCardCounts(user);
    }

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
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G4_ArrangeEducativeEvent.fxml");
    }


    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G5_AssignCommunityProgramVolunteer.fxml");
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
    public void studentdashboardOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2_Dashboard.fxml");
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
                try {
                    SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/LoginView.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }



    private static ArrayList<String> eventNames(DataStore store) {
        return store.getEvents().stream()
                .map(ArrangeClubEvent::getEventName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static void logHistory(String action) {
        User user = Session.getCurrentUser();
        if (user != null) {
            DataStore.get().logHistory(user.getUserId(), action);
        }
    }
}
