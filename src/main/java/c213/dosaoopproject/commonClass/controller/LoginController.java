package c213.dosaoopproject.commonClass.controller;

import c213.dosaoopproject.commonClass.model.User;
import c213.dosaoopproject.commonClass.util.SceneSwitcher;
import c213.dosaoopproject.commonClass.util.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML private TextField userIDTextF;
    @FXML private PasswordField passwordTextF;
    @FXML private Label errorLabel;
    @FXML private Button loginButton;
    @FXML private Label forgotPasswordLabel;

    private static final int MAX_FAILED_ATTEMPTS = 3;

    @FXML
    public void initialize() {
        if (errorLabel != null) {
            errorLabel.setVisible(false);
            errorLabel.setText("");
        }

        // Auto-uppercase User ID input
        if (userIDTextF != null) {
            userIDTextF.setTextFormatter(new TextFormatter<>(change -> {
                change.setText(change.getText().toUpperCase());
                return change;
            }));
        }

        // Restrict Password input length to maximum 7 characters
        if (passwordTextF != null) {
            passwordTextF.setTextFormatter(new TextFormatter<>(change ->
                    change.getControlNewText().length() <= 7 ? change : null
            ));
        }
    }

    // CHANGED FROM private TO public
    @FXML
    public void loginButton(ActionEvent event) {
        String userId = userIDTextF.getText().trim();
        String password = passwordTextF.getText().trim();

        clearError();

        // 1. Input Validation
        if (userId.isEmpty() || password.isEmpty()) {
            showError("Please enter both ID and password.");
            return;
        }

        SessionManager sessionManager = SessionManager.getInstance();

        // 2. User Existence Check
        if (!sessionManager.isValidUserId(userId)) {
            showError("Invalid ID or password.");
            return;
        }

        User user = sessionManager.getUser(userId);

        // 3. Lockout Verification
        if (user.isLocked()) {
            showError("Account locked due to multiple failed attempts.");
            showLockoutAlert();
            return;
        }

        // 4. Password Verification
        if (user.getPassword().equals(password)) {
            user.setFailedAttempts(0);
            sessionManager.saveUserDatabase();
            sessionManager.setCurrentUser(user);

            // Clean, one-line navigation via utility class
            SceneSwitcher.switchTo(event, user.getFxmlPath(), user.getRole() + " Dashboard");
        } else {
            handleFailedLogin(user, sessionManager);
        }
    }

    private void handleFailedLogin(User user, SessionManager sessionManager) {
        int attempts = user.getFailedAttempts() + 1;
        user.setFailedAttempts(attempts);

        if (attempts >= MAX_FAILED_ATTEMPTS) {
            user.setLocked(true);
            sessionManager.saveUserDatabase();
            showError("Account locked due to multiple failed attempts.");
            showLockoutAlert();
        } else {
            sessionManager.saveUserDatabase();
            int remaining = MAX_FAILED_ATTEMPTS - attempts;
            showError("Invalid ID or password. " + remaining + " attempt(s) remaining.");
        }
    }

    // CHANGED FROM private TO public
    @FXML
    public void setForgotPasswordLabel(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password Reset");
        alert.setHeaderText("Forgot Password");
        alert.setContentText("Please contact DoSA IT Support to reset your credentials.");
        alert.showAndWait();
    }

    private void clearError() {
        if (errorLabel != null) {
            errorLabel.setVisible(false);
            errorLabel.setText("");
        }
    }

    private void showError(String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setVisible(true);
        }
    }

    private void showLockoutAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Account Locked");
        alert.setHeaderText("Access Denied");
        alert.setContentText("Your DoSA account has been locked due to multiple failed login attempts.");
        alert.showAndWait();
    }
}