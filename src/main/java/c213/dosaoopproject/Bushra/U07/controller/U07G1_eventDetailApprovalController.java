package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.BudgetItem;
import c213.dosaoopproject.Bushra.U07.model.Event;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class U07G1_eventDetailApprovalController {

    @FXML private Label pageTitleLabel;
    @FXML private Label eventNameLabel;
    @FXML private Label clubNameLabel;
    @FXML private Label eventDateLabel;
    @FXML private Label venueLabel;
    @FXML private Label requestedBudgetLabel;
    @FXML private Label riskLevelLabel;
    @FXML private Label statusLabel;

    @FXML private TableView<BudgetItem> budgetTableView;
    @FXML private TableColumn<BudgetItem, String> itemNameTableCC;
    @FXML private TableColumn<BudgetItem, String> amountTableCC;

    @FXML private VBox inlineRejectVBox;
    @FXML private TextArea rejectReasonTextF;
    @FXML private Button confirmRejectButton;

    @FXML private VBox inlineRevisionVBox;
    @FXML private TextArea revisionCommentsTextF;
    @FXML private Button confirmRevisionButton;

    @FXML private Label warningBannerLabel;
    @FXML private VBox warningBannerVBox;

    private Event selectedEvent;

    @FXML
    public void initialize() {
        // Setup TableView Columns for Budget Items
        itemNameTableCC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        amountTableCC.setCellValueFactory(new PropertyValueFactory<>("amount"));

        // Hide inline feedback boxes initially
        if (inlineRejectVBox != null) inlineRejectVBox.setVisible(false);
        if (inlineRevisionVBox != null) inlineRevisionVBox.setVisible(false);
        if (warningBannerVBox != null) warningBannerVBox.setVisible(false);
    }

    // Called from Queue Controller when passing selected event
    public void setSelectedEvent(Event event) {
        this.selectedEvent = event;
        populateEventDetails();
    }

    private void populateEventDetails() {
        if (selectedEvent == null) return;

        eventNameLabel.setText(selectedEvent.getEventName());
        clubNameLabel.setText(selectedEvent.getClubName());
        eventDateLabel.setText(selectedEvent.getEventDate());
        requestedBudgetLabel.setText("BDT " + selectedEvent.getBudget());
        riskLevelLabel.setText(selectedEvent.getRiskLevel());
        statusLabel.setText(selectedEvent.getStatus());

        if (venueLabel != null) {
            venueLabel.setText(selectedEvent.getVenue() != null ? selectedEvent.getVenue() : "N/A");
        }

        // Show warning if high risk
        if ("High".equalsIgnoreCase(selectedEvent.getRiskLevel())) {
            if (warningBannerVBox != null) warningBannerVBox.setVisible(true);
            if (warningBannerLabel != null) warningBannerLabel.setText("WARNING: High-risk event requires careful safety check.");
        }

        // Populate TableView with budget items if present
        if (selectedEvent.getBudgetItems() != null) {
            ObservableList<BudgetItem> items = FXCollections.observableArrayList(selectedEvent.getBudgetItems());
            budgetTableView.setItems(items);
        }
    }

    @FXML
    public void approveOA(ActionEvent actionEvent) {
        if (selectedEvent == null) return;

        selectedEvent.setStatus("Approved");
        saveUpdatedEvent();
        AlertUtil.showInformation("Success", "Event proposal approved successfully!");
        returnToQueue();
    }

    @FXML
    public void rejectOA(ActionEvent actionEvent) {
        // Toggle inline rejection text box
        inlineRejectVBox.setVisible(true);
        inlineRevisionVBox.setVisible(false);
    }

    @FXML
    public void confirmRejectOA(ActionEvent actionEvent) {
        String reason = rejectReasonTextF.getText().trim();
        if (reason.isEmpty()) {
            AlertUtil.showWarning("Missing Input", "Please provide a rejection reason.");
            return;
        }

        selectedEvent.setStatus("Rejected");
        // Optionally save reason if Event class supports setRejectionReason(reason)
        saveUpdatedEvent();
        AlertUtil.showInformation("Rejected", "Event proposal has been rejected.");
        returnToQueue();
    }

    @FXML
    public void confirmRevisionOA(ActionEvent actionEvent) {
        String comments = revisionCommentsTextF.getText().trim();
        if (comments.isEmpty()) {
            AlertUtil.showWarning("Missing Input", "Please enter revision notes.");
            return;
        }

        selectedEvent.setStatus("Revision Requested");
        saveUpdatedEvent();
        AlertUtil.showInformation("Revision Sent", "Revision request submitted to Club Executive.");
        returnToQueue();
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        returnToQueue();
    }

    @FXML
    public void returnOA(ActionEvent actionEvent) {
        returnToQueue();
    }

    private void saveUpdatedEvent() {
        String filePath = "data/events_data.dat";
        List<Event> events = BinaryFileUtil.readObjects(filePath);

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getEventId().equals(selectedEvent.getEventId())) {
                events.set(i, selectedEvent);
                break;
            }
        }
        BinaryFileUtil.writeObjects(filePath, events);
    }

    private void returnToQueue() {
        Pane contentArea = (Pane) budgetTableView.getScene().lookup("#contentArea");
        SceneSwitcher.switchContent(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G1_eventApprovalQueue.fxml");
    }
}