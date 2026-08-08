package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.model.Complaint;
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

public class U1G6_SubmitComplaintsController {

    @javafx.fxml.FXML
    private Label welcomeUserLabel;
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private TextField searchOFCRTF;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;
    @javafx.fxml.FXML
    private TextField describeTF;
    @javafx.fxml.FXML
    private ComboBox<String> categoryCB;
    @javafx.fxml.FXML
    private TextField titleTF;

    @javafx.fxml.FXML
    public void initialize() {
        Ui.greet(nameLabel11, userIdLabel11);
        User user = Session.getCurrentUser();
        if (welcomeUserLabel != null && user != null) {
            welcomeUserLabel.setText("Welcome, " + user.getFullName() + "!");
        }
        categoryCB.setItems(FXCollections.observableArrayList(
                "Academic", "Facilities", "Harassment", "Other"));
        if (searchOFCRTF != null) {
            searchOFCRTF.setOnAction(e ->
                    ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION,
                            c213.dosaoopproject.fahmida.utility.Search.query(searchOFCRTF.getText())));
        }
    }

    @javafx.fxml.FXML
    public void submitsComplaintOA(ActionEvent actionEvent) throws IOException {
        String category = categoryCB.getValue();
        String title = titleTF.getText();
        String description = describeTF.getText();
        if (category == null || description == null || description.isBlank()) {
            ToShowAlert.showWaitAlert(Alert.AlertType.WARNING, "Please choose a category and describe your complaint.");
            return;
        }
        User user = Session.getCurrentUser();
        DataStore store = DataStore.get();
        String details = (title == null || title.isBlank() ? "" : title + ": ") + description;
        store.getComplaints().add(new Complaint(
                store.getComplaints().size() + 1, user.getUserId(),
                category, details, LocalDate.now()));
        store.logHistory(user.getUserId(), "Submitted a " + category + " complaint");
        ToShowAlert.showWaitAlert(Alert.AlertType.INFORMATION, "Complaint submitted. Thank you.");
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
    public void submitComplaintsOA(ActionEvent actionEvent) {
        // already on this screen
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
