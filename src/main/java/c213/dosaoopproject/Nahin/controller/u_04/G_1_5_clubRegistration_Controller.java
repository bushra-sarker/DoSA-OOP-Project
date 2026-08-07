package c213.dosaoopproject.Nahin.controller.u_04;
import c213.dosaoopproject.Nahin.model.u_04.NewClubRegister;
import c213.dosaoopproject.Nahin.model.u_04.SuspendClub;
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

public class G_1_5_clubRegistration_Controller
{
    @javafx.fxml.FXML
    private TableColumn<NewClubRegister, LocalDate> dateCOL;
    @javafx.fxml.FXML
    private TableColumn<NewClubRegister, String> statusCOL;
    @javafx.fxml.FXML
    private TableColumn<NewClubRegister, String> categoryCOL;
    @javafx.fxml.FXML
    private TableColumn<NewClubRegister, Integer> appliIDCOL;
    @javafx.fxml.FXML
    private TableColumn<NewClubRegister, String> clubNameCOL;
    @javafx.fxml.FXML
    private TableView<NewClubRegister> proposalLISTTableView;

    //Suspension club
    @javafx.fxml.FXML
    private TextField spIDTF;
    @javafx.fxml.FXML
    private TableColumn<SuspendClub,Integer> spClubID;
    @javafx.fxml.FXML
    private TableColumn<SuspendClub,String> spClubName;
    @javafx.fxml.FXML
    private TableView<SuspendClub> spClubTableview;
    @javafx.fxml.FXML
    private ComboBox<String> filterBySTATUSCOMBO;
    @javafx.fxml.FXML
    private TableColumn<SuspendClub,LocalDate> lastActivityDate;
    @javafx.fxml.FXML
    private TableColumn<SuspendClub,String> spStatus;

    private ArrayList<SuspendClub> clubList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        appliIDCOL.setCellValueFactory(new PropertyValueFactory<>("applicationID"));
        clubNameCOL.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        categoryCOL.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateCOL.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        statusCOL.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadApplications();

        //suspension
        spClubID.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        spClubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        lastActivityDate.setCellValueFactory(new PropertyValueFactory<>("lastActivityDate"));
        spStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadDummyClubs();

        filterBySTATUSCOMBO.getItems().addAll("Inactive","Active","Suspended");
    }

    public void loadApplications(){
        ArrayList<NewClubRegister> dataFile = readFile("NewClubApplications.bin");
        proposalLISTTableView.getItems().clear();
        if(dataFile!=null){
            proposalLISTTableView.getItems().addAll(dataFile);
        }
    }

    @javafx.fxml.FXML
    public void refreshTableOA(ActionEvent actionEvent) {
        proposalLISTTableView.getItems().clear();
        loadApplications();
    }


    //data passing to see details
    @javafx.fxml.FXML
    public void detailsViewOA(ActionEvent actionEvent) {
        NewClubRegister selectedApplication = proposalLISTTableView.getSelectionModel().getSelectedItem();
        if(selectedApplication==null){
            showAlert(Alert.AlertType.ERROR,"Please select an application");
            return;
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Nahin/fxmlView/u4_G1_clubRegistrationDetails_view.fxml"));
            Parent root = loader.load();

            G_1_clubRegistrationDETAILS_Controller controller = loader.getController();
            controller.receiveData(selectedApplication);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @javafx.fxml.FXML
    public void backTodashOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u4_dashBoard.fxml");
    }


    //to suspend inactive club
    @javafx.fxml.FXML
    public void suspendClubOA(ActionEvent actionEvent) {
        if(spIDTF.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"Enter Club ID");
            return;
        }
        int id = Integer.parseInt(spIDTF.getText());

        //find club by id
        for(SuspendClub club:clubList){
            if(club.getClubID()==id){
                club.suspendClub();

                spClubTableview.refresh();

                showAlert(Alert.AlertType.INFORMATION,"Suspended Successfully");
                return;
            }
        }showAlert(Alert.AlertType.ERROR,"Club Not Found");
    }



    //clear all filter
    @javafx.fxml.FXML
    public void spClearOA(ActionEvent actionEvent) {
        filterBySTATUSCOMBO.setValue(null);
        spClubTableview.getItems().clear();

        spClubTableview.getItems().addAll(clubList);
    }



    //check club by status
    @javafx.fxml.FXML
    public void spFilterOA(ActionEvent actionEvent) {
        String selectedStatus = filterBySTATUSCOMBO.getValue();
        spClubTableview.getItems().clear();

        for(SuspendClub club : clubList){
            if(club.getStatus().equals(selectedStatus)){

                spClubTableview.getItems().add(club);
            }
        }
    }



    //fake objects
    private void loadDummyClubs(){
        clubList.clear();

        clubList.add(new SuspendClub(101, "Programming Club", LocalDate.of(2026,7,10),false));
        clubList.add(new SuspendClub(102, "Photography Club", LocalDate.of(2025,12,10),false));
        clubList.add(new SuspendClub(103, "Business Club", LocalDate.of(2026,5,20),false));
        clubList.add(new SuspendClub(104, "Cultural Club", LocalDate.of(2026,1,3),false));
        clubList.add(new SuspendClub(105, "Debating Club", LocalDate.of(2025,10,15),false));
        clubList.add(new SuspendClub(106, "Chess Club", LocalDate.of(2026,4,15),false));

        spClubTableview.getItems().clear();
        spClubTableview.getItems().addAll(clubList);
    }
}