package c213.dosaoopproject.Bushra.U07.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class U07_dashboardOverviewController {

    @FXML private Label pendingEventsCountLabel;
    @FXML private Label pendingBudgetAllocationsLabel;
    @FXML private Label pendingAppealsCountLabel;
    @FXML private Label pendingReportsCountLabel;
    @FXML private Label activeCrisisCountLabel;
    @FXML private Label pendingExchangeCountLabel;
    @FXML private Label pendingTranscriptCountLabel;
    @FXML private Label pendingPartnershipCountLabel;

    @FXML
    public void initialize() {
        // Load initial metric counts
        loadDashboardMetrics();
    }

    public void loadDashboardMetrics() {
        // Example dynamic values / hooks to your data model:
        setPendingEventsCount(3);
        setPendingBudgetAllocationsCount(5);
        setPendingAppealsCount(2);
        setPendingReportsCount(1);
        setActiveCrisisCount(4);
        setPendingExchangeCount(6);
        setPendingTranscriptCount(8);
        setPendingPartnershipCount(2);
    }

    // --- Setter methods for dynamic runtime updates ---

    public void setPendingEventsCount(int count) {
        pendingEventsCountLabel.setText(String.valueOf(count));
    }

    public void setPendingBudgetAllocationsCount(int count) {
        pendingBudgetAllocationsLabel.setText(String.valueOf(count));
    }

    public void setPendingAppealsCount(int count) {
        pendingAppealsCountLabel.setText(String.valueOf(count));
    }

    public void setPendingReportsCount(int count) {
        pendingReportsCountLabel.setText(String.valueOf(count));
    }

    public void setActiveCrisisCount(int count) {
        activeCrisisCountLabel.setText(String.valueOf(count));
    }

    public void setPendingExchangeCount(int count) {
        pendingExchangeCountLabel.setText(String.valueOf(count));
    }

    public void setPendingTranscriptCount(int count) {
        pendingTranscriptCountLabel.setText(String.valueOf(count));
    }

    public void setPendingPartnershipCount(int count) {
        pendingPartnershipCountLabel.setText(String.valueOf(count));
    }
}