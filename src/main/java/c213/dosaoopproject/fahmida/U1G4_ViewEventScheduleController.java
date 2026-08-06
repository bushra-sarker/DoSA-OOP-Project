package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * User-1 Goal-4: View Event Schedule. Read-only list of upcoming events from the
 * DataStore (the same events an advisor creates).
 */
public class U1G4_ViewEventScheduleController {

    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, String> timeTC;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, String> activityNameTC;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, String> venueTC;
    @javafx.fxml.FXML
    private TableView<ArrangeClubEvent> EventScheduleTV;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, Object> dateTC;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, String> organizingClubTC;

    // Clubs shown in the "Organizing Club" column, picked at random for each event.
    private static final String[] ORGANIZING_CLUBS = {"EEE", "CSE"};

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        activityNameTC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        venueTC.setCellValueFactory(new PropertyValueFactory<>("venue"));
        dateTC.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        // "time" is not part of the event model; column left empty.

        ObservableList<ArrangeClubEvent> events =
                FXCollections.observableArrayList(DataStore.get().getEvents());

        // Give each event one random organizing club and keep it fixed.
        Random random = new Random();
        Map<ArrangeClubEvent, String> clubOf = new HashMap<>();
        for (ArrangeClubEvent event : events) {
            clubOf.put(event, ORGANIZING_CLUBS[random.nextInt(ORGANIZING_CLUBS.length)]);
        }
        organizingClubTC.setCellValueFactory(cell ->
                new SimpleStringProperty(clubOf.get(cell.getValue())));

        EventScheduleTV.setItems(events);
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
        // already on this screen
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G6_SubmitComplaints");
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G5_CommunityService");
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
