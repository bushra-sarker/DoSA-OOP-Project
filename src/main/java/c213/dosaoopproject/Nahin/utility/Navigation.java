package c213.dosaoopproject.Nahin.utility;

import c213.dosaoopproject.Nahin.nonUser.DbinFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    public static void navigate(ActionEvent actionEvent, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }


    public static void newStage(ActionEvent actionEvent,String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public static class Application extends javafx.application.Application {
        @Override
        public void start(Stage stage) throws IOException {

            DbinFile.generateCampaign();
            DbinFile.generateNewClubRegistration();
    //
//            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("c213/dosaoopproject/Nahin/fxmlView/u4_dashBoard.fxml"));
//            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/Nahin/fxmlView/u4_dashBoard.fxml"));
//            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/commonFXML/LoginView.fxml"));
//            Scene scene = new Scene(fxmlLoader.load());
//            stage.setTitle("DoSA Management Simulation");
//            stage.setScene(scene);
//            stage.show();


        }
    }
}
