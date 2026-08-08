package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.InsuranceClaim;
import c213.dosaoopproject.commonClass.data.TextFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.util.ValidationUtil;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class U08G4_ClaimDetailController {

    @FXML
    private Label claimIdLabel;

    @FXML
    private Label studentIdLabel;

    @FXML
    private Label studentNameLabel;

    @FXML
    private Label hospitalNameLabel;

    @FXML
    private Label claimedAmountLabel;

    @FXML
    private Label submissionDateLabel;

    @FXML
    private Label claimStatusLabel;

    @FXML
    private ListView<String> expensesListV;

    @FXML
    private CheckBox dischargeSummaryCheckB;

    @FXML
    private CheckBox hospitalBillsCheckB;

    @FXML
    private Label missingDocWarningLabel;

    @FXML
    private ComboBox<String> claimActionComboB;

    @FXML
    private VBox inlineActionNoteVBox;

    @FXML
    private TextArea actionNoteTextF;


    private InsuranceClaim claim;
    private final String FILE_PATH = "insurance_claims.TXT";

    @FXML
    public void initialize() {

        claimActionComboB.setItems(FXCollections.observableArrayList("Forward", "Request Docs", "Reject"));

        claim = U08G4_InsuranceClaimsListController.getSelectedClaim();

        if (claim != null) {

            claimIdLabel.setText(claim.getClaimId());
            studentIdLabel.setText(claim.getStudentId());
            studentNameLabel.setText(claim.getStudentName());
            hospitalNameLabel.setText(claim.getHospitalName());
            claimedAmountLabel.setText(claim.getClaimedAmount() + " BDT");
            submissionDateLabel.setText(claim.getSubmissionDate());
            claimStatusLabel.setText(claim.getStatus());

            // Expenses
            String[] expenses = claim.getExpenses().split(";");
            expensesListV.setItems(FXCollections.observableArrayList(expenses));

            // Documents
            dischargeSummaryCheckB.setSelected(claim.isDischargeSummary());
            hospitalBillsCheckB.setSelected(claim.isHospitalBills());
        }


        claimActionComboB.setOnAction(e -> {
            String action = claimActionComboB.getValue();

            if (action != null) {
                inlineActionNoteVBox.setManaged(true);
                inlineActionNoteVBox.setVisible(true);
            }
        });
    }


    @FXML
    public void submitClaimOA(ActionEvent event) {

        if (claim == null) {
            return;
        }

        String action = claimActionComboB.getValue();

        // No action selected
        if (action == null || action.isEmpty()) {
            AlertUtil.showWarning("Missing Action", "Please select an action.");
            return;
        }


        // Check documents for Forward
        if (action.equals("Forward")) {

            if (!dischargeSummaryCheckB.isSelected() || !hospitalBillsCheckB.isSelected()) {

                missingDocWarningLabel.setManaged(true);
                missingDocWarningLabel.setVisible(true);

                AlertUtil.showWarning("Missing Documents", "Required documents are missing.");
                return;
            }
        }


        // Reason required for Request Docs / Reject
        if (action.equals("Request Docs") || action.equals("Reject")) {

            String note = actionNoteTextF.getText();

            if (ValidationUtil.isEmpty(note)) {
                AlertUtil.showWarning("Missing Explanation", "Please enter a reason or explanation.");
                return;
            }

            claim.setActionNote(note);
        }


        // Update status
        if (action.equals("Forward")) {
            claim.setStatus("Approved");

        } else if (action.equals("Request Docs")) {
            claim.setStatus("Need Documents");

        } else if (action.equals("Reject")) {
            claim.setStatus("Rejected");
        }


        claim.setAction(action);

        // Save updated data
        saveClaim();
        AlertUtil.showSuccess("Success", "Insurance claim updated successfully.");

        // Return to claim list
        backToClaims(event);
    }


    private void saveClaim() {

        List<String> lines = TextFileUtil.readLines(FILE_PATH);

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith(claim.getClaimId() + ",")) {
                lines.set(i, claim.toTxt());
                break;
            }
        }

        TextFileUtil.overwriteFile(FILE_PATH, lines);
    }


    private void backToClaims(ActionEvent event) {
        AnchorPane contentArea = (AnchorPane) claimIdLabel.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G4_insuranceClaimsList.fxml");
    }

    @Deprecated
    public void backToOA(ActionEvent event) {
        Button button = (Button) event.getSource();
        AnchorPane contentArea = (AnchorPane) button.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G4_insuranceClaimsList.fxml");
    }

}