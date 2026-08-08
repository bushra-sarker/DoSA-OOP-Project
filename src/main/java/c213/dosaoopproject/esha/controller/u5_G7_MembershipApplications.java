package c213.dosaoopproject.esha.controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
public class u5_G7_MembershipApplications
{
    @FXML private TableView<ApplicationRow> applicationsTable;
    @FXML private TableColumn<ApplicationRow, Integer> applicationIdColumn;
    @FXML private TableColumn<ApplicationRow, Integer> studentIdColumn;
    @FXML private TableColumn<ApplicationRow, String> majorColumn;
    @FXML private TableColumn<ApplicationRow, String> reasonColumn;
    @FXML private TableColumn<ApplicationRow, String> skillsColumn;
    @FXML private TableColumn<ApplicationRow, String> statusColumn;

    @FXML private TextArea detailsArea;
    @FXML private Label statusLabel;

    private final ObservableList<ApplicationRow> applications = FXCollections.observableArrayList();

    private String clubName; // scope the table to this executive's club
    @FXML
    private Button approveBtn;
    @FXML
    private Button rejectBtn;

    public void setClubName(String clubName) {
        this.clubName = clubName;
        loadApplications();
    }
    @FXML
    public void initialize() {
        applicationIdColumn.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reasonToJoin"));
        skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skills"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        applicationsTable.setItems(applications);
        applicationsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldRow, newRow) -> {
            if (newRow != null) {
                detailsArea.setText("Reason: " + newRow.getReasonToJoin() + "\nSkills: " + newRow.getSkills());
            }
        });
    }

    private void loadApplications() {
        // TODO: replace with a real service call, e.g.:
        // applications.setAll(membershipService.getPendingApplications(clubName));
        applications.setAll(
                new ApplicationRow(1, 2411001, "Computer Science", "Passionate about robotics", "Java, Python", "Pending"),
                new ApplicationRow(2, 2411002, "Business", "Wants to build event skills", "Excel, Marketing", "Pending")
        );
    }

    @FXML
    private void handleApprove(ActionEvent event) {
        ApplicationRow selected = applicationsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select an application first.");
            return;
        }

        // TODO: membershipService.approve(selected.getApplicationId());

        selected.setStatus("Approved");
        applicationsTable.refresh();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Application #" + selected.getApplicationId() + " approved.");
    }

    @FXML
    private void handleReject(ActionEvent event) {
        ApplicationRow selected = applicationsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select an application first.");
            return;
        }

        // TODO: membershipService.reject(selected.getApplicationId());

        selected.setStatus("Rejected");
        applicationsTable.refresh();
        statusLabel.setStyle("-fx-text-fill:orange;");
        statusLabel.setText("Application #" + selected.getApplicationId() + " rejected.");
    }

    /** Simple row model for the TableView. Swap this out for your real entity/DTO. */
    public static class ApplicationRow {
        private final SimpleIntegerProperty applicationId;
        private final SimpleIntegerProperty studentId;
        private final SimpleStringProperty major;
        private final SimpleStringProperty reasonToJoin;
        private final SimpleStringProperty skills;
        private final SimpleStringProperty status;

        public ApplicationRow(int applicationId, int studentId, String major,
                              String reasonToJoin, String skills, String status) {
            this.applicationId = new SimpleIntegerProperty(applicationId);
            this.studentId = new SimpleIntegerProperty(studentId);
            this.major = new SimpleStringProperty(major);
            this.reasonToJoin = new SimpleStringProperty(reasonToJoin);
            this.skills = new SimpleStringProperty(skills);
            this.status = new SimpleStringProperty(status);
        }

        public int getApplicationId() { return applicationId.get(); }
        public int getStudentId() { return studentId.get(); }
        public String getMajor() { return major.get(); }
        public String getReasonToJoin() { return reasonToJoin.get(); }
        public String getSkills() { return skills.get(); }
        public String getStatus() { return status.get(); }
        public void setStatus(String status) { this.status.set(status); }
    }}