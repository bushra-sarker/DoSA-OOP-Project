package c213.dosaoopproject.Nahin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class nahinApplication extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(nahinApplication.class.getResource("Nahin/display/u3_dashBoard_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }

    public class Launcher {
        public static void main(String[] args) {
            javafx.application.Application.launch(nahinApplication.class, args);
        }
    }
}