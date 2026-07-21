package c213.dosaoopproject.Fiha.controller;

import c213.dosaoopproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseSideBarController {

    @javafx.fxml.FXML
    protected ImageView ppImageView;
    @javafx.fxml.FXML
    protected Label nameLabel;
    @javafx.fxml.FXML
    protected Label userIdLabel;

    public void navigate(ActionEvent actionEvent, String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
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
    public void contributionViewOA(ActionEvent actionEvent) throws Exception{
        navigate(actionEvent,"Fiha/display/user_3_contribution_view.fxml");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void registerViewOA(ActionEvent actionEvent) throws IOException{
        navigate(actionEvent,"Fiha/display/user_3_eventRegister_view.fxml");
    }

    @javafx.fxml.FXML
    public void teamViewOA(ActionEvent actionEvent) throws IOException{
        navigate(actionEvent,"Fiha/display/user_3_JoinTeam_view.fxml");
    }

    @javafx.fxml.FXML
    public void feedbackShareOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void leaveRqstViewOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void dashboardViewOA(ActionEvent actionEvent) throws IOException{
        navigate(actionEvent,"Fiha/display/user_3_dashBoard_view.fxml");
    }

    @javafx.fxml.FXML
    public void reportViewOA(ActionEvent actionEvent) {
    }
}