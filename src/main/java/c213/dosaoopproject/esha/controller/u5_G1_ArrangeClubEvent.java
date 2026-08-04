package c213.dosaoopproject.esha.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
public class u5_G1_ArrangeClubEvent
{
    @FXML private TextField eventNameField;
    @FXML private TextArea descriptionArea;
    @FXML private DatePicker eventDatePicker;
    @FXML private TextField venueField;
    @FXML private ComboBox<String> statusCombo;
    @FXML private Label statusLabel;

    private Integer currentEventId; // null until an event has been created/loaded
    @FXML
    private Button viewDetailsBtn;
    @FXML
    private Button updateEventBtn;
    @FXML
    private Button cancelEventBtn;
    @FXML
    private Button createEventBtn;

    @FXML
    public void initialize() {
        statusCombo.getItems().setAll("Planned", "Confirmed", "Cancelled", "Completed");
        statusCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleCreateEvent(ActionEvent event) {
        if (!validateFields()) return;

        // TODO: replace with real service call, e.g.:
        // ArrangeClubEvent newEvent = new ArrangeClubEvent(eventNameField.getText(),
        //         descriptionArea.getText(), eventDatePicker.getValue(), venueField.getText(),
        //         statusCombo.getValue());
        // currentEventId = clubEventService.createEvent(newEvent);

        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Event created successfully.");
    }

    @FXML
    private void handleUpdateEvent(ActionEvent event) {
        if (currentEventId == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Create the event first before updating it.");
            return;
        }
        if (!validateFields()) return;

        // TODO: clubEventService.updateEvent(currentEventId, ...);

        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Event updated successfully.");
    }

    @FXML
    private void handleCancelEvent(ActionEvent event) {
        if (currentEventId == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("No event selected to cancel.");
            return;
        }

        // TODO: clubEventService.cancelEvent(currentEventId);

        statusCombo.setValue("Cancelled");
        statusLabel.setStyle("-fx-text-fill:orange;");
        statusLabel.setText("Event cancelled.");
    }

    @FXML
    private void handleViewDetails(ActionEvent event) {
        if (currentEventId == null) {
            statusLabel.setText("No event created yet.");
            return;
        }
        // TODO: fetch and display full details, e.g. in a dialog or a read-only panel
        statusLabel.setText("Showing details for event #" + currentEventId);
    }

    private boolean validateFields() {
        LocalDate date = eventDatePicker.getValue();
        if (eventNameField.getText().isBlank() || venueField.getText().isBlank() || date == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Event name, venue, and date are required.");
            return false;
        }
        return true;
    }
}
