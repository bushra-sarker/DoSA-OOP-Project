package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class U1G2_EventListController {

    @javafx.fxml.FXML
    private Label regForEventsLabel;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private TextField searchOFCRTF;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, String> eventDescriptionTC;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, String> venueTC;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, String> eventNameTC;
    @javafx.fxml.FXML
    private TableView<ArrangeClubEvent> EventListTV;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, Object> dateTC;

    private ObservableList<ArrangeClubEvent> eventList;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);

        eventNameTC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        eventDescriptionTC.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateTC.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        venueTC.setCellValueFactory(new PropertyValueFactory<>("venue"));

        eventList = FXCollections.observableArrayList(DataStore.get().getEvents());
        EventListTV.setItems(eventList);
        EventListTV.getSelectionModel().selectFirst();
    }

    @javafx.fxml.FXML
    public void RegNowOA(ActionEvent actionEvent) throws IOException {
        ArrangeClubEvent event = EventListTV.getSelectionModel().getSelectedItem();

        if (event == null) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please select an event first.");
            return;
        }

        // save the chosen event so the next screen knows which event to register for
        Session.setSelectedEvent(event);

        // navigate to the registration form screen
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G2_RegisterForEvents.fxml");
    }
    @javafx.fxml.FXML
    public void backtoDashboardOA(ActionEvent actionEvent) throws IOException {
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
    public void registerEventsOA(ActionEvent actionEvent) {
        // already on this screen
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G3_ApplyForClub.fxml");
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G4_ViewEventSchedule.fxml");
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
