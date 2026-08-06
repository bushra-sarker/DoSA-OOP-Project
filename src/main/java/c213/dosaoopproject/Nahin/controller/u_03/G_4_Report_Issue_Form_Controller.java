package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.model.u_03.ReportConcerns;
import c213.dosaoopproject.Nahin.utility.FileManager;
import c213.dosaoopproject.Nahin.utility.VIA;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.VIA.generateRegistrationId;

public class G_4_Report_Issue_Form_Controller
{
    @javafx.fxml.FXML
    private Label complaintIDlbl;
    @javafx.fxml.FXML
    private TextField userIDTF;
    @javafx.fxml.FXML
    private TextField eventnameTF;
    @javafx.fxml.FXML
    private ComboBox<String> categoryCOMBO;
    @javafx.fxml.FXML
    private TextArea detailsTXTAR;
    @javafx.fxml.FXML
    private DatePicker incidentDateDP;
    @javafx.fxml.FXML
    private SideMenuBar_Controller nullController;

    @javafx.fxml.FXML
    public void initialize() {
        complaintIDlbl.setText(Integer.toString(generateRegistrationId()));
        categoryCOMBO.getItems().addAll("Disruptive Behaviour","Lack of cooperation","Non-Compliance","Verbal Abuse","Conflict with peers");
    }

    @javafx.fxml.FXML
    public void backFRONTOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G4_issueReporting_view.fxml");
    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) {

        //check if any field is empty
        if(complaintIDlbl.getText().isEmpty() || userIDTF.getText().isEmpty() || eventnameTF.getText().isEmpty() ||
                categoryCOMBO.getValue()== null || detailsTXTAR.getText().isEmpty() || incidentDateDP.getValue().equals(null)){
            VIA.showAlert(Alert.AlertType.ERROR, "Fill up all required field");
            return;
        }

        ReportConcerns reportConcerns = new ReportConcerns(
                userIDTF.getText(),
                Integer.parseInt(complaintIDlbl.getText()),
                eventnameTF.getText(), categoryCOMBO.getValue(), detailsTXTAR.getText(),
                LocalDate.now(),incidentDateDP.getValue()
        );

        //Validate all info from model class method
        if(!reportConcerns.validateInfo()){
            VIA.showAlert(Alert.AlertType.WARNING, "Invalid Information");
            return;
        }

        //if success add to file
        ArrayList<ReportConcerns> reportList =readFile("Volunteer_Issue_reports.bin");
        if(reportList==null){
            reportList = new ArrayList<>();
        }
        reportList.add(reportConcerns);

        FileManager.writeFile("Volunteer_Issue_reports.bin",reportList);
        VIA.showAlert(Alert.AlertType.CONFIRMATION,"Report Submitted Successfully");


        //clear all field
        complaintIDlbl.setText(null);userIDTF.clear();eventnameTF.clear();detailsTXTAR.clear();eventnameTF.clear();categoryCOMBO.setValue(null);incidentDateDP.setValue(null);

        //return to the previous screen
        try {
            navigate(actionEvent, "/Nahin/fxmlView/u3G4_issueReporting_view.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}