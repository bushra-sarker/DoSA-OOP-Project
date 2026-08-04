package c213.dosaoopproject.Bushra.U07.controller;
import c213.dosaoopproject.Bushra.U07.model.Event;
import c213.dosaoopproject.Bushra.U07.util.EventManager;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import c213.dosaoopproject.Bushra.U07.controller.U07G1_eventDetailApprovalController;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class U07G1_eventApprovalQueueController
{
    @javafx.fxml.FXML
    private Button resetFiltersButton;
    @javafx.fxml.FXML
    private ComboBox<String> statusFilterComboBox;
    @javafx.fxml.FXML
    private Label totalCountLabel;
    @javafx.fxml.FXML
    private ComboBox<String> riskFilterComboBox;
    @javafx.fxml.FXML
    private TableView<Event> eventsTableView;
    @javafx.fxml.FXML
    private Button viewDetailsButton;
    @javafx.fxml.FXML
    private TextField searchTextField;
    private TableColumn<Event, String> eventNameTableC;
    private TableColumn<Event, String> clubNameTableC;
    private TableColumn<Event, String> eventDateTableC;
    private TableColumn<Event, Double> budgetTableC;
    private TableColumn<Event, String> riskTableC;
    private TableColumn<Event, String> statusTableC;


    //private ObservableList<Event> eventList;
    private ObservableList<Event> eventList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {
        //TableC
        eventNameTableC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        clubNameTableC.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        eventDateTableC.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        budgetTableC.setCellValueFactory(new PropertyValueFactory<>("budget"));
        riskTableC.setCellValueFactory(new PropertyValueFactory<>("riskLevel"));
        statusTableC.setCellValueFactory(new PropertyValueFactory<>("status"));

        //comboBox
        statusFilterComboBox.getItems().addAll("All", "Pending", "Approved", "Rejected");
        riskFilterComboBox.getItems().addAll("All", "Low", "Medium", "High");

        loadEvents();
    }

    //to load event from the binary file
    private void loadEvents() {
        // 1 line fetches or auto-creates mock data!
        List<Event> events = EventManager.loadEvents();

        eventList = FXCollections.observableArrayList(events);
        eventsTableView.setItems(eventList);
        totalCountLabel.setText(String.valueOf(eventList.size()));
    }

    // Helper method to create dummy data
    private List<Event> createDummyEvents() {
        List<Event> list = new ArrayList<>();

        // Make sure Event model implements java.io.Serializable!
        Event e1 = new Event("E101", "Spring Cultural Fest", "Cultural Club", "2026-05-20", 45000.0, "Medium", "Pending", "Auditorium Main Hall");
        Event e2 = new Event("E102", "Inter-Uni Hackathon", "IEEE Student Branch", "2026-06-15", 85000.0, "High", "Pending", "Lab 402 & 403");
        Event e3 = new Event("E103", "Blood Donation Camp", "ROVER Scout", "2026-04-10", 12000.0, "Low", "Approved", "Cafeteria Grounds");

        list.add(e1);
        list.add(e2);
        list.add(e3);

        return list;
    }


    @javafx.fxml.FXML
    public void handleViewDetails(ActionEvent actionEvent) {
        Event selected = eventsTableView.getSelectionModel().getSelectedItem();

        if (selected == null){
            AlertUtil.showWarning("No selection", "Please select an event first.");
            return;
        }

        Pane contentArea = (Pane) eventsTableView.getScene().lookup("#contentArea");
        FXMLLoader loader = SceneSwitcher.switchContent(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G1_eventDetailApproval.fxml");

        if (loader != null && loader.getController() != null){
            U07G1_eventDetailApprovalController detailController = loader.getController();
            detailController.setSelectedEvent(selected);
        }
    }
}