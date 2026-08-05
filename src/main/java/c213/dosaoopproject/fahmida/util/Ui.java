package c213.dosaoopproject.fahmida.util;

import commonClass.User;
import c213.dosaoopproject.fahmida.session.Session;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.List;
import java.util.Optional;

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

    public static void info(String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, message);
        a.setHeaderText(null);
        a.showAndWait();
    }

    /** Single-line text prompt. Returns empty if the user cancels. */
    public static Optional<String> prompt(String title, String header) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        return dialog.showAndWait().map(String::trim).filter(s -> !s.isEmpty());
    }

    /** Pick one option from a list. Returns empty if the list is empty or cancelled. */
    public static Optional<String> choose(String title, String header, List<String> options) {
        if (options.isEmpty()) {
            return Optional.empty();
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(options.get(0), options);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        return dialog.showAndWait();
    }
}
