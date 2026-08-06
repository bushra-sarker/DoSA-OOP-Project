package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.commonClass.User;
import javafx.scene.control.Label;

public class DashBoardView_Controller
{
    @javafx.fxml.FXML
    private Label titleUserNameLBL;


    @javafx.fxml.FXML
    public void initialize() {
    }
    public void setUser(User user){
        titleUserNameLBL.setText("Welcome, "+user.getFullName());
    }
}