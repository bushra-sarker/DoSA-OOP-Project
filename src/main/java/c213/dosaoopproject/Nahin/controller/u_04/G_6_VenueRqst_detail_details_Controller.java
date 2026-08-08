package c213.dosaoopproject.Nahin.controller.u_04;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import c213.dosaoopproject.Nahin.model.u_04.VenueRequestApprove;
import static c213.dosaoopproject.Nahin.utility.VIA.showAlert;

public class G_6_VenueRqst_detail_details_Controller {
    @javafx.fxml.FXML
    private Label clubNameLBL;
    @javafx.fxml.FXML
    private Label timeLBL;
    @javafx.fxml.FXML
    private Label requestedVenueLBL;
    @javafx.fxml.FXML
    private Label requestedBYLBL;
    @javafx.fxml.FXML
    private Label dateLBL;

    private VenueRequestApprove selectedRequest;

    @javafx.fxml.FXML
    public void initialize() {
    }

    public void receiveData(VenueRequestApprove selectedRequest) {
        this.selectedRequest = selectedRequest;

        if (selectedRequest != null) {
            clubNameLBL.setText(selectedRequest.getClubName());
            requestedVenueLBL.setText(selectedRequest.getRequestedVenue());
            requestedBYLBL.setText(selectedRequest.getRequestedBy());
            dateLBL.setText(selectedRequest.getFormattedDate());
            timeLBL.setText(selectedRequest.getRequestTime());
        }
    }


    @javafx.fxml.FXML
    public void rejectVrqstOA(ActionEvent actionEvent) {
        selectedRequest.reject();
        showAlert(Alert.AlertType.INFORMATION,"Rejected Successfully");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }



    @javafx.fxml.FXML
    public void checkIFAvailableOA(ActionEvent actionEvent) {
        //
    }


    @javafx.fxml.FXML
    public void approveVrqstOA(ActionEvent actionEvent) {
        if (selectedRequest != null) {
            selectedRequest.approve();
            showAlert(Alert.AlertType.INFORMATION,"Approved");
        }
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}