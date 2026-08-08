package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.data.DataStore;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.utility.SceneManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the login screen (shared Login process from the spec).
 *
 * <p>Validates that both fields are filled, verifies the credentials against the
 * {@link DataStore}, stores the user in the {@link Session}, then routes to the
 * correct dashboard via {@link User#getDashboardFxml()} (role-based, polymorphic).</p>
 */
public class LoginViewController {

    @FXML
    private TextField userIdTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        errorLabel.setText("");
    }

    @FXML
    public void loginOA(ActionEvent actionEvent) throws IOException {
        String userId = userIdTF.getText() == null ? "" : userIdTF.getText().trim();
        String password = passwordTF.getText() == null ? "" : passwordTF.getText();

        // Validation (VL): both fields required.
        if (userId.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter both ID and password");
            return;
        }

        DataStore store = DataStore.get();
        User user = store.findByLoginId(userId);

        // Unknown id — do not reveal which field was wrong.
        if (user == null) {
            errorLabel.setText("Invalid ID or password");
            return;
        }

        // Locked account (too many failed attempts).
        if (user.isLocked()) {
            errorLabel.setText("Account locked after "
                    + User.MAX_FAILED_ATTEMPTS + " failed attempts. Contact the DoSA office.");
            return;
        }

        // Verification (VR): wrong password → count the attempt, warn or lock.
        if (!user.getPasswordHash().equals(password)) {
            user.recordFailedAttempt();
            store.save();
            if (user.isLocked()) {
                errorLabel.setText("Account locked after "
                        + User.MAX_FAILED_ATTEMPTS + " failed attempts.");
            } else {
                errorLabel.setText("Invalid ID or password. "
                        + user.attemptsRemaining() + " attempt(s) remaining before lockout.");
            }
            return;
        }

        // Success: reset the counter, start the session, load the dashboard.
        user.resetFailedAttempts();
        store.save();
        Session.setCurrentUser(user);
        SceneManager.navigate(actionEvent, user.getDashboardFxml());
    }
}
