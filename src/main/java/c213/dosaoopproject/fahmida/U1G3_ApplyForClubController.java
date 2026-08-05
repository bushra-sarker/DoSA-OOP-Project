package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.model.ClubMembershipApplication;
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
 * User-1 Goal-3: Apply for Club Membership. Lists clubs from the DataStore;
 * "Apply to Join" creates a {@link ClubMembershipApplication} (status Pending)
 * that the Club Advisor later reviews.
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
    public void applyToJoinOA(ActionEvent actionEvent) {
        ClubInfo club = applyForClubTV.getSelectionModel().getSelectedItem();
        if (club == null) {
            Ui.info("Please select a club first.");
            return;
        }
        User user = Session.getCurrentUser();
        String major = (user instanceof Student s) ? s.getDepartment() : "";
        DataStore store = DataStore.get();
        store.getMembershipApplications().add(new ClubMembershipApplication(
                store.getMembershipApplications().size() + 1, user.getUserId(),
                club.getClubName(), major, "Interested in joining", ""));
        store.logHistory(user.getUserId(), "Applied to club: " + club.getClubName());
        store.notifyRole("Club Advisor", "New membership application from "
                + user.getFullName() + " for " + club.getClubName() + ".");
        Ui.info("Application to \"" + club.getClubName() + "\" submitted (Pending review).");
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
        SceneManager.switchTo("U1G2_EventList");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
        // already on this screen
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G4_ViewEventSchedule");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G6");
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
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
