package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.HistoryEntry;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;

/**
 * User-1 Goal: Track History. Shows the logged-in student's own activity log
 * (registrations, applications, complaints, downloads, ...).
 */
public class U1G8_TrackHistoryController {

    @javafx.fxml.FXML
    private TableView<HistoryEntry> historyTV;
    @javafx.fxml.FXML
    private TableColumn<HistoryEntry, Object> historyDateTC;
    @javafx.fxml.FXML
    private TableColumn<HistoryEntry, String> historyActionTC;

    @javafx.fxml.FXML
    public void initialize() {
        historyDateTC.setCellValueFactory(new PropertyValueFactory<>("date"));
        historyActionTC.setCellValueFactory(new PropertyValueFactory<>("action"));
        User user = Session.getCurrentUser();
        int uid = user != null ? user.getUserId() : -1;
        historyTV.setItems(FXCollections.observableArrayList(
                DataStore.get().getHistory().stream()
                        .filter(h -> h.getUserId() == uid)
                        .collect(Collectors.toList())));
    }

    // --- navigation ----------------------------------------------------------

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
        // already on this screen
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
