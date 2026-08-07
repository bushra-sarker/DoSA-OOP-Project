package c213.dosaoopproject.commonClass.controller;

import c213.dosaoopproject.commonClass.model.User;
import c213.dosaoopproject.commonClass.util.Navigation;
import c213.dosaoopproject.commonClass.util.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

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

    @FXML
    private void loginButton(ActionEvent event) throws IOException {
        String userId = userIDTextF.getText().trim();
        String password = passwordTextF.getText().trim();

        if (userId.isEmpty() || password.isEmpty()) {
            showError("Please enter both User ID and Password.");
            return;
        }

        SessionManager sessionManager = SessionManager.getInstance();

        // 1. Fetch user by ID from SessionManager / users.dat
        User user = sessionManager.getUser(userId);

        if (user == null) {
            showError("Invalid User ID or Password.");
            return;
        }

        if (user.isLocked()) {
            showError("Account is locked due to multiple failed attempts.");
            showLockoutAlert();
            return;
        }

        // 2. Validate Password
        if (user.getPassword().equals(password)) {
            user.setFailedAttempts(0);
            sessionManager.saveUserDatabase();
            sessionManager.setCurrentUser(user);

            // 3. Navigate directly to this user's assigned dashboard FXML
            Navigation.navigate(event, user.getFxmlPath());
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

    @FXML
    public void setForgotPasswordLabel(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password Reset");
        alert.setHeaderText("Forgot Password");
        alert.setContentText("Please contact DoSA IT Support to reset your credentials.");
        alert.showAndWait();
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