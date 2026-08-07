package c213.dosaoopproject.Nahin.controller.u_04;

import c213.dosaoopproject.Nahin.commonClass.User;
import javafx.scene.control.Label;

public class DashBoard_View_Controller
{
    @javafx.fxml.FXML
    private Label officerUserNameLBL;

    @javafx.fxml.FXML
    public void initialize(User user) {
        officerUserNameLBL.setText("Welcome, "+user.getFullName());
    }}