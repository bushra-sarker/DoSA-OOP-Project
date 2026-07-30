package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.BudgetItem;
import c213.dosaoopproject.Bushra.U07.model.EventProposal;
import commonClass.data.BinaryFileUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class U07G1_EventDetailApprovalController {

    @FXML private Label eventNameLabel;
    @FXML private Label clubNameLabel;
    @FXML private Label warningBannerLabel;

    @FXML private TableView<BudgetItem> budgetTableView;
    @FXML private TableColumn<BudgetItem, String> itemTableCC;
    @FXML private TableColumn<BudgetItem, Double> amountTableCC;

    @FXML private VBox inlineRevisionVBox;
    @FXML private TextArea revisionCommentsTextF;

    @FXML private VBox inlineRejectVBox;
    @FXML private TextArea rejectReasonTextF;

    private EventProposal currentEvent;

    @FXML
    public void initialize() {
        if (itemTableCC != null && amountTableCC != null) {
            itemTableCC.setCellValueFactory(new PropertyValueFactory<>("description"));
            amountTableCC.setCellValueFactory(new PropertyValueFactory<>("amount"));
        }
    }

    public void setEventData(EventProposal event) {
        this.currentEvent = event;
        eventNameLabel.setText("Event: " + event.getEventName());
        clubNameLabel.setText("Organizer: " + event.getClubName());

        if (event.getBudgetItems() != null) {
            budgetTableView.getItems().setAll(event.getBudgetItems());
        }

        if (!event.isProposalPdfAttached() || !event.isBudgetSheetAttached()) {
            warningBannerLabel.setVisible(true);
            warningBannerLabel.setManaged(true);
        } else {
            warningBannerLabel.setVisible(false);
            warningBannerLabel.setManaged(false);
        }
    }

    @FXML
    void approveOA(ActionEvent event) {
        updateStatusAndSave("Head Approved", "Approved by Head of DoSA.");
    }

    @FXML
    void returnForRevisionOA(ActionEvent event) {
        inlineRevisionVBox.setVisible(true);
        inlineRevisionVBox.setManaged(true);
        inlineRejectVBox.setVisible(false);
        inlineRejectVBox.setManaged(false);
    }

    @FXML
    void submitRevisionOA(ActionEvent event) {
        String comments = revisionCommentsTextF.getText().trim();
        if (comments.isEmpty() || comments.length() > 500) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Feedback is required and must not exceed 500 characters.");
            return;
        }
        updateStatusAndSave("Revision Requested", comments);
    }

    @FXML
    void rejectOA(ActionEvent event) {
        inlineRejectVBox.setVisible(true);
        inlineRejectVBox.setManaged(true);
        inlineRevisionVBox.setVisible(false);
        inlineRevisionVBox.setManaged(false);
    }

    @FXML
    void submitRejectOA(ActionEvent event) {
        String reason = rejectReasonTextF.getText().trim();
        if (reason.length() < 20) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Rejection reason must be at least 20 characters long.");
            return;
        }
        updateStatusAndSave("Rejected", reason);
    }

    private void updateStatusAndSave(String status, String comments) {
        ArrayList<EventProposal> allEvents = BinaryFileUtil.readList("events_data.dat");

        if (allEvents != null) {
            for (EventProposal ep : allEvents) {
                if (ep.getEventId().equals(currentEvent.getEventId())) {
                    ep.setStatus(status);
                    ep.setFeedbackComments(comments);
                    break;
                }
            }
            BinaryFileUtil.saveList("events_data.dat", allEvents);
        }

        showAlert(Alert.AlertType.INFORMATION, "Success", "Event proposal status updated to '" + status + "'.");
        reloadQueueView();
    }

    private void reloadQueueView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/c213/dosaoopproject/Bushra/U07/U07G1_eventApprovalQueue.fxml"));
            Parent root = loader.load();
            StackPane contentArea = (StackPane) eventNameLabel.getScene().lookup("#contentArea");
            if (contentArea != null) {
                contentArea.getChildren().setAll(root);
            }
        } catch (IOException e) {
            e.printStackTrace();
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