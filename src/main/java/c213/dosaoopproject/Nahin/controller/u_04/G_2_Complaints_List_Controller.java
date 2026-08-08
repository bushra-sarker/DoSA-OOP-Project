package c213.dosaoopproject.Nahin.controller.u_04;

import c213.dosaoopproject.Nahin.model.u_03.ReportConcerns;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.VIA.showAlert;

public class G_2_Complaints_List_Controller
{
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,Integer> complainIDCOL;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns, LocalDate> dateCOL;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,String> statusCOL;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,String> categoryCOL;
    @javafx.fxml.FXML
    private TableView<ReportConcerns> complaintTableView;
    @javafx.fxml.FXML
    private TableView<ReportConcerns> studentIssueTableView;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,Integer> stComplaintIDCOL;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,LocalDate> stDateCOL;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,String> stStatusCOL;
    @javafx.fxml.FXML
    private TableColumn<ReportConcerns,String> stIssueCategoryCOL;
    @javafx.fxml.FXML
    private DatePicker filterByDateDP;

    private static final String volunteerFile = "VolunteerIssuereports.bin";
//    private static final String studentFile = "StudentIssueReports.bin";

    @javafx.fxml.FXML
    public void initialize() {
        //volunteer records
        complainIDCOL.setCellValueFactory(new PropertyValueFactory<>("complaintID"));
        categoryCOL.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateCOL.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusCOL.setCellValueFactory(new PropertyValueFactory<>("status"));

        //student records
//        stComplaintIDCOL.setCellValueFactory(new PropertyValueFactory<>("complaintID"));
//        stIssueCategoryCOL.setCellValueFactory(new PropertyValueFactory<>("category"));
//        stDateCOL.setCellValueFactory(new PropertyValueFactory<>("date"));
//        stStatusCOL.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadComplaints();
    }



    private void loadComplaints(){
        //read volunteer reports
        ArrayList<ReportConcerns> vReports = readFile(volunteerFile);
        if(vReports!=null){
            complaintTableView.getItems().clear();
            complaintTableView.getItems().addAll(vReports);
        }
//        //read student issues
//        ArrayList<ReportConcerns> stReports = readFile(studentFile);
//        if(stReports!=null){
//            studentIssueTableView.getItems().clear();
//            studentIssueTableView.getItems().addAll(stReports);
//        }
    }



    //to see details & pass data to details page
    @javafx.fxml.FXML
    public void seeComplaintDetailsOA(ActionEvent actionEvent) {
        ReportConcerns selectreport = complaintTableView.getSelectionModel().getSelectedItem();

//        if(selectreport==null){
//            selectreport = studentIssueTableView.getSelectionModel().getSelectedItem();
//        }

        if(selectreport==null){
            showAlert(Alert.AlertType.WARNING,"Select a record first");
            return;
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Nahin/fxmlView/u4_G2_Complaints_details.fxml"));
            Parent root = loader.load();

            G_2_Complaints_details_Controller controller = loader.getController();
            controller.receiveData(selectreport);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    //clear filter & load table again
    @javafx.fxml.FXML
    public void clearFilterOA(ActionEvent actionEvent) {
        filterByDateDP.setValue(null);
        loadComplaints();
    }

    @javafx.fxml.FXML
    public void filterOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent)throws IOException {
        navigate(actionEvent,"/Nahin/fxmlView/u4_dashBoard.fxml");
    }

    @javafx.fxml.FXML
    public void reloadTableOA(ActionEvent actionEvent) {
        complaintTableView.getItems().clear(); studentIssueTableView.getItems().clear();
        loadComplaints();
    }
}