package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.Notice;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.Notifications;
import c213.dosaoopproject.fahmida.util.SceneManager;

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
 * <p>Fills the table from {@link DataStore#getNotices()} — the same list a Club
 * Advisor writes to when posting a notice — and opens the details screen when the
 * student selects a row and clicks "Read More".</p>
 */
public class U1G1_ViewNoticesController {

    @javafx.fxml.FXML
    private TableColumn<Notice, String> noticeTitleTC;
    @javafx.fxml.FXML
    private Label viewNoticeLabel;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private TableView<Notice> noticeTV;
    @javafx.fxml.FXML
    private TableColumn<Notice, String> postedByTC;
    @javafx.fxml.FXML
    private TableColumn<Notice, Object> datePostedTC;
    @javafx.fxml.FXML
    private TableColumn<Notice, String> categoryTC;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;

    @javafx.fxml.FXML
    public void initialize() {
        // Header labels
        User user = Session.getCurrentUser();
        if (user != null) {
            if (nameLabel11 != null) {
                nameLabel11.setText(user.getFullName());
            }
            if (userIdLabel11 != null) {
                userIdLabel11.setText(user.getLoginId());
            }
        }

        // Map each column to a Notice property (getTitle, getClubName, ...)
        noticeTitleTC.setCellValueFactory(new PropertyValueFactory<>("title"));
        postedByTC.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        datePostedTC.setCellValueFactory(new PropertyValueFactory<>("datePosted"));

        // Fill the table from the shared data store.
        noticeTV.setItems(FXCollections.observableArrayList(DataStore.get().getNotices()));

        // Drop the stray empty placeholder column ("C2") left over in the FXML.
        noticeTV.getColumns().removeIf(c -> "C2".equals(c.getText()));

        // Add a per-row "Read More" button that opens that notice's details.
        addReadMoreColumn();

        // Make all columns (including the button) share the visible width — no
        // horizontal scrolling, so the Read More button sits beside each notice.
        noticeTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /** Appends a column whose every row shows a "Read More" button for that notice. */
    private void addReadMoreColumn() {
        TableColumn<Notice, Void> actionCol = new TableColumn<>("");
        actionCol.setPrefWidth(120);
        actionCol.setMinWidth(120);
        actionCol.setSortable(false);
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button("Read More");
            {
                button.setOnAction(e -> {
                    Notice notice = getTableView().getItems().get(getIndex());
                    openDetails(notice);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : button);
            }
        });
        noticeTV.getColumns().add(actionCol);
    }

    /** Hands the chosen notice to the details screen and navigates there. */
    private void openDetails(Notice notice) {
        U1G1_NoticeDetailsController.setSelectedNotice(notice);
        SceneManager.switchTo("U1G1_NoticeDetails");
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
