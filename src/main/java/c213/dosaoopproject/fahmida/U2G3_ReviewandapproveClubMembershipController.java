package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ClubMembershipApplication;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;

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
        refresh();
    }

    // Reloads the table from the shared data store.
    private void refresh() {
        applicationsTV.setItems(FXCollections.observableArrayList(
                DataStore.get().getMembershipApplications()));
    }

    // "Approve" button.
    @javafx.fxml.FXML
    public void approveOA(ActionEvent actionEvent) {
        ClubMembershipApplication app = applicationsTV.getSelectionModel().getSelectedItem();
        if (app == null) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please select an application first.");
            return;
        }

        boolean approve = true;
        String action;
        if (approve) {
            app.approve();
            action = "Approved";
        } else {
            app.reject();
            action = "Rejected";
        }

        DataStore.get().save();
        DataStore.get().notify(app.getStudentId(),
                "Your membership request for " + app.getClubName() + " was " + app.getStatus() + ".");
        logHistory(action + " membership for student " + app.getStudentId());
        refresh();
        ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, "Application " + action.toLowerCase() + ".");
    }

    // "Reject" button.
    @javafx.fxml.FXML
    public void rejectOA(ActionEvent actionEvent) {
        ClubMembershipApplication app = applicationsTV.getSelectionModel().getSelectedItem();
        if (app == null) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please select an application first.");
            return;
        }

        boolean approve = false;
        String action;
        if (approve) {
            app.approve();
            action = "Approved";
        } else {
            app.reject();
            action = "Rejected";
        }

        DataStore.get().save();
        DataStore.get().notify(app.getStudentId(),
                "Your membership request for " + app.getClubName() + " was " + app.getStatus() + ".");
        logHistory(action + " membership for student " + app.getStudentId());
        refresh();
        ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, "Application " + action.toLowerCase() + ".");
    }

    private void logHistory(String action) {
        User user = Session.getCurrentUser();
        if (user != null) {
            DataStore.get().logHistory(user.getUserId(), action);
        }
    }

    // --- navigation ----------------------------------------------------------

    @javafx.fxml.FXML
    public void backtoDashboardButtonOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2_Dashboard.fxml");
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
    public void clubMembershipOA(ActionEvent actionEvent) {
        // already on Review Membership
    }

    @javafx.fxml.FXML
    public void createNoticeButtonOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G2_PostClubNotice.fxml");
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
    public void communityProgramOA(ActionEvent actionEvent) throws IOException {
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
