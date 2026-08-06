package c213.dosaoopproject;

import c213.dosaoopproject.Nahin.nonUser.CampaignList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        CampaignList.createDummyCampaign();
        
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/Nahin/fxmlView/u4_dashBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();


    }

}