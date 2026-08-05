package c213.dosaoopproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fahmida/U1_Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1026, 731);
        stage.setTitle("DoSA — Student Portal");
        stage.setScene(scene);
        stage.show();
    }
}
