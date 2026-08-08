package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.CommunityServiceProgram;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * User-1 Goal: Community Service Programs. Lists the programs and lets the
 * student sign up for the selected one as a volunteer.
 */
public class U1G5_CommunityServiceController {

    @javafx.fxml.FXML
    private TableView<CommunityServiceProgram> programsTV;
    @javafx.fxml.FXML
    private TableColumn<CommunityServiceProgram, String> typeTC;
    @javafx.fxml.FXML
    private TableColumn<CommunityServiceProgram, String> durationTC;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TableColumn venueTC;
    @javafx.fxml.FXML
    private TableColumn dateTC;
    @javafx.fxml.FXML
    private TableColumn EventNameTC;

    @javafx.fxml.FXML
    public void initialize() {
        EventNameTC.setCellValueFactory(new PropertyValueFactory<>("programName"));
        typeTC.setCellValueFactory(new PropertyValueFactory<>("type"));
        durationTC.setCellValueFactory(new PropertyValueFactory<>("campaignDuration"));
        venueTC.setCellValueFactory(new PropertyValueFactory<>("venue"));
        refresh();
    }

    private void refresh() {
        programsTV.setItems(FXCollections.observableArrayList(
                DataStore.get().getCommunityPrograms()));
    }

    @javafx.fxml.FXML
    public void registerVolunteerOA(ActionEvent actionEvent) {
        CommunityServiceProgram program = programsTV.getSelectionModel().getSelectedItem();

        if (program == null) {
            Ui.info("Please select a program first.");
            return;
        }

        // remember the chosen program so the form can auto-fill its name
        Session.setSelectedProgram(program);

        // open the volunteer registration form
        SceneManager.switchTo("U1G5_CommunityServiceForm");
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
        // already on this screen
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
        SceneManager.switchTo("U1G8_TrackHistory");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        c213.dosaoopproject.fahmida.utility.Notifications.showForCurrentUser();
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Session.clear();
        SceneManager.switchTo("LoginView");
    }
}
