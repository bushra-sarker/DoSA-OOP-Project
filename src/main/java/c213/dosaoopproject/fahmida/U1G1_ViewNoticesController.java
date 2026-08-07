package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.Notice;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * User-1 Goal-1: View DoSA Notices and Announcements.
 *
 * <p>Shows all notices in a table. Each row has a "Read More" button that
 * opens that notice's details on the {@link U1G1_NoticeDetailsController}
 * screen.</p>
 */
public class U1G1_ViewNoticesController {

    @javafx.fxml.FXML
    private TableView<Notice> noticeTV;
    @javafx.fxml.FXML
    private TableColumn<Notice, String> noticeTitleTC;
    @javafx.fxml.FXML
    private TableColumn<Notice, String> postedByTC;
    @javafx.fxml.FXML
    private TableColumn<Notice, String> categoryTC;
    @javafx.fxml.FXML
    private TableColumn<Notice, Object> datePostedTC;
    @javafx.fxml.FXML
    private TableColumn<Notice, Void> actionTC;

    @javafx.fxml.FXML
    private Label viewNoticeLabel;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private ImageView ppImageView11;

    @javafx.fxml.FXML
    public void initialize() {
        // Show the logged-in user's name and ID on the header.
        User user = Session.getCurrentUser();
        if (user != null) {
            nameLabel11.setText(user.getFullName());
            userIdLabel11.setText(user.getLoginId());
        }

        // Tell each column which Notice field to read.
        noticeTitleTC.setCellValueFactory(new PropertyValueFactory<>("title"));
        postedByTC.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        datePostedTC.setCellValueFactory(new PropertyValueFactory<>("datePosted"));

        // Put a "Read More" button on every row.
        addReadMoreButton();

        // Put all the notices into the table.
        noticeTV.setItems(FXCollections.observableArrayList(DataStore.get().getNotices()));
    }

    /** Makes the last column show a "Read More" button on each row. */
    private void addReadMoreButton() {
        actionTC.setCellFactory(column -> new TableCell<Notice, Void>() {
            private final Button readMoreBtn = new Button("Read More");

            {
                readMoreBtn.setOnAction(e -> {
                    // Find the notice on this row and open its details.
                    Notice notice = getTableView().getItems().get(getIndex());
                    U1G1_NoticeDetailsController.setSelectedNotice(notice);
                    SceneManager.switchTo("U1G1_NoticeDetails");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);          // empty rows get no button
                } else {
                    setGraphic(readMoreBtn);
                }
            }
        });
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
    public void viewNoticesOA(ActionEvent actionEvent) {
        // already on this screen — no-op
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
    public void logOutOA(ActionEvent actionEvent) {
        Session.clear();
        SceneManager.switchTo("LoginView");
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
    public void trackHistoryOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G8_TrackHistory");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U1G6_SubmitComplaints");
    }
}
