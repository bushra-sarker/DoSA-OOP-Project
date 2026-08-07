package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.model.u_03.LeaveApplication;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.FileManager.writeFile;
import static c213.dosaoopproject.Nahin.utility.VIA.generateRegistrationId;
import static c213.dosaoopproject.Nahin.utility.VIA.showAlert;

public class G5_leave_request_FORM_Controller
{
    @javafx.fxml.FXML
    private TextField userIdTF;
    @javafx.fxml.FXML
    private TextField activityNameTF;
    @javafx.fxml.FXML
    private TextArea detailsTXTAR;
    @javafx.fxml.FXML
    private ComboBox<String> leaveReasonCOMBO;
    @javafx.fxml.FXML
    private Label applicationIDLBL;
    @javafx.fxml.FXML
    private Label applicationDATElbl;

    @javafx.fxml.FXML
    public void initialize() {
        //set applicationID
        applicationIDLBL.setText(Integer.toString(generateRegistrationId()));
        applicationDATElbl.setText(String.valueOf(LocalDate.now()));

        leaveReasonCOMBO.getItems().addAll("Sick Leave","Personal Problem","Family Emergency","Academic Conflict","Other");
    }

    @javafx.fxml.FXML
    public void cancelButtonOA(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @javafx.fxml.FXML
    public void submitButtonOA(ActionEvent actionEvent) {
        //check if empty field
        if(applicationIDLBL.getText().isEmpty() || userIdTF.getText().isEmpty() || activityNameTF.getText().isEmpty() || detailsTXTAR.getText().isEmpty() ||
                applicationDATElbl.getText() == null || leaveReasonCOMBO.getValue() == null){
           showAlert(Alert.AlertType.WARNING, "Please fill up all required field");
           return;
        }

        LeaveApplication application = new LeaveApplication(
                userIdTF.getText(),
                Integer.parseInt(applicationIDLBL.getText()),
                activityNameTF.getText(),
                detailsTXTAR.getText(),
                leaveReasonCOMBO.getValue(),
                LocalDate.now()
        );

        if(!application.validateRequestInfo()){
            showAlert(Alert.AlertType.ERROR,"Invalid Information");
            return;
        }

        //read old data & add
        ArrayList<LeaveApplication> applicationsList = readFile("LeaveRequests.bin");
        if(applicationsList==null){
            applicationsList=new ArrayList<>();
        }
        applicationsList.add(application);

        //write new data
        writeFile("LeaveRequests.bin", applicationsList);

        showAlert(Alert.AlertType.CONFIRMATION,"Submitted Successfully");

        userIdTF.clear();activityNameTF.clear();detailsTXTAR.clear();leaveReasonCOMBO.setValue(null);applicationDATElbl.setText(null);applicationIDLBL.setText(null);
        //window close
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

    }
}