package c213.dosaoopproject.Nahin.controller.u_04;

import c213.dosaoopproject.Application;
import c213.dosaoopproject.Nahin.controller.U_04_NavigationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class G_1_clubRegistration_Controller extends U_04_NavigationController
{
    @javafx.fxml.FXML
    private TableColumn dateCOL;
    @javafx.fxml.FXML
    private TableColumn statusCOL;
    @javafx.fxml.FXML
    private TextField searchOFCRTF;
    @javafx.fxml.FXML
    private TableColumn categoryCOL;
    @javafx.fxml.FXML
    private TableColumn appliIDCOL;
    @javafx.fxml.FXML
    private TableColumn clubNameCOL;
    @javafx.fxml.FXML
    private TableView proposalLISTTableView;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void refreshTableOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void detailsViewOA(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/c213/dosaoopproject/Nahin/u4_G1_clubRegistrationDetails_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Reject Application");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void backTodashOA(ActionEvent actionEvent) throws IOException {
        navigateTo(actionEvent,"/c213/dosaoopproject/Nahin/u4_dashboard_view.fxml");
    }
}