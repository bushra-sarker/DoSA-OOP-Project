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

public class u6_G3_DashboardView
{
    @FXML
    private TableColumn volunteerIdColumn;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button approveHoursBtn;
    @FXML
    private TableView recordsTable;
    @FXML
    private TableColumn approvalIdColumn;
    @FXML
    private TableColumn certificateStatusColumn;
    @FXML
    private TableColumn totalHoursColumn;
    @FXML
    private TableColumn hourStatusColumn;
    @FXML
    private Button checkEligibilityBtn;
    @FXML
    private Button generateCertificateBtn;
    @FXML
    private Label statusLabel;
    private int coordinatorId;

    @javafx.fxml.FXML
    public void initialize() {
        private static final double CERTIFICATE_HOUR_THRESHOLD = 20.0; // adjust to your program's rule

        @FXML private TableView<VolunteerRow> recordsTable;
        @FXML private TableColumn<VolunteerRow, Integer> approvalIdColumn;
        @FXML private TableColumn<VolunteerRow, Integer> volunteerIdColumn;
        @FXML private TableColumn<VolunteerRow, Double> totalHoursColumn;
        @FXML private TableColumn<VolunteerRow, String> hourStatusColumn;
        @FXML private TableColumn<VolunteerRow, String> certificateStatusColumn;
        @FXML private Label statusLabel;

        private final ObservableList<VolunteerRow> records = FXCollections.observableArrayList();

        private int coordinatorId;

        public void setCoordinatorId(int coordinatorId) {
            this.coordinatorId = coordinatorId;
            loadRecords();
        }

        @FXML
        public void initialize() {
            approvalIdColumn.setCellValueFactory(new PropertyValueFactory<>("approvalId"));
            volunteerIdColumn.setCellValueFactory(new PropertyValueFactory<>("volunteerId"));
            totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalVolunteerHours"));
            hourStatusColumn.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
            certificateStatusColumn.setCellValueFactory(new PropertyValueFactory<>("certificateStatus"));

            recordsTable.setItems(records);
            loadRecords();
        }

        private void loadRecords() {
            records.setAll(
                    new VolunteerRow(1, 3001, 12.5, "Pending", "Not Eligible"),
                    new VolunteerRow(2, 3002, 30.0, "Pending", "Not Eligible"),
                    new VolunteerRow(3, 3003, 22.0, "Approved", "Not Generated")
            );
        }

        private VolunteerRow requireSelection() {
            VolunteerRow selected = recordsTable.getSelectionModel().getSelectedItem();
            if (selected == null) {
                statusLabel.setStyle("-fx-text-fill:red;");
                statusLabel.setText("Select a record first.");
            }
            return selected;
        }
        @FXML
        private void handleApproveHours(ActionEvent event) {
            VolunteerRow selected = requireSelection();
            if (selected == null) return;

            if (!validateRecord(selected)) return;
            selected.setCurrentStatus("Approved");
            updateCertificateEligibility(selected);
            recordsTable.refresh();

            statusLabel.setStyle("-fx-text-fill:green;");
            statusLabel.setText("Hours approved for volunteer #" + selected.getVolunteerId() + ".");
        }

        @FXML
        private void handleCheckEligibility(ActionEvent event) {
            VolunteerRow selected = requireSelection();
            if (selected == null) return;

            updateCertificateEligibility(selected);
            recordsTable.refresh();

            boolean eligible = "Eligible".equals(selected.getCertificateStatus())
                    || "Not Generated".equals(selected.getCertificateStatus());
            statusLabel.setStyle(eligible ? "-fx-text-fill:green;" : "-fx-text-fill:orange;");
            statusLabel.setText("Volunteer #" + selected.getVolunteerId() + " is "
                    + (eligible ? "eligible" : "not yet eligible") + " for a certificate.");
        }

        private boolean validateRecord(VolunteerRow row) {
            if (row.getTotalVolunteerHours() < 0) {
                statusLabel.setStyle("-fx-text-fill:red;");
                statusLabel.setText("Invalid hour record — hours cannot be negative.");
                return false;
            }
            return true;
        }

        private void updateCertificateEligibility(VolunteerRow row) {
            boolean approved = "Approved".equals(row.getCurrentStatus());
            boolean meetsThreshold = row.getTotalVolunteerHours() >= CERTIFICATE_HOUR_THRESHOLD;

            if (!approved) {
                row.setCertificateStatus("Not Eligible");
            } else if (!meetsThreshold) {
                row.setCertificateStatus("Not Eligible (needs " + CERTIFICATE_HOUR_THRESHOLD + "+ hrs)");
            } else if (!"Generated".equals(row.getCertificateStatus())) {
                row.setCertificateStatus("Not Generated");
            }
            // if already "Generated", leave it as is
        }

        // ===================== CERTIFICATE GENERATION =====================
        @FXML
        private void handleGenerateCertificate(ActionEvent event) {
            VolunteerRow selected = requireSelection();
            if (selected == null) return;

            if (!"Approved".equals(selected.getCurrentStatus())) {
                statusLabel.setStyle("-fx-text-fill:red;");
                statusLabel.setText("Approve the hours before generating a certificate.");
                return;
            }
            if (selected.getTotalVolunteerHours() < CERTIFICATE_HOUR_THRESHOLD) {
                statusLabel.setStyle("-fx-text-fill:red;");
                statusLabel.setText("Volunteer does not meet the minimum hours for a certificate.");
                return;
            }
            if ("Generated".equals(selected.getCertificateStatus())) {
                statusLabel.setStyle("-fx-text-fill:orange;");
                statusLabel.setText("Certificate already generated for this volunteer.");
                return;
            }
            selected.setCertificateStatus("Generated");
            recordsTable.refresh();

            statusLabel.setStyle("-fx-text-fill:green;");
            statusLabel.setText("Certificate generated for volunteer #" + selected.getVolunteerId() + ".");
        }

        @FXML
        private void handleRefresh(ActionEvent event) {
            loadRecords();
            statusLabel.setText("List refreshed.");
        }
        public static class VolunteerRow {
            private final SimpleIntegerProperty approvalId;
            private final SimpleIntegerProperty volunteerId;
            private final SimpleDoubleProperty totalVolunteerHours;
            private final SimpleStringProperty currentStatus;
            private final SimpleStringProperty certificateStatus;

            public VolunteerRow(int approvalId, int volunteerId, double totalVolunteerHours,
                                String currentStatus, String certificateStatus) {
                this.approvalId = new SimpleIntegerProperty(approvalId);
                this.volunteerId = new SimpleIntegerProperty(volunteerId);
                this.totalVolunteerHours = new SimpleDoubleProperty(totalVolunteerHours);
                this.currentStatus = new SimpleStringProperty(currentStatus);
                this.certificateStatus = new SimpleStringProperty(certificateStatus);
            }

            public int getApprovalId() { return approvalId.get(); }
            public int getVolunteerId() { return volunteerId.get(); }
            public double getTotalVolunteerHours() { return totalVolunteerHours.get(); }
            public String getCurrentStatus() { return currentStatus.get(); }
            public void setCurrentStatus(String status) { this.currentStatus.set(status); }
            public String getCertificateStatus() { return certificateStatus.get(); }
            public void setCertificateStatus(String status) { this.certificateStatus.set(status); }
        }}

    private boolean validateRecord(VolunteerRow selected) {
    }

    private void updateCertificateEligibility(VolunteerRow selected) {
    }

    private void loadRecords() {
    }

    @FXML
    public void handleGenerateCertificate(ActionEvent actionEvent) {
    }

    @FXML
    public void handleCheckEligibility(ActionEvent actionEvent) {
    }

    @FXML
    public void handleApproveHours(ActionEvent actionEvent) {
    }

    @FXML
    public void handleRefresh(ActionEvent actionEvent) {
    }