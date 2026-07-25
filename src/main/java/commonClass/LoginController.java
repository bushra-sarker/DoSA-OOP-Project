package commonClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML private TextField userIDTextF;
    @FXML private PasswordField passwordTextF;
    @FXML private Label errorLabel;
    @FXML private Button loginButton;
    @FXML private Label forgotPasswordLabel;

    // Track failed login attempts and lockout state
    private int failedAttempts = 0;
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private boolean isAccountLocked = false;

    @FXML
    public void initialize() {
        // Initially hide error label
        if (errorLabel != null) {
            errorLabel.setVisible(false);
            errorLabel.setText("");
        }

        // Event-3 Constraint: Limit password text field length to 7 characters
        if (passwordTextF != null) {
            passwordTextF.setTextFormatter(new TextFormatter<>(change ->
                    change.getControlNewText().length() <= 7 ? change : null
            ));
        }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        if (userIDTextF == null || passwordTextF == null) {
            System.err.println("Error: @FXML controls are not linked properly in LoginView.fxml!");
            return;
        }

        // Check if account is currently locked
        if (isAccountLocked) {
            showError("Account locked for 15 minutes due to multiple failed attempts.");
            showLockoutAlert();
            return;
        }

        String userId = userIDTextF.getText().trim();
        String password = passwordTextF.getText().trim();

        if (errorLabel != null) {
            errorLabel.setVisible(false);
            errorLabel.setText("");
        }

        // Event-4: Validate non-empty fields
        if (userId.isEmpty() || password.isEmpty()) {
            showError("Please enter both ID and password.");
            return;
        }

        // Event-5 & 6: Validate Credentials and Route to Dashboards

        // 1. Student
        if (userId.equalsIgnoreCase("S01") && password.equals("2521807")) {
            resetFailedAttempts();
            navigateToDashboard(event, ".fxml", "Student Dashboard");
        }
        // 2. Club Advisor
        else if (userId.equalsIgnoreCase("CA02") && password.equals("2521807")) {
            resetFailedAttempts();
            navigateToDashboard(event, "/commonFXML/clubAdvisorDashboard.fxml", "Club Advisor Dashboard");
        }
        // 3. Volunteer (Nahin)
        else if (userId.equalsIgnoreCase("V03") && password.equals("2411850")) {
            resetFailedAttempts();
            navigateToDashboard(event, "/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml", "Volunteer Dashboard");
        }
        // 4. DoSA Officer
        else if (userId.equalsIgnoreCase("DO04") && password.equals("2411850")) {
            resetFailedAttempts();
            navigateToDashboard(event, "/commonFXML/u4_dashBoard_view.fxml", "DoSA Officer Dashboard");
        }
        // 5. Club Executive
        else if (userId.equalsIgnoreCase("CE05") && password.equals("2430898")) {
            resetFailedAttempts();
            navigateToDashboard(event, "/commonFXML/clubExecutiveDashboard.fxml", "Club Executive Dashboard");
        }
        // 6. Community Service Coordinator
        else if (userId.equalsIgnoreCase("DCSC06") && password.equals("2430898")) {
            resetFailedAttempts();
            navigateToDashboard(event, "/commonFXML/coordinatorDashboard.fxml", "Community Service Coordinator Dashboard");
        }
        // 7. Head of DoSA (Bushra - User 07)
        else if (userId.equalsIgnoreCase("HOD07") && password.equals("2411837")) {
            resetFailedAttempts();
            navigateToDashboard(event, "/c213/dosaoopproject/bushra/U07/U07_HeadOfDoSADashboard.fxml", "Head of DoSA Dashboard");
        }
        // 8. Student Welfare Officer (Bushra - User 08)
        else if (userId.equalsIgnoreCase("SWO08") && password.equals("2411837")) {
            resetFailedAttempts();
            navigateToDashboard(event, "/c213/dosaoopproject/bushra/U08/U08_StudentWelfareOfficerDashboard.fxml", "Student Welfare Officer Dashboard");
        }
        // Event-5 & 7: Credential Mismatch & Attempt Handling
        else {
            handleFailedLogin();
        }
    }

    private void handleFailedLogin() {
        failedAttempts++;

        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            isAccountLocked = true;
            showError("Account locked for 15 minutes due to multiple failed attempts.");
            showLockoutAlert();
        } else {
            int remaining = MAX_FAILED_ATTEMPTS - failedAttempts;
            showError("Invalid ID or password. " + remaining + " attempt(s) remaining before account lockout.");
        }
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
        isAccountLocked = false;
    }

    private void showError(String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setVisible(true);
        } else {
            System.err.println("Login Error: " + message);
        }
    }

    private void showLockoutAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Account Locked");
        alert.setHeaderText("Access Denied");
        alert.setContentText("Your DoSA account has been temporarily locked due to multiple failed login attempts.");
        alert.showAndWait();
    }

    private void navigateToDashboard(ActionEvent event, String fxmlPath, String title) {
        try {
            URL resource = getClass().getResource(fxmlPath);
            if (resource == null) {
                System.err.println("Could not find FXML file at path: " + fxmlPath);
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
        }
        catch (IOException e) {
            System.err.println("Failed to load dashboard FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }
}