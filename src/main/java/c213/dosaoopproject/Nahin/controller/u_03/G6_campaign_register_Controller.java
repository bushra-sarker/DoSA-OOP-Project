package c213.dosaoopproject.Nahin.controller.u_03;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.Navigation.newStage;

public class G6_campaign_register_Controller
{
    @javafx.fxml.FXML
    private TableColumn locationCOL;
    @javafx.fxml.FXML
    private TableColumn datECOL;
    @javafx.fxml.FXML
    private VBox pane_1;
    @javafx.fxml.FXML
    private TableColumn campaignNameCol;
    @javafx.fxml.FXML
    private TableColumn statusCOL;
    @javafx.fxml.FXML
    private TableView campaignsTABLEVIEW;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void refreshTable(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void joinCampaignOA(ActionEvent actionEvent) throws IOException {
        newStage(actionEvent, "/Nahin/fxmlView/u3G6_Campaigns_form.fxml");
    }

    @javafx.fxml.FXML
    public void backDashBoardOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G1_Register_view.fxml");
    }
}