package c213.dosaoopproject.Fiha.controller.user_03;

import c213.dosaoopproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterEvent_Controller
{
    @javafx.fxml.FXML
    private ImageView ppImageView;
    @javafx.fxml.FXML
    private Label nameLabel;
    @javafx.fxml.FXML
    private Label userIdLabel;
    @javafx.fxml.FXML
    private TableView availableEventsTABLEVIEW;
    @javafx.fxml.FXML
    private TableColumn dateCOL;
    @javafx.fxml.FXML
    private TableColumn statusCOL;
    @javafx.fxml.FXML
    private TableColumn venueCOL;
    @javafx.fxml.FXML
    private TableColumn timeCOL;
    @javafx.fxml.FXML
    private TableColumn eventNameCol;
    @javafx.fxml.FXML
    private TableColumn clubNameCOL;
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
    private Label registerIDLABEL;
    @javafx.fxml.FXML
    private DatePicker dateDP;
    @javafx.fxml.FXML
    private VBox pane_2;
    @javafx.fxml.FXML
    private VBox pane_1;

    @javafx.fxml.FXML
    public void initialize() {
        pane_1.setVisible(true);
        pane_1.setManaged(true);

        pane_2.setVisible(false);
        pane_2.setManaged(false);
    }

    @javafx.fxml.FXML
    public void certificateApplyOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void campaignsViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void registerViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void teamViewOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Fiha/display/user_3_JoinTeam_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void feedbackShareOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void leaveRqstViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void dashboardViewOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Fiha/display/user_3_dashBoard_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void contributionViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void registerButtonOA(ActionEvent actionEvent) {
        pane_1.setVisible(false);
        pane_1.setManaged(false);

        pane_2.setVisible(true);
        pane_2.setManaged(true);
    }


    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
    }


    @javafx.fxml.FXML
    public void reportViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void refreshEventOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backDashOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Fiha/display/user_3_dashBoard_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
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