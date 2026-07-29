package c213.dosaoopproject.Nahin.controller.u_03;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class G6_campaigns_FORM_Controller
{
    @javafx.fxml.FXML
    private TextField userIdTF;
    @javafx.fxml.FXML
    private VBox mailTF;
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private Label registerIDLBL;
    @javafx.fxml.FXML
    private TextField userIdTF1;
    @javafx.fxml.FXML
    private TextField userNameTF;
    @javafx.fxml.FXML
    private Label campaignNameLBL;
    @javafx.fxml.FXML
    private Label dateLBL;
    @javafx.fxml.FXML
    private TextArea notesTXTAR;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void cancelButtonOA(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @javafx.fxml.FXML
    public void submitButtonOA(ActionEvent actionEvent) {
    }
}