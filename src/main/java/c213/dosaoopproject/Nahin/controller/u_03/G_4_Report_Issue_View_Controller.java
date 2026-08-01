package c213.dosaoopproject.Nahin.controller.u_03;

import c213.dosaoopproject.Nahin.model.u_03.ReportConcerns;
import c213.dosaoopproject.Nahin.utility.FileManager;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;

public class G_4_Report_Issue_View_Controller
{
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns, LocalDate> dateCOL;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,String> statusCOL;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,Integer> complaintIDCOL;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,String> categoryCOL;
    @javafx.fxml.FXML
    private TextField searchTF;
    @javafx.fxml.FXML
    private TableView<ReportConcerns> reportViewTABLE;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,String> eventNameCOL;

    @javafx.fxml.FXML
    public void initialize() {
        complaintIDCOL.setCellValueFactory(new PropertyValueFactory<>("complaintID"));
        eventNameCOL.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        categoryCOL.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateCOL.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusCOL.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadReportDetails();
    }

    //load stored data
    private void loadReportDetails(){
        ArrayList<ReportConcerns> reportList = FileManager.readFile("Volunteer_Issue_reports.bin");

        if(reportList!=null){
            reportViewTABLE.getItems().addAll(reportList);
        }
    }

    @javafx.fxml.FXML
    public void refreshTableOA(ActionEvent actionEvent) {
        reportViewTABLE.getItems().clear();
        loadReportDetails();
    }

    @javafx.fxml.FXML
    public void searchOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u3_dashBoard_view.fxml");
    }

    @javafx.fxml.FXML
    public void createReportOA(ActionEvent actionEvent) throws IOException{
        navigate(actionEvent, "/Nahin/fxmlView/u3G4_issueReporting_Form_view.fxml");
    }
}