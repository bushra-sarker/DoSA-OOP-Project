package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ClubAdvisor;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.model.Notice;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalDate;

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

        for (ClubInfo club : DataStore.get().getClubs()) {
            clubNameCB.getItems().add(club.getClubName());
        }
        clubNameCB.setValue(currentClubName());
    }

    @javafx.fxml.FXML
    public void postNoticeOA(ActionEvent actionEvent) throws IOException {
        String club = clubNameCB.getValue();
        String title = noticeTitleTF.getText().trim();
        String body = noticeBodyTF.getText().trim();

        if (club == null || club.isEmpty()) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please choose a club.");
            return;
        }
        if (title.isEmpty() || body.isEmpty()) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please enter both a title and a body.");
            return;
        }

        DataStore store = DataStore.get();
        store.getNotices().add(new Notice(
                store.getNotices().size() + 1, club,
                title, body, "Club Activity", LocalDate.now()));
        logHistory("Posted notice: " + title);
        store.notifyRole("Student", "New notice: " + title);
        store.save();

        ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, "Notice posted. Students can now see it.");
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G2_PostClubNotice.fxml");
    }

    @javafx.fxml.FXML
    public void backtoDashboardOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2_Dashboard.fxml");
    }


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


    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2_Dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G1_updateClubinfo.fxml");
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G2_PostClubNotice.fxml");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G3_ReviewandapproveClubMembership.fxml");
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2_Dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2_Dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2_Dashboard.fxml");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2_Dashboard.fxml");
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
