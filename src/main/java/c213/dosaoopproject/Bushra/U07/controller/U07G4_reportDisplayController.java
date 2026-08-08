package c213.dosaoopproject.Bushra.U07.controller;

import c213.dosaoopproject.commonClass.data.TextFileUtil;
import c213.dosaoopproject.commonClass.util.AlertUtil;
import c213.dosaoopproject.commonClass.util.SubViewSwitcher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

public class U07G4_reportDisplayController {

    @FXML private Button exportTXTButton;
    @FXML private Label totalStudentsReachedLabel;
    @FXML private Button submitToVCOfficeButton;
    @FXML private PieChart eventCategoryPieChart;
    @FXML private Label totalEventsLabel;
    @FXML private PieChart budgetUtilizationpiechart;

    private static final String REPORTS_FILE = "reports.txt";

    @FXML
    public void initialize() {
        // Set metrics from Config Controller
        totalEventsLabel.setText(String.valueOf(U07G4_reportConfigController.calculatedTotalEvents));
        totalStudentsReachedLabel.setText(String.valueOf(U07G4_reportConfigController.calculatedTotalStudents));

        // event-5: Render Charts
        ObservableList<PieChart.Data> categoryData = FXCollections.observableArrayList(
                new PieChart.Data("Academic", 12),
                new PieChart.Data("Cultural", 8),
                new PieChart.Data("Sports", 5)
        );
        eventCategoryPieChart.setData(categoryData);

        ObservableList<PieChart.Data> budgetData = FXCollections.observableArrayList(
                new PieChart.Data("Allocated", 80),
                new PieChart.Data("Remaining", 20)
        );
        budgetUtilizationpiechart.setData(budgetData);
    }

    @FXML
    public void exportTXTOA(ActionEvent event) {
        // event-6: FileChooser and path validation
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Report Summary");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt"));
        fileChooser.setInitialFileName("semester_report_summary.txt");

        File destination = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (destination != null) {
            String reportText = "Semester Activity Summary Report\n" +
                    "Total Events Organized: " + totalEventsLabel.getText() + "\n" +
                    "Total Students Reached: " + totalStudentsReachedLabel.getText();

            // Overwrite report summary to destination
            TextFileUtil.overwriteFile(destination.getAbsolutePath(), reportText);
            AlertUtil.showSuccess("Success", "Report exported successfully.");
        }
    }

    @FXML
    public void submitToVCOfficeOA(ActionEvent event) {
        // event-7: Append timestamped submission entry to reports.txt
        String logEntry = System.currentTimeMillis() + " - Semester Report Submitted to VC Office.";
        TextFileUtil.appendLine(REPORTS_FILE, logEntry);

        // event-8: Show alert and reload Config View
        AlertUtil.showSuccess("Success", "Report submitted to VC Office!");
        backOA(event);
    }

    @FXML
    public void backOA(ActionEvent event) {
        AnchorPane contentArea = (AnchorPane) ((Node) event.getSource()).getScene().lookup("#contentArea");
        SubViewSwitcher.loadSubView(contentArea, "/c213/dosaoopproject/Bushra/U07/U07G4_reportConfig.fxml");
    }
}