package c213.dosaoopproject.Nahin.controller.u_04;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;

public class G_7_Resourse_request_Controller
{
    @javafx.fxml.FXML
    private Label clubNameLBL;
    @javafx.fxml.FXML
    private Label eventNameLBL;
    @javafx.fxml.FXML
    private TableView resourceTableVIEW;
    @javafx.fxml.FXML
    private Label quantityLBL;
    @javafx.fxml.FXML
    private Label materialLBL;
    @javafx.fxml.FXML
    private TableColumn applicationIdCOL;
    @javafx.fxml.FXML
    private TableColumn categoryCOL;
    @javafx.fxml.FXML
    private VBox application_Pane;
    @javafx.fxml.FXML
    private Label purposeLBL;
    @javafx.fxml.FXML
    private TableColumn clubNameCol;
    @javafx.fxml.FXML
    private Label dateLBL;
    @javafx.fxml.FXML
    private SideMenuBar_Controller nullController;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void detailsOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void refreshOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u4_dashBoard.fxml");
    }

    @javafx.fxml.FXML
    public void rejectOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void approvOA(ActionEvent actionEvent) {
    }
}