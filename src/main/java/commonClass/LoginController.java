package commonClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML private TextField txtUserID;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;
    @FXML private Button btnLogin;

    @FXML
    private void handleLogin(ActionEvent event) {
        if (txtUserID == null || txtPassword == null) {
            System.err.println("Error: @FXML controls are not linked properly in LoginView.fxml!");
            return;
        }

        String userId = txtUserID.getText().trim();
        String password = txtPassword.getText().trim();

        if (lblError != null) {
            lblError.setVisible(false);
            lblError.setText("");
        }

        if (userId.isEmpty() || password.isEmpty()) {
            showError("Please enter both User ID and Password.");
            return;
        }

        // 1. User 7: Head of DoSA
        if (userId.equalsIgnoreCase("HOD07") && password.equals("1234")) {
            navigateToDashboard(event, "/c213/dosaoopproject/bushraView/U07/U07_HeadOfDoSADashboard.fxml", "Head of DoSA Dashboard");
        }
        // 2. User 8: Student Welfare Officer
        else if (userId.equalsIgnoreCase("SWO08") && password.equals("1234")) {
            navigateToDashboard(event, "/c213/dosaoopproject/bushraView/U08/U08_StudentWelfareOfficerDashboard.fxml", "Student Welfare Officer Dashboard");
        }
        // 3. Invalid Credentials
        else {
            showError("Invalid User ID or Password!");
        }
    }

    private void showError(String message) {
        if (lblError != null) {
            lblError.setText(message);
            lblError.setVisible(true);
        } else {
            System.err.println("Login Error: " + message);
        }
    }

    private void navigateToDashboard(ActionEvent event, String fxmlPath, String title) {
        try {
            URL resource = getClass().getResource(fxmlPath);
            if (resource == null) {
                System.err.println("❌ Could not find FXML file at path: " + fxmlPath);
                showError("Dashboard template not found.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load dashboard FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }
}