package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.DisciplinaryAppeal;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class U07G3_AppealDetailController {

    @FXML private Label studentIDLabel;
    @FXML private Label offenseTypeLabel;
    @FXML private Label incidentDateLabel;
    @FXML private Label originalPenaltyLabel;
    @FXML private Label incidentReportLabel;
    @FXML private Label lateSubmissionWarningLabel;
    @FXML private TextArea appealStatementTextArea;
    @FXML private TextArea decisionRationaleTextF;
    @FXML private RadioButton upholdPenaltyRadio;
    @FXML private RadioButton reducePenaltyRadio;
    @FXML private RadioButton dismissChargesRadio;
    @FXML private ToggleGroup decisionToggleGroup;
    @FXML private ComboBox<String> newPenaltyComboBox;
    @FXML private VBox lateSubmissionWarningVBox;
    @FXML private Button finalizeDecisionButton;

    private static final String FILE_PATH = "student_cases.dat";
    private DisciplinaryAppeal appeal;

    @FXML
    public void initialize() {
        // Ensure student_cases.dat file exists
        ensureDatFileExists();

        // Retrieve selected appeal from list controller
        appeal = U07G3_appealsListController.getSelectedAppeal();

        if (appeal != null) {
            studentIDLabel.setText(appeal.getStudentId());
            offenseTypeLabel.setText(appeal.getOffense());
            incidentDateLabel.setText(appeal.getIncidentDate().toString());
            originalPenaltyLabel.setText(appeal.getOriginalPenalty());
            incidentReportLabel.setText(appeal.getIncidentReport());
            appealStatementTextArea.setText(appeal.getAppealStatement());

            // Check if submission is late (> 7 days post-incident)
            long daysBetween = ChronoUnit.DAYS.between(appeal.getIncidentDate(), appeal.getSubmissionDate());
            if (daysBetween > 7) {
                lateSubmissionWarningVBox.setVisible(true);
                lateSubmissionWarningVBox.setManaged(true);
            }
        }

        // Enable combo box only when "Reduce Penalty" radio button is selected
        if (reducePenaltyRadio != null && newPenaltyComboBox != null) {
            reducePenaltyRadio.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                newPenaltyComboBox.setDisable(!isSelected);
            });
        }
    }

    @FXML
    public void finalizeDecisionOA(ActionEvent event) {
        if (appeal == null) {
            AlertUtil.showWarning("Warning", "No appeal record selected.");
            return;
        }

        // Validate decision rationale length (Minimum 50 characters)
        String rationale = decisionRationaleTextF.getText() != null ? decisionRationaleTextF.getText().trim() : "";
        if (rationale.length() < 50) {
            AlertUtil.showWarning("Validation Error", "Decision rationale must be at least 50 characters long.");
            return;
        }

        // Determine decision type
        String decision = "Uphold";
        if (reducePenaltyRadio.isSelected()) {
            decision = "Reduce";
        } else if (dismissChargesRadio.isSelected()) {
            decision = "Dismiss";
        }

        // Update object properties
        appeal.setDecision(decision);
        appeal.setDecisionRationale(rationale);
        appeal.setStatus("Reviewed");
        if (reducePenaltyRadio.isSelected() && newPenaltyComboBox.getValue() != null) {
            appeal.setNewPenalty(newPenaltyComboBox.getValue());
        }

        // Save updated list to binary file
        updateAppealInBinaryFile(appeal);

        AlertUtil.showSuccess("Success", "Appeal decision finalized successfully.");
        backOA(event);
    }

    private void updateAppealInBinaryFile(DisciplinaryAppeal updatedAppeal) {
        ArrayList<Object> list = BinaryFileUtil.readObjects(FILE_PATH);
        if (list == null) {
            list = new ArrayList<>();
        }

        for (Object obj : list) {
            if (obj instanceof DisciplinaryAppeal) {
                DisciplinaryAppeal item = (DisciplinaryAppeal) obj;
                if (item.getAppealId().equals(updatedAppeal.getAppealId())) {
                    list.set(list.indexOf(obj), updatedAppeal);
                    break;
                }
            }
        }
        BinaryFileUtil.writeObjects(FILE_PATH, list);
    }

    private void ensureDatFileExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            ArrayList<Object> sampleList = new ArrayList<>();
            sampleList.add(new DisciplinaryAppeal(
                    "APP-101", "221001", "Cheating in Exam", "1 Semester Suspension",
                    LocalDate.now().minusDays(10), LocalDate.now(),
                    "Found with unauthorized notes in exam.", "Requesting leniency for first offense."
            ));
            sampleList.add(new DisciplinaryAppeal(
                    "APP-102", "221002", "Plagiarism", "F Grade in Course",
                    LocalDate.now().minusDays(3), LocalDate.now(),
                    "Uncited code in final project.", "Misunderstood citation rules."
            ));
            BinaryFileUtil.writeObjects(FILE_PATH, sampleList);
        }
    }

    @FXML
    public void backOA(ActionEvent event) {
        AnchorPane contentArea = (AnchorPane) ((Node) event.getSource()).getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G3_appealsList.fxml");
    }
}