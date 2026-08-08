package c213.dosaoopproject.Bushra.U08.controller;

import c213.dosaoopproject.Bushra.U08.model.OrientationProgram;
import c213.dosaoopproject.commonClass.data.BinaryFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;
import c213.dosaoopproject.commonClass.util.ValidationUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringJoiner;

public class U08G5_OrientationSetupController
{
    @javafx.fxml.FXML
    private Label scheduleConflictWarningLabel;
    @javafx.fxml.FXML
    private DatePicker endDateP;
    @javafx.fxml.FXML
    private CheckBox culturalEveningCheckB;
    @javafx.fxml.FXML
    private DatePicker startDateP;
    @javafx.fxml.FXML
    private TextField venueTextF;
    @javafx.fxml.FXML
    private CheckBox welfareServicesCheckB;
    @javafx.fxml.FXML
    private CheckBox campusTourCheckB;
    @javafx.fxml.FXML
    private Button publishProgramButton;
    @javafx.fxml.FXML
    private ComboBox<String> semesterComboB;
    @javafx.fxml.FXML
    private CheckBox academicBriefingCheckB;

    private OrientationProgram targetProgram;
    private static final String FILE_PATH = "orientations.dat";

    @FXML
    public void initialize() {
        //ComboBox
        semesterComboB.setItems(FXCollections.observableArrayList("Spring 2026", "Summer 2026", "Fall 2026"));

        targetProgram = U08G5_OrientationOverviewController.getSelectedProgram();

        if (targetProgram != null) {
            // Step 5: Display Program Details
            semesterComboB.setValue(targetProgram.getSemester());
            venueTextF.setText(targetProgram.getVenue());

            if (targetProgram.getStartDate() != null && !targetProgram.getStartDate().isEmpty()) {
                startDateP.setValue(LocalDate.parse(targetProgram.getStartDate()));
            }
            if (targetProgram.getEndDate() != null && !targetProgram.getEndDate().isEmpty()) {
                endDateP.setValue(LocalDate.parse(targetProgram.getEndDate()));
            }

            String modules = targetProgram.getModules();
            if (modules != null) {
                campusTourCheckB.setSelected(modules.contains("Campus & Facilities Tour"));
                academicBriefingCheckB.setSelected(modules.contains("Academic Policies & Curriculum Briefing"));
                welfareServicesCheckB.setSelected(modules.contains("Student Welfare Services"));
                culturalEveningCheckB.setSelected(modules.contains("Cultural Evening"));
            }
        }
    }

    @FXML
    private void publishProgramOA(ActionEvent event) {
        scheduleConflictWarningLabel.setVisible(false);
        scheduleConflictWarningLabel.setManaged(false);

        // Ensure an orientation program was selected
        if (targetProgram == null) {
            AlertUtil.showError("Error", "No orientation program selected for setup.");
            return;
        }

        // Step 6: Validate Inputs
        if (ValidationUtil.isComboUnselected(semesterComboB)) {
            AlertUtil.showError("Validation Error", "Please select a target semester.");
            return;
        }

        if (ValidationUtil.isEmpty(venueTextF.getText())) {
            AlertUtil.showError("Validation Error", "Please specify the venue name.");
            return;
        }

        if (startDateP.getValue() == null || endDateP.getValue() == null) {
            AlertUtil.showError("Validation Error", "Please select both start and end dates.");
            return;
        }

        if (endDateP.getValue().isBefore(startDateP.getValue())) {
            scheduleConflictWarningLabel.setText("⚠ Invalid date range: End Date cannot be before Start Date!");
            scheduleConflictWarningLabel.setVisible(true);
            scheduleConflictWarningLabel.setManaged(true);
            return;
        }

        // Build selected modules string
        StringJoiner modulesJoiner = new StringJoiner(";");
        if (campusTourCheckB.isSelected()) modulesJoiner.add("Campus & Facilities Tour");
        if (academicBriefingCheckB.isSelected()) modulesJoiner.add("Academic Policies & Curriculum Briefing");
        if (welfareServicesCheckB.isSelected()) modulesJoiner.add("Student Welfare Services");
        if (culturalEveningCheckB.isSelected()) modulesJoiner.add("Cultural Evening");

        String modulesStr = modulesJoiner.length() > 0 ? modulesJoiner.toString() : "None";

        // Step 7: Read, Update, and Save File via BinaryFileUtil
        ArrayList<Object> programs = BinaryFileUtil.readObjects(FILE_PATH);
        if (programs == null) {
            AlertUtil.showError("File Error", "Unable to load orientation records.");
            return;
        }

        for (Object obj : programs) {
            if (obj instanceof OrientationProgram) {
                OrientationProgram prog = (OrientationProgram) obj;
                if (prog.getProgramId().equals(targetProgram.getProgramId())) {
                    prog.setSemester(semesterComboB.getValue());
                    prog.setVenue(venueTextF.getText().trim());
                    prog.setStartDate(startDateP.getValue().toString());
                    prog.setEndDate(endDateP.getValue().toString());
                    prog.setModules(modulesStr);
                    break;
                }
            }
        }

        BinaryFileUtil.writeObjects(FILE_PATH, programs);

        // Step 8: Alert & Return to Overview
        AlertUtil.showSuccess("Success", "Orientation program updated successfully.");
        returnToOverview(event);
    }

    @FXML
    private void backOA(ActionEvent event) {
        returnToOverview(event);
    }

    // Helper method to handle navigation back to overview
    private void returnToOverview(ActionEvent event) {
        Button button = (Button) event.getSource();
        AnchorPane contentArea = (AnchorPane) button.getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U08/U08G5_orientationOverview.fxml");
    }

}




