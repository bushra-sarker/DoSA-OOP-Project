package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.MajorEvent;
import c213.dosaoopproject.Bushra.U07.util.EventManager;
import commonClass.util.SubViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class U07G1_eventApprovalQueueController {

    @FXML private TableView<MajorEvent> eventsTableView;
    @FXML private TableColumn<MajorEvent, String> eventNameCol;
    @FXML private TableColumn<MajorEvent, String> clubNameCol;
    @FXML private TableColumn<MajorEvent, LocalDate> eventDateCol;
    @FXML private TableColumn<MajorEvent, Double> budgetCol;
    @FXML private TableColumn<MajorEvent, String> riskCol;
    @FXML private TableColumn<MajorEvent, String> statusCol;

    @FXML private Button viewDetailsButton;
    @FXML private Label totalCountLabel;

    private ArrayList<MajorEvent> masterList;
    private ObservableList<MajorEvent> observableEvents;

    @FXML
    public void initialize() {
        // Map TableView columns to MajorEvent model properties
        eventNameCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        clubNameCol.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        eventDateCol.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        budgetCol.setCellValueFactory(new PropertyValueFactory<>("requestedBudget"));
        riskCol.setCellValueFactory(new PropertyValueFactory<>("riskLevel"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Enable "View Details" button only when a row is selected
        viewDetailsButton.disableProperty().bind(
                eventsTableView.getSelectionModel().selectedItemProperty().isNull()
        );

        loadTableData();
    }

    private void loadTableData() {
        masterList = EventManager.loadEvents();
        observableEvents = FXCollections.observableArrayList(masterList);
        eventsTableView.setItems(observableEvents);
        totalCountLabel.setText("Total: " + observableEvents.size() + " items");
    }


    @FXML
    private void handleViewDetails() {
        MajorEvent selectedEvent = eventsTableView.getSelectionModel().getSelectedItem();

        if (selectedEvent != null) {
            // 1. Get the container StackPane from the main shell controller
            StackPane contentArea = U07_HeadOfDoSAViewController.getInstance().getContentArea();

            // 2. Load the view and get its controller using SubViewSwitcher
            U07G1_eventDetailApprovalController detailController = SubViewSwitcher.loadSubView(
                    contentArea,
                    "/c213/dosaoopproject/Bushra/U07/U07G1_eventDetailApproval.fxml"
            );

            // 3. Pass the selected event data to the controller
            if (detailController != null) {
                detailController.setEventData(selectedEvent);
            }
        }
    }


}