package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.BudgetItem;
import c213.dosaoopproject.Bushra.U07.model.MajorEvent;
import c213.dosaoopproject.Bushra.U07.util.EventManager;
import commonClass.util.SubViewSwitcher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class U07G1_eventDetailApprovalController {

    @FXML private Label pageTitleLabel;
    @FXML private Label pageDescriptionLabel;
    @FXML private Label statusLabel;

    @FXML private Label eventNameLabel;
    @FXML private Label clubNameLabel;
    @FXML private Label eventDateLabel;
    @FXML private Label venueLabel;
    @FXML private Label requestedBudgetLabel;
    @FXML private Label scopeLabel;
    @FXML private Label riskLevelLabel;

    @FXML private TableView<BudgetItem> budgetTableView;
    @FXML private TableColumn<BudgetItem, String> itemNameTableCC;
    @FXML private TableColumn<BudgetItem, Double> amountTableCC;

    @FXML private Label proposalStatusLabel;
    @FXML private Label budgetSheetStatusLabel;

    @FXML private VBox warningBannerVBox;
    @FXML private Label warningBannerLabel;

    @FXML private Button approveButton; // Needed to enable/disable programmatically

    @FXML private VBox inlineRevisionVBox;
    @FXML private TextArea revisionCommentsTextF;
    @FXML private Button confirmRevisionButton;

    @FXML private VBox inlineRejectVBox;
    @FXML private TextArea rejectReasonTextF;
    @FXML private Button confirmRejectButton;

    private MajorEvent currentEvent;

    @FXML
    public void initialize() {
        // Setup budget table columns
        itemNameTableCC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        amountTableCC.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    public void setEventData(MajorEvent event) {
        this.currentEvent = event;
        if (event == null) return;

        // Populate basic information labels
        eventNameLabel.setText(event.getEventName());
        clubNameLabel.setText(event.getClubName());
        eventDateLabel.setText(event.getEventDate() != null ? event.getEventDate().toString() : "N/A");
        venueLabel.setText(event.getVenue());
        requestedBudgetLabel.setText(String.format("%,.2f BDT", event.getRequestedBudget()));
        scopeLabel.setText(event.getScope());
        riskLevelLabel.setText(event.getRiskLevel());
        statusLabel.setText(event.getStatus());

        // Document Status
        proposalStatusLabel.setText(event.isProposalUploaded() ? "✔ Uploaded" : "❌ Missing");
        proposalStatusLabel.setStyle(event.isProposalUploaded() ? "-fx-text-fill: #1E7E34; -fx-font-weight: bold;" : "-fx-text-fill: #BD2130; -fx-font-weight: bold;");

        budgetSheetStatusLabel.setText(event.isBudgetSheetUploaded() ? "✔ Uploaded" : "❌ Missing");
        budgetSheetStatusLabel.setStyle(event.isBudgetSheetUploaded() ? "-fx-text-fill: #1E7E34; -fx-font-weight: bold;" : "-fx-text-fill: #BD2130; -fx-font-weight: bold;");

        // Populate Budget Table
        if (event.getBudgetItems() != null) {
            budgetTableView.setItems(FXCollections.observableArrayList(event.getBudgetItems()));
        }

        // Validate uploaded documents and show warning banner if incomplete
        if (!event.isProposalUploaded() || !event.isBudgetSheetUploaded()) {
            warningBannerVBox.setVisible(true);
            warningBannerVBox.setManaged(true);
            if (approveButton != null) approveButton.setDisable(true);
        } else {
            warningBannerVBox.setVisible(false);
            warningBannerVBox.setManaged(false);
            if (approveButton != null) approveButton.setDisable(false);
        }
    }

    // --- OnAction Handler Methods linked to Scene Builder FXML ---

    @FXML
    public void approveOA(ActionEvent actionEvent) {
        if (currentEvent == null) return;

        currentEvent.setStatus("Approved");
        EventManager.updateEvent(currentEvent);

        showAlert(Alert.AlertType.INFORMATION, "Proposal Approved",
                "The event proposal '" + currentEvent.getEventName() + "' has been approved.");

        backOA(actionEvent);
    }

    @FXML
    public void returnOA(ActionEvent actionEvent) {
        // Toggle Revision Panel
        boolean show = !inlineRevisionVBox.isVisible();
        inlineRevisionVBox.setVisible(show);
        inlineRevisionVBox.setManaged(show);

        // Hide reject panel if open
        inlineRejectVBox.setVisible(false);
        inlineRejectVBox.setManaged(false);
    }

    @FXML
    public void rejectOA(ActionEvent actionEvent) {
        // Toggle Reject Panel
        boolean show = !inlineRejectVBox.isVisible();
        inlineRejectVBox.setVisible(show);
        inlineRejectVBox.setManaged(show);

        // Hide revision panel if open
        inlineRevisionVBox.setVisible(false);
        inlineRevisionVBox.setManaged(false);
    }

    @FXML
    public void confirmRevisionOA(ActionEvent actionEvent) {
        String comments = revisionCommentsTextF.getText().trim();
        if (comments.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please provide revision comments.");
            return;
        }

        currentEvent.setStatus("Revision Requested");
        currentEvent.setRevisionComments(comments);
        EventManager.updateEvent(currentEvent);

        showAlert(Alert.AlertType.INFORMATION, "Revision Requested",
                "The proposal has been returned for revision.");

        backOA(actionEvent);
    }

    @FXML
    public void confirmRejectOA(ActionEvent actionEvent) {
        String reason = rejectReasonTextF.getText().trim();
        if (reason.length() < 20) {
            showAlert(Alert.AlertType.WARNING, "Validation Error",
                    "Rejection reason must be at least 20 characters long.");
            return;
        }

        currentEvent.setStatus("Rejected");
        currentEvent.setRejectionReason(reason);
        EventManager.updateEvent(currentEvent);

        showAlert(Alert.AlertType.INFORMATION, "Proposal Rejected",
                "The event proposal has been rejected.");

        backOA(actionEvent);
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        if (U07_HeadOfDoSAViewController.getInstance() != null) {
            StackPane contentArea = U07_HeadOfDoSAViewController.getInstance().getContentArea();
            SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G1_eventApprovalQueue.fxml");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}