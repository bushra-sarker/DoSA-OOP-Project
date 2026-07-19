package c213.dosaoopproject.controller.user_03;

import c213.dosaoopproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Join_Team_2_form_Controller
{
    @javafx.fxml.FXML
    private ImageView ppImageView;
    @javafx.fxml.FXML
    private Label nameLabel;
    @javafx.fxml.FXML
    private Label userIdLabel;
    @javafx.fxml.FXML
    private TextArea experienceTXTAREA;
    @javafx.fxml.FXML
    private TextArea whyChooseTXTAREA;

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
    public void contributionViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
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
    public void reportViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void submitOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backToFirstOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_3_JoinTeam_1_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
    }
}