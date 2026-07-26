package c213.dosaoopproject.esha.controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class u6_G2_DashboardView
{@FXML private ComboBox<String> eventCombo;
    @FXML private ComboBox<String> volunteerCombo;
    @FXML private TextField shiftField;
    @FXML private TextField teamField;
    @FXML private Label statusLabel;

    private int coordinatorId;
    @FXML
    private Button confirmAssignmentBtn;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    @javafx.fxml.FXML
    public void initialize() { eventCombo.setItems(FXCollections.observableArrayList("Blood Donation Drive", "Beach Cleanup"));
        volunteerCombo.setItems(FXCollections.observableArrayList("Ayesha Rahman (V-1042)", "Tanvir Hasan (V-1077)"));
    }

    private boolean validateAssignmentRules() {
        if (eventCombo.getValue() == null || volunteerCombo.getValue() == null
                || shiftField.getText().isBlank()) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select an event, a volunteer, and a shift.");
            return false;
        }
        return false;
    }
    @FXML
    private void handleConfirmAssignment(ActionEvent event) {
        if (!validateAssignmentRules()) return;
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Assignment confirmed and volunteer notified.");
    }}