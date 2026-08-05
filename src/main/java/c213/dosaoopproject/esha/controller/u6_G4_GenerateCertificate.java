package c213.dosaoopproject.esha.controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
public class u6_G4_GenerateCertificate
{
    @FXML private TableView<EligibleRow> eligibleTable;
    @FXML private TableColumn<EligibleRow, Integer> approvalIdColumn;
    @FXML private TableColumn<EligibleRow, Integer> volunteerIdColumn;
    @FXML private TableColumn<EligibleRow, Double> totalHoursColumn;
    @FXML private TableColumn<EligibleRow, String> certStatusColumn;
    @FXML private Label statusLabel;

    private final ObservableList<EligibleRow> eligibleVolunteers = FXCollections.observableArrayList();

    private int coordinatorId;
    @FXML
    private Button generateBtn;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
        loadEligible();
    }
    @FXML
    public void initialize() {
        approvalIdColumn.setCellValueFactory(new PropertyValueFactory<>("approvalId"));
        volunteerIdColumn.setCellValueFactory(new PropertyValueFactory<>("volunteerId"));
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
        certStatusColumn.setCellValueFactory(new PropertyValueFactory<>("certificateStatus"));
        eligibleTable.setItems(eligibleVolunteers);
        loadEligible();
    }

    private void loadEligible() {
        // TODO: replace with real service call, e.g.:
        // eligibleVolunteers.setAll(certificateService.getEligibleVolunteers(coordinatorId));
        eligibleVolunteers.setAll(
                new EligibleRow(1, 3001, 25.0, "Not Generated"),
                new EligibleRow(2, 3002, 40.0, "Not Generated")
        );
    }

    @FXML
    private void handleGenerate(ActionEvent event) {
        EligibleRow selected = eligibleTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a volunteer first.");
            return;
        }

        // TODO: replace with real service call, e.g.:
        // int certificateId = certificateService.generate(selected.getApprovalId(),
        //         selected.getVolunteerId(), selected.getTotalHours());

        selected.setCertificateStatus("Generated");
        eligibleTable.refresh();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Certificate generated for volunteer #" + selected.getVolunteerId() + ".");
    }

    /** Simple row model for the TableView. Swap this out for your real entity/DTO. */
    public static class EligibleRow {
        private final SimpleIntegerProperty approvalId;
        private final SimpleIntegerProperty volunteerId;
        private final SimpleDoubleProperty totalHours;
        private final SimpleStringProperty certificateStatus;

        public EligibleRow(int approvalId, int volunteerId, double totalHours, String certificateStatus) {
            this.approvalId = new SimpleIntegerProperty(approvalId);
            this.volunteerId = new SimpleIntegerProperty(volunteerId);
            this.totalHours = new SimpleDoubleProperty(totalHours);
            this.certificateStatus = new SimpleStringProperty(certificateStatus);
        }

        public int getApprovalId() { return approvalId.get(); }
        public int getVolunteerId() { return volunteerId.get(); }
        public double getTotalHours() { return totalHours.get(); }
        public String getCertificateStatus() { return certificateStatus.get(); }
        public void setCertificateStatus(String status) { this.certificateStatus.set(status); }

    }
}