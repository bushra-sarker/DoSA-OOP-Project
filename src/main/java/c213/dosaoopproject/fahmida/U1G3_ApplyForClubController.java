package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * User-1 Goal-3: Apply for Club Membership. Lists all clubs; clicking
 * "Apply to Join" remembers the chosen club and opens the application form.
 */
public class U1G3_ApplyForClubController {

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
    private Label applyforclubLabel;
    @javafx.fxml.FXML
    private TableColumn<ClubInfo, Integer> totalMembersTC;
    @javafx.fxml.FXML
    private TableView<ClubInfo> applyForClubTV;
    @javafx.fxml.FXML
    private TableColumn<ClubInfo, String> clubNameTC;
    @javafx.fxml.FXML
    private TableColumn<ClubInfo, String> moderatorNameTC;
    @javafx.fxml.FXML
    private TableColumn<ClubInfo, String> categoryTC;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        clubNameTC.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        totalMembersTC.setCellValueFactory(new PropertyValueFactory<>("totalMembers"));
        moderatorNameTC.setCellValueFactory(new PropertyValueFactory<>("moderatorName"));
        applyForClubTV.setItems(FXCollections.observableArrayList(DataStore.get().getClubs()));
    }

    @javafx.fxml.FXML
    public void applyToJoinOA(ActionEvent actionEvent) throws IOException {
        ClubInfo club = applyForClubTV.getSelectionModel().getSelectedItem();

        if (club == null) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please select a club first.");
            return;
        }

        Session.setSelectedClub(club);

        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G3_ApplyForClubForm.fxml");
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
    public void registerEventsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G2_EventList.fxml");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
        // already on this screen
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
