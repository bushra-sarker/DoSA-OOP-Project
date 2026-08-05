package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.CommunityServiceProgram;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.SceneManager;
import c213.dosaoopproject.fahmida.util.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * User-1 Goal: Community Service Programs. Lists the programs and lets the
 * student sign up for the selected one as a volunteer.
 */
public class U1G5_CommunityServiceController {

    @javafx.fxml.FXML
    private TableView<CommunityServiceProgram> programsTV;
    @javafx.fxml.FXML
    private TableColumn<CommunityServiceProgram, String> programNameTC;
    @javafx.fxml.FXML
    private TableColumn<CommunityServiceProgram, String> typeTC;
    @javafx.fxml.FXML
    private TableColumn<CommunityServiceProgram, String> durationTC;
    @javafx.fxml.FXML
    private TableColumn<CommunityServiceProgram, Integer> slotsTC;

    @javafx.fxml.FXML
    public void initialize() {
        programNameTC.setCellValueFactory(new PropertyValueFactory<>("programName"));
        typeTC.setCellValueFactory(new PropertyValueFactory<>("type"));
        durationTC.setCellValueFactory(new PropertyValueFactory<>("campaignDuration"));
        slotsTC.setCellValueFactory(new PropertyValueFactory<>("volunteerSlotsAvailable"));
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
        User user = Session.getCurrentUser();
        if (!program.registerAsVolunteer(user.getUserId())) {
            Ui.info("Sorry, \"" + program.getProgramName() + "\" has no slots left.");
            return;
        }
        DataStore.get().logHistory(user.getUserId(),
                "Volunteered for " + program.getProgramName());
        DataStore.get().save();
        refresh();
        Ui.info("You are registered as a volunteer for \"" + program.getProgramName() + "\".");
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
        c213.dosaoopproject.fahmida.util.Notifications.showForCurrentUser();
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Session.clear();
        SceneManager.switchTo("LoginView");
    }
}
