package c213.dosaoopproject.esha.controller;
import c213.dosaoopproject.esha.model.Request;
import c213.dosaoopproject.esha.model.RequestStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
public class u5_G3_VenueBooking
{@FXML private TextField venueField;
    @FXML private DatePicker bookingDatePicker;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;
    @FXML private Label statusLabel;

    private int executiveId;
    @FXML
    private Button bookVenueBtn;

    public void setExecutiveId(int executiveId) {
        this.executiveId = executiveId;
    }
    @FXML
    private void handleBookVenue(ActionEvent event) {
        if (venueField.getText().isBlank() || bookingDatePicker.getValue() == null) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Venue and booking date are required.");
            return;
        }

        LocalTime start;
        LocalTime end;
        try {
            start = LocalTime.parse(startTimeField.getText());
            end = LocalTime.parse(endTimeField.getText());
        } catch (DateTimeParseException e) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("Enter times as HH:MM, e.g. 14:00.");
            return;
        }

        if (!end.isAfter(start)) {
            statusLabel.setStyle("-fx-text-fill:red;");
            statusLabel.setText("End time must be after start time.");
            return;
        }
        String details = "Venue: " + venueField.getText() + "\nDate: " + bookingDatePicker.getValue()
                + "\nTime: " + startTimeField.getText() + " - " + endTimeField.getText();
        RequestStore.getInstance().addRequest(new Request("Venue Booking", details));

        statusLabel.setStyle("-fx-text-fill:green;");
        statusLabel.setText("Venue booking submitted to DoSA Coordinator!");
    }}