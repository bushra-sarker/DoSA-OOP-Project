package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.data.TextFileUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class U08G1_scholarshipReviewDetailController {

    @FXML private RadioButton recommendedRadio;
    @FXML private Label studentProfileLabel;
    @FXML private Label calculatedEligibilityLabel;
    @FXML private TextArea reviewNotesTextF;
    @FXML private Label transcriptSummaryLabel;
    @FXML private Button submitAssessmentButton;
    @FXML private RadioButton notEligibleRadio;
    @FXML private VBox inlineReviewNotesVBox;
    @FXML private Label cgpaLabel;
    @FXML private Label familyIncomeLabel;
    @FXML private ToggleGroup decisionGroup;
    @FXML private RadioButton needMoreInfoRadio;
    @FXML private Label eligibilityWarningLabel;

    @FXML
    public void initialize() {
        // Set basic values directly for display
        studentProfileLabel.setText("Rahim Ahmed (21301001)");
        cgpaLabel.setText("3.85");
        familyIncomeLabel.setText("BDT 45,000");
        transcriptSummaryLabel.setText("Excellent academic standing.");
        calculatedEligibilityLabel.setText("Eligible for Scheme");

        // Hide warning by default
        eligibilityWarningLabel.setVisible(false);
    }

    @FXML
    public void submitAssessmentOA(ActionEvent actionEvent) {
        String notes = reviewNotesTextF.getText();

        // Check if user selected "Not Eligible" or "Need More Info" without notes
        if ((notEligibleRadio.isSelected() || needMoreInfoRadio.isSelected())
                && reviewNotesTextF.getText().trim().isEmpty()) {
            AlertUtil.showError("Error", "Please write a reason in the review notes!");
            return;
        }

        // Save entry to action_reports.txt
        TextFileUtil.appendLine("action_reports.txt", "Scholarship Reviewed for Rahim Ahmed - Notes: " + notes);

        AlertUtil.showSuccess("Success", "Assessment Submitted Successfully!");

        backOA(actionEvent);
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        AnchorPane contentArea = (AnchorPane) studentProfileLabel.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G1_scholarshipManagement.fxml");
    }
}