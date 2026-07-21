package c213.dosaoopproject.Fiha.controller.user_03;

import c213.dosaoopproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardView_Controller
{
    @javafx.fxml.FXML
    private Label pendingCardLabel;
    @javafx.fxml.FXML
    private Label recent_FourLabel;
    @javafx.fxml.FXML
    private Label msgWelcomeLabel;
    @javafx.fxml.FXML
    private Label recentTime_TwoLabel;
    @javafx.fxml.FXML
    private Label recentTime_ThreeLabel;
    @javafx.fxml.FXML
    private Label hoursCardLabel;
    @javafx.fxml.FXML
    private Label recentTime_OneLabel;
    @javafx.fxml.FXML
    private Hyperlink viewAllHyperLink;
    @javafx.fxml.FXML
    private ImageView ppImageView;
    @javafx.fxml.FXML
    private Label titleWelcomeLabel;
    @javafx.fxml.FXML
    private Label crtfStatusCardLabel;
    @javafx.fxml.FXML
    private Label participationCardLabel;
    @javafx.fxml.FXML
    private Label recent_ThreeLabel;
    @javafx.fxml.FXML
    private Label recent_OneLabel;
    @javafx.fxml.FXML
    private Label recent_TwoLabel;
    @javafx.fxml.FXML
    private Label recentTime_FourLabel;
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
    public void contributionViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void menuBarOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void registerViewOA(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_3_eventRegister_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void teamViewOA(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_3_JoinTeam_view.fxml"));
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
    public void dashboardViewOA(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user_3_dashBoard_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void viewAllNotificationLinkOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void reportViewOA(ActionEvent actionEvent) {
    }
}