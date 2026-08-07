package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ClubAdvisor;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.model.Notice;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

/**
 * Club Advisor Goal (create form): enter a new club notice and post it. The new
 * {@link Notice} is added to the shared DataStore, so it appears both on the
 * advisor's notice list and on every student's "View Notices" screen.
 */
public class U2G2_CreateClubNoticeController {

    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TextField noticeBodyTF;
    @javafx.fxml.FXML
    private ComboBox<String> clubNameCB;
    @javafx.fxml.FXML
    private TextField noticeTitleTF;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);

        // Fill the club dropdown with all club names.
        for (ClubInfo club : DataStore.get().getClubs()) {
            clubNameCB.getItems().add(club.getClubName());
        }
        // Pre-select the advisor's own club.
        clubNameCB.setValue(currentClubName());
    }

    // "Post Notice" button — saves the new notice, then goes back to the list.
    @javafx.fxml.FXML
    public void postNoticeOA(ActionEvent actionEvent) {
        String club = clubNameCB.getValue();
        String title = noticeTitleTF.getText().trim();
        String body = noticeBodyTF.getText().trim();

        if (club == null || club.isEmpty()) {
            Ui.info("Please choose a club.");
            return;
        }
        if (title.isEmpty() || body.isEmpty()) {
            Ui.info("Please enter both a title and a body.");
            return;
        }

        DataStore store = DataStore.get();
        store.getNotices().add(new Notice(
                store.getNotices().size() + 1, club,
                title, body, "Club Activity", LocalDate.now()));
        logHistory("Posted notice: " + title);
        store.notifyRole("Student", "New notice: " + title);
        store.save();

        Ui.info("Notice posted. Students can now see it.");
        SceneManager.switchTo("U2G2_PostClubNotice");
    }

    @javafx.fxml.FXML
    public void backtoDashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2_Dashboard");
    }

    // --- helpers -------------------------------------------------------------

    private String currentClubName() {
        User user = Session.getCurrentUser();
        if (user instanceof ClubAdvisor advisor) {
            return DataStore.get().getClubs().stream()
                    .filter(c -> c.getClubId() == advisor.getClubId())
                    .map(ClubInfo::getClubName)
                    .findFirst().orElse("DoSA");
        }
        return "DoSA";
    }

    private void logHistory(String action) {
        User user = Session.getCurrentUser();
        if (user != null) {
            DataStore.get().logHistory(user.getUserId(), action);
        }
    }

    // --- navigation (sidebar) ------------------------------------------------

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2_Dashboard");
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G1_updateClubinfo");
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G2_PostClubNotice");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G3_ReviewandapproveClubMembership");
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2_Dashboard");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2_Dashboard");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2_Dashboard");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2_Dashboard");
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
