package c213.dosaoopproject;

import c213.dosaoopproject.Nahin.nonUser.DbinFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static c213.dosaoopproject.Nahin.nonUser.DbinFile.generateCampaign;
import static c213.dosaoopproject.Nahin.nonUser.DbinFile.generateNewClubRegistration;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

//        generateCampaign();
//        generateNewClubRegistration();

//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/Nahin/fxmlView/u3_dashBoard_view.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/Nahin/fxmlView/u4_dashBoard.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/commonFXML/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }
}