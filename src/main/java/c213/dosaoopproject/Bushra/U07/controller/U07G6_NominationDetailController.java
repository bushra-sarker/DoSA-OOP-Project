package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.ExchangeNomination;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.data.TextFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class U07G6_NominationDetailController
{
    @javafx.fxml.FXML
    private ComboBox<String> cmbAction;
    @javafx.fxml.FXML
    private Label lblStudentName;
    @javafx.fxml.FXML
    private Label lblCgpa;
    @javafx.fxml.FXML
    private TextArea txtComments;
    @javafx.fxml.FXML
    private Label lblHostUniversity;
    @javafx.fxml.FXML
    private Button confirmDecisionButton;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private Label lblNominationId;


    private ExchangeNomination currentNomination;
    private static final String FILE_PATH = "exchange_data.dat";

    @FXML
    public void initialize() {
        cmbAction.setItems(FXCollections.observableArrayList("Approve", "Request Info", "Reject"));

        currentNomination = U07G6_ExchangeProgramsListController.getSelectedNomination();

        if (currentNomination != null) {
            lblNominationId.setText(currentNomination.getNominationId());
            lblStudentName.setText(currentNomination.getStudentName());
            lblHostUniversity.setText(currentNomination.getHostUniversity());
            lblCgpa.setText(String.valueOf(currentNomination.getCgpa()));
            statusLabel.setText(currentNomination.getStatus());
            txtComments.setText(currentNomination.getComments());
        }
    }

    @FXML
    private void handleConfirmDecision(ActionEvent event) {
        // Step 6: Validate
        if (ValidationUtil.isComboUnselected(cmbAction)) {
            AlertUtil.showError("Validation Error", "Please select an action (Approve / Request Info / Reject).");
            return;
        }

        String selectedAction = cmbAction.getValue();

        if (!selectedAction.equals("Approve") && ValidationUtil.isEmpty(txtComments.getText())) {
            AlertUtil.showError("Validation Error", "Please provide a reason or comment for Request Info or Reject.");
            return;
        }

        // Step 7: Read binary data
        ArrayList<Object> nominations = BinaryFileUtil.readObjects(FILE_PATH);

        if (nominations == null) {
            AlertUtil.showError("File Error", "Unable to load exchange nominations.");
            return;
        }

        // Find and update matching nomination
        for (Object obj : nominations) {
            if (obj instanceof ExchangeNomination) {
                ExchangeNomination nomination = (ExchangeNomination) obj;

                if (nomination.getNominationId().equals(currentNomination.getNominationId())) {
                    if (selectedAction.equals("Approve")) {
                        nomination.setStatus("Approved");
                    } else {
                        nomination.setStatus(selectedAction);
                    }

                    nomination.setComments(txtComments.getText().isEmpty() ? "N/A" : txtComments.getText());
                    break;
                }
            }
        }

        // Save binary records back to file
        BinaryFileUtil.writeObjects(FILE_PATH, nominations);

        // Step 8: Alert & Refresh
        AlertUtil.showSuccess("Success", "Nomination decision saved successfully.");

        AnchorPane contentArea = (AnchorPane) txtComments.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G6_exchangeProgramsList.fxml");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        AnchorPane contentArea = (AnchorPane) txtComments.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G6_exchangeProgramsList.fxml");
    }
}