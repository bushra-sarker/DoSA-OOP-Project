package c213.dosaoopproject;

import c213.dosaoopproject.commonClass.model.User;
import c213.dosaoopproject.commonClass.util.SessionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DoSAJavaFXApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/commonFXML/LoginView.fxml"));
        primaryStage.setTitle("DoSA System Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        // RESET LOCKOUTS WHEN APPLICATION CLOSES
        primaryStage.setOnCloseRequest(event -> {
            resetUserLockoutsOnExit();
        });
    }

    private void resetUserLockoutsOnExit() {
        SessionManager sessionManager = SessionManager.getInstance();
        if (sessionManager.getAllUsers() != null) {
            for (User user : sessionManager.getAllUsers()) {
                user.setLocked(false);
                user.setFailedAttempts(0);
            }
            sessionManager.saveUserDatabase();
            System.out.println("[INFO] Lockout state cleared on application close.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}