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

public class u6_G3_AssignVolunteers
{@FXML private TableView<VolunteerHourRow> hoursTable;
    @FXML private TableColumn<VolunteerHourRow, Integer> approvalIdColumn;
    @FXML private TableColumn<VolunteerHourRow, Integer> volunteerIdColumn;
    @FXML private TableColumn<VolunteerHourRow, Double> totalHoursColumn;
    @FXML private TableColumn<VolunteerHourRow, String> hourStatusColumn;
    @FXML private TableColumn<VolunteerHourRow, String> certificateStatusColumn;
    @FXML private Label statusLabel;

    private static final double ELIGIBILITY_HOURS_THRESHOLD = 20.0;

    private final ObservableList<VolunteerHourRow> records = FXCollections.observableArrayList();

    private int coordinatorId;
    @FXML
    private Label statusLabel1;
    @FXML
    private TableColumn volunteerIdColumn1;
    @FXML
    private TableView recordsTable;
    @FXML
    private TableColumn totalHoursColumn1;
    @FXML
    private TableColumn approvalIdColumn1;
    @FXML
    private Button checkEligibilityBtn1;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button approveHoursBtn1;
    @FXML
    private Button approveHoursBtn;
    @FXML
    private TableColumn hourStatusColumn1;
    @FXML
    private Button refreshBtn1;
    @FXML
    private Button checkEligibilityBtn;
    @FXML
    private Button generateCertificateBtn1;
    @FXML
    private Button generateCertificateBtn;
    @FXML
    private TableColumn certificateStatusColumn1;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
        loadRecords();
    }

    @javafx.fxml.FXML
    public void initialize() {
        approvalIdColumn.setCellValueFactory(new PropertyValueFactory<>("approvalId"));
        volunteerIdColumn.setCellValueFactory(new PropertyValueFactory<>("volunteerId"));
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalVolunteerHours"));
        hourStatusColumn.setCellValueFactory(new PropertyValueFactory<>("hourStatus"));
        certificateStatusColumn.setCellValueFactory(new PropertyValueFactory<>("certificateStatus"));

        hoursTable.setItems(records);
        loadRecords();
    }

    private void loadRecords() {
        // TODO: replace with real service call, e.g.:
        // records.setAll(volunteerHourService.getAllRecords(coordinatorId));
        records.setAll(
                new VolunteerHourRow(1, 3001, 12.5, "Pending", "Not Eligible"),
                new VolunteerHourRow(2, 3002, 30.0, "Pending", "Not Eligible"),
                new VolunteerHourRow(3, 3003, 22.0, "Approved", "Not Generated")
        );
    }

    private VolunteerHourRow requireSelection() {
        VolunteerHourRow selected = hoursTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a volunteer record first.");
        }
        return selected;
    }
    /** Maps to VolunteerHourApproval.validateRecord() */
    private boolean validateRecord(VolunteerHourRow row) {
        return row.getTotalVolunteerHours() > 0;
    }

    @FXML
    private void handleApproveHours(ActionEvent event) {
        VolunteerHourRow selected = requireSelection();
        if (selected == null) return;

        if (!validateRecord(selected)) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Invalid hours record — cannot approve.");
            return;
        }

        // TODO: replace with real service call, e.g.:
        // volunteerHourService.approveHours(selected.getApprovalId(), coordinatorId);

        selected.setHourStatus("Approved");
        refreshEligibility(selected);
        hoursTable.refresh();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Hours approved for volunteer #" + selected.getVolunteerId() + ".");
    }

    /** Maps to VolunteerHourApproval.checkCertificateEligibility() */
    @FXML
    private void handleCheckEligibility(ActionEvent event) {
        VolunteerHourRow selected = requireSelection();
        if (selected == null) return;

        refreshEligibility(selected);
        hoursTable.refresh();

        boolean eligible = isEligible(selected);
        statusLabel.setStyle(eligible ? "-fx-text-fill:green;" : "-fx-text-fill:orange;");
        statusLabel.setText(eligible
                ? "Volunteer #" + selected.getVolunteerId() + " is eligible for a certificate."
                : "Not yet eligible (needs " + ELIGIBILITY_HOURS_THRESHOLD + "+ approved hours).");
    }

    private void refreshEligibility(VolunteerHourRow row) {
        if (!"Certificate Generated".equals(row.getCertificateStatus())) {
            row.setCertificateStatus(isEligible(row) ? "Eligible" : "Not Eligible");
        }
    }

    private boolean isEligible(VolunteerHourRow row) {
        // TODO: replace with real service call, e.g.:
        // return volunteerHourService.checkCertificateEligibility(row.getApprovalId());
        return "Approved".equals(row.getHourStatus())
                && row.getTotalVolunteerHours() >= ELIGIBILITY_HOURS_THRESHOLD;
    }

    /** Maps to Certificate.generate() */
    @FXML
    private void handleGenerateCertificate(ActionEvent event) {
        VolunteerHourRow selected = requireSelection();
        if (selected == null) return;

        if (!isEligible(selected)) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Approve the hours and confirm eligibility before generating a certificate.");
            return;
        }

        if ("Certificate Generated".equals(selected.getCertificateStatus())) {
            statusLabel.setStyle("-fx-text-fill:orange;");
            statusLabel.setText("A certificate has already been generated for this volunteer.");
            return;
        }

        // TODO: replace with real service call, e.g.:
        // int certificateId = certificateService.generate(selected.getApprovalId(),
        //         selected.getVolunteerId(), selected.getTotalVolunteerHours());

        selected.setCertificateStatus("Certificate Generated");
        hoursTable.refresh();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Certificate generated for volunteer #" + selected.getVolunteerId() + ".");
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadRecords();
        statusLabel.setText("List refreshed.");
    }

    /** Simple row model for the TableView. Swap this out for your real entity/DTO. */
    public static class VolunteerHourRow {
        private final SimpleIntegerProperty approvalId;
        private final SimpleIntegerProperty volunteerId;
        private final SimpleDoubleProperty totalVolunteerHours;
        private final SimpleStringProperty hourStatus;
        private final SimpleStringProperty certificateStatus;

        public VolunteerHourRow(int approvalId, int volunteerId, double totalVolunteerHours,
                                String hourStatus, String certificateStatus) {
            this.approvalId = new SimpleIntegerProperty(approvalId);
            this.volunteerId = new SimpleIntegerProperty(volunteerId);
            this.totalVolunteerHours = new SimpleDoubleProperty(totalVolunteerHours);
            this.hourStatus = new SimpleStringProperty(hourStatus);
            this.certificateStatus = new SimpleStringProperty(certificateStatus);
        }

        public int getApprovalId() { return approvalId.get(); }
        public int getVolunteerId() { return volunteerId.get(); }
        public double getTotalVolunteerHours() { return totalVolunteerHours.get(); }
        public String getHourStatus() { return hourStatus.get(); }
        public void setHourStatus(String status) { this.hourStatus.set(status); }
        public String getCertificateStatus() { return certificateStatus.get(); }
        public void setCertificateStatus(String status) { this.certificateStatus.set(status); }
    }
}



