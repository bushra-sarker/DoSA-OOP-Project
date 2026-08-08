package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
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
    public void gotoDashboardOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1_Dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1_Dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G1_ViewNotices.fxml");
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G2_EventList.fxml");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G3_ApplyForClub.fxml");
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
        // already on this screen
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G6_SubmitComplaints.fxml");
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G5_CommunityService.fxml");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G7_DownloadApproval.fxml");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G8_TrackHistory.fxml");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, "No new notifications.");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) throws IOException {
        Session.clear();
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/LoginView.fxml");
    }
}
