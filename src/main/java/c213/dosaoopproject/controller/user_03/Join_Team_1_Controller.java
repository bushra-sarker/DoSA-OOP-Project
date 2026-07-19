package c213.dosaoopproject.controller.user_03;

import c213.dosaoopproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Join_Team_1_Controller
{
    @javafx.fxml.FXML
    private TextField userIDTF;
    @javafx.fxml.FXML
    private RadioButton decorationRD;
    @javafx.fxml.FXML
    private TextField mailTF;
    @javafx.fxml.FXML
    private TextField nameTF;
    @javafx.fxml.FXML
    private ImageView ppImageView;
    @javafx.fxml.FXML
    private RadioButton culturalRD;
    @javafx.fxml.FXML
    private RadioButton logisticRD;
    @javafx.fxml.FXML
    private RadioButton photographyRD;
    @javafx.fxml.FXML
    private Label registerIDLabeL;
    @javafx.fxml.FXML
    private RadioButton eventRRD;
    @javafx.fxml.FXML
    private TextField phoneYF;
    @javafx.fxml.FXML
    private Label nameLabel;
    @javafx.fxml.FXML
    private Label userIdLabel;

    @javafx.fxml.FXML
    public void initialize() {
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
    public void continueScreenButtonOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_3_JoinTeam_2_form_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void registerViewOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_3_eventRegister_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
    }

    @javafx.fxml.FXML
    public void teamViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void feedbackShareOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void leaveRqstViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void dashboardViewOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_3_dashBoard_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_3_JoinTeam_0_view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setTitle("DoSA Management Simulation");
            stage.setScene(scene);
    }

    @javafx.fxml.FXML
    public void contributionViewOA(ActionEvent actionEvent) {
    }


    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void reportViewOA(ActionEvent actionEvent) {
    }
}