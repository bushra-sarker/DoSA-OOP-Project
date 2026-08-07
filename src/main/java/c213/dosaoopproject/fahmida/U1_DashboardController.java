package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Search;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class U1_DashboardController
{
    @javafx.fxml.FXML
    private Label welcomeUserLabel;
    @javafx.fxml.FXML
    private Label noticesCardLabel;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private Label trackHistoryLabel;
    @javafx.fxml.FXML
    private Label submitComplaintsCardLabel;
    @javafx.fxml.FXML
    private TextField searchOFCRTF;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label downloadLetterCardLabel;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;

    @javafx.fxml.FXML
    public void initialize() {
        User user = Session.getCurrentUser();
        if (user != null) {
            if (welcomeUserLabel != null) {
                welcomeUserLabel.setText("Welcome, " + user.getFullName());
            }
            if (nameLabel11 != null) {
                nameLabel11.setText(user.getFullName());
            }
            if (userIdLabel11 != null) {
                userIdLabel11.setText(user.getLoginId());
            }
        }
        if (searchOFCRTF != null) {
            // Search runs when the user presses Enter in the search box.
            searchOFCRTF.setOnAction(e -> Ui.info(Search.query(searchOFCRTF.getText())));
        }
        showCardCounts(user);
    }

    /** Fills the four summary cards with real counts from the data store. */
    private void showCardCounts(User user) {
        DataStore store = DataStore.get();
        int uid = user != null ? user.getUserId() : -1;
        if (noticesCardLabel != null) {
            noticesCardLabel.setText(String.valueOf(store.getNotices().size()));
        }
        if (submitComplaintsCardLabel != null) {
            long n = store.getComplaints().stream().filter(c -> c.getStudentId() == uid).count();
            submitComplaintsCardLabel.setText(String.valueOf(n));
        }
        if (downloadLetterCardLabel != null) {
            long n = store.getApprovalLetters().stream().filter(l -> l.getStudentId() == uid).count();
            downloadLetterCardLabel.setText(String.valueOf(n));
        }
        if (trackHistoryLabel != null) {
            long n = store.getHistory().stream().filter(h -> h.getUserId() == uid).count();
            trackHistoryLabel.setText(String.valueOf(n));
        }
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G2_EventList");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        Notifications.showForCurrentUser();
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G4_ViewEventSchedule");
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G5_CommunityService");
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G1_ViewNotices");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you want to exit?", ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);
        confirm.showAndWait().ifPresent(choice -> {
            if (choice == ButtonType.YES) {
                Session.clear();
                SceneManager.switchTo("LoginView");
            }
        });
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G7_DownloadApproval");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G3_ApplyForClub");
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1_Dashboard");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G8_TrackHistory");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G6_SubmitComplaints");
    }
}