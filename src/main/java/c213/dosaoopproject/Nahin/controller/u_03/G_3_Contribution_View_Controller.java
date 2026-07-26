package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.controller.U_03_NavigationController;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class G_3_Contribution_View_Controller extends U_03_NavigationController
{
    @javafx.fxml.FXML
    private Label participationLBL;
    @javafx.fxml.FXML
    private Label hourLBL;
    @javafx.fxml.FXML
    private Label volIDLBL;
    @javafx.fxml.FXML
    private Label statusCardLabel;
    @javafx.fxml.FXML
    private Label totalTaskLBL;
    @javafx.fxml.FXML
    private Label completedCARDLabel;
    @javafx.fxml.FXML
    private Label hourCardLabel;
    @javafx.fxml.FXML
    private Label participationCARDLabel;
    @javafx.fxml.FXML
    private Label statusLBL;
    @javafx.fxml.FXML
    private VBox pane_2;
    @javafx.fxml.FXML
    private VBox pane_1;


    @javafx.fxml.FXML
    public void initialize() {
        boolean hasContribution = checkIfUserHasContribution();
        if(hasContribution) {
            pane_1.setVisible(true);
            pane_1.setManaged(true);

            pane_2.setVisible(false);
            pane_2.setManaged(false);
        }else {
            pane_2.setVisible(true);
            pane_2.setManaged(true);

            pane_1.setVisible(false);
            pane_1.setManaged(false);
        }
    }

    private boolean checkIfUserHasContribution(){
        return false;
    }
}