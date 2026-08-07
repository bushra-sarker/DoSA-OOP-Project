package c213.dosaoopproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

//        DbinFile.generateCampaign();
//        DbinFile.generateNewClubRegistration();
//
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/Nahin/fxmlView/u3_dashBoard_view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/commonFXML/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();


    }
}