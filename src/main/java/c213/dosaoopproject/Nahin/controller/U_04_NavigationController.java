package c213.dosaoopproject.Nahin.controller;

import c213.dosaoopproject.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class U_04_NavigationController {
    @javafx.fxml.FXML
    private ImageView ppImageView;
    @javafx.fxml.FXML
    private Label nameLabel;
    @javafx.fxml.FXML
    private Label userIdLabel;

    public void navigateTo(ActionEvent actionEvent, String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("DoSA Management Simulation");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void notificationOA(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void eventCalenderOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void resourcesOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void suspendClubOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void dashboardVwOA(ActionEvent actionEvent) throws IOException{
        navigateTo(actionEvent,"/c213/dosaoopproject/Nahin/u4_dashboard_view.fxml");
    }

    @javafx.fxml.FXML
    public void announcementsOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void complaintsOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void oversightCLUBOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void venueRqstOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void registerCLUBViewOA(ActionEvent actionEvent) throws IOException{
        navigateTo(actionEvent,"/c213/dosaoopproject/Nahin/u4_G1_clubRegistrationList_view.fxml");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
    }
}
