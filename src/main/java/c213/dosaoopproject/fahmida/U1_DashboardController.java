package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Search;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

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

            searchOFCRTF.setOnAction(e -> ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, Search.query(searchOFCRTF.getText())));
        }
        showCardCounts(user);
    }

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
    public void registerEventsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G2_EventList.fxml");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        Notifications.showForCurrentUser();
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G4_ViewEventSchedule.fxml");
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G5_CommunityService.fxml");
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G1_ViewNotices.fxml");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you want to exit?", ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);
        confirm.showAndWait().ifPresent(choice -> {
            if (choice == ButtonType.YES) {
                Session.clear();
                try {
                    SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/LoginView.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G3_ApplyForClub.fxml");
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1_Dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G6_SubmitComplaints.fxml");
    }
}
