package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.EventProposal;
import c213.dosaoopproject.Bushra.U07.util.EventSelectionHolder;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class U07G1_eventApprovalQueueController {

    @FXML private TableView<EventProposal> eventsTableView;
    @FXML private TableColumn<EventProposal, String> eventNameTableC;
    @FXML private TableColumn<EventProposal, String> clubNameTableC;
    @FXML private TableColumn<EventProposal, String> eventDateTableC;
    @FXML private TableColumn<EventProposal, String> budgetTableC;
    @FXML private TableColumn<EventProposal, String> riskTableC;
    @FXML private TableColumn<EventProposal, String> statusTableC;

    @FXML private Label totalCountLabel;
    @FXML private Button viewDetailsButton;

    private final String DATA_FILE = "events_data.dat";

    @FXML
    public void initialize() {
        // Map Table Columns to Model Properties
        eventNameTableC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        clubNameTableC.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        eventDateTableC.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        budgetTableC.setCellValueFactory(new PropertyValueFactory<>("budget"));
        riskTableC.setCellValueFactory(new PropertyValueFactory<>("riskLevel"));
        statusTableC.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Event 4: Read objects from binary file and display in TableView
        loadEventData();

        // Enable "View Details" button only when a row is selected
        eventsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            viewDetailsButton.setDisable(newVal == null);
        });
    }

    private void loadEventData() {
        ArrayList<EventProposal> list = BinaryFileUtil.readList(DATA_FILE);

        // Populate initial sample data if file is missing/empty
        if (list == null || list.isEmpty()) {
            list = new ArrayList<>();
            list.add(new EventProposal("National Tech Fest", "IUB CSE Society", "15 August 2026", "Auditorium", "120000", "Inter-University", "HIGH", "Pending Review", true, true));
            list.add(new EventProposal("Cultural Night 2026", "Music Club", "20 September 2026", "Open Plaza", "45000", "Intra-University", "LOW", "Pending Review", true, false));
            BinaryFileUtil.saveList(DATA_FILE, list);
        }

        ObservableList<EventProposal> observableList = FXCollections.observableArrayList(list);
        eventsTableView.setItems(observableList);
        totalCountLabel.setText("Total: " + list.size() + " items");
    }

    // Event 5: Select row and view details
    @FXML
    public void handleViewDetails(ActionEvent event) {
        EventProposal selected = eventsTableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            EventSelectionHolder.setSelectedEvent(selected);

            Node source = (Node) event.getSource();
            AnchorPane contentArea = (AnchorPane) source.getScene().lookup("#contentArea");

            if (contentArea != null) {
                SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G1_eventDetailApproval.fxml");
            }
        }
    }
}