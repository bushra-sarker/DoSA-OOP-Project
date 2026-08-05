package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ClubAdvisor;
import c213.dosaoopproject.fahmida.model.ClubInfo;
import c213.dosaoopproject.fahmida.model.Notice;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.SceneManager;
import c213.dosaoopproject.fahmida.util.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.time.LocalDate;

/**
 * Club Advisor Goal: Post Club Notices. Lists the advisor's existing notices and
 * creates a new {@link Notice} — which immediately shows up in every student's
 * "View Notices" screen (both read the same DataStore list).
 */
public class U2G2_PostClubNoticeController {

    @javafx.fxml.FXML
    private TableColumn<Notice, String> noticeTitleTC;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private TableColumn<Notice, String> noticeBodyTC;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private TableView<Notice> NoticeTableView;
    @javafx.fxml.FXML
    private TableColumn<Notice, Object> dateTC;
    @javafx.fxml.FXML
    private Label nameLabel11;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        noticeTitleTC.setCellValueFactory(new PropertyValueFactory<>("title"));
        noticeBodyTC.setCellValueFactory(new PropertyValueFactory<>("body"));
        dateTC.setCellValueFactory(new PropertyValueFactory<>("datePosted"));
        NoticeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refresh();
    }

    private void refresh() {
        NoticeTableView.setItems(
                FXCollections.observableArrayList(DataStore.get().getNotices()));
    }

    @javafx.fxml.FXML
    public void createNoticeButtonOA(ActionEvent actionEvent) {
        Ui.prompt("Post Notice", "Notice title:").ifPresent(title ->
                Ui.prompt("Post Notice", "Notice body:").ifPresent(body -> {
                    DataStore store = DataStore.get();
                    store.getNotices().add(new Notice(
                            store.getNotices().size() + 1, currentClubName(),
                            title, body, "Club Activity", LocalDate.now()));
                    logHistory("Posted notice: " + title);
                    store.save();
                    refresh();
                    Ui.info("Notice posted. Students can now see it.");
                }));
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

    // --- navigation ----------------------------------------------------------

    @javafx.fxml.FXML
    public void backtoDashboardButtonOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2_Dashboard");
    }

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
        // already on Post Notice
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
