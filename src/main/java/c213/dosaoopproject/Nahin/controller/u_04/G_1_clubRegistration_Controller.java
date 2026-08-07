package c213.dosaoopproject.Nahin.controller.u_04;
import c213.dosaoopproject.Nahin.model.u_04.NewClubRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static c213.dosaoopproject.Nahin.utility.FileManager.readFile;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;
import static c213.dosaoopproject.Nahin.utility.Navigation.newStage;
import static c213.dosaoopproject.Nahin.utility.VIA.showAlert;

public class G_1_clubRegistration_Controller
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

    @javafx.fxml.FXML
    public void initialize() {
        appliIDCOL.setCellValueFactory(new PropertyValueFactory<>("applicationID"));
        clubNameCOL.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        categoryCOL.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateCOL.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        statusCOL.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadApplications();
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
}