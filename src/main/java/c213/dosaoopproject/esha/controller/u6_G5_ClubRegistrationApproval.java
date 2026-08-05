package c213.dosaoopproject.esha.controller;
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
public class u6_G5_ClubRegistrationApproval
{
    @FXML private TableView<RegistrationRow> requestsTable;
    @FXML private TableColumn<RegistrationRow, Integer> registrationIdColumn;
    @FXML private TableColumn<RegistrationRow, String> clubNameColumn;
    @FXML private TableColumn<RegistrationRow, String> categoryColumn;
    @FXML private TableColumn<RegistrationRow, String> submissionDateColumn;
    @FXML private TableColumn<RegistrationRow, String> statusColumn;
    @FXML private Label statusLabel;

    private final ObservableList<RegistrationRow> requests = FXCollections.observableArrayList();

    private int coordinatorId;
    @FXML
    private Button approveBtn;
    @FXML
    private Button rejectBtn;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
        loadRequests();
    }

    @FXML
    public void initialize() {
        registrationIdColumn.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        submissionDateColumn.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        requestsTable.setItems(requests);
        loadRequests();
    }

    private void loadRequests() {
        // TODO: replace with real service call, e.g.:
        // requests.setAll(clubRegistrationService.getPendingRequests());
        requests.setAll(
                new RegistrationRow(1, "AI & Robotics Society", "Technical", "2026-07-10", "Pending"),
                new RegistrationRow(2, "Photography Club", "Arts", "2026-07-15", "Pending")
        );
    }

    @FXML
    private void handleApprove(ActionEvent event) {
        RegistrationRow selected = requestsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a club registration request first.");
            return;
        }

        // TODO: clubRegistrationService.approve(selected.getRegistrationId());
        // This is also where you'd typically create the matching ClubActive record.

        selected.setStatus("Approved");
        requestsTable.refresh();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("\"" + selected.getClubName() + "\" approved.");
    }

    @FXML
    private void handleReject(ActionEvent event) {
        RegistrationRow selected = requestsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a club registration request first.");
            return;
        }

        // TODO: clubRegistrationService.reject(selected.getRegistrationId());

        selected.setStatus("Rejected");
        requestsTable.refresh();
        statusLabel.setStyle("-fx-text-fill:orange;");
        statusLabel.setText("\"" + selected.getClubName() + "\" rejected.");
    }

    /** Simple row model for the TableView. Swap this out for your real entity/DTO. */
    public static class RegistrationRow {
        private final SimpleIntegerProperty registrationId;
        private final SimpleStringProperty clubName;
        private final SimpleStringProperty category;
        private final SimpleStringProperty submissionDate;
        private final SimpleStringProperty status;

        public RegistrationRow(int registrationId, String clubName, String category,
                               String submissionDate, String status) {
            this.registrationId = new SimpleIntegerProperty(registrationId);
            this.clubName = new SimpleStringProperty(clubName);
            this.category = new SimpleStringProperty(category);
            this.submissionDate = new SimpleStringProperty(submissionDate);
            this.status = new SimpleStringProperty(status);
        }

        public int getRegistrationId() { return registrationId.get(); }
        public String getClubName() { return clubName.get(); }
        public String getCategory() { return category.get(); }
        public String getSubmissionDate() { return submissionDate.get(); }
        public String getStatus() { return status.get(); }
        public void setStatus(String status) { this.status.set(status); }
    }}