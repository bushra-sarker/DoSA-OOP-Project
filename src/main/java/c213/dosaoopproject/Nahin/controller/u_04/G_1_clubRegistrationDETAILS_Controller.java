package c213.dosaoopproject.Nahin.controller.u_04;

import c213.dosaoopproject.Application;
import c213.dosaoopproject.Nahin.controller.U_04_NavigationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class G_1_clubRegistrationDETAILS_Controller extends U_04_NavigationController
{

    @javafx.fxml.FXML
    private TextArea remarksTXTAR;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @Deprecated
    public void rejectButtonOA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/c213/dosaoopproject/Nahin/remarks_dialogView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Reject Application");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Deprecated
    public void approveButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void cancelRejectionOA(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void confirmationOFrejectionOA(ActionEvent actionEvent) {
    }

    @Deprecated
    public void closeDetailsOA(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}