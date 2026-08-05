package c213.dosaoopproject.fahmida;

import commonClass.User;
import c213.dosaoopproject.fahmida.session.Session;
import c213.dosaoopproject.fahmida.util.SceneManager;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class U2_DashboardController {
    @javafx.fxml.FXML
    private Label userIdLabel11;
    @javafx.fxml.FXML
    private ImageView ppImageView11;
    @javafx.fxml.FXML
    private Label DOSALabel;
    @javafx.fxml.FXML
    private Label nameLabel11;

    @javafx.fxml.FXML
    public void initialize() {
        User user = Session.getCurrentUser();
        if (user != null) {
            if (nameLabel11 != null) {
                nameLabel11.setText(user.getFullName());
            }
            if (userIdLabel11 != null) {
                userIdLabel11.setText(user.getLoginId());
            }
        }
    }

    @javafx.fxml.FXML
    public void registerEventsOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewScheduleOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void communityProgramOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewNoticesOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you want to exit?", ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);
        confirm.showAndWait().ifPresent(choice -> {
            if (choice == ButtonType.YES) {
                Session.clear();
                SceneManager.switchTo("LoginView");
            }
        });
    }

    @javafx.fxml.FXML
    public void downloadApprovalOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void clubMembershipOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void studentdashboardOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void trackHistoryOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void submitComplaintsOA(ActionEvent actionEvent) {
    }

}
