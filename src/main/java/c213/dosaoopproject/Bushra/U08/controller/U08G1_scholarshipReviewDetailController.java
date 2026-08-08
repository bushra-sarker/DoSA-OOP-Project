package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.ScholarshipApplication;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.data.TextFileUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

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

    private ScholarshipApplication targetApplication;
    private static final String FILE_PATH = "scholarships.dat";

    @FXML
    public void initialize() {
        // Event 4: Fetch selected student application details from dashboard
        targetApplication = U08G1_scholarshipManagementController.getSelectedApplication();

        if (targetApplication != null) {
            studentProfileLabel.setText(targetApplication.getStudentName() + " (" + targetApplication.getStudentId() + ")");
            cgpaLabel.setText(String.format("%.2f", targetApplication.getCgpa()));
            familyIncomeLabel.setText(String.format("BDT %.2f", targetApplication.getFamilyIncome()));
            transcriptSummaryLabel.setText(targetApplication.getTranscriptSummary() != null ? targetApplication.getTranscriptSummary() : "N/A");

            // Dynamic eligibility text calculation
            if (targetApplication.getCgpa() >= 3.50) {
                calculatedEligibilityLabel.setText("Eligible for Scheme");
                eligibilityWarningLabel.setVisible(false);
            } else {
                calculatedEligibilityLabel.setText("Below Preferred CGPA Standard");
                eligibilityWarningLabel.setVisible(true);
            }

            // Populate notes if existing
            if (targetApplication.getReviewNotes() != null) {
                reviewNotesTextF.setText(targetApplication.getReviewNotes());
            }
        } else {
            studentProfileLabel.setText("No Application Selected");
        }
    }

    @FXML
    public void submitAssessmentOA(ActionEvent actionEvent) {
        // Event 6: Validation checks
        if (targetApplication == null) {
            AlertUtil.showError("Error", "No scholarship application selected.");
            return;
        }

        if (decisionGroup.getSelectedToggle() == null) {
            AlertUtil.showError("Validation Error", "Please select a decision option (Recommended, Not Eligible, or Need More Info).");
            return;
        }

        String notes = reviewNotesTextF.getText() != null ? reviewNotesTextF.getText().trim() : "";

        // Enforce review notes requirement when Not Eligible or Need More Info is selected
        if ((notEligibleRadio.isSelected() || needMoreInfoRadio.isSelected()) && notes.isEmpty()) {
            AlertUtil.showError("Validation Error", "Please write a reason in the review notes for this decision.");
            return;
        }

        // Determine assessment decision status
        String newStatus = "Pending";
        if (recommendedRadio.isSelected()) {
            newStatus = "Eligible";
        } else if (notEligibleRadio.isSelected()) {
            newStatus = "Rejected";
        } else if (needMoreInfoRadio.isSelected()) {
            newStatus = "Under Review";
        }

        // Event 7: Save updated list of ScholarshipApplication objects to scholarships.dat using BinaryFileUtil
        ArrayList<Object> applications = BinaryFileUtil.readObjects(FILE_PATH);
        if (applications != null) {
            for (Object obj : applications) {
                if (obj instanceof ScholarshipApplication) {
                    ScholarshipApplication app = (ScholarshipApplication) obj;
                    if (app.getApplicationId().equals(targetApplication.getApplicationId())) {
                        app.setApplicationStatus(newStatus);
                        app.setReviewNotes(notes);
                        break;
                    }
                }
            }
            BinaryFileUtil.writeObjects(FILE_PATH, applications);
        }

        // Event 8: Display success alert & navigate back using SubViewSwitcher
        AlertUtil.showSuccess("Success", "Assessment submitted successfully!");
        backOA(actionEvent);
    }

    @FXML
    public void backOA(ActionEvent actionEvent) {
        AnchorPane contentArea = (AnchorPane) studentProfileLabel.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/view/U08G1_scholarshipManagement.fxml");
    }
}