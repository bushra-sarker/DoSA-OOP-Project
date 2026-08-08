package c213.dosaoopproject.esha.controller;

import c213.dosaoopproject.esha.model.Request;
import c213.dosaoopproject.esha.model.RequestStore;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;

public class PendingRequestsController {

    @FXML private TableView<Request> requestsTable;
    @FXML private TableColumn<Request, Integer> idColumn;
    @FXML private TableColumn<Request, String> typeColumn;
    @FXML private TableColumn<Request, String> detailsColumn;
    @FXML private TableColumn<Request, String> dateColumn;
    @FXML private TableColumn<Request, String> statusColumn;
    @FXML private TextArea noteArea;
    @FXML private Label statusLabel;
    @FXML private Button approveBtn;
    @FXML private Button rejectBtn;
    @FXML private Button refreshBtn;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cd ->
                new SimpleIntegerProperty(cd.getValue().getRequestId()).asObject());
        typeColumn.setCellValueFactory(cd ->
                new SimpleStringProperty(cd.getValue().getType()));
        detailsColumn.setCellValueFactory(cd ->
                new SimpleStringProperty(cd.getValue().getDetails().replace("\n", " | ")));
        dateColumn.setCellValueFactory(cd ->
                new SimpleStringProperty(cd.getValue().getSubmittedAt().format(FMT)));
        statusColumn.setCellValueFactory(cd ->
                new SimpleStringProperty(cd.getValue().getStatus()));

        loadRequests();
    }

    private void loadRequests() {
        requestsTable.setItems(FXCollections.observableArrayList(
                RequestStore.getInstance().getAllRequests()));
    }

    private Request requireSelection() {
        Request selected = requestsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Please select a request first.");
        }
        return selected;
    }

    @FXML
    private void handleApprove(ActionEvent event) {
        Request selected = requireSelection();
        if (selected == null) return;

        if (!"Pending".equals(selected.getStatus())) {
            statusLabel.setStyle("-fx-text-fill:orange;");
            statusLabel.setText("This request has already been " + selected.getStatus().toLowerCase() + ".");
            return;
        }

        selected.setStatus("Approved");
        if (!noteArea.getText().isBlank()) {
            selected.setCoordinatorNote(noteArea.getText());
        }
        requestsTable.refresh();
        noteArea.clear();
        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Request #" + selected.getRequestId() + " (" + selected.getType() + ") APPROVED ✔");
    }

    @FXML
    private void handleReject(ActionEvent event) {
        Request selected = requireSelection();
        if (selected == null) return;

        if (!"Pending".equals(selected.getStatus())) {
            statusLabel.setStyle("-fx-text-fill:orange;");
            statusLabel.setText("This request has already been " + selected.getStatus().toLowerCase() + ".");
            return;
        }

        selected.setStatus("Rejected");
        if (!noteArea.getText().isBlank()) {
            selected.setCoordinatorNote(noteArea.getText());
        }
        requestsTable.refresh();
        noteArea.clear();
        statusLabel.setStyle("-fx-text-fill:red;");
        statusLabel.setText("Request #" + selected.getRequestId() + " (" + selected.getType() + ") REJECTED ✘");
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadRequests();
        statusLabel.setStyle("-fx-text-fill:black;");
        statusLabel.setText("Requests refreshed. Total: " + requestsTable.getItems().size());
    }
}
