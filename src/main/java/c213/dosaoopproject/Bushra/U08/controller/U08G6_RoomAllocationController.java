package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.HostelRoom;
import c213.dosaoopproject.Bushra.U08.model.HousingApplication;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.ArrayList;

public class U08G6_RoomAllocationController
{
    @javafx.fxml.FXML
    private Label selectedRentLabel;
    @javafx.fxml.FXML
    private Button confirmAllocationButton;
    @javafx.fxml.FXML
    private Label applicationStatusLabel;
    @javafx.fxml.FXML
    private DatePicker allocationStartDateP;
    @javafx.fxml.FXML
    private Label studentIdLabel;
    @javafx.fxml.FXML
    private Label genderLabel;
    @javafx.fxml.FXML
    private Label studentNameLabel;
    @javafx.fxml.FXML
    private TableColumn<HostelRoom, String> roomStatusTableC;
    @javafx.fxml.FXML
    private TextArea housingNotesTextF;
    @javafx.fxml.FXML
    private TableColumn<HostelRoom, Double> monthlyRentTableC;
    @javafx.fxml.FXML
    private TableColumn<HostelRoom, String> hallNameTableC;
    @javafx.fxml.FXML
    private TableColumn<HostelRoom, Integer> capacityTableC;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private TableColumn<HostelRoom, String> roomNumberTableC;
    @javafx.fxml.FXML
    private TableColumn<HostelRoom, String> genderAllocationTableC;
    @javafx.fxml.FXML
    private Label applicationDateLabel;
    @javafx.fxml.FXML
    private Label selectedHallLabel;
    @javafx.fxml.FXML
    private Label urgencyPriorityLabel;
    @javafx.fxml.FXML
    private TableView<HostelRoom> availableRoomsTableView;
    @javafx.fxml.FXML
    private Label selectedRoomLabel;

    private HousingApplication targetApplication;
    private static final String FILE_PATH = "housing_data.dat";

    @FXML
    public void initialize() {
        // Map Table Columns
        hallNameTableC.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        roomNumberTableC.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        genderAllocationTableC.setCellValueFactory(new PropertyValueFactory<>("genderAllocation"));
        capacityTableC.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        monthlyRentTableC.setCellValueFactory(new PropertyValueFactory<>("monthlyRent"));
        roomStatusTableC.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));

        // Table Selection Listener
        availableRoomsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                selectedHallLabel.setText(newSel.getHallName());
                selectedRoomLabel.setText(newSel.getRoomNumber());
                selectedRentLabel.setText(String.format("BDT %.2f", newSel.getMonthlyRent()));
            } else {
                selectedHallLabel.setText("None Selected");
                selectedRoomLabel.setText("--");
                selectedRentLabel.setText("--");
            }
        });

        loadAvailableRooms();

        targetApplication = U08G6_HousingDashboardController.getSelectedApplication();

        if (targetApplication != null) {
            // Step 5: Display Student Details
            studentNameLabel.setText(targetApplication.getStudentName());
            studentIdLabel.setText(targetApplication.getStudentId());
            genderLabel.setText(targetApplication.getGender());
            applicationDateLabel.setText(targetApplication.getApplicationDate() != null ? targetApplication.getApplicationDate().toString() : "--");            urgencyPriorityLabel.setText(targetApplication.getUrgencyPriority());
            applicationStatusLabel.setText(targetApplication.getApplicationStatus());
        }
    }

    private void loadAvailableRooms() {
        ObservableList<HostelRoom> rooms = FXCollections.observableArrayList();
        rooms.add(new HostelRoom("Bijoy Hall", "302-A", "Male", 2, 4500.00, "Available"));
        rooms.add(new HostelRoom("Bijoy Hall", "405-B", "Male", 2, 4500.00, "Available"));
        rooms.add(new HostelRoom("Ekushey Hall", "102-A", "Female", 2, 4800.00, "Available"));
        rooms.add(new HostelRoom("Ekushey Hall", "204-C", "Female", 3, 4200.00, "Available"));

        availableRoomsTableView.setItems(rooms);
    }

    @FXML
    private void confirmAllocationOA(ActionEvent event) {
        if (targetApplication == null) {
            AlertUtil.showError("Error", "No student application selected for room allocation.");
            return;
        }

        HostelRoom selectedRoom = availableRoomsTableView.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            AlertUtil.showError("Validation Error", "Please select an available room from the table.");
            return;
        }

        // Step 6: Validate Inputs
        if (allocationStartDateP.getValue() == null) {
            AlertUtil.showError("Validation Error", "Please select an allocation start date.");
            return;
        }

        // Step 7: Read, Update, and Save File via BinaryFileUtil
        ArrayList<Object> applications = BinaryFileUtil.readObjects(FILE_PATH);
        if (applications == null) {
            AlertUtil.showError("File Error", "Unable to load housing records.");
            return;
        }

        for (Object obj : applications) {
            if (obj instanceof HousingApplication) {
                HousingApplication app = (HousingApplication) obj;
                if (app.getRequestId().equals(targetApplication.getRequestId())) {
                    app.setApplicationStatus("Allocated");
                    app.setAllocatedHall(selectedRoom.getHallName());
                    app.setAllocatedRoomNumber(selectedRoom.getRoomNumber());
                    app.setMonthlyRent(selectedRoom.getMonthlyRent());
                    app.setAllocationStartDate(LocalDate.parse(allocationStartDateP.getValue().toString()));
                    app.setHousingNotes(housingNotesTextF.getText() != null ? housingNotesTextF.getText().trim() : "");
                    break;
                }
            }
        }

        BinaryFileUtil.writeObjects(FILE_PATH, applications);

        // Step 8: Alert & Return to Dashboard
        AlertUtil.showSuccess("Success", "Room " + selectedRoom.getRoomNumber() + " allocated successfully.");
        returnToDashboard(event);
    }

    @FXML
    private void backOA(ActionEvent event) {
        returnToDashboard(event);
    }

    private void returnToDashboard(ActionEvent event) {
        Button button = (Button) event.getSource();
        AnchorPane contentArea = (AnchorPane) button.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G6_housingDashboard.fxml");
    }
}