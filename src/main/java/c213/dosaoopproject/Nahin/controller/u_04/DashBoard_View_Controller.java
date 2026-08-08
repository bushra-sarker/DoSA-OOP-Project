package c213.dosaoopproject.Nahin.controller.u_04;

import c213.dosaoopproject.commonClass.model.User;
import c213.dosaoopproject.commonClass.util.SessionManager;
import javafx.scene.control.Label;

public class DashBoard_View_Controller
{
    @javafx.fxml.FXML
    private Label officerUserNameLBL;

    @javafx.fxml.FXML
    public void initialize() {
        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser != null){
            officerUserNameLBL.setText("Welcome, "+currentUser.getUserId());
        }
    }
}