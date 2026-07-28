package c213.dosaoopproject.Nahin.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class reject_remark_dialog_Controller
{
    @javafx.fxml.FXML
    private TextArea remarksTXTAR;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void cancelRejectionOA(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @javafx.fxml.FXML
    public void confirmationOFrejectionOA(ActionEvent actionEvent) {
    }
}