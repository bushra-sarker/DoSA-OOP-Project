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

public class LoginController {

    // MATCHED EXACTLY TO YOUR BEAUTIFUL FXML:
    @FXML private TextField txtUserID;      // Capital ID
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;
    @FXML private Button btnLogin;
    @FXML
    private Label lblForgotPassword;
    @FXML
    private Label lblTitle;

    @FXML
    private void handleLogin(ActionEvent event) {
        // Safety check to ensure UI components are injected properly
        if (txtUserID == null || txtPassword == null) {
            System.err.println("Error: @FXML controls are not linked properly in Login.fxml!");
            return;
        }

        String userId = txtUserID.getText().trim();
        String password = txtPassword.getText().trim();

        // Clear previous errors
        if (lblError != null) {
            lblError.setVisible(false);
            lblError.setText("");
        }

        // 1. Validation for empty inputs
        if (userId.isEmpty() || password.isEmpty()) {
            showError("Please enter both User ID and Password.");
            return;
        }

        // 2. User 7: Head of DoSA
        if (userId.equalsIgnoreCase("HOD07") && password.equals("1234")) {
            navigateToDashboard(event, "/bushraView/user7/HeadOfDoSADashboard.fxml", "Head of DoSA Dashboard");
        }
        // 3. User 8: Student Welfare Officer
        else if (userId.equalsIgnoreCase("SWO08") && password.equals("1234")) {
            navigateToDashboard(event, "/bushraView/user8/StudentWelfareOfficerDashboard.fxml", "Student Welfare Officer Dashboard");
        }
        // 4. Invalid Credentials
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
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