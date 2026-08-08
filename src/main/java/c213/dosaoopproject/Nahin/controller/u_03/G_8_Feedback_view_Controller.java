package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.model.u_03.VolFeedback;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.FileManager.writeFile;
import static c213.dosaoopproject.Nahin.utility.IdGenerator.generateRegistrationId;
import static c213.dosaoopproject.Nahin.utility.VIA.showAlert;


public class G_8_Feedback_view_Controller {
    @javafx.fxml.FXML
    private VBox record_Pane;
    @javafx.fxml.FXML
    private VBox form_Pane;
    @javafx.fxml.FXML
    private Label activityNameLBL;
    @javafx.fxml.FXML
    private Label dateLBL;
    @javafx.fxml.FXML
    private Label feedbackIdLBL;
    @javafx.fxml.FXML
    private TextArea commentTXTAREA;
    @javafx.fxml.FXML
    private ComboBox<String> ratingCOMBO;
    @javafx.fxml.FXML
    private ComboBox<String> activityNameCOMBO;
    @javafx.fxml.FXML
    private TableView<VolFeedback> historyTableVIEW;
    @javafx.fxml.FXML
    private TableColumn<VolFeedback, Integer> feedBackIdCOL;
    @javafx.fxml.FXML
    private TableColumn<VolFeedback, String> activityNameCOL;
    @javafx.fxml.FXML
    private TableColumn<VolFeedback, LocalDate> dateCOL;

    private int currentUserId;
    @javafx.fxml.FXML
    private SideMenuBar_Controller nullController;


    @javafx.fxml.FXML
    public void initialize() {
        feedBackIdCOL.setCellValueFactory(new PropertyValueFactory<>("feedbackId"));
        activityNameCOL.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        dateCOL.setCellValueFactory(new PropertyValueFactory<>("date"));

        ratingCOMBO.getItems().addAll("Excellent","Good","Average","Poor");
        activityNameCOMBO.getItems().addAll("Team Coordination", "Event Management", "Campaign Activity", "Volunteer Activity");

        record_Pane.setVisible(true);record_Pane.setManaged(true);
        form_Pane.setVisible(false);form_Pane.setManaged(false);
        loadFeedbackHistory();
    }


    public void setVolunteerId(int userId) {
        this.currentUserId = userId;
    }

    private void loadFeedbackHistory() {
        historyTableVIEW.getItems().clear();
        ArrayList<VolFeedback> feedbackList = readFile("VolunteerFeedbacks.bin");
        historyTableVIEW.getItems().clear();
        if (feedbackList != null) {
                    historyTableVIEW.getItems().addAll(feedbackList);
                }
            }

    @javafx.fxml.FXML
    public void feedBackOA(ActionEvent actionEvent) {
        String selectedActivity = activityNameCOMBO.getValue();
        if (selectedActivity == null) {
            showAlert(Alert.AlertType.WARNING, "Please select an activity");
            return;
        }

        int feedbackId = generateRegistrationId();
        feedbackIdLBL.setText(String.valueOf(feedbackId));
        activityNameLBL.setText(selectedActivity);
        dateLBL.setText(String.valueOf(LocalDate.now()));

        ratingCOMBO.setValue(null);
        commentTXTAREA.clear();

        record_Pane.setVisible(false);record_Pane.setManaged(false);
        form_Pane.setVisible(true);form_Pane.setManaged(true);
    }

    @javafx.fxml.FXML
    public void submitfeedbackOA(ActionEvent actionEvent) {
        if (ratingCOMBO.getValue() == null || commentTXTAREA.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Complete all required fields");
            return;
        }

        VolFeedback feedback = new VolFeedback(
                Integer.parseInt(feedbackIdLBL.getText()),
                activityNameLBL.getText(),
                ratingCOMBO.getValue(),
                commentTXTAREA.getText(),
                LocalDate.now()
        );

        if (!feedback.validateFeedback()) {
            showAlert(Alert.AlertType.WARNING, "Complete all required fields");
            return;
        }

        ArrayList<VolFeedback> feedbackList = readFile("VolunteerFeedbacks.bin");
        if (feedbackList == null) {
            feedbackList = new ArrayList<>();
        }
        feedbackList.add(feedback);

        writeFile("VolunteerFeedbacks.bin", feedbackList);
        System.out.println(feedback);
        showAlert(Alert.AlertType.INFORMATION, "Submitted Successfully");

        loadFeedbackHistory();

        record_Pane.setVisible(true);record_Pane.setManaged(true);
        form_Pane.setVisible(false);form_Pane.setManaged(false);

        ratingCOMBO.setValue(null);commentTXTAREA.clear();activityNameCOMBO.setValue(null);
    }

    @javafx.fxml.FXML
    public void cancelFeedbackOA(ActionEvent actionEvent) {
        ratingCOMBO.setValue(null);commentTXTAREA.clear();activityNameCOMBO.setValue(null);
        record_Pane.setVisible(true);record_Pane.setManaged(true);
        form_Pane.setVisible(false);form_Pane.setManaged(false);
    }

    @javafx.fxml.FXML
    public void historyOA(ActionEvent actionEvent) {
        loadFeedbackHistory();
        record_Pane.setVisible(true);record_Pane.setManaged(true);
        form_Pane.setVisible(false);form_Pane.setManaged(false);
    }
}