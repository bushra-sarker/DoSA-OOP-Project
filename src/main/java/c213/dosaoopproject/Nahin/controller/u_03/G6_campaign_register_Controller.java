package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.nonUser.CampaignData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.VIA.showAlert;

public class G6_campaign_register_Controller
{
    @javafx.fxml.FXML
    private TableColumn<CampaignData,String> locationCOL;
    @javafx.fxml.FXML
    private TableColumn<CampaignData, LocalDate>  datECOL;
    @javafx.fxml.FXML
    private TableColumn<CampaignData,String>  campaignNameCol;
    @javafx.fxml.FXML
    private TableColumn<CampaignData,String>  statusCOL;
    @javafx.fxml.FXML
    private TableView<CampaignData>  campaignsTABLEVIEW;

    @javafx.fxml.FXML
    public void initialize() {
        // load table with published campaign list
        campaignNameCol.setCellValueFactory(new PropertyValueFactory<>("campaignName"));
        datECOL.setCellValueFactory(new PropertyValueFactory<>("date"));
        locationCOL.setCellValueFactory(new PropertyValueFactory<>("location"));
        statusCOL.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    //load data at upcoming campaign table (Dummy)
    private void loadCampaigns(){
        ArrayList<CampaignData> campaignData = readFile("campaigns.bin");
        if(campaignData!=null){
            campaignsTABLEVIEW.getItems().addAll(campaignData);
        }
    }

    @javafx.fxml.FXML
    public void refreshTable(ActionEvent actionEvent) {
        campaignsTABLEVIEW.getItems().clear();
        loadCampaigns();
    }


    @javafx.fxml.FXML
    public void joinCampaignOA(ActionEvent actionEvent) throws IOException {

        CampaignData selectedCampaign = campaignsTABLEVIEW.getSelectionModel().getSelectedItem();

        if( selectedCampaign== null){
            showAlert(Alert.AlertType.WARNING,"Please select a campaign first");
            return;
        }
        if(!selectedCampaign.getStatus().equals("open")){
            showAlert(Alert.AlertType.ERROR, "This campaign is not available");
            return;
        }


        //to show & load specific name date location from table to form (new window)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Nahin/fxmlView/u3G6_Campaigns_form.fxml"));
        Parent root = loader.load();
        G6_campaigns_FORM_Controller controller = loader.getController();
        controller.setCampaignData(selectedCampaign);  //from Form controller
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //

    //

    @javafx.fxml.FXML
    public void backDashBoardOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G1_Register_view.fxml");
    }
}