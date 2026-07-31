package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.model.u_03.EventRegister;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.time.LocalDate;

import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.IdGenerator.generateRegistrationId;
import static c213.dosaoopproject.Nahin.utility.ToShowAlert.*;

public class G_1_Club_RegisterEvent_Controller
{
    @javafx.fxml.FXML
    private TextField userIDTF;
    @javafx.fxml.FXML
    private TextField mailTF;
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private TextArea experienceTXTAR;
    @javafx.fxml.FXML
    private TableColumn venueCOL;
    @javafx.fxml.FXML
    private Label registerIDLABEL;
    @javafx.fxml.FXML
    private VBox pane_2;
    @javafx.fxml.FXML
    private VBox pane_1;
    @javafx.fxml.FXML
    private TableView availableEventsTABLEVIEW;
    @javafx.fxml.FXML
    private TableColumn dateCOL;
    @javafx.fxml.FXML
    private TableColumn statusCOL;
    @javafx.fxml.FXML
    private TableColumn timeCOL;
    @javafx.fxml.FXML
    private TableColumn eventNameCol;
    @javafx.fxml.FXML
    private TableColumn clubNameCOL;
    @javafx.fxml.FXML
    private Label registrationDateLBL;
    @javafx.fxml.FXML
    private Label clubNameLBL;
    @javafx.fxml.FXML
    private Label eventNameLBL;

    @javafx.fxml.FXML
    public void initialize() {
        pane_2.setVisible(false);
        pane_2.setManaged(false);

        pane_1.setVisible(true);
        pane_1.setManaged(true);

        registrationDateLBL.setText(LocalDate.now().toString());

        //TO-DO --> set tableView

        //TO-DO --> Load all published events created by
    }

    @javafx.fxml.FXML
    public void refreshEventOA(ActionEvent actionEvent) {
        //TO-DO --> reload published table data from the list/file
    }

    @javafx.fxml.FXML
    public void submitFormOA(ActionEvent actionEvent) {
        if(userIDTF.getText().isEmpty() || nameTF.getText().isEmpty() ||
                phoneTF.getText().isEmpty() || mailTF.getText().isEmpty()){
            showWaitAlert(Alert.AlertType.ERROR,"Please fill up all required fields");
            return;
        }

        int registrationID = generateRegistrationId();

        EventRegister eventRegister = new EventRegister(
                registrationID,
                userIDTF.getText(),
                phoneTF.getText(),
                nameTF.getText(),
                mailTF.getText(),
                LocalDate.now(),
                clubNameLBL.getText(),
                eventNameLBL.getText(),
                experienceTXTAR.getText()
        );
        if (eventRegister.validateRegistration()){
            showAlert(Alert.AlertType.INFORMATION,"Submitted Successfully");

            //TO-DO--> save the registered information
            //--> show success msg
        }else{
            showAlert(Alert.AlertType.INFORMATION,"Invalid Information");
        }


    }

    @javafx.fxml.FXML
    public void clearOA(ActionEvent actionEvent) {
        userIDTF.clear(); nameTF.clear();
        phoneTF.clear(); mailTF.clear();
        experienceTXTAR.clear();
    }

    @javafx.fxml.FXML
    public void backToFirstOA(ActionEvent actionEvent) {
        pane_2.setVisible(false);
        pane_2.setManaged(false);

        pane_1.setVisible(true);
        pane_1.setManaged(true);
    }

    @javafx.fxml.FXML
    public void registerButtonOA(ActionEvent actionEvent) {
        if(availableEventsTABLEVIEW.getSelectionModel().getSelectedItem()==null){
            showWaitAlert(Alert.AlertType.ERROR,"Please select an event first");
            return;
        }
        //--> display the selected club name
        //--> display the selected event name

        pane_1.setVisible(false);
        pane_1.setManaged(false);

        pane_2.setVisible(true);
        pane_2.setManaged(true);
    }

    @javafx.fxml.FXML
    public void backDashOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G1_Register_view.fxml");
    }
}