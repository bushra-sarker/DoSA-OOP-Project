package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.EventProposal;
import commonClass.data.BinaryFileUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class U07G1_EventApprovalQueueController {

    @FXML
    private TableColumn requestedBudgetTableCC;
    @FXML
    private TableView pendingMajorEventsTableView;
    @FXML
    private TableColumn eventDateTableCC;
    @FXML
    private TableColumn eventNameTableCC;
    @FXML
    private TableColumn riskLevelTableCC;
    @FXML
    private TableColumn clubNameTableCC;


    @FXML
    public void initialize() {
        // Map TableView columns to EventProposal getter properties
        eventNameTableCC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        clubNameTableCC.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        requestedBudgetTableCC.setCellValueFactory(new PropertyValueFactory<>("requestedBudget"));
        eventDateTableCC.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        riskLevelTableCC.setCellValueFactory(new PropertyValueFactory<>("riskLevel"));

        loadPendingEvents();
    }

    private void loadPendingEvents() {
        // Read your data as an ArrayList from binary file
        ArrayList<EventProposal> loadedList = BinaryFileUtil.readList("events_data.dat");
        ArrayList<EventProposal> pendingList = new ArrayList<>();

        if (loadedList != null) {
            for (EventProposal ep : loadedList) {
                if ("Pending".equalsIgnoreCase(ep.getStatus())) {
                    pendingList.add(ep);
                }
            }
        }

        pendingMajorEventsTableView.setItems(FXCollections.observableArrayList(pendingList));
    }

    @Deprecated
    void reviewEventDetailsOA(ActionEvent event) {
        EventProposal selectedEvent = (EventProposal) pendingMajorEventsTableView.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an event proposal from the table to review.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/c213/dosaoopproject/Bushra/U07/U07G1_eventDetailApproval.fxml"));
            Parent detailView = loader.load();

            // Pass the selected event object to the detail controller
            U07G1_EventDetailApprovalController detailController = loader.getController();
            detailController.setEventData(selectedEvent);

            // Replace content pane
            StackPane contentArea = (StackPane) pendingMajorEventsTableView.getScene().lookup("#contentArea");
            if (contentArea != null) {
                contentArea.getChildren().setAll(detailView);
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not load the event detail approval view.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void viewDetailsOA(ActionEvent actionEvent) {
    }
}