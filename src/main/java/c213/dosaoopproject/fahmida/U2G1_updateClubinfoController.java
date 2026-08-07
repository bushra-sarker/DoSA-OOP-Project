package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ClubAdvisor;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Club Advisor Goal: Update Club Information. Loads the advisor's club into the
 * form and saves the edits back to the DataStore.
 */
public class U2G1_updateClubinfoController {

    @javafx.fxml.FXML
    private Label welcomeUserLabel;
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private TextField scheduleTF;
    @javafx.fxml.FXML
    private TextField descriptionTF;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private TextField venueTF;
    @javafx.fxml.FXML
    private Label nameLabel11;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        ClubInfo club = currentClub();
        if (club != null) {
            if (welcomeUserLabel != null) {
                welcomeUserLabel.setText(club.getClubName());
            }
            descriptionTF.setText(club.getDescription());
            scheduleTF.setText(club.getMeetingSchedule());
            venueTF.setText(club.getVenue());
            phoneTF.setText(club.getContactPhone());
            emailTF.setText(club.getContactEmail());
        }
    }

    @javafx.fxml.FXML
    public void saveChangesOA(ActionEvent actionEvent) {
        ClubInfo club = currentClub();
        if (club == null) {
            Ui.info("No club is linked to this advisor.");
            return;
        }
        club.update(descriptionTF.getText(), scheduleTF.getText(),
                emailTF.getText(), phoneTF.getText(), venueTF.getText());
        DataStore.get().save();
        logHistory("Updated club info for " + club.getClubName());
        Ui.info("Club information saved.");
        SceneManager.switchTo("U2_Dashboard");
    }

    /** The club managed by the logged-in advisor. */
    private ClubInfo currentClub() {
        User user = Session.getCurrentUser();
        if (!(user instanceof ClubAdvisor advisor)) {
            return null;
        }
        return DataStore.get().getClubs().stream()
                .filter(c -> c.getClubId() == advisor.getClubId())
                .findFirst().orElse(null);
    }

    private void logHistory(String action) {
        User user = Session.getCurrentUser();
        if (user != null) {
            DataStore.get().logHistory(user.getUserId(), action);
        }
    }

    // --- sidebar navigation --------------------------------------------------

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) {
        // already on Update Club Info
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
    public void studentdashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2_Dashboard");
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
    public void communityProgramOA(ActionEvent actionEvent) {
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
