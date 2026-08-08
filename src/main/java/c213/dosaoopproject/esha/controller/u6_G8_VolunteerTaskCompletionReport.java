package c213.dosaoopproject.esha.controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class u6_G8_VolunteerTaskCompletionReport
{ @FXML private TableView<AssignmentRow> assignmentsTable;
    @FXML private TableColumn<AssignmentRow, Integer> assignmentIdColumn;
    @FXML private TableColumn<AssignmentRow, Integer> volunteerIdColumn;
    @FXML private TableColumn<AssignmentRow, String> eventNameColumn;
    @FXML private TableColumn<AssignmentRow, String> taskColumn;
    @FXML private TableColumn<AssignmentRow, String> completionStatusColumn;

    @FXML private ComboBox<String> completionStatusCombo;
    @FXML private Spinner<Integer> hoursWorkedSpinner;
    @FXML private TextArea remarksArea;
    @FXML private Label statusLabel;

    private final ObservableList<AssignmentRow> assignments = FXCollections.observableArrayList();

    private int coordinatorId;
    @FXML
    private Button submitReportBtn;
    @FXML
    private Button refreshBtn;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
        loadAssignments();
    }
    @FXML
    public void initialize() {assignmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("assignmentId"));
        volunteerIdColumn.setCellValueFactory(new PropertyValueFactory<>("volunteerId"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("task"));
        completionStatusColumn.setCellValueFactory(new PropertyValueFactory<>("completionStatus"));

        assignmentsTable.setItems(assignments);
        completionStatusCombo.setItems(FXCollections.observableArrayList("Completed", "Partially Completed", "No-show"));
        hoursWorkedSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, 0));

        loadAssignments();
    }

    private void loadAssignments() { assignments.setAll(
            new AssignmentRow(1, 3001, "Blood Donation Drive", "Registration Desk, Morning Shift", "Not Reported"),
            new AssignmentRow(2, 3002, "Beach Cleanup", "Waste Sorting Team", "Not Reported")
    );
    }

    @FXML
    private void handleSubmitReport(ActionEvent event) {
        AssignmentRow selected = assignmentsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select an assignment first.");
            return;
        }
        if (!validate()) return;
        selected.setCompletionStatus(completionStatusCombo.getValue());
        assignmentsTable.refresh();

        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Completion report submitted for volunteer #" + selected.getVolunteerId() + ".");
        remarksArea.clear();
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadAssignments();
        statusLabel.setText("List refreshed.");
    }

    private boolean validate() {
        if (completionStatusCombo.getValue() == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a completion status.");
            return false;
        }
        return true;
    }
    public static class AssignmentRow {
        private final SimpleIntegerProperty assignmentId;
        private final SimpleIntegerProperty volunteerId;
        private final SimpleStringProperty eventName;
        private final SimpleStringProperty task;
        private final SimpleStringProperty completionStatus;

        public AssignmentRow(int assignmentId, int volunteerId, String eventName,
                             String task, String completionStatus) {
            this.assignmentId = new SimpleIntegerProperty(assignmentId);
            this.volunteerId = new SimpleIntegerProperty(volunteerId);
            this.eventName = new SimpleStringProperty(eventName);
            this.task = new SimpleStringProperty(task);
            this.completionStatus = new SimpleStringProperty(completionStatus);
        }

        public int getAssignmentId() { return assignmentId.get(); }
        public int getVolunteerId() { return volunteerId.get(); }
        public String getEventName() { return eventName.get(); }
        public String getTask() { return task.get(); }
        public String getCompletionStatus() { return completionStatus.get(); }
        public void setCompletionStatus(String status) { this.completionStatus.set(status); }
    }}