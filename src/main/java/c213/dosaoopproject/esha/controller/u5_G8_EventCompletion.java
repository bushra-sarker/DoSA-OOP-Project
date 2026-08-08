package c213.dosaoopproject.esha.controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class u5_G8_EventCompletion
{
    @FXML private ComboBox<String> eventCombo;
    @FXML private Spinner<Integer> attendanceSpinner;
    @FXML private TextArea outcomeSummaryArea;
    @FXML private Label statusLabel;

    private int executiveId;
    @FXML
    private Button submitReportBtn;

    public void setExecutiveId(int executiveId) {
        this.executiveId = executiveId;
    }
    @FXML
    public void initialize() {
        attendanceSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000, 0));
        // TODO: replace with the executive's own completed events, e.g.:
        // eventCombo.setItems(FXCollections.observableArrayList(eventService.getCompletedEventNames(executiveId)));
        eventCombo.setItems(FXCollections.observableArrayList("Freshers Welcome Night", "Charity Bake Sale"));
    }

    private boolean validate() {
        if (eventCombo.getValue() == null || outcomeSummaryArea.getText().isBlank()) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select an event and enter an outcome summary.");
            return false;
        }
        return true;
    }

    @FXML
    private void handleSubmitReport(ActionEvent event) {
        if (!validate()) return;

        // TODO: replace with real service call, e.g.:
        // EventCompletionReport report = new EventCompletionReport(eventCombo.getValue(),
        //         attendanceSpinner.getValue(), outcomeSummaryArea.getText(), "Submitted");
        // eventReportService.submitReport(report);

        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Completion report submitted.");
    }}