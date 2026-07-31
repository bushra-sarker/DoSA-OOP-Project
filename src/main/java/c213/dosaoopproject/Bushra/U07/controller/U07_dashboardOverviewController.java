package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.MajorEvent;
import c213.dosaoopproject.Bushra.U07.util.EventManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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

    /**
     * Fetches live data from events_data.dat and populates metric badges
     */
    private void refreshDashboardMetrics() {
        ArrayList<MajorEvent> events = EventManager.loadEvents();
        long pendingEventsCount = events.stream()
                .filter(e -> "Pending".equalsIgnoreCase(e.getStatus()))
                .count();

        pendingEventsCountLabel.setText(String.valueOf(pendingEventsCount));

        // Static/Mock values for other goals until their respective dat stores are added
        pendingBudgetAllocationsLabel.setText("5");
        pendingAppealsCountLabel.setText("2");
        pendingReportsCountLabel.setText("1");
        activeCrisisCountLabel.setText("4");
        pendingExchangeCountLabel.setText("6");
        pendingTranscriptCountLabel.setText("8");
        pendingPartnershipCountLabel.setText("2");
    }

    // Card-click handlers delegating content change to the parent shell
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