package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.EmergencyRequest;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.data.TextFileUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class U08G2_requestDetailController {

    @FXML private Label studentNameLabel;
    @FXML private Label studentIdLabel;
    @FXML private Label departmentLabel;
    @FXML private Label emergencyCategoryLabel;
    @FXML private Label requestedAmountLabel;
    @FXML private Label deptVerificationStatusLabel;
    @FXML private Label departmentVerificationLabel;
    @FXML private Label proofStatusLabel;
    @FXML private Label availableFundBalanceLabel;
    @FXML private Label validationStatusLabel;
    @FXML private Label missingDocWarningLabel;
    @FXML private Label exceedsBalanceWarningLabel;

    @FXML private TextArea emergencyStatementTextArea;
    @FXML private ListView<String> attachedDocsListV;
    @FXML private TextField approvedAmountTextF;
    @FXML private TextField accountNumberTextF;
    @FXML private RadioButton bankTransferRadio;
    @FXML private RadioButton cashDisbursementRadio;
    @FXML private ToggleGroup paymentMethodGroup;
    @FXML private Button approveDisbursementButton;
    @FXML private Button verifyDeptButton;


    private static final String FILE_PATH = "emergency_fund.txt";
    private EmergencyRequest currentRequest;

    @FXML
    public void initialize() {
        currentRequest = U08G2_emergencyFundDashboardController.getSelectedRequest();

        if (currentRequest != null) {
            studentNameLabel.setText(currentRequest.getStudentName());
            studentIdLabel.setText(currentRequest.getStudentId());
            departmentLabel.setText(currentRequest.getDepartment());
            emergencyCategoryLabel.setText(currentRequest.getCategory());
            requestedAmountLabel.setText("BDT " + currentRequest.getRequestedAmount());
            emergencyStatementTextArea.setText(currentRequest.getStatement());
            approvedAmountTextF.setText(String.valueOf(currentRequest.getRequestedAmount()));

            deptVerificationStatusLabel.setText(currentRequest.getDeptVerificationStatus());
            departmentVerificationLabel.setText(currentRequest.getDeptVerificationStatus());

            if (currentRequest.getDocuments() != null && !currentRequest.getDocuments().isEmpty()) {
                attachedDocsListV.getItems().addAll(currentRequest.getDocuments().split(","));
            }
        }

        availableFundBalanceLabel.setText("BDT " + U08G2_emergencyFundDashboardController.getAvailableBalance());
    }

    @FXML
    public void verifyDeptOA(ActionEvent event) {
        if (currentRequest != null) {
            currentRequest.setDeptVerificationStatus("Verified");
            deptVerificationStatusLabel.setText("Verified");
            departmentVerificationLabel.setText("Verified");
            AlertUtil.showInformation("Verified", "Department status updated.");
        }
    }

    @FXML
    public void approveDisbursementOA(ActionEvent event) {
        try {
            double approvedAmt = Double.parseDouble(approvedAmountTextF.getText().trim());
            double currentBalance = U08G2_emergencyFundDashboardController.getAvailableBalance();

            if (approvedAmt > currentBalance) {
                AlertUtil.showError("Error", "Approved amount exceeds available balance.");
                return;
            }

            // Update balance and request status
            U08G2_emergencyFundDashboardController.setAvailableBalance(currentBalance - approvedAmt);
            currentRequest.setApprovedAmount(approvedAmt);
            currentRequest.setStatus("Approved");

            saveData();
            AlertUtil.showSuccess("Success", "Disbursement approved successfully.");
            backOA(event);

        } catch (NumberFormatException e) {
            AlertUtil.showWarning("Invalid Input", "Please enter a valid amount.");
        }
    }

    private void saveData() {
        List<String> lines = TextFileUtil.readLines(FILE_PATH);
        List<String> updatedLines = new ArrayList<>();

        updatedLines.add("BALANCE;" + U08G2_emergencyFundDashboardController.getAvailableBalance());

        for (String line : lines) {
            if (line.startsWith("BALANCE;")) continue;
            String[] parts = line.split(";");
            if (parts.length >= 11 && parts[0].trim().equalsIgnoreCase(currentRequest.getRequestId())) {
                updatedLines.add(currentRequest.toFileFormat());
            } else {
                updatedLines.add(line);
            }
        }
        TextFileUtil.overwriteFile(FILE_PATH, updatedLines);
    }

    @FXML
    public void backOA(ActionEvent event) {
        AnchorPane contentArea = (AnchorPane) ((Button) event.getSource()).getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G2_emergencyFundDashboard.fxml");
    }
}