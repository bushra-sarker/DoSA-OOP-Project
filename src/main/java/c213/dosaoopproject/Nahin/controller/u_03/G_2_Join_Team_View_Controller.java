package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.model.u_03.TeamJoin;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.time.LocalDate;

import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.IdGenerator.generateRegistrationId;
import static c213.dosaoopproject.Nahin.utility.ToShowAlert.*;

public class G_2_Join_Team_View_Controller
{
    @javafx.fxml.FXML
    private TextField userIDTF;
    @javafx.fxml.FXML
    private RadioButton decorationRD;
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private Label totalRequestLBL;
    @javafx.fxml.FXML
    private VBox panel_3;
    @javafx.fxml.FXML
    private VBox panel_2;
    @javafx.fxml.FXML
    private VBox panel_1;
    @javafx.fxml.FXML
    private Label approvedRqstLBL;
    @javafx.fxml.FXML
    private RadioButton logisticRD;
    @javafx.fxml.FXML
    private RadioButton photographyRD;
    @javafx.fxml.FXML
    private TableColumn rqstIdCOL;
    @javafx.fxml.FXML
    private TextArea experienceTXTAREA;
    @javafx.fxml.FXML
    private TableColumn teamNameCOL;
    @javafx.fxml.FXML
    private TableView rqstTableView;
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private TextField mailTF;
    @javafx.fxml.FXML
    private RadioButton culturalRD;
    @javafx.fxml.FXML
    private TableColumn dateCOL;
    @javafx.fxml.FXML
    private TableColumn statusCOL;
    @javafx.fxml.FXML
    private Button joinButtonFXiD;
    @javafx.fxml.FXML
    private TextArea whyChooseTXTAREA;
    @javafx.fxml.FXML
    private RadioButton eventRRD;
    @javafx.fxml.FXML
    private Label pendingRqstLBL;
    @javafx.fxml.FXML
    private Label regIDLabeL;

    @javafx.fxml.FXML
    public void initialize() {

        panel_1.setVisible(true);
        panel_1.setManaged(true);

        panel_2.setVisible(false);
        panel_2.setManaged(false);

        panel_3.setVisible(false);
        panel_3.setManaged(false);
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
        if(userIDTF.getText().isEmpty() || nameTF.getText().isEmpty() || phoneTF.getText().isEmpty() ||
                mailTF.getText().isEmpty() || selectedTeam == null || whyChooseTXTAREA.getText().isEmpty()){
            showWaitAlert(Alert.AlertType.ERROR,"Please fill all required fields");
            return;
        }

        int regId = generateRegistrationId();

        TeamJoin teamJoin = new TeamJoin(
                regId,
                userIDTF.getText(),
                phoneTF.getText(),
                nameTF.getText(),
                mailTF.getText(),
                LocalDate.now(),
                selectedTeam,
                experienceTXTAREA.getText(),
                whyChooseTXTAREA.getText()
        );

        if(teamJoin.validateRegistration()){
            showAlert(Alert.AlertType.INFORMATION,"Submitted Successfully");

            //TO-DO--> save data
            //--> wait for approval
        }else {
            showAlert(Alert.AlertType.INFORMATION,"Invalid Information");
        }
    }

    @javafx.fxml.FXML
    public void continueScreenButtonOA(ActionEvent actionEvent) {
        joinButtonFXiD.setVisible(false);
        joinButtonFXiD.setManaged(false);

        panel_2.setVisible(false);
        panel_2.setManaged(false);

        panel_1.setVisible(false);
        panel_1.setManaged(false);

        panel_3.setVisible(true);
        panel_3.setManaged(true);

        //TO-DO--> load table with available teams from published list/file
    }

    @javafx.fxml.FXML
    public void backToDashOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G1_Register_view.fxml");
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        joinButtonFXiD.setVisible(true);
        joinButtonFXiD.setManaged(true);

        panel_2.setVisible(false);
        panel_2.setManaged(false);

        panel_3.setVisible(false);
        panel_3.setManaged(false);

        panel_1.setVisible(true);
        panel_1.setManaged(true);
    }

    @javafx.fxml.FXML
    public void goToNextOA(ActionEvent actionEvent) {
        joinButtonFXiD.setVisible(false);
        joinButtonFXiD.setManaged(false);

        panel_1.setVisible(false);
        panel_1.setManaged(false);

        panel_3.setVisible(false);
        panel_3.setManaged(false);

        panel_2.setVisible(true);
        panel_2.setManaged(true);
    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) {
        joinButtonFXiD.setVisible(false);
        joinButtonFXiD.setManaged(false);

        panel_1.setVisible(false);
        panel_1.setManaged(false);

        panel_3.setVisible(false);
        panel_3.setManaged(false);

        panel_2.setVisible(true);
        panel_2.setManaged(true);
    }
}