package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.model.u_03.LeaveApplication;
import c213.dosaoopproject.Nahin.utility.FileManager;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.Navigation.newStage;

public class G5_leave_request_view_Controller
{
    @javafx.fxml.FXML
    private TableColumn<LeaveApplication, String> leaveReasonCOL;
    @javafx.fxml.FXML
    private TableColumn<LeaveApplication, String> statusCOL;
    @javafx.fxml.FXML
    private TableColumn<LeaveApplication, String> activityNameCOL;
    @javafx.fxml.FXML
    private TableColumn<LeaveApplication, Integer> applicationIDCOL;
    @javafx.fxml.FXML
    private TableView<LeaveApplication> ApplicationHistoryVIEW;
    @javafx.fxml.FXML
    private TableColumn<LeaveApplication, LocalDate> applicationDATE;

    @javafx.fxml.FXML
    public void initialize() {
        applicationIDCOL.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        activityNameCOL.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        leaveReasonCOL.setCellValueFactory(new PropertyValueFactory<>("reason"));
        applicationDATE.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusCOL.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadHistory();
    }
    //to load all data from file
    private void loadHistory(){
        ArrayList<LeaveApplication> history = readFile("LeaveRequests.bin");
        if(history!=null){
            ApplicationHistoryVIEW.getItems().addAll(history);
        }
    }

    @javafx.fxml.FXML
    public void back_to_DashOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3_dashBoard_view.fxml");
    }

    @javafx.fxml.FXML
    public void refreshTableOA(ActionEvent actionEvent) {
        ApplicationHistoryVIEW.getItems().clear();
        loadHistory();
    }

    @javafx.fxml.FXML
    public void newLeeaveRqstOA(ActionEvent actionEvent) throws IOException {
        newStage(actionEvent, "/Nahin/fxmlView/u3G5_leave_Rqst_form.fxml");
    }
}