package c213.dosaoopproject.Nahin.controller.u_04;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import c213.dosaoopproject.Nahin.model.u_04.VenueRequestApprove;
import static c213.dosaoopproject.Nahin.utility.Navigation.navigate;

public class G_6_Venue_Rqst_Controller {

    @javafx.fxml.FXML
    private TableColumn<VenueRequestApprove,String> idCOL;
    @javafx.fxml.FXML
    private TableColumn<VenueRequestApprove,String> eventNameCOL;
    @javafx.fxml.FXML
    private TableColumn<VenueRequestApprove,String> requestedVenueCOL;
    @javafx.fxml.FXML
    private TableColumn<VenueRequestApprove,String> statusCOL;
    @javafx.fxml.FXML
    private TableView<VenueRequestApprove> venueRqstTABleView;


    @javafx.fxml.FXML
    public void initialize() {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        eventNameCOL.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        requestedVenueCOL.setCellValueFactory(new PropertyValueFactory<>("requestedVenue"));
        statusCOL.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadDummyData();
    }


    //Data passing to details view
    @javafx.fxml.FXML
    public void viewDetailOA(ActionEvent actionEvent) throws IOException {
        VenueRequestApprove selectedRequest = venueRqstTABleView.getSelectionModel().getSelectedItem();

        if (selectedRequest != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Nahin/fxmlView/u4_G6_Vrqst_details.fxml"));
            Parent root = loader.load();

            G_6_VenueRqst_detail_details_Controller detailsController = loader.getController();
            detailsController.receiveData(selectedRequest);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            venueRqstTABleView.refresh();
        }
    }

    @javafx.fxml.FXML
    public void backOA(ActionEvent actionEvent) throws IOException {
        navigate(actionEvent, "/Nahin/fxmlView/u4_dashBoard.fxml");
    }

    @javafx.fxml.FXML
    public void loadTableOA(ActionEvent actionEvent) {
        venueRqstTABleView.refresh();
    }




    //Dummy Data
    private void loadDummyData() {
        ArrayList<VenueRequestApprove> venueRequestList = new ArrayList<>();

        venueRequestList.add(new VenueRequestApprove("VR001", "Annual Fest", "Tech Club", "Auditorium", "Fariha Noor", LocalDate.of(2026, 8, 15), "10:00 AM"));
        venueRequestList.add(new VenueRequestApprove("VR002", "Science Fair", "Science Club", "Multipurpose Hall", "Karim", LocalDate.of(2026, 8, 20), "02:00 PM"));
        venueRequestList.add(new VenueRequestApprove("VR003", "Debate Competition", "Debate Club", "Seminar Room", "Fatima", LocalDate.of(2026, 8, 25), "03:00 PM"));

        venueRqstTABleView.getItems().clear();
        if(venueRequestList!=null) {
            venueRqstTABleView.getItems().addAll(venueRequestList);
        }
    }
}