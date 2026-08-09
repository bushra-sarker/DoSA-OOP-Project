package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.model.u_03.TeamJoin;
import c213.dosaoopproject.Nahin.utility.FileManager;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.writeFile;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.VIA.*;

public class G_2_Join_Team_View_Controller
{
    @javafx.fxml.FXML
    private TextField userIDTF;
    @javafx.fxml.FXML
    private RadioButton decorationRD;
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private VBox panel_2;
    @javafx.fxml.FXML
    private VBox panel_1;
    @javafx.fxml.FXML
    private RadioButton logisticRD;
    @javafx.fxml.FXML
    private RadioButton photographyRD;
    @javafx.fxml.FXML
    private TextArea experienceTXTAREA;
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private TextField mailTF;
    @javafx.fxml.FXML
    private RadioButton culturalRD;
    @javafx.fxml.FXML
    private TextArea whyChooseTXTAREA;
    @javafx.fxml.FXML
    private RadioButton eventRRD;
    @javafx.fxml.FXML
    private Label rqstIdLBL;

    //radioButton toggle
    ToggleGroup teamGroup =new ToggleGroup();


    @javafx.fxml.FXML
    public void initialize() {

        //Set requestId
        rqstIdLBL.setText(String.valueOf(generateRegistrationId()));

        decorationRD.setToggleGroup(teamGroup);
        logisticRD.setToggleGroup(teamGroup);
        photographyRD.setToggleGroup(teamGroup);
        culturalRD.setToggleGroup(teamGroup);
        eventRRD.setToggleGroup(teamGroup);

        panel_1.setVisible(true);panel_1.setManaged(true);
        panel_2.setVisible(false);panel_2.setManaged(false);


    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) {

        String selectedTeam = null;
        if(decorationRD.isSelected()){
            selectedTeam="Decoration";
        } else if (logisticRD.isSelected()) {
            selectedTeam="Logistic";
        } else if (photographyRD.isSelected()) {
            selectedTeam="Photography";
        } else if (culturalRD.isSelected()) {
            selectedTeam="Cultural";
        } else if (eventRRD.isSelected()) {
            selectedTeam="Event";
        }

        //Empty field checking
        if(userIDTF.getText().isEmpty() || nameTF.getText().isEmpty() || phoneTF.getText().isEmpty() || mailTF.getText().isEmpty() || selectedTeam==null || whyChooseTXTAREA.getText().isEmpty()){
            showWaitAlert(Alert.AlertType.ERROR,"Please fill all required fields");
            return;
        }

        TeamJoin teamJoin = new TeamJoin(
                Integer.parseInt(rqstIdLBL.getText()),
                userIDTF.getText(),
                phoneTF.getText(),
                nameTF.getText(),
                mailTF.getText(),
                LocalDate.now(),
                selectedTeam,
                experienceTXTAREA.getText(),
                whyChooseTXTAREA.getText()
        );

        //validation
        if(!teamJoin.validateRegistration()){
            //failed to validate-->go back to first page of form
            panel_1.setVisible(true);panel_1.setManaged(true);
            panel_2.setVisible(false);panel_2.setManaged(false);
            showAlert(Alert.AlertType.INFORMATION,"Invalid Information");
            return;
        }

        //add & read old data (form submit)
        ArrayList<TeamJoin> rqstList = FileManager.readFile("teamRequests.bin");
        if(rqstList==null){
            rqstList=new ArrayList<>();
        }rqstList.add(teamJoin);


        //write new data (form submit)
        writeFile("teamRequests.bin",rqstList);
        showAlert(Alert.AlertType.CONFIRMATION,"Submitted Successfully");
        System.out.println(rqstList);

        panel_2.setVisible(false);panel_2.setManaged(false);
        panel_1.setVisible(true);panel_1.setManaged(true);

        //clear field
        userIDTF.clear();nameTF.clear();phoneTF.clear();rqstIdLBL.setText(null);mailTF.clear();teamGroup.selectToggle(null);experienceTXTAREA.clear();whyChooseTXTAREA.clear();
    }

    @javafx.fxml.FXML
    public void continueScreenButtonOA(ActionEvent actionEvent) {
        panel_2.setVisible(true);panel_2.setManaged(true);
        panel_1.setVisible(false);panel_1.setManaged(false);
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G1_Register_view.fxml");
    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) {
        panel_1.setVisible(true);panel_1.setManaged(true);
        panel_2.setVisible(false);panel_2.setManaged(false);
    }
}