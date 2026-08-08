package c213.dosaoopproject.esha.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class u6_G1_PostServiceOpportunity
{
    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private DatePicker datePicker;
    @FXML private TextField locationField;
    @FXML private Spinner<Integer> slotsSpinner;
    @FXML private Label statusLabel;

    private int coordinatorId; // set from session context when this view is loaded
    @FXML
    private Button previewBtn;
    @FXML
    private Button publishBtn;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    @javafx.fxml.FXML
    public void initialize() {
        slotsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500, 10));
    }

    private boolean validate() {
        if (titleField.getText().isBlank() || locationField.getText().isBlank()
                || datePicker.getValue() == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Title, location, and date are required.");
            return false;
        }
        return true;
    }

    @FXML
    private void handlePreview(ActionEvent event) {
        if (!validate()) return;
        statusLabel.setStyle("-fx-text-fill:#432D57;");
        statusLabel.setText("Preview: \"" + titleField.getText() + "\" — "
                + slotsSpinner.getValue() + " slots at " + locationField.getText()
                + " on " + datePicker.getValue());
    }

    @FXML
    private void handlePublish(ActionEvent event) {
        if (!validate()) return;
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Opportunity published — visible to volunteers now.");
    }}