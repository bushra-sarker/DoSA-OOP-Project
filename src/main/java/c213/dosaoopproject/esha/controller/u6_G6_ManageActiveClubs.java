package c213.dosaoopproject.esha.controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class u6_G6_ManageActiveClubs
{
    @FXML private TableView<ClubRow> clubsTable;
    @FXML private TableColumn<ClubRow, Integer> clubIdColumn;
    @FXML private TableColumn<ClubRow, String> clubNameColumn;
    @FXML private TableColumn<ClubRow, String> categoryColumn;
    @FXML private TableColumn<ClubRow, String> statusColumn;
    @FXML private TextArea activitiesArea;
    @FXML private Label statusLabel;

    private final ObservableList<ClubRow> clubs = FXCollections.observableArrayList();

    private int coordinatorId;
    @FXML
    private Button viewActivitiesBtn;
    @FXML
    private Button activateBtn;
    @FXML
    private Button suspendBtn;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
        loadClubs();
    }
    @FXML
    public void initialize() {
        clubIdColumn.setCellValueFactory(new PropertyValueFactory<>("clubId"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        clubsTable.setItems(clubs);
        loadClubs();
    }

    private void loadClubs() {
        // TODO: replace with real service call, e.g.:
        // clubs.setAll(clubService.getAllActiveClubs());
        clubs.setAll(
                new ClubRow(1, "Debate Society", "Academic", "Active"),
                new ClubRow(2, "Football Club", "Sports", "Active")
        );
    }

    private ClubRow requireSelection() {
        ClubRow selected = clubsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a club first.");
        }
        return selected;
    }

    @FXML
    private void handleViewActivities(ActionEvent event) {
        ClubRow selected = requireSelection();
        if (selected == null) return;

        // TODO: replace with real service call, e.g.:
        // List<Activity> activities = clubService.viewActivities(selected.getClubId());
        // activitiesArea.setText(activities as formatted text);

        activitiesArea.setText("Sample activities for \"" + selected.getClubName()
                + "\":\n- Weekly meeting\n- Semester showcase event");
    }

    @FXML
    private void handleSuspendClub(ActionEvent event) {
        ClubRow selected = requireSelection();
        if (selected == null) return;

        // TODO: clubService.suspendClub(selected.getClubId());

        selected.setStatus("Suspended");
        clubsTable.refresh();
        statusLabel.setStyle("-fx-text-fill:orange;");
        statusLabel.setText("\"" + selected.getClubName() + "\" suspended.");
    }

    @FXML
    private void handleActivateClub(ActionEvent event) {
        ClubRow selected = requireSelection();
        if (selected == null) return;

        // TODO: clubService.activateClub(selected.getClubId());

        selected.setStatus("Active");
        clubsTable.refresh();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("\"" + selected.getClubName() + "\" activated.");
    }

    /** Simple row model for the TableView. Swap this out for your real entity/DTO. */
    public static class ClubRow {
        private final SimpleIntegerProperty clubId;
        private final SimpleStringProperty clubName;
        private final SimpleStringProperty category;
        private final SimpleStringProperty status;

        public ClubRow(int clubId, String clubName, String category, String status) {
            this.clubId = new SimpleIntegerProperty(clubId);
            this.clubName = new SimpleStringProperty(clubName);
            this.category = new SimpleStringProperty(category);
            this.status = new SimpleStringProperty(status);
        }

        public int getClubId() { return clubId.get(); }
        public String getClubName() { return clubName.get(); }
        public String getCategory() { return category.get(); }
        public String getStatus() { return status.get(); }
        public void setStatus(String status) { this.status.set(status); }
    }}