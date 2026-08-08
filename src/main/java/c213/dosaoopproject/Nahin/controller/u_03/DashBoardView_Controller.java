package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.commonClass.model.User;
import c213.dosaoopproject.commonClass.util.SessionManager;
import javafx.scene.control.Label;

public class DashBoardView_Controller
{
    @javafx.fxml.FXML
    private Label titleUserNameLBL;


    @javafx.fxml.FXML
    public void initialize() {
        User currentUser = SessionManager.getInstance().getCurrentUser();

        if (currentUser != null){
            titleUserNameLBL.setText("Welcome, "+currentUser.getUserId());
        }

    }
}