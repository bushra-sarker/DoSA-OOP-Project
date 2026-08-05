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

public class u6_G2_ApproveVolunteerHour
{
    @FXML private TableView<HourRow> hoursTable;
    @FXML private TableColumn<HourRow, Integer> approvalIdColumn;
    @FXML private TableColumn<HourRow, Integer> volunteerIdColumn;
    @FXML private TableColumn<HourRow, Double> totalHoursColumn;
    @FXML private TableColumn<HourRow, String> statusColumn;
    @FXML private Label statusLabel;

    private final ObservableList<HourRow> records = FXCollections.observableArrayList();

    private int coordinatorId;
    @FXML
    private Button approveHoursBtn;
    @FXML
    private Button checkEligibilityBtn;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
        loadRecords();
    }

    @FXML
    public void initialize() {
        approvalIdColumn.setCellValueFactory(new PropertyValueFactory<>("approvalId"));
        volunteerIdColumn.setCellValueFactory(new PropertyValueFactory<>("volunteerId"));
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalVolunteerHours"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
        hoursTable.setItems(records);
        loadRecords();
    }

    private void loadRecords() {
        // TODO: replace with real service call, e.g.:
        // records.setAll(volunteerHourService.getPendingApprovals(coordinatorId));
        records.setAll(
                new HourRow(1, 3001, 12.5, "Pending"),
                new HourRow(2, 3002, 30.0, "Pending")
        );
    }

    @FXML
    private void handleApproveHours(ActionEvent event) {
        HourRow selected = hoursTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a record first.");
            return;
        }

        // TODO: volunteerHourService.approveHours(selected.getApprovalId());

        selected.setCurrentStatus("Approved");
        hoursTable.refresh();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Hours approved for volunteer #" + selected.getVolunteerId() + ".");
    }

    @FXML
    private void handleCheckEligibility(ActionEvent event) {
        HourRow selected = hoursTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a record first.");
            return;
        }

        // TODO: boolean eligible = volunteerHourService.checkCertificateEligibility(selected.getApprovalId());
        boolean eligible = selected.getTotalVolunteerHours() >= 20.0; // placeholder rule

        statusLabel.setStyle(eligible ? "-fx-text-fill:green;" : "-fx-text-fill:orange;");
        statusLabel.setText(eligible
                ? "Eligible for a certificate."
                : "Not yet eligible — needs more hours.");
    }

    /** Simple row model for the TableView. Swap this out for your real entity/DTO. */
    public static class HourRow {
        private final SimpleIntegerProperty approvalId;
        private final SimpleIntegerProperty volunteerId;
        private final SimpleDoubleProperty totalVolunteerHours;
        private final SimpleStringProperty currentStatus;

        public HourRow(int approvalId, int volunteerId, double totalVolunteerHours, String currentStatus) {
            this.approvalId = new SimpleIntegerProperty(approvalId);
            this.volunteerId = new SimpleIntegerProperty(volunteerId);
            this.totalVolunteerHours = new SimpleDoubleProperty(totalVolunteerHours);
            this.currentStatus = new SimpleStringProperty(currentStatus);
        }

        public int getApprovalId() { return approvalId.get(); }
        public int getVolunteerId() { return volunteerId.get(); }
        public double getTotalVolunteerHours() { return totalVolunteerHours.get(); }
        public String getCurrentStatus() { return currentStatus.get(); }
        public void setCurrentStatus(String status) { this.currentStatus.set(status); }
    }
}