package c213.dosaoopproject.fahmida.utility;

import commonClass.User;
import c213.dosaoopproject.fahmida.session.Session;

import javafx.scene.control.Label;

/**
 * Small shared UI helpers used by the feature controllers, so the same few lines
 * (show the logged-in name, pop an alert / prompt) are not copied into every screen.
 */
public final class Ui {

    private Ui() {
    }

    /** Fills the standard header name/id labels from the current session. */
    public static void greet(Label nameLabel, Label idLabel) {
        User user = Session.getCurrentUser();
        if (user == null) {
            return;
        }
        if (nameLabel != null) {
            nameLabel.setText(user.getFullName());
        }
        if (idLabel != null) {
            idLabel.setText(user.getLoginId());
        }
    }
}
