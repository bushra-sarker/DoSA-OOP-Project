package c213.dosaoopproject.Nahin.controller.u_03;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

import static commonClass.Navigation.navigate;

public class G_4_Report_Issue_View_Controller
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
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml");
    }

    @javafx.fxml.FXML
    public void createReportOA(ActionEvent actionEvent) throws IOException{
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3G4_issueReporting_Form_view.fxml");
    }
}