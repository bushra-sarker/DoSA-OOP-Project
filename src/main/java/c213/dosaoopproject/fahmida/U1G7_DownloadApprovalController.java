package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ApprovalLetter;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * User-1 Goal: Download Approval Letter. Lists the student's letters and lets
 * them generate a new one (written to a .txt file, standing in for a PDF).
 */
public class U1G7_DownloadApprovalController {

    @javafx.fxml.FXML
    private TableView<ApprovalLetter> lettersTV;
    @javafx.fxml.FXML
    private TableColumn<ApprovalLetter, Integer> letterIdTC;
    @javafx.fxml.FXML
    private TableColumn<ApprovalLetter, String> letterNameTC;
    @javafx.fxml.FXML
    private TableColumn<ApprovalLetter, Object> letterDateTC;
    @javafx.fxml.FXML
    private TableColumn<ApprovalLetter, String> letterFileTC;

    @javafx.fxml.FXML
    public void initialize() {
        letterIdTC.setCellValueFactory(new PropertyValueFactory<>("letterId"));
        letterNameTC.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        letterDateTC.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        letterFileTC.setCellValueFactory(new PropertyValueFactory<>("filePath"));
        refresh();
    }

    private void refresh() {
        User user = Session.getCurrentUser();
        int uid = user != null ? user.getUserId() : -1;
        lettersTV.setItems(FXCollections.observableArrayList(
                DataStore.get().getApprovalLetters().stream()
                        .filter(l -> l.getStudentId() == uid)
                        .collect(Collectors.toList())));
    }

    @javafx.fxml.FXML
    public void generateLetterOA(ActionEvent actionEvent) {
        User user = Session.getCurrentUser();
        if (user == null) {
            return;
        }
        DataStore store = DataStore.get();
        ApprovalLetter letter = new ApprovalLetter(
                store.getApprovalLetters().size() + 1, user.getUserId(),
                user.getFullName(), LocalDate.now());
        Path path = Path.of("ApprovalLetter_" + user.getLoginId() + ".txt");
        try {
            Files.writeString(path, letter.content());
            letter.setFilePath(path.toString());
            store.getApprovalLetters().add(letter);
            store.logHistory(user.getUserId(), "Downloaded approval letter");
            store.save();
            refresh();
            ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, "Approval letter saved to:\n" + path.toAbsolutePath());
        } catch (IOException e) {
            ToShowAlert.showWaitAlert(Alert.AlertType.ERROR, "Could not save the letter: " + e.getMessage());
        }
    }

    // --- navigation ----------------------------------------------------------

    @javafx.fxml.FXML
    public void gotoDashboardOA(ActionEvent actionEvent) throws IOException {
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
    public void clubMembershipOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G3_ApplyForClub.fxml");
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
    public void submitComplaintsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G6_SubmitComplaints.fxml");
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
        // already on this screen
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U1G8_TrackHistory.fxml");
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
        Notifications.showForCurrentUser();
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) throws IOException {
        Session.clear();
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/LoginView.fxml");
    }
}
