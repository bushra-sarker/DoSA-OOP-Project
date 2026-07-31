package c213.dosaoopproject.esha.controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class u6_G4_DashboardView
{@FXML private ComboBox<String> semesterCombo;
    @FXML private TextField yearField;
    @FXML private TextField totalEventsField;
    @FXML private TextField totalVolunteersField;
    @FXML private TextField totalImpactHoursField;
    @FXML private TextArea executiveSummaryArea;
    @FXML private Label statusLabel;

    private int coordinatorId;
    private boolean dataCompiled = false;
    @FXML
    private Button saveAndPublishBtn;
    @FXML
    private Button compileDataBtn;
    @FXML
    private Button generatePdfBtn;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    @FXML
    public void initialize() {semesterCombo.setItems(FXCollections.observableArrayList("Spring", "Summer", "Fall"));
    }

    @FXML
    private void handleCompileData(ActionEvent event) {
        if (semesterCombo.getValue() == null || yearField.getText().isBlank()) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a semester and enter a year.");
            return;
        }
        totalEventsField.setText("18");
        totalVolunteersField.setText("152");
        totalImpactHoursField.setText("2140.5");
        dataCompiled = true;

        statusLabel.setStyle("-fx-text-fill:#432D57;");
        statusLabel.setText("Data compiled. Add an executive summary before publishing.");
    }
    @FXML
    private void handleSaveAndPublish(ActionEvent event) {
        if (!dataCompiled) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Compile the data first.");
            return;
        }
        if (executiveSummaryArea.getText().isBlank()) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Executive summary is required before publishing.");
            return;
        }
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Impact report saved and published.");
    }

    @FXML
    private void handleGeneratePdf(ActionEvent event) {
        if (!dataCompiled) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Compile the data first.");
            return;
        }
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("PDF generated.");
    }}