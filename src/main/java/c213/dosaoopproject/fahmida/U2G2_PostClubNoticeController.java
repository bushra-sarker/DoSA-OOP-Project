package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.Notice;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * Club Advisor Goal: Post Club Notices. Shows the list of existing notices in a
 * table. The "Create Notice" button opens the separate create-notice screen
 * ({@link U2G2_CreateClubNoticeController}) where the new notice is entered.
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
        refresh();
    }

    private void refresh() {
        NoticeTableView.setItems(
                FXCollections.observableArrayList(DataStore.get().getNotices()));
    }

    // Opens the separate "Create Club Notice" screen.
    @javafx.fxml.FXML
    public void createNoticeButtonOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G2_CreateClubNotice");
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
