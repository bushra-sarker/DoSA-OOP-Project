package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.MajorEvent;
import c213.dosaoopproject.Bushra.U07.util.EventManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
        refreshDashboardMetrics();
    }

    private void refreshDashboardMetrics() {
        // 1. Dynamic Events Count
        ArrayList<MajorEvent> events = EventManager.loadEvents();
        long pendingEventsCount = 0;
        if (events != null) {
            pendingEventsCount = events.stream()
                    .filter(e -> "Pending".equalsIgnoreCase(e.getStatus()))
                    .count();
        }
        pendingEventsCountLabel.setText(String.valueOf(pendingEventsCount));

        // 2. Dynamic Budget Allocations Count (Reads from club_budgets.txt)
        int pendingBudgets = getPendingBudgetCount();
        pendingBudgetAllocationsLabel.setText(String.valueOf(pendingBudgets));

        // 3. Static/Mock values for remaining modules until data stores are added
        pendingAppealsCountLabel.setText("2");
        pendingReportsCountLabel.setText("1");
        activeCrisisCountLabel.setText("4");
        pendingExchangeCountLabel.setText("6");
        pendingTranscriptCountLabel.setText("8");
        pendingPartnershipCountLabel.setText("2");
    }

    /**
     * Helper method to count lines/clubs in club_budgets.txt that need allocation review.
     * Customize the condition based on how your text file formats pending status.
     */
    private int getPendingBudgetCount() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("club_budgets.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Example: Increment if line is not empty (or check specific status token)
                if (!line.trim().isEmpty()) {
                    count++;
                }
            }
        } catch (IOException e) {
            // Fallback if file does not exist yet
            System.err.println("Could not read club_budgets.txt: " + e.getMessage());
            return 0;
        }
        return count;
    }

    // --- CARD-CLICK NAVIGATION HANDLERS ---

    @FXML
    public void eventViewOA(MouseEvent event) {
        if (U07_HeadOfDoSAViewController.getInstance() != null) {
            U07_HeadOfDoSAViewController.getInstance().majorEventViewOA(null);
        }
    }

    @FXML
    public void budgetManagementOA(MouseEvent event) {
        if (U07_HeadOfDoSAViewController.getInstance() != null) {
            U07_HeadOfDoSAViewController.getInstance().budgetManagementOA(null);
        }
    }

    @FXML
    public void disciplinaryAppealsOA(MouseEvent event) {
        if (U07_HeadOfDoSAViewController.getInstance() != null) {
            U07_HeadOfDoSAViewController.getInstance().disciplinaryAppealsOA(null);
        }
    }

    @FXML
    public void reportViewOA(MouseEvent event) {
        if (U07_HeadOfDoSAViewController.getInstance() != null) {
            U07_HeadOfDoSAViewController.getInstance().reportViewOA(null);
        }
    }

    @FXML
    public void crisisViewOA(MouseEvent event) {
        if (U07_HeadOfDoSAViewController.getInstance() != null) {
            U07_HeadOfDoSAViewController.getInstance().crisisViewOA(null);
        }
    }

    @FXML
    public void exchangeViewOA(MouseEvent event) {
        if (U07_HeadOfDoSAViewController.getInstance() != null) {
            U07_HeadOfDoSAViewController.getInstance().exchangeViewOA(null);
        }
    }

    @FXML
    public void transcriptViewOA(MouseEvent event) {
        if (U07_HeadOfDoSAViewController.getInstance() != null) {
            U07_HeadOfDoSAViewController.getInstance().transcriptViewOA(null);
        }
    }

    @FXML
    public void partnershipViewOA(MouseEvent event) {
        if (U07_HeadOfDoSAViewController.getInstance() != null) {
            U07_HeadOfDoSAViewController.getInstance().partnershipViewOA(null);
        }
    }
}