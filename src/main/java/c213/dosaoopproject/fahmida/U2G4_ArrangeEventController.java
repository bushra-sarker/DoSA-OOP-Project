package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.ArrangeClubEvent;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.Notifications;
import c213.dosaoopproject.fahmida.utility.SceneManager;
import c213.dosaoopproject.fahmida.utility.ToShowAlert;
import c213.dosaoopproject.fahmida.utility.Ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.LocalDate;

public class U2G4_ArrangeEventController {

    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TextField eventNameTF;
    @javafx.fxml.FXML
    private TextArea descTA;
    @javafx.fxml.FXML
    private DatePicker eventDP;
    @javafx.fxml.FXML
    private TextField venueTF;
    @javafx.fxml.FXML
    private TableView<ArrangeClubEvent> eventsTV;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, String> evNameTC;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, Object> evDateTC;
    @javafx.fxml.FXML
    private TableColumn<ArrangeClubEvent, String> evVenueTC;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        evNameTC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        evDateTC.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        evVenueTC.setCellValueFactory(new PropertyValueFactory<>("venue"));
        eventDP.setValue(LocalDate.now().plusDays(7));
        refresh();
    }

    private void refresh() {
        eventsTV.setItems(FXCollections.observableArrayList(DataStore.get().getEvents()));
    }

    @javafx.fxml.FXML
    public void createEventOA(ActionEvent actionEvent) {
        String name = eventNameTF.getText().trim();
        if (name.isEmpty() || eventDP.getValue() == null) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please enter at least an event name and date.");
            return;
        }

        DataStore store = DataStore.get();
        int id = store.getEvents().size() + 1;
        String venue = venueTF.getText().isBlank() ? "TBA" : venueTF.getText().trim();
        String desc = descTA.getText().isBlank() ? "Created by advisor" : descTA.getText().trim();
        store.getEvents().add(new ArrangeClubEvent(id, name, desc,
                eventDP.getValue(), venue, "Upcoming"));
        logHistory("Created event: " + name);
        store.save();
        clearForm();
        refresh();
        ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, "Event \"" + name + "\" created (Upcoming).");
    }

    private void clearForm() {
        eventNameTF.clear();
        descTA.clear();
        venueTF.clear();
        eventDP.setValue(LocalDate.now().plusDays(7));
    }

    private void logHistory(String action) {
        User user = Session.getCurrentUser();
        if (user != null) {
            DataStore.get().logHistory(user.getUserId(), action);
        }
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

    @Deprecated
    public void communityProgramOA(ActionEvent actionEvent) {
        String programs = DataStore.get().getCommunityPrograms().stream()
                .map(p -> "• " + p.getDetails())
                .reduce((a, b) -> a + "\n" + b).orElse("");
        ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, programs.isEmpty() ? "No community programs." : programs);
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
        // already on Arrange Club Events
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) throws IOException {
        SceneManager.navigate(actionEvent, "/c213/dosaoopproject/fahmida/U2G5_AssignVolunteer.fxml");
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
