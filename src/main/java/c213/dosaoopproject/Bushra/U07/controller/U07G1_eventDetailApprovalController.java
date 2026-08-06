package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.EventProposal;
import c213.dosaoopproject.Bushra.U07.util.EventSelectionHolder;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.util.ValidationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class U07G1_eventDetailApprovalController {

    @FXML private VBox warningBannerVBox;
    @FXML private Label warningBannerLabel;
    @FXML private Label statusLabel;
    @FXML private Label pageTitleLabel;

    @FXML private Label eventNameLabel;
    @FXML private Label clubNameLabel;
    @FXML private Label eventDateLabel;
    @FXML private Label venueLabel;
    @FXML private Label requestedBudgetLabel;
    @FXML private Label scopeLabel;
    @FXML private Label riskLevelLabel;

    @FXML private Label proposalStatusLabel;
    @FXML private Label budgetSheetStatusLabel;

    @FXML private Button approveButton;
    @FXML private VBox inlineRevisionVBox;
    @FXML private TextArea revisionCommentsTextF;
    @FXML private VBox inlineRejectVBox;
    @FXML private TextArea rejectReasonTextF;

    private EventProposal currentEvent;
    private final String DATA_FILE = "events_data.dat";

    @FXML
    public void initialize() {
        currentEvent = EventSelectionHolder.getSelectedEvent();
        if (currentEvent != null) {
            populateDetails();
            checkDocumentAttachments();
        }
    }

    private void populateDetails() {
        eventNameLabel.setText(currentEvent.getEventName());
        clubNameLabel.setText(currentEvent.getClubName());
        eventDateLabel.setText(currentEvent.getEventDate());
        venueLabel.setText(currentEvent.getVenue());
        requestedBudgetLabel.setText(currentEvent.getBudget() + " BDT");
        scopeLabel.setText(currentEvent.getScope());
        riskLevelLabel.setText(currentEvent.getRiskLevel());
        statusLabel.setText(currentEvent.getStatus());

        proposalStatusLabel.setText(currentEvent.isProposalPdfUploaded() ? "✔ Uploaded" : "❌ Missing");
        budgetSheetStatusLabel.setText(currentEvent.isBudgetSheetUploaded() ? "✔ Uploaded" : "❌ Missing");
    }

    // Event 6: Verify required attachments and display warning if missing
    private void checkDocumentAttachments() {
        if (!currentEvent.isProposalPdfUploaded() || !currentEvent.isBudgetSheetUploaded()) {
            warningBannerVBox.setVisible(true);
            warningBannerVBox.setManaged(true);
            warningBannerLabel.setText("Required documents are missing. Proposal cannot be directly approved.");
            approveButton.setDisable(true);
        }
    }

    @FXML
    public void backOA(ActionEvent event) {
        returnToQueue(event);
    }

    @FXML
    public void approveOA(ActionEvent event) {
        saveDecision(event, "Approved", "Proposal approved by Head of DoSA.");
    }

    @FXML
    public void returnOA(ActionEvent event) {
        inlineRevisionVBox.setVisible(true);
        inlineRevisionVBox.setManaged(true);
        inlineRejectVBox.setVisible(false);
        inlineRejectVBox.setManaged(false);
    }

    @FXML
    public void confirmRevisionOA(ActionEvent event) {
        // Event 6: Validate rationale input via ValidationUtil
        if (ValidationUtil.isEmpty(new TextField(revisionCommentsTextF.getText()))) {
            AlertUtil.showError("Validation Error", "Please provide comments for revision.");
            return;
        }
        saveDecision(event, "Revision Required", revisionCommentsTextF.getText());
    }

    @FXML
    public void rejectOA(ActionEvent event) {
        inlineRejectVBox.setVisible(true);
        inlineRejectVBox.setManaged(true);
        inlineRevisionVBox.setVisible(false);
        inlineRevisionVBox.setManaged(false);
    }

    @FXML
    public void confirmRejectOA(ActionEvent event) {
        String reason = rejectReasonTextF.getText();
        // Event 6: Validate rationale minimum character length
        if (reason == null || reason.trim().length() < 20) {
            AlertUtil.showError("Validation Error", "Rejection rationale must be at least 20 characters.");
            return;
        }
        saveDecision(event, "Rejected", reason);
    }

    // Event 7 & Event 8: Update proposal status, persist changes, show alert, and reload queue
    private void saveDecision(ActionEvent event, String newStatus, String rationale) {
        ArrayList<EventProposal> list = BinaryFileUtil.readList(DATA_FILE);

        for (EventProposal p : list) {
            if (p.getEventName().equalsIgnoreCase(currentEvent.getEventName())) {
                p.setStatus(newStatus);
                p.setDecisionRationale(rationale);
                break;
            }
        }

        // Event 7: Save updated list using BinaryFileUtil
        BinaryFileUtil.saveList(DATA_FILE, list);

        // Event 8: Display alert and reload Queue view
        AlertUtil.showInformation("Success", "Event proposal status updated to: " + newStatus);
        returnToQueue(event);
    }

    private void returnToQueue(ActionEvent event) {
        AnchorPane contentArea = (AnchorPane) ((Node) event.getSource()).getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/view/U07G1_eventApprovalQueue.fxml");
    }
}