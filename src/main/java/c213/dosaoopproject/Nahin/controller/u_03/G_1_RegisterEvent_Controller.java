package c213.dosaoopproject.Nahin.controller.u_03;

import javafx.event.ActionEvent;
import java.io.IOException;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;

public class G_1_RegisterEvent_Controller
{

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backToDashOA(ActionEvent actionEvent) throws IOException{
            navigate(actionEvent, "/Nahin/fxmlView/u3_dashBoard_view.fxml");
    }

    @javafx.fxml.FXML
    public void registerClubEventOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G1_Club_Event_Register_view.fxml");
    }

    @javafx.fxml.FXML
    public void campaignsViewOA(ActionEvent actionEvent)  throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G6_Campaigns_Register_view.fxml");
    }

    @javafx.fxml.FXML
    public void exploreTeamOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G2_JoinTeam_view.fxml");
    }
}