package c213.dosaoopproject.esha.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class u5_G4_SponsorshipRequest
{
    @FXML private TextField eventNameField;
    @FXML private TextField sponsorshipTypeField;
    @FXML private TextField sponsorOrgNameField;
    @FXML private TextArea expectedSupportArea;
    @FXML private Label statusLabel;

    private int executiveId;
    @FXML
    private Button generateProposalBtn;
    @FXML
    private Button submitSponsorshipBtn;

    public void setExecutiveId(int executiveId) {
        this.executiveId = executiveId;
    }

    private boolean validateFields() {
        if (eventNameField.getText().isBlank() || sponsorshipTypeField.getText().isBlank()
                || sponsorOrgNameField.getText().isBlank()) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Event name, sponsorship type, and sponsor org are required.");
            return false;
        }
        return true;
    }
    @FXML
    private void handleGenerateProposal(ActionEvent event) {
        if (!validateFields()) return;
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Proposal generated — review before submitting.");
    }
    @FXML
    private void handleSubmitSponsorship(ActionEvent event) {
        if (!validateFields()) return;
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Sponsorship request submitted.");


    }}