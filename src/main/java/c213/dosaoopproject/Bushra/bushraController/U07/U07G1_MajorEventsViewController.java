package c213.dosaoopproject.Bushra.bushraController.U07;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class U07G1_MajorEventsViewController {

    @FXML
    private TableView<?> tvPendingMajorEvents;
    @FXML
    private TableColumn<?, ?> colEventName;
    @FXML
    private TableColumn<?, ?> colClubName;
    @FXML
    private TableColumn<?, ?> colEventDate;
    @FXML
    private TableColumn<?, ?> colRequestedBudget;
    @FXML
    private TableColumn<?, ?> colRiskLevel;

    @FXML
    public void initialize() {
        // Initialization logic for Major Events view goes here
    }

    @FXML
    public void handleApprove(ActionEvent actionEvent) {
        System.out.println("Approve button clicked!");
        // Add approval logic here
    }

    @FXML
    public void handleReject(ActionEvent actionEvent) {
        System.out.println("Reject button clicked!");
        // Add rejection logic here
    }
}