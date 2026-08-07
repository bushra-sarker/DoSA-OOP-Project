package c213.dosaoopproject.Nahin.controller.u_04;
import c213.dosaoopproject.Nahin.model.u_04.NewClubRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.FileManager.writeFile;
import static c213.dosaoopproject.Nahin.utility.Navigation.newStage;
import static c213.dosaoopproject.Nahin.utility.VIA.showAlert;

public class G_1_clubRegistrationDETAILS_Controller
{

    @javafx.fxml.FXML
    private Label setPurposeLBL;
    @javafx.fxml.FXML
    private Label setCategoryLBL;
    @javafx.fxml.FXML
    private Label setContactLBL;
    @javafx.fxml.FXML
    private Label setDateLBL;
    @javafx.fxml.FXML
    private Label setClubNmLBL;
    @javafx.fxml.FXML
    private Label showAPPLidLBL;
    @javafx.fxml.FXML
    private Label setFounderLBL;
    private NewClubRegister selectedApplication;

    @javafx.fxml.FXML
    public void initialize() {
    }


    @javafx.fxml.FXML
    public void rejectButtonOA(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Nahin/fxmlView/remarks_dialogView.fxml"));
            Parent root = loader.load();

            reject_remark_dialog_Controller controller = loader.getController();
            controller.receiveApplication(selectedApplication);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }



    @javafx.fxml.FXML
    public void approveButtonOA(ActionEvent actionEvent) {
        //set status as approved
        selectedApplication.markApprove();

        ArrayList<NewClubRegister> approvedClubList = readFile("NewClubApplications.bin");

        //if no data, then add new empty list
        if(approvedClubList==null){
            approvedClubList = new ArrayList<>();
        }

        //to stop repetition, using loop to update specific data
        for(NewClubRegister application : approvedClubList){
            if(application.getApplicationID() == selectedApplication.getApplicationID()){
                application.markApprove();
                break;
            }
        }

        //write & update data
        writeFile("NewClubApplications.bin",approvedClubList);
        showAlert(Alert.AlertType.INFORMATION, "Approved");


        //closing stage
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }


    @javafx.fxml.FXML
    public void closeDetailsOA(ActionEvent actionEvent) {
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
    }


    //to receive passed data
    public void receiveData(NewClubRegister selectedApplicationData){
        this.selectedApplication = selectedApplicationData;

        showAPPLidLBL.setText(String.valueOf(selectedApplicationData.getApplicationID()));
        setClubNmLBL.setText(selectedApplicationData.getClubName());
        setCategoryLBL.setText(selectedApplicationData.getCategory());
        setContactLBL.setText(selectedApplicationData.getContactNumber());
        setFounderLBL.setText(selectedApplicationData.getFounderName());
        setPurposeLBL.setText(selectedApplicationData.getPurpose());
        setDateLBL.setText(String.valueOf(selectedApplicationData.getSubmissionDate()));
    }
}