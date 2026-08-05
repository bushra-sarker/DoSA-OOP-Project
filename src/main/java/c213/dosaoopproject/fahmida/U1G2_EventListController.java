package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.model.EventRegistration;
import c213.dosaoopproject.fahmida.model.Student;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.SceneManager;
import c213.dosaoopproject.fahmida.util.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * User-1 Goal-2: Register for Event. Lists the club events from the DataStore;
 * "Reg Now" creates an {@link EventRegistration} for the logged-in student.
 */
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
    private TableColumn<ArrangeClubEvent, String> availableSeatsTC;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, String> eventNameTC;
    @javafx.fxml.FXML
    private TableView<ArrangeClubEvent> EventListTV;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, Object> dateTC;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        eventNameTC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        eventDescriptionTC.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateTC.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        venueTC.setCellValueFactory(new PropertyValueFactory<>("venue"));
        availableSeatsTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        EventListTV.setItems(FXCollections.observableArrayList(DataStore.get().getEvents()));
    }

    @javafx.fxml.FXML
    public void RegNowOA(ActionEvent actionEvent) {
        ArrangeClubEvent event = EventListTV.getSelectionModel().getSelectedItem();
        if (event == null) {
            Ui.info("Please select an event first.");
            return;
        }
        User user = Session.getCurrentUser();
        String department = (user instanceof Student s) ? s.getDepartment() : "";
        DataStore store = DataStore.get();
        store.getEventRegistrations().add(new EventRegistration(
                user.getUserId(), event.getEventName(), department, "Pending"));
        store.logHistory(user.getUserId(), "Registered for event: " + event.getEventName());
        store.notifyRole("Club Advisor", user.getFullName() + " registered for "
                + event.getEventName() + ".");
        Ui.info("Registered for \"" + event.getEventName() + "\" (status: Pending).");
    }

    @javafx.fxml.FXML
    public void backtoDashboardOA(ActionEvent actionEvent) {
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
        // already on this screen
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
