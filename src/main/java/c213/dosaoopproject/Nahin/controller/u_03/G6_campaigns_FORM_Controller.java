package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.model.u_03.CampaignData;
import c213.dosaoopproject.Nahin.model.u_03.CampaignRegister;
import c213.dosaoopproject.Nahin.utility.FileManager;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.IdGenerator.generateRegistrationId;
import static c213.dosaoopproject.Nahin.utility.ToShowAlert.showAlert;
import static c213.dosaoopproject.Nahin.utility.ToShowAlert.showWaitAlert;

public class G6_campaigns_FORM_Controller
{
    @javafx.fxml.FXML
    private TextField userIdTF;
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private Label registerIDLBL;
    @javafx.fxml.FXML
    private TextField userNameTF;
    @javafx.fxml.FXML
    private Label campaignNameLBL;
    @javafx.fxml.FXML
    private Label dateLBL;
    @javafx.fxml.FXML
    private TextArea notesTXTAR;
    @javafx.fxml.FXML
    private TextField mailTF;
    @javafx.fxml.FXML
    private Label campaignLocationLBL;

    @javafx.fxml.FXML
    public void initialize() {
        registerIDLBL.setText(Integer.toString(generateRegistrationId()));
    }

    @javafx.fxml.FXML
    public void cancelButtonOA(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @javafx.fxml.FXML
    public void submitButtonOA(ActionEvent actionEvent) {

        //Check empty field
        if(userIdTF.getText().isEmpty() || userNameTF.getText().isEmpty() ||
        mailTF.getText().isEmpty() || phoneTF.getText().isEmpty()){
            showWaitAlert(Alert.AlertType.ERROR,"Please fill up all required field");
            return;
        }

        //object create
        CampaignRegister campaignRegister = new CampaignRegister(
                Integer.parseInt(registerIDLBL.getText()),
                userIdTF.getText(),
                phoneTF.getText(),
                userNameTF.getText(),
                mailTF.getText(), LocalDate.now() , campaignNameLBL.getText(),
                LocalDate.parse(dateLBL.getText()),notesTXTAR.getText(),campaignLocationLBL.getText()
        );

        //show validation msg
        if (!campaignRegister.validateRegistration()){
            showAlert(Alert.AlertType.WARNING, "Invalid registered information");
            return;
        }

        //read stored file & add
        ArrayList<CampaignRegister> list = (ArrayList<CampaignRegister>) FileManager.readFile("campaignRegister.bin");

        if(list==null){
            list= new ArrayList<>();
        }
        list.add(campaignRegister);

        //add new data
        FileManager.writeFile("campaignRegister.bin", list);
            showAlert(Alert.AlertType.INFORMATION, "Request Submitted Successfully");
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();

    }

    //method to access data from given list of upcoming campaign table
    public void setCampaignData(CampaignData campaignData) {

        campaignNameLBL.setText(campaignData.getCampaignName());
        dateLBL.setText(String.valueOf(campaignData.getDate()));
        campaignLocationLBL.setText(campaignData.getLocation());
    }
}