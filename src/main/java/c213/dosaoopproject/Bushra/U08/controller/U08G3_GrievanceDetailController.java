package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.Grievance;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.util.ValidationUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class U08G3_GrievanceDetailController {

    @FXML
    private Label grievanceBodyLabel;
    @FXML
    private Label suggestedUnitLabel;
    @FXML
    private TextArea resolutionSummaryTextF;
    @FXML
    private Label validationWarningLabel;
    @FXML
    private ComboBox<String> actionComboB;
    @FXML
    private VBox inlineForwardVBox;
    @FXML
    private VBox inlineDismissalVBox;
    @FXML
    private TextArea dismissalReasonTextF;
    @FXML
    private ListView<String> evidenceListV;



    private Grievance grievance;
    private final String FILE_PATH = "grievances_data.dat";
    @FXML
    private ComboBox forwardUnitComboB;

    @FXML
    public void initialize() {

        actionComboB.getItems().addAll("Acknowledge", "Forward", "Dismiss");

        grievance = U08G3_GrievanceManagementController.getSelectedGrievance();

        if (grievance != null) {
            grievanceBodyLabel.setText(grievance.getComplaintBody());
            suggestedUnitLabel.setText(grievance.getSuggestedUnit());

            if (grievance.getEvidenceFiles() != null){
                evidenceListV.getItems().add(grievance.getEvidenceFiles());
            }
        }
    }


    @FXML
    private void closeGrievanceOA(ActionEvent event) {

        String action = actionComboB.getValue();

        // No action selected
        if (action == null || action.isEmpty()) {

            AlertUtil.showWarning(
                    "Missing Action",
                    "Please select an action."
            );

            return;
        }


        // Dismiss
        if (action.equals("Dismiss")) {

            String reason =
                    dismissalReasonTextF.getText();

            if (ValidationUtil.isEmpty(reason)) {

                AlertUtil.showError(
                        "Missing Reason",
                        "Please enter a dismissal reason."
                );

                return;
            }

            grievance.setDismissalReason(reason);
        }


        // Save action
        grievance.setActionTaken(action);

        grievance.setResolutionSummary(
                resolutionSummaryTextF.getText()
        );

        grievance.setStatus("Closed");


        // Save to file
        saveGrievance();


        // Success message
        AlertUtil.showSuccess(
                "Success",
                "Grievance updated successfully."
        );


        // Go back
        backToGrievances(event);
    }


    private void saveGrievance() {
        List<Object> list = BinaryFileUtil.readObjects("grievances_data.dat");

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Grievance) {
                Grievance g = (Grievance) list.get(i);
                if (g.getGrievanceId().equals(grievance.getGrievanceId())) {list.set(i, grievance);
                    break;
                }
            }
        }

        BinaryFileUtil.writeObjects("grievances_data.dat", list);
    }


    private void backToGrievances(ActionEvent event) {

        AnchorPane contentArea = (AnchorPane) ((Button) event.getSource()).getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G3_grievanceManagement.fxml");
    }

    @FXML
    public void backOA(ActionEvent event) {
        Button button = (Button) event.getSource();
        AnchorPane contentArea = (AnchorPane) button.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G3_grievanceManagement.fxml");
    }
}