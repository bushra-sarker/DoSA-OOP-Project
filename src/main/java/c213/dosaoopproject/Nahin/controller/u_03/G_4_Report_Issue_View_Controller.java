package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Application;
import c213.dosaoopproject.Nahin.controller.User3BaseSideBarController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class G_4_Report_Issue_View_Controller extends User3BaseSideBarController
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
    private ComboBox categoryCOMBO;
    @javafx.fxml.FXML
    private TextArea detailsTXTAR;


    @javafx.fxml.FXML
    public void initialize() {
    }

    @Deprecated
    public void refreshTableOA(ActionEvent actionEvent) {
    }

    @Deprecated
    public void searchOA(ActionEvent actionEvent) {
    }

    @Deprecated
    public void backOA(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
//        stage.setTitle("DoSA Management Simulation");
//        stage.setScene(scene);
//        stage.show();
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml");
    }

    @Deprecated
    public void createReportOA(ActionEvent actionEvent) throws IOException{
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3G4_issueReporting_Form_view.fxml");
    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backFRONTOA(ActionEvent actionEvent) throws IOException{
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3G4_issueReporting_view.fxml");
    }
}