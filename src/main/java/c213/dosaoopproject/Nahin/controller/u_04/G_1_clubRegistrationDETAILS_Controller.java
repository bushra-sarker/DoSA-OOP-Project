package c213.dosaoopproject.Nahin.controller.u_04;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import static commonClass.Navigation.newStage;

public class G_1_clubRegistrationDETAILS_Controller
{

    @javafx.fxml.FXML
    private Label setPurposeLBL;
    @javafx.fxml.FXML
    private Label setCategoryLBL;
    @javafx.fxml.FXML
    private Label setContactLBL;
    @javafx.fxml.FXML
    private Label setDateLBL;
    @javafx.fxml.FXML
    private Label setClubNmLBL;
    @javafx.fxml.FXML
    private Label showAPPLidLBL;
    @javafx.fxml.FXML
    private Label setFounderLBL;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void rejectButtonOA(ActionEvent actionEvent) throws IOException{
        newStage(actionEvent,"/c213/dosaoopproject/Nahin/remarks_dialogView.fxml");
    }

    @javafx.fxml.FXML
    public void approveButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void closeDetailsOA(ActionEvent actionEvent) {
            Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();
    }
}