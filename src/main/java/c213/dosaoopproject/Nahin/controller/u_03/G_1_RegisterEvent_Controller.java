package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static commonClass.Navigation.navigate;

public class G_1_RegisterEvent_Controller
{

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backToDashOA(ActionEvent actionEvent) throws IOException{
            navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml");
    }

    @javafx.fxml.FXML
    public void registerClubEventOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3G1_Club_Event_Register_view.fxml");
    }

    @javafx.fxml.FXML
    public void campaignsViewOA(ActionEvent actionEvent)  throws IOException {
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3G6_Campaigns_Register_view.fxml");
    }

    @javafx.fxml.FXML
    public void exploreTeamOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3G2_JoinTeam_view.fxml");
    }
}