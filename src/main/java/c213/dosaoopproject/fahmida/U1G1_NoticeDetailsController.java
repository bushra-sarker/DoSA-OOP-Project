package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.model.Notice;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.SceneManager;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * User-1 Goal-1 (details): shows the full text of the notice the student picked
 * on the "View Notices" screen ("Read More").
 */
public class U1G1_NoticeDetailsController {

    /** The notice chosen on the list screen, handed over before navigation. */
    private static Notice selectedNotice;

    public static void setSelectedNotice(Notice notice) {
        selectedNotice = notice;
    }

    @javafx.fxml.FXML
    private Label viewNoticeLabel;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private Label detailTitle;
    @javafx.fxml.FXML
    private Label detailMeta;
    @javafx.fxml.FXML
    private Label detailBody;

    @javafx.fxml.FXML
    public void initialize() {
        User user = Session.getCurrentUser();
        if (user != null) {
            if (nameLabel11 != null) {
                nameLabel11.setText(user.getFullName());
            }
            if (userIdLabel11 != null) {
                userIdLabel11.setText(user.getLoginId());
            }
        }

        if (viewNoticeLabel != null) {
            viewNoticeLabel.setText("Notice Details");
        }
        if (selectedNotice != null) {
            detailTitle.setText(selectedNotice.getTitle());
            detailMeta.setText("Posted by " + selectedNotice.getClubName()
                    + "  •  " + selectedNotice.getCategory()
                    + "  •  " + selectedNotice.getDatePosted());
            detailBody.setText(selectedNotice.getBody());
        } else {
            detailTitle.setText("No notice selected");
            detailMeta.setText("");
            detailBody.setText("Go back and click \"Read More\" on a notice.");
        }
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G1_ViewNotices");
    }

    @javafx.fxml.FXML
    public void goDashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Session.clear();
        SceneManager.switchTo("LoginView");
    }

    // --- remaining navigation buttons (wired in later phases) ---------------

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
    }
}
