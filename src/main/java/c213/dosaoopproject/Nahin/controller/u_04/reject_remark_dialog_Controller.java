package c213.dosaoopproject.Nahin.controller.u_04;

import c213.dosaoopproject.Nahin.model.u_04.NewClubRegister;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.FileManager.writeFile;
import static c213.dosaoopproject.Nahin.utility.VIA.showAlert;

public class reject_remark_dialog_Controller
{
    @javafx.fxml.FXML
    private TextArea remarksTXTAR;

    @javafx.fxml.FXML
    public void initialize() {
    }
    private NewClubRegister clubApplication;
    @javafx.fxml.FXML
    public void cancelRejectionOA(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @javafx.fxml.FXML
    public void confirmationOFrejectionOA(ActionEvent actionEvent) {
        String remarks = remarksTXTAR.getText();

        boolean rejectStatus = clubApplication.markRejected(remarks);
        if(!rejectStatus){
            showAlert(Alert.AlertType.WARNING,"Remarks field must not be empty");
            return;
        }

        //created rejected club proposal file
        ArrayList<NewClubRegister> rejectedClubList = readFile("NewClubApplications.bin");
        for(NewClubRegister lst: rejectedClubList){
            if(lst.getApplicationID()==clubApplication.getApplicationID()){
                lst.markRejected(remarks);
            }
        }
        writeFile("NewClubApplications.bin",rejectedClubList);

        showAlert(Alert.AlertType.INFORMATION,"Rejection Successful");

        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void receiveApplication(NewClubRegister application){
        this.clubApplication =application;
    }
}