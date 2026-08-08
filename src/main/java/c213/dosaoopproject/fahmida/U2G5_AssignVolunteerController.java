package c213.dosaoopproject.fahmida;

import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.EventRegistration;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
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
 * Club Advisor Goal: Assign Student Volunteers to Events. Lists every student
 * sign-up recorded by the Community Service registration form and lets the
 * advisor pick one to finalize on the Assign Volunteer form.
 */
public class U2G5_AssignVolunteerController {

    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TableView<EventRegistration> assignmentsTV;
    @javafx.fxml.FXML
    private TableColumn<EventRegistration, String> eventNameTC;
    @javafx.fxml.FXML
    private TableColumn<EventRegistration, Integer> volunteerIDTC;
    @javafx.fxml.FXML
    private TableColumn<EventRegistration, String> deptTC;
    @javafx.fxml.FXML
    private TableColumn<EventRegistration, String> statusTC;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        eventNameTC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        volunteerIDTC.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        deptTC.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
        assignmentsTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        refresh();
    }

    private void refresh() {
        assignmentsTV.setItems(FXCollections.observableArrayList(DataStore.get().getEventRegistrations()));
    }

    @javafx.fxml.FXML
    public void assignOA(ActionEvent actionEvent) throws IOException {
        EventRegistration selected = assignmentsTV.getSelectionModel().getSelectedItem();
        if (selected == null) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please select a registrant first.");
            return;
        }

        Session.setSelectedRegistration(selected);
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G5_AssignVolunteerForm.fxml");
    }

    // --- navigation ----------------------------------------------------------

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
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G4_ArrangeEvent.fxml");
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
        // already on Assign Volunteer
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G6_ViewParticipants.fxml");
    }

    @javafx.fxml.FXML
    public void submitCompletionReportOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G7_CompletionReport.fxml");
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G8_ActivityHistory.fxml");
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
