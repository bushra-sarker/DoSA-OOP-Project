package c213.dosaoopproject.esha.controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.stream.Collectors;

public class u6_G5_ReviewEventFeedback
{
    @FXML private ComboBox<String> activityFilterCombo;
    @FXML private TableView<FeedbackRow> feedbackTable;
    @FXML private TableColumn<FeedbackRow, Integer> feedbackIdColumn;
    @FXML private TableColumn<FeedbackRow, String> userIdColumn;
    @FXML private TableColumn<FeedbackRow, String> activityNameColumn;
    @FXML private TableColumn<FeedbackRow, String> ratingColumn;
    @FXML private TableColumn<FeedbackRow, String> dateColumn;
    @FXML private TableColumn<FeedbackRow, String> flaggedColumn;
    @FXML private TextArea commentsArea;
    @FXML private Label statusLabel;
    @FXML private Label summaryLabel;

    private final ObservableList<FeedbackRow> allFeedback = FXCollections.observableArrayList();
    private final ObservableList<FeedbackRow> visibleFeedback = FXCollections.observableArrayList();

    private int coordinatorId;
    @FXML
    private Button unflagBtn;
    @FXML
    private Button clearFilterBtn;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button applyFilterBtn;
    @FXML
    private Button flagBtn;

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
        loadFeedback();
    }
    @FXML
    public void initialize() {
        feedbackIdColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("feedbackId"));
        userIdColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("userId"));
        activityNameColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("activityName"));
        ratingColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("rating"));
        dateColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("date"));
        flaggedColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("flaggedDisplay"));

        feedbackTable.setItems(visibleFeedback);
        feedbackTable.getSelectionModel().selectedItemProperty().addListener((obs, oldRow, newRow) -> {
            commentsArea.setText(newRow != null ? newRow.getComments() : "");
        });

        loadFeedback();
    }

    private void loadFeedback() {
        // TODO: replace with real service call, e.g.:
        // allFeedback.setAll(feedbackService.getAllFeedback(coordinatorId));
        allFeedback.setAll(
                new FeedbackRow(1, "V-3001", "Beach Cleanup", "5", "2026-07-20", "Great turnout, well organized!", false),
                new FeedbackRow(2, "V-3002", "Beach Cleanup", "2", "2026-07-20", "Not enough gloves for everyone.", false),
                new FeedbackRow(3, "S-2411001", "Blood Donation Drive", "4", "2026-07-25", "Smooth process, friendly staff.", false),
                new FeedbackRow(4, "V-3005", "Blood Donation Drive", "1", "2026-07-25", "Long wait, no clear instructions.", false)
        );

        activityFilterCombo.setItems(FXCollections.observableArrayList(
                allFeedback.stream().map(FeedbackRow::getActivityName).distinct().collect(Collectors.toList())
        ));

        visibleFeedback.setAll(allFeedback);
        updateSummary();
    }

    @FXML
    private void handleApplyFilter(ActionEvent event) {
        String activity = activityFilterCombo.getValue();
        if (activity == null) {
            visibleFeedback.setAll(allFeedback);
        } else {
            visibleFeedback.setAll(
                    allFeedback.stream()
                            .filter(row -> row.getActivityName().equals(activity))
                            .collect(Collectors.toList())
            );
        }
        updateSummary();
    }

    @FXML
    private void handleClearFilter(ActionEvent event) {
        activityFilterCombo.setValue(null);
        visibleFeedback.setAll(allFeedback);
        updateSummary();
    }

    private FeedbackRow requireSelection() {
        FeedbackRow selected = feedbackTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Select a feedback entry first.");
        }
        return selected;
    }

    @FXML
    private void handleFlag(ActionEvent event) {
        FeedbackRow selected = requireSelection();
        if (selected == null) return;

        // TODO: feedbackService.flagForFollowUp(selected.getFeedbackId(), coordinatorId);

        selected.setFlagged(true);
        feedbackTable.refresh();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Feedback #" + selected.getFeedbackId() + " flagged for follow-up.");
    }

    @FXML
    private void handleUnflag(ActionEvent event) {
        FeedbackRow selected = requireSelection();
        if (selected == null) return;

        // TODO: feedbackService.unflag(selected.getFeedbackId());

        selected.setFlagged(false);
        feedbackTable.refresh();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Feedback #" + selected.getFeedbackId() + " unflagged.");
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadFeedback();
        statusLabel.setText("List refreshed.");
    }

    private void updateSummary() {
        if (visibleFeedback.isEmpty()) {
            summaryLabel.setText("No feedback to show.");
            return;
        }
        double avg = visibleFeedback.stream()
                .mapToInt(row -> parseRatingSafe(row.getRating()))
                .average()
                .orElse(0.0);
        long flaggedCount = visibleFeedback.stream().filter(FeedbackRow::isFlagged).count();
        summaryLabel.setText(String.format("%d entries shown — average rating %.1f/5 — %d flagged",
                visibleFeedback.size(), avg, flaggedCount));
    }

    private int parseRatingSafe(String rating) {
        try {
            return Integer.parseInt(rating);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /** Simple row model for the TableView. Swap this out for your real entity/DTO. */
    public static class FeedbackRow {
        private final SimpleIntegerProperty feedbackId;
        private final SimpleStringProperty userId;
        private final SimpleStringProperty activityName;
        private final SimpleStringProperty rating;
        private final SimpleStringProperty date;
        private final SimpleStringProperty comments;
        private boolean flagged;
        private final SimpleStringProperty flaggedDisplay;

        public FeedbackRow(int feedbackId, String userId, String activityName, String rating,
                           String date, String comments, boolean flagged) {
            this.feedbackId = new SimpleIntegerProperty(feedbackId);
            this.userId = new SimpleStringProperty(userId);
            this.activityName = new SimpleStringProperty(activityName);
            this.rating = new SimpleStringProperty(rating);
            this.date = new SimpleStringProperty(date);
            this.comments = new SimpleStringProperty(comments);
            this.flagged = flagged;
            this.flaggedDisplay = new SimpleStringProperty(flagged ? "Yes" : "No");
        }

        public int getFeedbackId() { return feedbackId.get(); }
        public String getUserId() { return userId.get(); }
        public String getActivityName() { return activityName.get(); }
        public String getRating() { return rating.get(); }
        public String getDate() { return date.get(); }
        public String getComments() { return comments.get(); }
        public boolean isFlagged() { return flagged; }
        public String getFlaggedDisplay() { return flaggedDisplay.get(); }

        public void setFlagged(boolean flagged) {
            this.flagged = flagged;
            this.flaggedDisplay.set(flagged ? "Yes" : "No");
        }
    }
}

