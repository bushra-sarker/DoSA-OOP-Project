package c213.dosaoopproject.bushraController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class bushraApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(bushraApplication.class.getResource("/bushraView/HeadOfDoSADashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }
}
