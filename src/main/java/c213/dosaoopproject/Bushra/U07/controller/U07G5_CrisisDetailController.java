package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.CrisisInterventionRecord;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.util.ValidationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class U07G5_CrisisDetailController
{
    @javafx.fxml.FXML
    private Label lblUrgency;
    @javafx.fxml.FXML
    private Label lblStudentId;
    @javafx.fxml.FXML
    private Label lblCaseId;
    @javafx.fxml.FXML
    private TextArea txtInterventionLog;
    @javafx.fxml.FXML
    private TextField txtResponseTeam;
    @javafx.fxml.FXML
    private Button markResolvedButton;
    @javafx.fxml.FXML
    private Label statusLabel;

    private CrisisInterventionRecord currentRecord;

    @FXML
    public void initialize() {
        currentRecord = U07G5_CrisisDashboardController.getSelectedRecord();

        if (currentRecord != null) {
            lblCaseId.setText(currentRecord.getCaseId());
            lblStudentId.setText(currentRecord.getStudentId());
            lblUrgency.setText(currentRecord.getUrgencyLevel());
            txtResponseTeam.setText(currentRecord.getResponseTeam());
            txtInterventionLog.setText(currentRecord.getInterventionLog());
            statusLabel.setText(currentRecord.getStatus());
        }
    }

    @FXML
    private void handleMarkResolved(ActionEvent event) {
        // Step 6: Validate input
        if (ValidationUtil.isEmpty(txtResponseTeam.getText()) || ValidationUtil.isEmpty(txtInterventionLog.getText())) {

            AlertUtil.showError("Validation Error", "Response team and intervention log cannot be empty.");
            return;
        }

        // Step 7: Read existing records with null safety check
        ArrayList<Object> records = BinaryFileUtil.readObjects("crisis_records.dat");

        if (records == null) {
            AlertUtil.showError("File Error", "Unable to load crisis records.");
            return;
        }

        // Find and update selected record
        for (Object obj : records) {
            if (obj instanceof CrisisInterventionRecord) {
                CrisisInterventionRecord rec = (CrisisInterventionRecord) obj;
                if (rec.getCaseId().equals(currentRecord.getCaseId())) {
                    rec.setResponseTeam(txtResponseTeam.getText());
                    rec.setInterventionLog(txtInterventionLog.getText());
                    rec.setStatus("RESOLVED");
                    break;
                }
            }
        }

        // Persist updated list back to file
        BinaryFileUtil.writeObjects("crisis_records.dat", records);

        // Step 8: Alert & refresh dashboard
        AlertUtil.showSuccess("Success", "Crisis case marked as resolved and saved.");

        AnchorPane contentArea = (AnchorPane) txtInterventionLog.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G5_crisisDashboard.fxml");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        AnchorPane contentArea = (AnchorPane) txtInterventionLog.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G5_crisisDashboard.fxml");
    }
}