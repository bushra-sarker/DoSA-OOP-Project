package c213.dosaoopproject.esha.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class u6_G5_DashboardView
{@FXML private ComboBox<String> resourceCategoryCombo;
    @FXML private TextField materialNameField;
    @FXML private Spinner<Integer> quantitySpinner;
    @FXML private DatePicker requiredDatePicker;
    @FXML private TextArea purposeArea;
    @FXML private Label statusLabel;

    private int executiveId;
    @FXML
    private Button submitResourceReqBtn;

    public void setExecutiveId(int executiveId) {
        this.executiveId = executiveId;
    }

    @FXML
    public void initialize() {resourceCategoryCombo.getItems().setAll("Equipment", "Stationery", "Furniture", "AV/Tech", "Other");
        quantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
    }

    private boolean validateFields() {
        if (resourceCategoryCombo.getValue() == null || materialNameField.getText().isBlank()
                || requiredDatePicker.getValue() == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Category, material name, and required date are required.");
            return false;
        }
        return true;
    }

    @FXML
    private void handleSubmitResourceRequest(ActionEvent event) {
        if (!validateFields()) return;
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Resource request submitted.");
    }}