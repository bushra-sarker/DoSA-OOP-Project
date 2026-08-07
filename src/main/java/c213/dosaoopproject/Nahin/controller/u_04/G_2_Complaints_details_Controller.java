package c213.dosaoopproject.Nahin.controller.u_04;

import c213.dosaoopproject.Nahin.model.u_03.ReportConcerns;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import static c213.dosaoopproject.Nahin.model.u_03.ReportConcerns.updateStatus;
import static c213.dosaoopproject.Nahin.utility.VIA.showAlert;

public class G_2_Complaints_details_Controller
{
    @javafx.fxml.FXML
    private Label userIDLBL;
    @javafx.fxml.FXML
    private Label categoryLBL;
    @javafx.fxml.FXML
    private Label descriptionLBL;
    @javafx.fxml.FXML
    private Label complaintDate;
    @javafx.fxml.FXML
    private Label incidentDateLBL;
    @javafx.fxml.FXML
    private ComboBox<String> decisionSelection;
    @javafx.fxml.FXML
    private Label complainIDLBL;
    private ReportConcerns selected;


    @javafx.fxml.FXML
    public void initialize() {
        decisionSelection.getItems().addAll("Under Review","In Progress","Solved");
    }



    @javafx.fxml.FXML
    public void statusUpdateOA(ActionEvent actionEvent) {
        String selectStatus = decisionSelection.getValue();

        // status updating
        if(selectStatus==null) {
            showAlert(Alert.AlertType.ERROR, "Please select your decision first");
            return;
        }


        boolean updated = updateStatus("VolunteerIssuereports.bin",selected.getComplaintID(),selectStatus);
        if(!updated){
            updateStatus("StudentIssueReports.bin",selected.getComplaintID(),selectStatus);
        }
        if(updated){
            showAlert(Alert.AlertType.INFORMATION,"Status updated successfully");
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }else{
            showAlert(Alert.AlertType.ERROR, "could not find that complaint");
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    }



    @javafx.fxml.FXML
    public void cancelOA(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }



    public void receiveData(ReportConcerns selectedRecord){
        this.selected = selectedRecord;

        complainIDLBL.setText(String.valueOf(selectedRecord.getComplaintID()));
        categoryLBL.setText(selectedRecord.getCategory());
        complaintDate.setText(String.valueOf(selectedRecord.getDate()));
        descriptionLBL.setText(selectedRecord.getComplaintDetails());
        incidentDateLBL.setText(String.valueOf(selectedRecord.getIncidentDate()));
        userIDLBL.setText(String.valueOf(selectedRecord.getUserID()));
    }
}