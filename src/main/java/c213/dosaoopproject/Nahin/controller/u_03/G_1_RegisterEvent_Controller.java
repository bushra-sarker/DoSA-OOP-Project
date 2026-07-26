package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.controller.U_03_NavigationController;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class G_1_RegisterEvent_Controller extends U_03_NavigationController
{

    @javafx.fxml.FXML
    private TextField userIDTF;
    @javafx.fxml.FXML
    private TextField mailTF;
    @javafx.fxml.FXML
    private TextField phoneTF;
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private TextArea experienceTXTAR;
    @javafx.fxml.FXML
    private TableColumn venueCOL;
    @javafx.fxml.FXML
    private Label registerIDLABEL;
    @javafx.fxml.FXML
    private DatePicker dateDP;
    @javafx.fxml.FXML
    private VBox pane_2;
    @javafx.fxml.FXML
    private VBox pane_1;
    @javafx.fxml.FXML
    private TableView availableEventsTABLEVIEW;
    @javafx.fxml.FXML
    private TableColumn dateCOL;
    @javafx.fxml.FXML
    private TableColumn statusCOL;
    @javafx.fxml.FXML
    private TableColumn timeCOL;
    @javafx.fxml.FXML
    private TableColumn eventNameCol;
    @javafx.fxml.FXML
    private TableColumn clubNameCOL;

    @javafx.fxml.FXML
    public void initialize() {
        pane_1.setVisible(true);
        pane_1.setManaged(true);

        pane_2.setVisible(false);
        pane_2.setManaged(false);
    }

    @javafx.fxml.FXML
    public void registerButtonOA(ActionEvent actionEvent) {
        pane_1.setVisible(false);
        pane_1.setManaged(false);

        pane_2.setVisible(true);
        pane_2.setManaged(true);
    }

    @javafx.fxml.FXML
    public void refreshEventOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backDashOA(ActionEvent actionEvent) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
//        stage.setTitle("DoSA Management Simulation");
//        stage.setScene(scene);
//        stage.show();
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u3_dashBoard_view.fxml");
    }

    @javafx.fxml.FXML
    public void submitFormOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void clearOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backToFirstOA(ActionEvent actionEvent) {
        pane_2.setVisible(false);
        pane_2.setManaged(false);

        pane_1.setVisible(true);
        pane_1.setManaged(true);

    }
}