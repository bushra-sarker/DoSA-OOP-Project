package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.util.EventManager;
import c213.dosaoopproject.commonClass.util.SceneSwitcher;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class U07_dashboardOverviewController
{
    @javafx.fxml.FXML
    private Label pendingReportsCountLabel;
    @javafx.fxml.FXML
    private Label pendingEventsCountLabel;
    @javafx.fxml.FXML
    private Label pendingBudgetAllocationsLabel;
    @javafx.fxml.FXML
    private Label activeCrisisCountLabel;
    @javafx.fxml.FXML
    private Label pendingPartnershipCountLabel;
    @javafx.fxml.FXML
    private Label pendingAppealsCountLabel;
    @javafx.fxml.FXML
    private Label pendingTranscriptCountLabel;
    @javafx.fxml.FXML
    private Label pendingExchangeCountLabel;

    @javafx.fxml.FXML
    public void initialize() {
        updateDashboardCounts();
    }

    public void updateDashboardCounts() {
        int pendingEventsCount = EventManager.getPendingEvents().size();
        pendingEventsCountLabel.setText(String.valueOf(pendingEventsCount));
    }

    @javafx.fxml.FXML
    public void eventViewOA(Event event) {
        Pane contentArea = (Pane) pendingAppealsCountLabel.getScene().lookup("#contentArea");
        SceneSwitcher.switchContent(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G1_eventApprovalQueue.fxml");

    }

    @javafx.fxml.FXML
    public void budgetManagementOA(Event event) {
        //goal02
    }

    @javafx.fxml.FXML
    public void disciplinaryAppealsOA(Event event) {
        //goal03
    }

    @javafx.fxml.FXML
    public void reportViewOA(Event event) {
        //goal04
    }

    @javafx.fxml.FXML
    public void crisisViewOA(Event event) {
        //goal05
    }

    @javafx.fxml.FXML
    public void exchangeViewOA(Event event) {
        //goal06
    }

    @javafx.fxml.FXML
    public void transcriptViewOA(Event event) {
        //goal07
    }

    @javafx.fxml.FXML
    public void partnershipViewOA(Event event) {
        //goal08
    }

}