package c213.dosaoopproject.bushraController;

import c213.dosaoopproject.bushraModel.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class HeadOfDoSAViewController {

    // Main Container Panes
    @FXML private StackPane contentArea;
    @FXML private VBox dashboardOverviewPane;
    @FXML private VBox majorEventsPane;

    // Header & User Labels
    @FXML private Label nameLabel;
    @FXML private Label userIdLabel;
    @FXML private Label titleWelcomeLabel;
    @FXML private Label msgWelcomeLabel;
    @FXML private ImageView ppImageView;

    // Dashboard Summary Card Labels & Badges
    @FXML private Label lblPendingEventsCount;
    @FXML private Label lblPendingBudgetCount;
    @FXML private Label lblPendingClubCount;
    @FXML private Label lblPendingScholarshipsCount;
    @FXML private Label lblBadgeEvents;
    @FXML private Label lblBadgeBudget;
    @FXML private Label lblBadgeClub;
    @FXML private Label lblBadgeScholarship;

    // Sidebar Buttons
    @FXML private Button btnDashboard;
    @FXML private Button btnPendingEventsCard;
    @FXML private Button btnPendingScholarshipsCard;
    @FXML private Button btnBudgetManagement;
    @FXML private Button btnDisciplinaryAppeals;
    @FXML private Button btnCrisisManagement;
    @FXML private Button btnReportsAnalytics;
    @FXML private Button btnExchangePrograms;
    @FXML private Button btnExternalRelations;
    @FXML private Button btnCoCurricularTranscripts;

    // Recent Activity Controls
    @FXML private Hyperlink viewAllHyperLink;
    @FXML private Label recent_OneLabel;
    @FXML private Label recentTime_OneLabel;

    // Goal-1 TableView Controls (Major Events Queue)
    @FXML private TableView<Event> tvPendingMajorEvents;
    @FXML private TableColumn<Event, String> colEventName;
    @FXML private TableColumn<Event, String> colClubName;
    @FXML private TableColumn<Event, Double> colRequestedBudget;
    @FXML private TableColumn<Event, LocalDate> colEventDate;
    @FXML private TableColumn<Event, String> colRiskLevel;
    @FXML private TableColumn<Event, Void> colAction;
    @FXML private ProgressIndicator piLoading;

    // Data List
    private final ObservableList<Event> pendingEventsList = FXCollections.observableArrayList();

    // CSS Styles
    private final String ACTIVE_STYLE = "-fx-background-color: #36649B; -fx-text-fill: white; -fx-background-radius: 10; -fx-border-radius: 10;";
    private final String INACTIVE_STYLE = "-fx-background-color: #004675; -fx-text-fill: white; -fx-background-radius: 7; -fx-border-radius: 7;";

    @FXML
    public void initialize() {
        setupTableColumns();
        loadDummyData();
        showPane(dashboardOverviewPane, btnDashboard);
    }

    private void setupTableColumns() {
        colEventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colClubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        colRequestedBudget.setCellValueFactory(new PropertyValueFactory<>("requestedBudget"));
        colEventDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        colRiskLevel.setCellValueFactory(new PropertyValueFactory<>("riskLevel"));

        // Add custom action buttons (Approve / Reject) into each row
        colAction.setCellFactory(param -> new TableCell<>() {
            private final Button btnApprove = new Button("Approve");
            private final Button btnReject = new Button("Reject");
            private final HBox pane = new HBox(5, btnApprove, btnReject);

            {
                btnApprove.setStyle("-fx-background-color: #27AE60; -fx-text-fill: white; -fx-font-size: 11px;");
                btnReject.setStyle("-fx-background-color: #C0392B; -fx-text-fill: white; -fx-font-size: 11px;");
                pane.setAlignment(Pos.CENTER);

                btnApprove.setOnAction(e -> {
                    Event event = getTableView().getItems().get(getIndex());
                    handleApproveEvent(event);
                });

                btnReject.setOnAction(e -> {
                    Event event = getTableView().getItems().get(getIndex());
                    handleRejectEvent(event);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(pane);
                }
            }
        });
    }

    private void loadDummyData() {
        // Goal-1 filtered items: Budget > 50,000 BDT or Inter-University scale
        pendingEventsList.add(new Event("National Tech Fest 2026", "Robotics Club", 120000.0, LocalDate.of(2026, 8, 15), "Medium", "Pending"));
        pendingEventsList.add(new Event("Inter-University Cultural Gala", "Cultural Club", 85000.0, LocalDate.of(2026, 9, 10), "Low", "Pending"));
        pendingEventsList.add(new Event("National Programming Contest", "ACM Club", 150000.0, LocalDate.of(2026, 10, 5), "High", "Pending"));

        tvPendingMajorEvents.setItems(pendingEventsList);
        updateDashboardCounts();
    }

    private void handleApproveEvent(Event event) {
        pendingEventsList.remove(event);
        updateDashboardCounts();
        System.out.println("Approved Event: " + event.getEventName());
    }

    private void handleRejectEvent(Event event) {
        pendingEventsList.remove(event);
        updateDashboardCounts();
        System.out.println("Rejected Event: " + event.getEventName());
    }

    private void updateDashboardCounts() {
        int count = pendingEventsList.size();
        lblPendingEventsCount.setText(String.valueOf(count));
        lblBadgeEvents.setText(String.valueOf(count));
    }

    // Helper method to toggle views cleanly
    private void showPane(VBox targetPane, Button activeBtn) {
        dashboardOverviewPane.setVisible(false);
        dashboardOverviewPane.setManaged(false);

        majorEventsPane.setVisible(false);
        majorEventsPane.setManaged(false);

        targetPane.setVisible(true);
        targetPane.setManaged(true);

        resetButtonStyles();
        if (activeBtn != null) {
            activeBtn.setStyle(ACTIVE_STYLE);
        }
    }

    private void resetButtonStyles() {
        btnDashboard.setStyle(INACTIVE_STYLE);
        btnPendingEventsCard.setStyle(INACTIVE_STYLE);
        btnPendingScholarshipsCard.setStyle(INACTIVE_STYLE);
        btnBudgetManagement.setStyle(INACTIVE_STYLE);
        btnDisciplinaryAppeals.setStyle(INACTIVE_STYLE);
        btnCrisisManagement.setStyle(INACTIVE_STYLE);
        btnReportsAnalytics.setStyle(INACTIVE_STYLE);
        btnExchangePrograms.setStyle(INACTIVE_STYLE);
        btnExternalRelations.setStyle(INACTIVE_STYLE);
        btnCoCurricularTranscripts.setStyle(INACTIVE_STYLE);
    }

    // Navigation Handlers
    @FXML private void dashboardViewOA(ActionEvent event) { showPane(dashboardOverviewPane, btnDashboard); }
    @FXML private void majorEventViewOA(ActionEvent event) { showPane(majorEventsPane, btnPendingEventsCard); }
    @FXML private void scholarshipViewOA(ActionEvent event) { showPane(dashboardOverviewPane, btnPendingScholarshipsCard); }
    @FXML private void budgetManagementOA(ActionEvent event) { showPane(dashboardOverviewPane, btnBudgetManagement); }
    @FXML private void disciplinaryAppealsOA(ActionEvent event) { showPane(dashboardOverviewPane, btnDisciplinaryAppeals); }
    @FXML private void crisisViewOA(ActionEvent event) { showPane(dashboardOverviewPane, btnCrisisManagement); }
    @FXML private void reportViewOA(ActionEvent event) { showPane(dashboardOverviewPane, btnReportsAnalytics); }
    @FXML private void exchangeViewOA(ActionEvent event) { showPane(dashboardOverviewPane, btnExchangePrograms); }
    @FXML private void partnershipViewOA(ActionEvent event) { showPane(dashboardOverviewPane, btnExternalRelations); }
    @FXML private void transcriptViewOA(ActionEvent event) { showPane(dashboardOverviewPane, btnCoCurricularTranscripts); }
    @FXML private void notificationOA(ActionEvent event) {}
    @FXML private void viewAllNotificationLinkOA(ActionEvent event) {}
    @FXML private void logOutOA(ActionEvent event) {}
}