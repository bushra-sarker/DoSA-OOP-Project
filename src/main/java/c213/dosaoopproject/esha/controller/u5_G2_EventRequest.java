package c213.dosaoopproject.esha.controller;
import c213.dosaoopproject.esha.model.Request;
import c213.dosaoopproject.esha.model.RequestStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class u5_G2_EventRequest
{    @FXML private TextArea eventDescArea;
    @FXML private DatePicker eventDatePicker;
    @FXML private Label statusLabel;

    private int executiveId; // set from session context when this view is loaded
    @FXML
    private Button submitEventReqBtn;

    public void setExecutiveId(int executiveId) {
        this.executiveId = executiveId;
    }

    @FXML
    private void handleSubmitEventRequest(ActionEvent event) {
        if (eventDescArea.getText().isBlank() || eventDatePicker.getValue() == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Please fill in the description and date.");
            return;
        }
        String details = "Date: " + eventDatePicker.getValue() + "\nDescription: " + eventDescArea.getText();
        RequestStore.getInstance().addRequest(new Request("Event Request", details));

        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Event request submitted to DoSA Coordinator!");
        eventDescArea.clear();
        eventDatePicker.setValue(null);

    }
}