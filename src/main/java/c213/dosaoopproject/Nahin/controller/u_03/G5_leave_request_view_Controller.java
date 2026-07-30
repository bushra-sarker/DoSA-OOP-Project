package c213.dosaoopproject.Nahin.controller.u_03;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;

import java.io.IOException;

import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.Navigation.newStage;

public class G5_leave_request_view_Controller
{
    @javafx.fxml.FXML
    private TableColumn rqstIDCOL;
    @javafx.fxml.FXML
    private TableColumn leaveReasonCOL;
    @javafx.fxml.FXML
    private TableColumn statusCOL;
    @javafx.fxml.FXML
    private TableColumn activityNameCOL;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void back_to_DashOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3_dashBoard_view.fxml");
    }

    @javafx.fxml.FXML
    public void refreshTableOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void newLeeaveRqstOA(ActionEvent actionEvent) throws IOException {
        newStage(actionEvent, "/Nahin/fxmlView/u3G5_leave_Rqst_form.fxml");
    }
}