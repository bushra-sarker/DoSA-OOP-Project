package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.controller.U_03_NavigationController;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

public class G_4_Report_Issue_View_Controller extends U_03_NavigationController
{

    @javafx.fxml.FXML
    private TableColumn dateCOL;
    @javafx.fxml.FXML
    private TableColumn statusCOL;
    @javafx.fxml.FXML
    private TableColumn complaintIDCOL;
    @javafx.fxml.FXML
    private TableColumn categoryCOL;
    @javafx.fxml.FXML
    private TextField searchTF;
    @javafx.fxml.FXML
    private TableView reportViewTABLE;
    @javafx.fxml.FXML
    private TableColumn eventNameCOL;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void refreshTableOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void searchOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
//        stage.setTitle("DoSA Management Simulation");
//        stage.setScene(scene);
//        stage.show();
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml");
    }

    @javafx.fxml.FXML
    public void createReportOA(ActionEvent actionEvent) throws IOException{
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3G4_issueReporting_Form_view.fxml");
    }

    @Deprecated
    public void submitOA(ActionEvent actionEvent) {
    }

    @Deprecated
    public void backFRONTOA(ActionEvent actionEvent) throws IOException{
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3G4_issueReporting_view.fxml");
    }
}