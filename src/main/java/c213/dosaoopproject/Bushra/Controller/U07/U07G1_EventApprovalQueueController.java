package c213.dosaoopproject.Bushra.Controller.U07;

import c213.dosaoopproject.Bushra.Model.U07.EventModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class U07G1_EventApprovalQueueController
{
    @javafx.fxml.FXML
    private TableColumn<EventModel, Double> requestedBudgetTableCC;
    @javafx.fxml.FXML
    private TableView<EventModel> pendingMajorEventsTableV;
    @javafx.fxml.FXML
    private TableColumn<EventModel, String> eventDateTableCC;
    @javafx.fxml.FXML
    private TableColumn<EventModel, String> eventNameTableCC;
    @javafx.fxml.FXML
    private TableColumn<EventModel, String> riskLevelTableCC;
    @javafx.fxml.FXML
    private TableColumn<EventModel, String> clubNameTableCC;

    @javafx.fxml.FXML
    public void initialize() {
        // Link columns to EventModel fields
        eventNameTableCC.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        clubNameTableCC.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        requestedBudgetTableCC.setCellValueFactory(new PropertyValueFactory<>("requestedBudget"));
        eventDateTableCC.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        riskLevelTableCC.setCellValueFactory(new PropertyValueFactory<>("riskLevel"));

        // Populate table with test data
        ObservableList<EventModel> eventsList = FXCollections.observableArrayList(
                new EventModel("National Tech Fest 2026", "Robotics Club", 120000.0, "2026-08-15", "High", true, true),
                new EventModel("Inter-Uni Cultural Gala", "Cultural Club", 85000.0, "2026-09-02", "Medium", true, false),
                new EventModel("Annual Debate Championship", "Debate Society", 60000.0, "2026-08-28", "Low", false, true)
        );

        pendingMajorEventsTableV.setItems(eventsList);
    }

    @javafx.fxml.FXML
    public void viewDetailsButton(ActionEvent actionEvent) {
        EventModel selectedEvent = pendingMajorEventsTableV.getSelectionModel().getSelectedItem();

        if (selectedEvent == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select an event from the table first!");
            alert.setHeaderText("No Selection");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/c213/dosaoopproject/Bushra/View/U07/U07G1_eventDetailApproval.fxml"));

            // 1. Load FXML view first
            Parent view = loader.load();

            // 2. Access target controller and pass data
            U07G1_EventDetailApprovalController controller = loader.getController();
            controller.initData(selectedEvent);

            // 3. Swap sub-view in the central contentArea StackPane
            Node sourceNode = (Node) actionEvent.getSource();
            StackPane contentArea = (StackPane) sourceNode.getScene().lookup("#contentArea");
            if (contentArea != null) {
                contentArea.getChildren().setAll(view);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}