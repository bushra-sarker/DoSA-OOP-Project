package c213.dosaoopproject.Nahin.controller.u_03;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;

public class G_4_Report_Issue_Form_Controller
{
    @javafx.fxml.FXML
    private Label complaintIDlbl;
    @javafx.fxml.FXML
    private TextField userIDTF;
    @javafx.fxml.FXML
    private TextField eventnameTF;
    @javafx.fxml.FXML
    private TextField timrTF;
    @javafx.fxml.FXML
    private ComboBox<String> categoryCOMBO;
    @javafx.fxml.FXML
    private TextArea detailsTXTAR;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backFRONTOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G4_issueReporting_view.fxml");
    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) {
    }
}