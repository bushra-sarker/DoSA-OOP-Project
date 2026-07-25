package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.controller.User3BaseSideBarController;
import c213.dosaoopproject.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class G_2_Join_Team_View_Controller extends User3BaseSideBarController
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
    }

    @javafx.fxml.FXML
    public void backToDashOA(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
//        stage.setTitle("DoSA Management Simulation");
//        stage.setScene(scene);
//        stage.show();
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml");
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