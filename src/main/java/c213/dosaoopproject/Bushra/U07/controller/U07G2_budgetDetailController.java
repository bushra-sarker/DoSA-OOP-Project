package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.ClubBudget;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.data.TextFileUtil;
import c213.dosaoopproject.commonClass.util.ValidationUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class U07G2_budgetDetailController {

    @FXML private TextField allocatedBudgetTextF;
    @FXML private TextArea remarksTextArea;
    @FXML private Button confirmBudgetButton;

    private static final String FILE_PATH = "club_budgets.txt";
    private ClubBudget currentBudget;

    @FXML
    public void initialize() {
        currentBudget = U07G2_budgetManagementController.getSelectedBudget();
        if (currentBudget != null) {
            allocatedBudgetTextF.setText(String.valueOf(currentBudget.getAllocatedAmount()));
            remarksTextArea.setText(currentBudget.getRemarks());
        }
    }

    // Easy & Clean confirmBudgetOA
    @FXML
    public void confirmBudgetOA(ActionEvent event) {
        String inputAmount = allocatedBudgetTextF.getText().trim();
        String remarks = remarksTextArea.getText().trim();

        // Check if input is a valid number
        if (!ValidationUtil.isNumeric(inputAmount)) {
            AlertUtil.showError("Error", "Please enter a valid amount.");
            return;
        }

        // Update model object
        currentBudget.setAllocatedAmount(Double.parseDouble(inputAmount));
        currentBudget.setRemarks(remarks);
        currentBudget.setStatus("Approved");

        // Read file lines
        ArrayList<String> lines = TextFileUtil.readLines(FILE_PATH);

        // Replace matching line
        if (lines != null) {
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith(currentBudget.getClubName())) {
                    lines.set(i, currentBudget.toFileLine());
                    break;
                }
            }
            // Save updated list
            TextFileUtil.overwriteFile(FILE_PATH, lines);
        }

        AlertUtil.showSuccess("Success", "Budget updated successfully.");
        backToManagementOA(event);
    }

    @FXML
    public void backToManagementOA(ActionEvent event) {
        AnchorPane contentArea = (AnchorPane) ((Node) event.getSource()).getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G2_budgetManagement.fxml");
    }
}