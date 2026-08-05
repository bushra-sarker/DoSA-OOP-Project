package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ClubMembershipApplication;
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

/**
 * Club Advisor Goal: Review and Approve Club Membership Requests. Lists the
 * applications students submitted and lets the advisor Approve or Reject the
 * selected one (updating its status in the shared DataStore).
 */
public class U2G3_ReviewandapproveClubMembershipController {

    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TableView<ClubMembershipApplication> applicationsTV;
    @javafx.fxml.FXML
    private TableColumn<ClubMembershipApplication, Integer> appStudentTC;
    @javafx.fxml.FXML
    private TableColumn<ClubMembershipApplication, String> appClubTC;
    @javafx.fxml.FXML
    private TableColumn<ClubMembershipApplication, String> appReasonTC;
    @javafx.fxml.FXML
    private TableColumn<ClubMembershipApplication, String> appStatusTC;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        appStudentTC.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        appClubTC.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        appReasonTC.setCellValueFactory(new PropertyValueFactory<>("reasonToJoin"));
        appStatusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        applicationsTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refresh();
    }

    private void refresh() {
        applicationsTV.setItems(FXCollections.observableArrayList(
                DataStore.get().getMembershipApplications()));
    }

    @javafx.fxml.FXML
    public void approveOA(ActionEvent actionEvent) {
        decide(true);
    }

    @javafx.fxml.FXML
    public void rejectOA(ActionEvent actionEvent) {
        decide(false);
    }

    private void decide(boolean approve) {
        ClubMembershipApplication app =
                applicationsTV.getSelectionModel().getSelectedItem();
        if (app == null) {
            Ui.info("Please select an application first.");
            return;
        }
        if (approve) {
            app.approve();
        } else {
            app.reject();
        }
        DataStore.get().save();
        DataStore.get().notify(app.getStudentId(), "Your membership request for "
                + app.getClubName() + " was " + app.getStatus() + ".");
        logHistory((approve ? "Approved" : "Rejected") + " membership for student "
                + app.getStudentId());
        refresh();
        Ui.info("Application " + (approve ? "approved." : "rejected."));
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
        SceneManager.switchTo("U2G2_PostClubNotice");
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
        // already on Review Membership
    }

    @javafx.fxml.FXML
    public void createNoticeButtonOA(ActionEvent actionEvent) {
        SceneManager.switchTo("U2G2_PostClubNotice");
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
