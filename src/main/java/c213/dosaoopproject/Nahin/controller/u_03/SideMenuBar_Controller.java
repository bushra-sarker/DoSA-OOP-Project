package c213.dosaoopproject.Nahin.controller.u_03;

import javafx.event.ActionEvent;
import java.io.IOException;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;

public class SideMenuBar_Controller
{
    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void certificateApplyOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G7_Apply_certificateView.fxml");
    }

    @javafx.fxml.FXML
    public void contributionViewOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G3_contribution_view.fxml");
    }

    @javafx.fxml.FXML
    public void logOutOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void registerViewOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G1_Register_view.fxml");
    }

    @javafx.fxml.FXML
    public void feedbackShareOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G8_volunteer_feedBack_View.fxml");
    }

    @javafx.fxml.FXML
    public void leaveRqstViewOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G5_leave_request_view.fxml");
    }

    @javafx.fxml.FXML
    public void dashboardViewOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3_dashBoard_view.fxml");
    }

    @javafx.fxml.FXML
    public void reportViewOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3G4_issueReporting_Form_view.fxml");
    }
}