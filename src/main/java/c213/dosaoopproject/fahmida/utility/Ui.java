package c213.dosaoopproject.fahmida.utility;

import commonClass.User;
import c213.dosaoopproject.fahmida.session.Session;

import javafx.scene.control.Label;


public final class Ui {

    private Ui() {
    }

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
