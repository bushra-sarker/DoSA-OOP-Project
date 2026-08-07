package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.Bushra.U07.model.EventProposal;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.data.TextFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class U07G4_reportConfigController {

    @FXML private ComboBox<String> semesterComboBox;
    @FXML private ComboBox<String> yearComboBox;
    @FXML private Button generateReportButton;

    // Static variables to pass metrics to the display view
    public static int calculatedTotalEvents = 0;
    public static int calculatedTotalStudents = 0;

    private static final String EVENTS_FILE = "events_data.dat";
    private static final String BUDGETS_FILE = "club_budgets.txt";

    @FXML
    public void initialize() {
        semesterComboBox.getItems().addAll("Spring", "Summer", "Autumn");
        yearComboBox.getItems().addAll("2026", "2025", "2024");
    }

    @FXML
    public void generateReportOA(ActionEvent event) {
        // 1. VALIDATION
        if (semesterComboBox.getValue() == null || semesterComboBox.getValue().isEmpty()) {
            AlertUtil.showError("Error", "Please select a semester.");
            return;
        }

        if (yearComboBox.getValue() == null || yearComboBox.getValue().isEmpty()) {
            AlertUtil.showError("Error", "Please select a year.");
            return;
        }

        // 2. READ VALUES
        String selectedSemester = semesterComboBox.getValue();
        String selectedYear = yearComboBox.getValue();

        // 3. READ DATA & CALCULATE METRICS (event-4)
        ArrayList<EventProposal> events = BinaryFileUtil.readList(EVENTS_FILE);
        ArrayList<String> budgetLines = TextFileUtil.readLines(BUDGETS_FILE);

        calculatedTotalEvents = (events != null) ? events.size() : 0;
        calculatedTotalStudents = 0;

        if (events != null) {
            for (EventProposal ep : events) {
                // Read numeric portion of budget as mock attendance or parsed value
                calculatedTotalStudents += 150; // Mock average student reach count per event
            }
        }

        // event-5: Load U07G4_reportDisplay.fxml into contentArea using scene lookup
        AnchorPane contentArea = (AnchorPane) ((Node) event.getSource()).getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G4_reportDisplay.fxml");
    }
}