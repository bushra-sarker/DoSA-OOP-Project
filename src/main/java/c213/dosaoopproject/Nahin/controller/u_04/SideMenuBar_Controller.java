package c213.dosaoopproject.Nahin.controller.u_04;

import javafx.event.ActionEvent;

import java.io.IOException;

import static commonClass.Navigation.navigate;

public class SideMenuBar_Controller
{
    @javafx.fxml.FXML
    public void initialize() {
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
    public void dashboardVwOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u4_dashBoard.fxml");
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
    public void logOutOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void registerCLUBViewOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent,"/c213/dosaoopproject/Nahin/u4_G1_clubRegistrationList_view.fxml");
    }
}