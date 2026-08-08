package c213.dosaoopproject.esha.controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class u6_G6_BudgetApproval
{@FXML private TableView<BudgetRow> budgetTable;
    @FXML private TableColumn<BudgetRow, Integer> budgetIdColumn;
    @FXML private TableColumn<BudgetRow, Integer> executiveIdColumn;
    @FXML private TableColumn<BudgetRow, String> eventTypeColumn;
    @FXML private TableColumn<BudgetRow, String> expectedDateColumn;
    @FXML private TableColumn<BudgetRow, Double> estimatedAmountColumn;
    @FXML private TableColumn<BudgetRow, String> statusColumn;

    @FXML private TextArea remarksArea;
    @FXML private Label statusLabel;

    private final ObservableList<BudgetRow> budgetRequests = FXCollections.observableArrayList();

    private int coordinatorId;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button approveBtn;
    @FXML
    private Button rejectBtn;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
        loadPendingRequests();
    }

    @FXML
    public void initialize() {
        budgetIdColumn.setCellValueFactory(new PropertyValueFactory<>("budgetId"));
        executiveIdColumn.setCellValueFactory(new PropertyValueFactory<>("executiveId"));
        eventTypeColumn.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        expectedDateColumn.setCellValueFactory(new PropertyValueFactory<>("expectedEventDate"));
        estimatedAmountColumn.setCellValueFactory(new PropertyValueFactory<>("estimatedAmount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        budgetTable.setItems(budgetRequests);
        loadPendingRequests();
    }

    private void loadPendingRequests() {
        // TODO: replace with real service call, e.g.:
        // budgetRequests.setAll(budgetService.getPendingRequests());
        budgetRequests.setAll(
                new BudgetRow(1, 2411837, "Cultural Fest", "2026-09-12", 45000.00, "Pending"),
                new BudgetRow(2, 2411850, "Sports Meet", "2026-10-05", 28000.00, "Pending")
        );
    }

    private BudgetRow requireSelection() {
        BudgetRow selected = budgetTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a budget request first.");
        }
        return selected;
    }

    @FXML
    private void handleApprove(ActionEvent event) {
        BudgetRow selected = requireSelection();
        if (selected == null) return;

        // TODO: replace with real service call, e.g.:
        // budgetService.approve(selected.getBudgetId(), coordinatorId, remarksArea.getText());

        selected.setStatus("Approved");
        budgetTable.refresh();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Budget request #" + selected.getBudgetId() + " approved.");
        remarksArea.clear();
    }

    @FXML
    private void handleReject(ActionEvent event) {
        BudgetRow selected = requireSelection();
        if (selected == null) return;

        if (remarksArea.getText().isBlank()) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Add a remark explaining the rejection.");
            return;
        }

        // TODO: budgetService.reject(selected.getBudgetId(), coordinatorId, remarksArea.getText());

        selected.setStatus("Rejected");
        budgetTable.refresh();
        statusLabel.setStyle("-fx-text-fill:orange;");
        statusLabel.setText("Budget request #" + selected.getBudgetId() + " rejected.");
        remarksArea.clear();
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadPendingRequests();
        statusLabel.setText("List refreshed.");
    }

    /** Simple row model for the TableView. Swap this out for your real entity/DTO. */
    public static class BudgetRow {
        private final SimpleIntegerProperty budgetId;
        private final SimpleIntegerProperty executiveId;
        private final SimpleStringProperty eventType;
        private final SimpleStringProperty expectedEventDate;
        private final SimpleDoubleProperty estimatedAmount;
        private final SimpleStringProperty status;

        public BudgetRow(int budgetId, int executiveId, String eventType,
                         String expectedEventDate, double estimatedAmount, String status) {
            this.budgetId = new SimpleIntegerProperty(budgetId);
            this.executiveId = new SimpleIntegerProperty(executiveId);
            this.eventType = new SimpleStringProperty(eventType);
            this.expectedEventDate = new SimpleStringProperty(expectedEventDate);
            this.estimatedAmount = new SimpleDoubleProperty(estimatedAmount);
            this.status = new SimpleStringProperty(status);
        }

        public int getBudgetId() { return budgetId.get(); }
        public int getExecutiveId() { return executiveId.get(); }
        public String getEventType() { return eventType.get(); }
        public String getExpectedEventDate() { return expectedEventDate.get(); }
        public double getEstimatedAmount() { return estimatedAmount.get(); }
        public String getStatus() { return status.get(); }
        public void setStatus(String status) { this.status.set(status); }
    }


    }
